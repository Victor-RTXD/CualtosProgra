import re

class SemanticAnalyzer:
    def __init__(self, code: str):
        self.raw_code = code
        self.logs = []      # Registra el detalle paso a paso
        self.errors = []    # Lista de errores semánticos encontrados
        self.symbols = {}   # Variables declaradas: nombre -> tipo (p. ej. "int", "float", "char", "char *")
        self.functions = {} # Funciones declaradas: nombre -> tipo de retorno
        self.macros = {}    # Macros detectadas

    def remove_comments(self, code: str) -> str:
        """Elimina comentarios de línea y de bloque; si faltan cierres de bloque, se agrega error."""
        # Primero se detectan los comentarios de bloque
        bloques = re.findall(r'/\*.*?\*/', code, re.DOTALL)
        count_open = code.count("/*")
        count_close = code.count("*/")
        if count_open != count_close:
            self.errors.append("Error: Comentario de bloque sin cerrar")
            self.logs.append("Se detectó un comentario de bloque sin cerrar")
        # Se remueven los comentarios en bloque (si están completos)
        code = re.sub(r'/\*.*?\*/', '', code, flags=re.DOTALL)
        # Se remueven los comentarios de línea
        code = re.sub(r'//.*', '', code)
        self.logs.append("Se han eliminado los comentarios")
        return code

    def detectar_macros(self, lines):
        """Detecta las líneas de macro (#define) y las elimina del código principal"""
        processed_lines = []
        for line in lines:
            line = line.strip()
            if line.startswith("#define"):
                parts = line.split()
                if len(parts) >= 2:
                    macro_name = parts[1]
                    self.macros[macro_name] = line
                    self.logs.append(f"Macro detectada: {macro_name}")
                # Si es macro, no se agrega la línea al código principal
            else:
                processed_lines.append(line)
        return processed_lines

    def detectar_funciones(self, code: str):
        """
        Extrae declaraciones de funciones (muy simplificado) usando una expresión regular.  
        Por ejemplo: int main( { ... }
        """
        pattern = r'\b(int|float|char|void)\s+(\w+)\s*\('
        matches = re.findall(pattern, code)
        for tipo, nombre in matches:
            # Si no se había detectado, guardamos la función
            if nombre not in self.functions:
                self.functions[nombre] = tipo
                self.logs.append(f"Función detectada: '{nombre}' con tipo de retorno '{tipo}'")

    def analizar_declaracion(self, stmt: str):
        """
        Analiza una declaración de variable.
        Se utiliza el patrón para declaraciones del tipo:
            int a = 10;
            char *str = "...";
            float b = 3.14;
        """
        # Patrón para declarar variables (se admite opcionalmente el * para pointers)
        pattern = r'^(int|float|char)(\s*\*?)\s+(\w+)\s*=\s*(.+)$'
        m = re.match(pattern, stmt)
        if m:
            base_type, ptr, varname, valor = m.groups()
            vartype = base_type + ptr
            vartype = vartype.strip()
            valor = valor.strip()
            self.symbols[varname] = vartype
            self.logs.append(f"Declaración encontrada: Variable '{varname}' de tipo '{vartype}' inicializada con: {valor}")
            
            # Verificación extra para enteros: si el literal es demasiado grande (suponemos 32 bits)
            if base_type == "int":
                # Se asume que el literal (sin paréntesis o espacios) es un número en base 10 o hexadecimal
                num_str = valor.split()[0]  # en caso de expresiones, se toma el primer token
                try:
                    if num_str.startswith("0x") or num_str.startswith("0X"):
                        num = int(num_str, 16)
                    else:
                        # Se quitan posibles terminaciones (como 'L', etc.) para el ejemplo
                        num = int(num_str)
                    if num > 2147483647 or num < -2147483648:
                        self.errors.append(f"Error semántico: Entero demasiado grande en la asignación de '{varname}'")
                        self.logs.append(f"Valor entero {num} fuera del rango permitido para 32 bits en variable '{varname}'")
                except Exception:
                    pass  # Si no se pudo convertir, se ignora aquí

            # Verificación para cadenas: si es char* se busca que las secuencias de escape estén en rango
            if vartype in ["char*", "char *"]:
                # Se espera que el literal inicie y termine en comillas dobles
                if valor.count('"') < 2:
                    self.errors.append(f"Error sintáctico: Cadena no cerrada en la declaración de '{varname}'")
                    self.logs.append(f"La cadena asignada a '{varname}' no tiene comillas de cierre")
                else:
                    # Buscar secuencias \xHH y chequear que HH <= 7F
                    matches = re.findall(r'\\x([0-9A-Fa-f]{2})', valor)
                    for hex_val in matches:
                        if int(hex_val, 16) > 0x7F:
                            self.errors.append(f"Error semántico: Caracter fuera del rango ASCII normal en '{varname}'")
                            self.logs.append(f"En la cadena asignada a '{varname}' se encontró \\x{hex_val} (valor mayor que 7F)")
        else:
            # No es una declaración de variable; se deja que otras rutinas lo procesen
            return False
        return True

    def get_operand_type(self, operand: str) -> str:
        """
        Retorna el tipo de un operando (para expresiones simples).
        Si es variable, se consulta la tabla de símbolos; si es literal numérico o literal char se detecta.
        """
        operand = operand.strip()
        # Si se trata de una variable declarada
        if operand in self.symbols:
            return self.symbols[operand]
        # Si es un literal numérico (entero o float)
        if re.match(r'^\d+(\.\d+)?$', operand):
            return "float" if "." in operand else "int"
        # Si es un literal char, se esperan comillas simples
        if re.match(r"^'.{1}'$", operand):
            return "char"
        # Si es una llamada a función (por ejemplo: funcion())
        if re.match(r'^\w+\s*\(\s*\)$', operand):
            m = re.match(r'^(\w+)\s*\(\s*\)$', operand)
            fname = m.group(1)
            return self.functions.get(fname, "undef")
        return "unknown"

    def check_types(self, t1: str, t2: str) -> bool:
        """
        En nuestro analizador, permitimos la suma entre int y float.  
        Si alguno de los operandos es 'char' (y el otro no es char) se indica incompatibilidad.
        (El ejemplo muestra error en: a = b + c; siendo b=float y c=char)
        """
        # Si uno de los dos es "unknown" o "undef", no se puede determinar; se asume compatible
        if t1 in ["unknown", "undef"] or t2 in ["unknown", "undef"]:
            return True
        # Si alguno contiene 'char' y el otro no es exactamente 'char', se indica error.
        if "char" in t1 or "char" in t2:
            if t1 == t2 == "char":
                # Podríamos rechazar incluso la suma de dos chars (según convenga), pero en este ejemplo se toma como error.
                return False
            return False
        # Se permiten combinaciones int + int, int + float, float + float, etc.
        return True

    def analizar_asignacion(self, stmt: str):
        """
        Analiza una instrucción de asignación del tipo:
            var = expression
        Se revisa:
           - Si la variable del LHS está declarada (excepto si la RHS es una llamada a función y la función no está declarada se reporta ese error primeramente).
           - Si en el RHS se utiliza el operador +, se chequean los tipos de los operandos.
        """
        stmt = stmt.strip()
        # Primero, se detecta si se trata de una asignación cuyo RHS es una llamada a función simple
        func_call_pattern = r'^(\w+)\s*=\s*(\w+)\s*\(\s*\)\s*$'
        m_func = re.match(func_call_pattern, stmt)
        if m_func:
            lhs, fname = m_func.groups()
            # Se chequea la función primero
            if fname not in self.functions:
                self.errors.append(f"Error semántico: Función '{fname}' no declarada")
                self.logs.append(f"Analizando asignación: {stmt} -> Función '{fname}' no declarada")
            else:
                self.logs.append(f"Analizando asignación: {stmt} -> Llamada a función '{fname}' correctamente detectada")
            # Para este caso, evitamos dar error adicional por LHS no declarado, ya que la prioridad es la función.
            return

        # Para asignaciones generales (que pueden incluir expresiones con +)
        assign_pattern = r'^(\w+)\s*=\s*(.+)$'
        m_assign = re.match(assign_pattern, stmt)
        if m_assign:
            lhs, expr = m_assign.groups()
            expr = expr.strip()
            # Si la asignación NO es parte de una declaración (es decir, ya fue procesada), se chequea la variable LHS.
            if lhs not in self.symbols:
                self.errors.append(f"Error semántico: Variable '{lhs}' no declarada")
                self.logs.append(f"Analizando asignación: {stmt} -> Variable '{lhs}' no declarada")
            else:
                self.logs.append(f"Analizando asignación: {stmt} -> Variable '{lhs}' declarada previamente")
            # Si la expresión contiene un operador '+', se asume que se trata de una suma binaria
            if '+' in expr:
                partes = expr.split('+')
                if len(partes) == 2:
                    op1 = partes[0].strip()
                    op2 = partes[1].strip()
                    t1 = self.get_operand_type(op1)
                    t2 = self.get_operand_type(op2)
                    if not self.check_types(t1, t2):
                        self.errors.append(f"Error de tipos: Operandos incompatibles ({t1} y {t2})")
                        self.logs.append(f"Analizando asignación: {stmt} -> Error de tipos en la suma: {t1} + {t2}")
                else:
                    self.logs.append(f"Analizando asignación: {stmt} -> No se pudo procesar correctamente la expresión con '+'")

    def analizar_codigo(self):
        """Realiza el análisis semántico completo paso a paso."""
        self.logs.append("=== Inicio del análisis semántico ===")
        # 1. Eliminar comentarios y detectar errores en ellos.
        code_limpio = self.remove_comments(self.raw_code)
        
        # 2. Se procesa por líneas para detectar macros y obtener el código “principal”
        lineas = code_limpio.splitlines()
        lineas_procesadas = self.detectar_macros(lineas)
        code_procesado = " ".join(lineas_procesadas)
        self.logs.append("Código procesado (sin macros ni comentarios):")
        self.logs.append(code_procesado)
        
        # 3. Detectar y registrar funciones declaradas (por ejemplo, main)
        self.detectar_funciones(code_procesado)
        
        # 4. Dividir el código en instrucciones separadas (usamos como separador el punto y coma)
        instrucciones = code_procesado.split(';')
        for instr in instrucciones:
            instr = instr.strip()
            if not instr:
                continue
            # Primero se prueba procesar la instrucción como declaración de variable
            if self.analizar_declaracion(instr):
                continue  # Si fue una declaración, ya se procesó y se registró
            # Sino, se asume que puede ser una asignación o una expresión que se quiere analizar
            self.analizar_asignacion(instr)
        self.logs.append("=== Fin del análisis semántico ===")

    def reportar_resultado(self):
        """Imprime el reporte del análisis, primero los pasos y al final los errores encontrados."""
        print("=== Análisis Semántico ===")
        for log in self.logs:
            print(log)
        print()
        if self.errors:
            print("✖ Errores encontrados:")
            for idx, err in enumerate(self.errors, start=1):
                print(f"{idx}. {err}")
        else:
            print("✔ No se encontraron errores semánticos.")


# ---------------------------
# Códigos de prueba
# ---------------------------

# Código de ejemplo (input esperado)
codigo_ejemplo = r'''
int main() {
    int a = 10;
    float b = 3.14;
    char c = 'x';
    
    // Errores semánticos a detectar:
    z = a + b;      // Variable no declarada
    a = b + c;      // Tipos incompatibles
    c = a + b;      // Esta suma se considera correcta en el análisis de operadores
    x = funcion_no_declarada();  // Función no declarada
    
    return 0;
}
'''

# Código 1 de prueba
codigo1 = r'''
int main() { 
    char *str = "\xFF"; 
    int x = 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF;
    // Comentario
    printf("%s %d", str, x); return 0;
}
/*comentario
largo*/
'''

# Código 2 de prueba
codigo2 = r'''
#define MEGA_DEFINE_AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

#define MEGA_DEFINE_BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB

int main() { 
    char *str = "cadena; 
    int x = 100000000000000000000000000000000000000000000000000000000000000; // Entero demasiado grande 
    /* Esto es un comentario sin cerrar...
    printf("\xFF\xFE\xFD"); 
    // Caracteres fuera del rango ASCII normal 
    return 0; 
}
'''

# ---------------------------
# Ejecución y reporte
# ---------------------------
def ejecutar_test(codigo: str, titulo: str):
    print("\n"+"="*50)
    print(f"Prueba: {titulo}")
    print("="*50+"\n")
    analizador = SemanticAnalyzer(codigo)
    analizador.analizar_codigo()
    analizador.reportar_resultado()
    print("\n"+"="*50+"\n")


if __name__ == '__main__':
    ejecutar_test(codigo_ejemplo, "Código Ejemplo (Input esperado)")
    ejecutar_test(codigo1, "Código 1")
    ejecutar_test(codigo2, "Código 2")
