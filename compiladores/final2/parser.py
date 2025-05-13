# parser.py

class ASTNode:
    def __init__(self, nodetype, value=None, children=None):
        self.nodetype = nodetype
        self.value = value
        self.children = children or []

    def __repr__(self):
        return f"{self.nodetype}({self.value}, {self.children})"

class ParserError(Exception):
    pass

class Parser:
    def __init__(self, tokens):
        self.tokens = tokens
        self.pos = 0

    def parse(self):
        nodes = []
        while self.pos < len(self.tokens):
            node = self.parse_statement()
            if node:
                nodes.append(node)
        return nodes

    def current_token(self):
        if self.pos < len(self.tokens):
            return self.tokens[self.pos]
        return None

    def eat(self, token_type=None):
        token = self.current_token()
        if not token:
            raise ParserError("Fin inesperado del archivo.")
        if token_type and token.type != token_type:
            raise ParserError(f"Se esperaba {token_type} pero se encontró {token.type}")
        self.pos += 1
        return token

    def parse_statement(self):
        token = self.current_token()

        if token.type == 'DEFINE':
            self.eat('DEFINE')
            name = self.eat('ID')
            return ASTNode('DEFINE', name.value)

        elif token.type == 'KEYWORD' and token.value == 'int':
            return self.parse_declaration()

        elif token.type == 'KEYWORD' and token.value == 'char':
            return self.parse_declaration()

        elif token.type == 'KEYWORD' and token.value == 'return':
            self.eat('KEYWORD')  # return
            expr = self.eat()
            self.eat('SEMICOLON')
            return ASTNode('RETURN', expr.value)

        elif token.type == 'KEYWORD' and token.value == 'printf':
            self.eat('KEYWORD')
            self.eat('LPAREN')
            args = []
            while self.current_token().type != 'RPAREN':
                tok = self.eat()
                if tok.type != 'COMMA':
                    args.append(tok.value)
            self.eat('RPAREN')
            self.eat('SEMICOLON')
            return ASTNode('PRINTF', args)

        else:
            self.eat()
            return None

    def parse_declaration(self):
        type_token = self.eat('KEYWORD')  # int, char, etc.
        var_token = self.eat('ID')        # el nombre (puede ser de variable o función)

        # FUNCIÓN → si sigue un paréntesis
        if self.current_token().type == 'LPAREN':
            self.eat('LPAREN')
            self.eat('RPAREN')
            self.eat('LBRACE')
            body = []
            while self.current_token().type != 'RBRACE':
                stmt = self.parse_statement()
                if stmt:
                    body.append(stmt)
            self.eat('RBRACE')
            return ('Function', type_token.value, var_token.value, body)

        # VARIABLE (declaración con o sin inicialización)
        elif self.current_token().type == 'OP' and self.current_token().value == '=':
            self.eat('OP')  # =
            expr = self.parse_expression()
            self.eat('SEMICOLON')
            return ('VarDecl', type_token.value, var_token.value, expr)

        elif self.current_token().type == 'SEMICOLON':
            self.eat('SEMICOLON')
            return ('VarDecl', type_token.value, var_token.value, None)

        else:
            raise ParserError(f'Error de sintaxis después de declaración: {self.current_token().type}')

