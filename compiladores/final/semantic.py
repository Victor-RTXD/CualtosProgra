# semantic.py
from parser import Program, Declaration, Assignment, Variable, BinOp, Number

def semantic_analysis(ast):
    # Se utiliza una tabla de símbolos para verificar que las variables se declaren antes de usarse.
    symbol_table = {}
    
    def visit(node):
        if isinstance(node, Program):
            for stmt in node.statements:
                visit(stmt)
        elif isinstance(node, Declaration):
            var_name = node.identifier
            if var_name in symbol_table:
                raise Exception(f"La variable '{var_name}' ya fue declarada")
            symbol_table[var_name] = "int"  # Solo se soporta el tipo int.
        elif isinstance(node, Assignment):
            var_name = node.identifier
            if var_name not in symbol_table:
                raise Exception(f"La variable '{var_name}' no fue declarada antes de la asignación")
            visit(node.expression)
        elif isinstance(node, BinOp):
            visit(node.left)
            visit(node.right)
        elif isinstance(node, Variable):
            var_name = node.name
            if var_name not in symbol_table:
                raise Exception(f"La variable '{var_name}' se utilizó sin ser declarada")
        elif isinstance(node, Number):
            pass  # Los números son siempre válidos.
        else:
            raise Exception("Nodo AST desconocido durante el análisis semántico")
    
    visit(ast)
    return symbol_table
