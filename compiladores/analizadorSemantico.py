import re
from collections import defaultdict

class SemanticAnalyzer:
    def __init__(self, code):
        self.code = code
        self.symbol_table = defaultdict(dict)
        self.errors = []
        self.functions_declared = set(["main", "printf"])

    def analyze(self):
        self.check_lexical_errors()
        self.extract_declarations()
        self.check_semantics()
        self.report()

    def check_lexical_errors(self):
        lines = self.code.splitlines()
        for i, line in enumerate(lines, 1):
            if re.search(r'\b\d{20,}\b', line):
                self.errors.append(f"{i}. Error léxico: Entero demasiado grande")
            if line.count('"') % 2 != 0:
                self.errors.append(f"{i}. Error léxico: Cadena de texto sin cerrar correctamente")
            for match in re.finditer(r'\\x([0-9A-Fa-f]{2})', line):
                hex_val = int(match.group(1), 16)
                if hex_val > 127:
                    self.errors.append(f"{i}. Advertencia: Caracter fuera del rango ASCII normal (\\x{match.group(1)})")
        open_comments = len(re.findall(r'/\*', self.code))
        close_comments = len(re.findall(r'\*/', self.code))
        if open_comments > close_comments:
            self.errors.append("Error léxico: Comentario bloque sin cerrar")

    def extract_declarations(self):
        lines = self.code.splitlines()
        decl_pattern = re.compile(r'\b(int|float|char)\s+([a-zA-Z_][a-zA-Z0-9_]*)')
        func_pattern = re.compile(r'(int|void|float|char)\s+([a-zA-Z_][a-zA-Z0-9_]*)\s*\(.*?\)')
        for line in lines:
            line = re.sub(r'//.*', '', line)
            line = re.sub(r'/\*.*?\*/', '', line, flags=re.DOTALL)
            for match in decl_pattern.finditer(line):
                typename, varname = match.groups()
                self.symbol_table[varname]['type'] = typename
            for match in func_pattern.finditer(line):
                _, fname = match.groups()
                self.functions_declared.add(fname)

    def get_expr_type(self, expr):
        expr = expr.strip()
        if re.match(r'^\d+\.?\d*$', expr):
            return 'float' if '.' in expr else 'int'
        elif re.match(r"^'.'$", expr):
            return 'char'
        elif expr in self.symbol_table:
            return self.symbol_table[expr]['type']
        return 'unknown'

    def check_semantics(self):
        assignment_pattern = re.compile(r'(\w+)\s*=\s*(.+?);')
        function_call_pattern = re.compile(r'(\w+)\s*=\s*(\w+)\s*\(.*?\)\s*;')
        lines = self.code.splitlines()
        for i, line in enumerate(lines, 1):
            line = re.sub(r'//.*', '', line)
            line = re.sub(r'/\*.*?\*/', '', line, flags=re.DOTALL)
            match = function_call_pattern.search(line)
            if match:
                var, func = match.groups()
                if func not in self.functions_declared:
                    self.errors.append(f"{i}. Error semántico: Función '{func}' no declarada")
                if var not in self.symbol_table:
                    self.errors.append(f"{i}. Error semántico: Variable '{var}' no declarada")
            match = assignment_pattern.search(line)
            if match:
                var, expr = match.groups()
                tokens = re.split(r'\s*[+\-*/]\s*', expr)
                types = [self.get_expr_type(tok) for tok in tokens]
                if var not in self.symbol_table:
                    self.errors.append(f"{i}. Error semántico: Variable '{var}' no declarada")
                if 'unknown' in types:
                    for tok in tokens:
                        if self.get_expr_type(tok) == 'unknown':
                            self.errors.append(f"{i}. Error semántico: Variable '{tok}' no declarada")
                elif len(set(types)) > 1:
                    self.errors.append(f"{i}. Error de tipos: Operandos incompatibles ({' y '.join(set(types))})")

    def report(self):
        print("=== Análisis Semántico y Léxico ===")
        if self.errors:
            print("\u2716 Errores encontrados:")
            for error in self.errors:
                print(error)
        else:
            print("\u2714 No se encontraron errores.")

if __name__ == "__main__":
    codigo_prueba = '''
int main() {
    char *str = "\xFF";
    int x = 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF;
    printf("%s %d", str, x); return 0;
}
/*comentario
largo*/
'''
    analyzer = SemanticAnalyzer(codigo_prueba)
    analyzer.analyze()
