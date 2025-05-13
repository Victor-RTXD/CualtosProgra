# parser.py
from lexer import Token

# Definición de clases para los nodos del AST.
class ASTNode:
    pass

class Program(ASTNode):
    def __init__(self, statements):
        self.statements = statements

class Declaration(ASTNode):
    def __init__(self, var_type, identifier):
        self.var_type = var_type   # En este caso solo 'int'
        self.identifier = identifier

class Assignment(ASTNode):
    def __init__(self, identifier, expression):
        self.identifier = identifier
        self.expression = expression

class BinOp(ASTNode):
    def __init__(self, left, op, right):
        self.left = left
        self.op = op  # Operador: "PLUS", "MINUS", "TIMES" o "DIVIDE"
        self.right = right

class Number(ASTNode):
    def __init__(self, value):
        self.value = value

class Variable(ASTNode):
    def __init__(self, name):
        self.name = name

class Parser:
    def __init__(self, tokens):
        self.tokens = tokens
        self.pos = 0

    def current_token(self):
        if self.pos < len(self.tokens):
            return self.tokens[self.pos]
        return None

    def eat(self, token_type):
        token = self.current_token()
        if token is not None and token.type == token_type:
            self.pos += 1
            return token
        else:
            raise SyntaxError(f"Se esperaba token {token_type} pero se obtuvo {token}")

    def parse(self):
        statements = []
        while self.current_token() is not None:
            stmt = self.statement()
            statements.append(stmt)
        return Program(statements)

    def statement(self):
        token = self.current_token()
        # Si encontramos la palabra clave 'int', es una declaración; de lo contrario, se trata de una asignación.
        if token.type == "ID" and token.value == "int":
            return self.declaration()
        else:
            return self.assignment()

    def declaration(self):
        # Espera: int identificador ;
        type_token = self.eat("ID")
        if type_token.value != "int":
            raise SyntaxError("Solo se soportan declaraciones de 'int'")
        id_token = self.eat("ID")
        self.eat("SEMICOLON")
        return Declaration("int", id_token.value)

    def assignment(self):
        # Espera: identificador = expresión ;
        id_token = self.eat("ID")
        self.eat("EQUALS")
        expr = self.expression()
        self.eat("SEMICOLON")
        return Assignment(id_token.value, expr)

    def expression(self):
        # expresión: término ((PLUS | MINUS) término)*
        node = self.term()
        while self.current_token() is not None and self.current_token().type in ("PLUS", "MINUS"):
            op_token = self.eat(self.current_token().type)
            right = self.term()
            node = BinOp(node, op_token.type, right)
        return node

    def term(self):
        # término: factor ((TIMES | DIVIDE) factor)*
        node = self.factor()
        while self.current_token() is not None and self.current_token().type in ("TIMES", "DIVIDE"):
            op_token = self.eat(self.current_token().type)
            right = self.factor()
            node = BinOp(node, op_token.type, right)
        return node

    def factor(self):
        token = self.current_token()
        if token.type == "NUMBER":
            self.eat("NUMBER")
            return Number(token.value)
        elif token.type == "ID":
            self.eat("ID")
            return Variable(token.value)
        elif token.type == "LPAREN":
            self.eat("LPAREN")
            node = self.expression()
            self.eat("RPAREN")
            return node
        else:
            raise SyntaxError("Token inesperado en factor")
