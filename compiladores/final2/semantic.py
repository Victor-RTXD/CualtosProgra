# semantic.py

class SemanticError(Exception):
    pass

class SemanticAnalyzer:
    def __init__(self):
        self.symbols = {}

    def analyze(self, ast):
        for node in ast:
            if node.nodetype == 'DECL':
                var = node.value['id']
                if var in self.symbols:
                    raise SemanticError(f"Variable redeclarada: {var}")
                self.symbols[var] = node.value['type']

            elif node.nodetype == 'RETURN':
                if node.value not in self.symbols and not node.value.isdigit():
                    raise SemanticError(f"Variable no declarada en return: {node.value}")

            elif node.nodetype == 'PRINTF':
                for arg in node.value:
                    if arg.startswith('"') or arg.isdigit():
                        continue
                    if arg not in self.symbols:
                        raise SemanticError(f"Variable no declarada en printf: {arg}")
