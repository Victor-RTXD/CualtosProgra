from parser import (
    ReturnStatement, ExpressionStatement, BinaryExpression, Identifier, Literal,
    CallExpression, AssignmentExpression, IfStatement, WhileStatement
)

class ThreeAddressCodeGenerator:
    def __init__(self):
        self.code = []      # Lista de instrucciones TAC
        self.temp_count = 0 # Contador de temporales (t1, t2, ...)
        self.label_count = 0 # Contador de etiquetas (L1, L2, ...)

    def generate(self, ast):
        """Genera código TAC a partir del AST."""
        self.visit_program(ast)
        return "\n".join(self.code)  # Devuelve el código como string

    def new_temp(self):
        """Genera un nuevo temporal."""
        self.temp_count += 1
        return f"t{self.temp_count}"

    def new_label(self):
        """Genera una nueva etiqueta."""
        self.label_count += 1
        return f"L{self.label_count}"

    def emit(self, op, arg1=None, arg2=None, result=None):
        """Formato correcto para TAC: [result] = [arg1] [op] [arg2]"""
        if op == "=":  
            self.code.append(f"{result} = {arg1}")  # Asignación simple
        elif op == "return":  
            self.code.append(f"return {arg1}")  # Return
        elif op == "goto":
            self.code.append(f"goto {arg1}")  # Saltos
        elif op == "if":
            self.code.append(f"if {arg1} goto {arg2}")  # Condicionales
        else:  
            self.code.append(f"{result} = {arg1} {op} {arg2}")  # Operaciones binarias

    # Métodos para recorrer el AST
    def visit_program(self, node):
        for func in node.functions:
            self.visit_function(func)

    def visit_function(self, node):
        self.code.append(f"\nFUNCTION {node.name}:")
        self.visit_compound_statement(node.body)

    def visit_compound_statement(self, node):
        for decl in node.declarations:
            self.visit_declaration(decl)
        for stmt in node.statements:
            self.visit_statement(stmt)

    def visit_declaration(self, node):
        if node.initializer:
            value = self.visit_expression(node.initializer)
            self.emit("=", value, None, node.name)

    def visit_statement(self, node):
        if isinstance(node, ReturnStatement):
            temp = self.visit_expression(node.expression) if node.expression else None
            self.emit("return", temp)
        elif isinstance(node, IfStatement):
            self.visit_if_statement(node)
        elif isinstance(node, WhileStatement):
            self.visit_while_statement(node)
        elif isinstance(node, ExpressionStatement):
            self.visit_expression(node.expression)

    def visit_if_statement(self, node):
        label_true = self.new_label()
        label_end = self.new_label()

        condition = self.visit_expression(node.condition)
        self.emit("if", condition, label_true)
        self.visit_statement(node.then_stmt)
        self.emit("goto", label_end)
        self.code.append(f"{label_true}:")
        if node.else_stmt:
            self.visit_statement(node.else_stmt)
        self.code.append(f"{label_end}:")

    def visit_while_statement(self, node):
        label_start = self.new_label()
        label_end = self.new_label()

        self.code.append(f"{label_start}:")
        condition = self.visit_expression(node.condition)
        self.emit("if", condition, label_end)
        self.visit_statement(node.body)
        self.emit("goto", label_start)
        self.code.append(f"{label_end}:")

    def visit_expression(self, node):
        if isinstance(node, BinaryExpression):
            left = self.visit_expression(node.left)
            right = self.visit_expression(node.right)
            temp = self.new_temp()
            self.emit(node.operator, left, right, temp)
            return temp
        elif isinstance(node, AssignmentExpression):
            value = self.visit_expression(node.right)
            self.emit("=", value, None, node.left.name)
            return node.left.name
        elif isinstance(node, CallExpression):
            temp = self.new_temp()
            args = ", ".join(self.visit_expression(arg) for arg in node.arguments)
            self.code.append(f"{temp} = CALL {node.callee.name}({args})")
            return temp
        elif isinstance(node, Identifier):
            return node.name
        elif isinstance(node, Literal):
            return node.value
