# codegen.py
from parser import Program, Declaration, Assignment, BinOp, Number, Variable

# Generaremos un código intermedio en formato de "three-address code".
class CodeGenerator:
    def __init__(self):
        self.temp_count = 0
        self.instructions = []
    
    def new_temp(self):
        self.temp_count += 1
        return f"t{self.temp_count}"
    
    def generate(self, ast):
        if isinstance(ast, Program):
            for stmt in ast.statements:
                self.generate(stmt)
        elif isinstance(ast, Declaration):
            self.instructions.append(f"declare {ast.identifier} as {ast.var_type}")
        elif isinstance(ast, Assignment):
            expr_result = self.generate(ast.expression)
            self.instructions.append(f"{ast.identifier} = {expr_result}")
        elif isinstance(ast, BinOp):
            left = self.generate(ast.left)
            right = self.generate(ast.right)
            temp = self.new_temp()
            op = {'PLUS': '+', 'MINUS': '-', 'TIMES': '*', 'DIVIDE': '/'}[ast.op]
            self.instructions.append(f"{temp} = {left} {op} {right}")
            return temp
        elif isinstance(ast, Number):
            return str(ast.value)
        elif isinstance(ast, Variable):
            return ast.name
        else:
            raise Exception("Nodo desconocido durante la generación de código")
    
    def get_instructions(self):
        return self.instructions
