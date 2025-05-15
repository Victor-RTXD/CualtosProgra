from lexer import TokenType

class ParserError(Exception):
    def __init__(self, message, token):
        self.message = message
        self.token = token
        super().__init__(f"Error en línea {token.line}, columna {token.column}: {message}")

class ASTNode:
    def __init__(self):
        pass
    
    def __str__(self):
        return self.__class__.__name__
    
    def __repr__(self):
        return self.__str__()

class Program(ASTNode):
    def __init__(self):
        super().__init__()
        self.functions = []
        self.declarations = []
    
    def __str__(self):
        return f"Program(functions={len(self.functions)}, declarations={len(self.declarations)})"

class Function(ASTNode):
    def __init__(self, return_type, name, params, body):
        super().__init__()
        self.return_type = return_type
        self.name = name
        self.params = params  # Lista de parámetros (objetos Parameter)
        self.body = body  # Cuerpo de la función (objeto CompoundStatement)
    
    def __str__(self):
        return f"Function({self.return_type}, {self.name}, params={len(self.params)})"

class Parameter(ASTNode):
    def __init__(self, type_spec, name):
        super().__init__()
        self.type = type_spec
        self.name = name
    
    def __str__(self):
        return f"Parameter({self.type}, {self.name})"

class VarDeclaration(ASTNode):
    def __init__(self, type_spec, name, initializer=None):
        super().__init__()
        self.type = type_spec
        self.name = name
        self.initializer = initializer  # Opcional
    
    def __str__(self):
        if self.initializer:
            return f"VarDeclaration({self.type}, {self.name}, initializer=True)"
        return f"VarDeclaration({self.type}, {self.name})"

class Statement(ASTNode):
    pass

class ExpressionStatement(Statement):
    def __init__(self, expression):
        super().__init__()
        self.expression = expression
    
    def __str__(self):
        return f"ExpressionStatement({self.expression})"

class CompoundStatement(Statement):
    def __init__(self):
        super().__init__()
        self.declarations = []
        self.statements = []
    
    def __str__(self):
        return f"CompoundStatement(declarations={len(self.declarations)}, statements={len(self.statements)})"

class IfStatement(Statement):
    def __init__(self, condition, then_stmt, else_stmt=None):
        super().__init__()
        self.condition = condition
        self.then_stmt = then_stmt
        self.else_stmt = else_stmt  # Opcional
    
    def __str__(self):
        if self.else_stmt:
            return f"IfStatement({self.condition}, then, else)"
        return f"IfStatement({self.condition}, then)"

class WhileStatement(Statement):
    def __init__(self, condition, body):
        super().__init__()
        self.condition = condition
        self.body = body
    
    def __str__(self):
        return f"WhileStatement({self.condition}, body)"

class ForStatement(Statement):
    def __init__(self, init, condition, update, body):
        super().__init__()
        self.init = init  # Puede ser None
        self.condition = condition  # Puede ser None
        self.update = update  # Puede ser None
        self.body = body
    
    def __str__(self):
        return f"ForStatement(init, {self.condition}, update, body)"

class ReturnStatement(Statement):
    def __init__(self, expression=None):
        super().__init__()
        self.expression = expression  # Puede ser None
    
    def __str__(self):
        if self.expression:
            return f"ReturnStatement({self.expression})"
        return "ReturnStatement()"

class Expression(ASTNode):
    pass

class BinaryExpression(Expression):
    def __init__(self, left, operator, right):
        super().__init__()
        self.left = left
        self.operator = operator
        self.right = right
    
    def __str__(self):
        return f"BinaryExpression({self.left}, {self.operator}, {self.right})"

class UnaryExpression(Expression):
    def __init__(self, operator, expression):
        super().__init__()
        self.operator = operator
        self.expression = expression
    
    def __str__(self):
        return f"UnaryExpression({self.operator}, {self.expression})"

class Identifier(Expression):
    def __init__(self, name):
        super().__init__()
        self.name = name
    
    def __str__(self):
        return f"Identifier({self.name})"

class Literal(Expression):
    def __init__(self, value, type_name):
        super().__init__()
        self.value = value
        self.type = type_name
    
    def __str__(self):
        return f"Literal({self.value}, {self.type})"

class CallExpression(Expression):
    def __init__(self, callee, arguments):
        super().__init__()
        self.callee = callee
        self.arguments = arguments  # Lista de expresiones
    
    def __str__(self):
        return f"CallExpression({self.callee}, args={len(self.arguments)})"

class AssignmentExpression(Expression):
    def __init__(self, left, operator, right):
        super().__init__()
        self.left = left
        self.operator = operator
        self.right = right
    
    def __str__(self):
        return f"AssignmentExpression({self.left}, {self.operator}, {self.right})"


class Parser:
    def __init__(self, tokens):
        self.tokens = tokens
        self.current = 0
        self.errors = []
    
    def parse(self):
        """Punto de entrada principal del parser."""
        try:
            self.ast = self.program()
            return self.errors
        except ParserError as e:
            self.errors.append(str(e))
            return self.errors
        except Exception as e:
            token = self.peek()
            self.errors.append(f"Error inesperado en línea {token.line}, columna {token.column}: {str(e)}")
            return self.errors
    
    def program(self):
        """Devuelve el AST completo para el analizador semántico."""
        if hasattr(self, 'ast'):
            return self.ast
        return self._parse_program()
    
    def _parse_program(self):
        """programa -> declaracion_externa*"""
        program = Program()
        
        while not self.is_at_end():
            try:
                # Verificar si es una declaración global o una función
                if self.match(TokenType.INT, TokenType.CHAR, TokenType.FLOAT, TokenType.VOID):
                    self.backup()  # Retroceder para procesar correctamente
                    
                    # Guardar la posición para retroceder si es necesario
                    save_pos = self.current
                    
                    # Intentar analizar como una función
                    try:
                        func = self.function_declaration()
                        program.functions.append(func)
                        continue
                    except ParserError:
                        # No era una función, retroceder
                        self.current = save_pos
                    
                    # Si no es una función, debe ser una declaración de variable
                    decl = self.declaration()
                    program.declarations.append(decl)
                    
                    # Verificar punto y coma
                    self.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
                else:
                    # Si no comienza con un tipo, es un error
                    token = self.peek()
                    raise ParserError(f"Se esperaba una declaración, se encontró '{token.value}'", token)
            except ParserError as e:
                self.errors.append(str(e))
                self.synchronize()
        
        self.ast = program
        return program
    
    def function_declaration(self):
        """function_declaration -> type identifier '(' parameters? ')' compound_statement"""
        return_type = self.type_spec()
        name = self.consume(TokenType.IDENTIFIER, "Se esperaba un identificador.").value
        
        self.consume(TokenType.LPAREN, "Se esperaba '(' después del nombre de la función.")
        params = []
        
        # Parámetros
        if not self.check(TokenType.RPAREN):
            params = self.parameters()
        
        self.consume(TokenType.RPAREN, "Se esperaba ')' después de los parámetros.")
        
        # Cuerpo de la función
        body = self.compound_statement()
        
        return Function(return_type, name, params, body)
    
    def parameters(self):
        """parameters -> parameter (',' parameter)*"""
        params = []
        
        # Primer parámetro
        params.append(self.parameter())
        
        # Resto de parámetros
        while self.match(TokenType.COMMA):
            params.append(self.parameter())
        
        return params
    
    def parameter(self):
        """parameter -> type_spec identifier"""
        type_spec = self.type_spec()
        name = self.consume(TokenType.IDENTIFIER, "Se esperaba un identificador.").value
        
        return Parameter(type_spec, name)
    
    def type_spec(self):
        """type_spec -> 'int' | 'char' | 'float' | 'void'"""
        if self.match(TokenType.INT):
            return "int"
        elif self.match(TokenType.CHAR):
            return "char"
        elif self.match(TokenType.FLOAT):
            return "float"
        elif self.match(TokenType.VOID):
            return "void"
        else:
            token = self.peek()
            raise ParserError("Se esperaba un tipo de dato.", token)
    
    def declaration(self):
        """declaration -> type_spec identifier ('=' expression)?"""
        type_spec = self.type_spec()
        name = self.consume(TokenType.IDENTIFIER, "Se esperaba un identificador.").value
        
        # Verificar inicialización
        initializer = None
        if self.match(TokenType.ASSIGN):
            initializer = self.expression()
        
        return VarDeclaration(type_spec, name, initializer)
    
    def compound_statement(self):
        """compound_statement -> '{' (declaration | statement)* '}'"""
        self.consume(TokenType.LBRACE, "Se esperaba '{'.")
        
        block = CompoundStatement()
        
        while not self.check(TokenType.RBRACE) and not self.is_at_end():
            # Verificar si es una declaración
            if self.check(TokenType.INT) or self.check(TokenType.CHAR) or self.check(TokenType.FLOAT) or self.check(TokenType.VOID):
                block.declarations.append(self.declaration())
                self.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la declaración.")
            else:
                block.statements.append(self.statement())
        
        self.consume(TokenType.RBRACE, "Se esperaba '}'.")
        
        return block
    
    def statement(self):
        """
        statement -> expression_statement
                  | compound_statement
                  | if_statement
                  | while_statement
                  | for_statement
                  | return_statement
        """
        if self.match(TokenType.IF):
            return self.if_statement()
        elif self.match(TokenType.WHILE):
            return self.while_statement()
        elif self.match(TokenType.FOR):
            return self.for_statement()
        elif self.match(TokenType.RETURN):
            return self.return_statement()
        elif self.check(TokenType.LBRACE):
            return self.compound_statement()
        else:
            return self.expression_statement()
    
    def if_statement(self):
        """if_statement -> 'if' '(' expression ')' statement ('else' statement)?"""
        self.consume(TokenType.LPAREN, "Se esperaba '(' después de 'if'.")
        condition = self.expression()
        self.consume(TokenType.RPAREN, "Se esperaba ')' después de la condición.")
        
        then_stmt = self.statement()
        
        # Manejar 'else' opcional
        else_stmt = None
        if self.match(TokenType.ELSE):
            else_stmt = self.statement()
        
        return IfStatement(condition, then_stmt, else_stmt)
    
    def while_statement(self):
        """while_statement -> 'while' '(' expression ')' statement"""
        self.consume(TokenType.LPAREN, "Se esperaba '(' después de 'while'.")
        condition = self.expression()
        self.consume(TokenType.RPAREN, "Se esperaba ')' después de la condición.")
        
        body = self.statement()
        
        return WhileStatement(condition, body)
    
    def for_statement(self):
        """for_statement -> 'for' '(' (expression?';' expression?';' expression?) ')' statement"""
        self.consume(TokenType.LPAREN, "Se esperaba '(' después de 'for'.")
        
        # Inicialización
        init = None
        if not self.check(TokenType.SEMICOLON):
            init = self.expression()
        self.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la inicialización.")
        
        # Condición
        condition = None
        if not self.check(TokenType.SEMICOLON):
            condition = self.expression()
        self.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la condición.")
        
        # Actualización
        update = None
        if not self.check(TokenType.RPAREN):
            update = self.expression()
        self.consume(TokenType.RPAREN, "Se esperaba ')' después de la expresión de actualización.")
        
        body = self.statement()
        
        return ForStatement(init, condition, update, body)
    
    def return_statement(self):
        """return_statement -> 'return' expression? ';'"""
        expr = None
        if not self.check(TokenType.SEMICOLON):
            expr = self.expression()
        
        self.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la sentencia return.")
        
        return ReturnStatement(expr)
    
    def expression_statement(self):
        """expression_statement -> expression? ';'"""
        expr = None
        if not self.check(TokenType.SEMICOLON):
            expr = self.expression()
        
        self.consume(TokenType.SEMICOLON, "Se esperaba ';' después de la expresión.")
        
        return ExpressionStatement(expr)
    
    def expression(self):
        """expression -> assignment"""
        return self.assignment()
    
    def assignment(self):
        """assignment -> logical_or (('=' | '+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '|=' | '^=' | '<<=' | '>>=') assignment)?"""
        expr = self.logical_or()
        
        if self.match(TokenType.ASSIGN, TokenType.PLUS_ASSIGN, TokenType.MINUS_ASSIGN, 
                      TokenType.MULT_ASSIGN, TokenType.DIV_ASSIGN, TokenType.MOD_ASSIGN,
                      TokenType.BITAND_ASSIGN, TokenType.BITOR_ASSIGN, TokenType.BITXOR_ASSIGN,
                      TokenType.LSHIFT_ASSIGN, TokenType.RSHIFT_ASSIGN):
            operator = self.previous().value
            right = self.assignment()
            
            # Validar que el lado izquierdo sea un lvalue
            if not isinstance(expr, Identifier):
                token = self.previous()
                self.error(token, "El lado izquierdo de una asignación debe ser un lvalue.")
            
            return AssignmentExpression(expr, operator, right)
        
        return expr
    
    def logical_or(self):
        """logical_or -> logical_and ('||' logical_and)*"""
        expr = self.logical_and()
        
        while self.match(TokenType.OR):
            operator = self.previous().value
            right = self.logical_and()
            expr = BinaryExpression(expr, operator, right)
        
        return expr
    
    def logical_and(self):
        """logical_and -> equality ('&&' equality)*"""
        expr = self.equality()
        
        while self.match(TokenType.AND):
            operator = self.previous().value
            right = self.equality()
            expr = BinaryExpression(expr, operator, right)
        
        return expr
    
    def equality(self):
        """equality -> comparison (('==' | '!=') comparison)*"""
        expr = self.comparison()
        
        while self.match(TokenType.EQUAL, TokenType.NOT_EQUAL):
            operator = self.previous().value
            right = self.comparison()
            expr = BinaryExpression(expr, operator, right)
        
        return expr
    
    def comparison(self):
        """comparison -> bitwise_or (('<' | '>' | '<=' | '>=') bitwise_or)*"""
        expr = self.bitwise_or()
        
        while self.match(TokenType.LESS_THAN, TokenType.GREATER_THAN, TokenType.LESS_EQUAL, TokenType.GREATER_EQUAL):
            operator = self.previous().value
            right = self.bitwise_or()
            expr = BinaryExpression(expr, operator, right)
        
        return expr
    
    def bitwise_or(self):
        """bitwise_or -> bitwise_xor ('|' bitwise_xor)*"""
        expr = self.bitwise_xor()
        
        while self.match(TokenType.BITOR):
            operator = self.previous().value
            right = self.bitwise_xor()
            expr = BinaryExpression(expr, operator, right)
        
        return expr
    
    def bitwise_xor(self):
        """bitwise_xor -> bitwise_and ('^' bitwise_and)*"""
        expr = self.bitwise_and()
        
        while self.match(TokenType.BITXOR):
            operator = self.previous().value
            right = self.bitwise_and()
            expr = BinaryExpression(expr, operator, right)
        
        return expr
    
    def bitwise_and(self):
        """bitwise_and -> shift ('&' shift)*"""
        expr = self.shift()
        
        while self.match(TokenType.BITAND):
            operator = self.previous().value
            right = self.shift()
            expr = BinaryExpression(expr, operator, right)
        
        return expr
    
    def shift(self):
        """shift -> additive (('<<' | '>>') additive)*"""
        expr = self.additive()
        
        while self.match(TokenType.LSHIFT, TokenType.RSHIFT):
            operator = self.previous().value
            right = self.additive()
            expr = BinaryExpression(expr, operator, right)
        
        return expr
    
    def additive(self):
        """additive -> multiplicative (('+' | '-') multiplicative)*"""
        expr = self.multiplicative()
        
        while self.match(TokenType.PLUS, TokenType.MINUS):
            operator = self.previous().value
            right = self.multiplicative()
            expr = BinaryExpression(expr, operator, right)
        
        return expr
    
    def multiplicative(self):
        """multiplicative -> unary (('*' | '/' | '%') unary)*"""
        expr = self.unary()
        
        while self.match(TokenType.MULTIPLY, TokenType.DIVIDE, TokenType.MOD):
            operator = self.previous().value
            right = self.unary()
            expr = BinaryExpression(expr, operator, right)
        
        return expr
    
    def unary(self):
        """unary -> ('!' | '-' | '~' | '++' | '--') unary | postfix"""
        if self.match(TokenType.NOT, TokenType.MINUS, TokenType.BITNOT, TokenType.INCREMENT, TokenType.DECREMENT):
            operator = self.previous().value
            right = self.unary()
            return UnaryExpression(operator, right)
        
        return self.postfix()
    
    def postfix(self):
        """postfix -> primary ('++' | '--')?"""
        expr = self.primary()
        
        if self.match(TokenType.INCREMENT, TokenType.DECREMENT):
            operator = self.previous().value
            return UnaryExpression(operator + "_post", expr)  # Usar un sufijo para diferenciar
        
        return expr
    
    def primary(self):
        """
        primary -> INTEGER_LITERAL | FLOAT_LITERAL | CHAR_LITERAL | STRING_LITERAL
                 | IDENTIFIER ('(' arguments? ')')?
                 | '(' expression ')'
        """
        if self.match(TokenType.INTEGER_LITERAL):
            return Literal(self.previous().value, "int")
        
        if self.match(TokenType.FLOAT_LITERAL):
            return Literal(self.previous().value, "float")
        
        if self.match(TokenType.CHAR_LITERAL):
            return Literal(self.previous().value, "char")
        
        if self.match(TokenType.STRING_LITERAL):
            return Literal(self.previous().value, "string")
        
        if self.match(TokenType.IDENTIFIER):
            identifier = Identifier(self.previous().value)
            
            # Verificar si es una llamada a función
            if self.match(TokenType.LPAREN):
                arguments = []
                
                # Procesar argumentos si hay
                if not self.check(TokenType.RPAREN):
                    arguments = self.arguments()
                
                self.consume(TokenType.RPAREN, "Se esperaba ')' después de los argumentos.")
                
                return CallExpression(identifier, arguments)
            
            return identifier
        
        if self.match(TokenType.LPAREN):
            expr = self.expression()
            self.consume(TokenType.RPAREN, "Se esperaba ')' después de la expresión.")
            return expr
        
        # Si no coincide con ninguno de los anteriores, es un error
        token = self.peek()
        raise ParserError(f"Se esperaba una expresión, se encontró '{token.value}'", token)
    
    def arguments(self):
        """arguments -> expression (',' expression)*"""
        args = []
        
        # Primer argumento
        args.append(self.expression())
        
        # Resto de argumentos
        while self.match(TokenType.COMMA):
            args.append(self.expression())
        
        return args
    
    # Métodos auxiliares para el parser
    
    def match(self, *types):
        """Verifica si el token actual es de alguno de los tipos dados."""
        for type in types:
            if self.check(type):
                self.advance()
                return True
        return False
    
    def check(self, type):
        """Verifica si el token actual es del tipo dado sin consumirlo."""
        if self.is_at_end():
            return False
        return self.peek().type == type
    
    def advance(self):
        """Avanza al siguiente token y devuelve el anterior."""
        if not self.is_at_end():
            self.current += 1
        return self.previous()
    
    def is_at_end(self):
        """Verifica si hemos llegado al final de los tokens."""
        return self.peek().type == TokenType.EOF
    
    def peek(self):
        """Devuelve el token actual sin consumirlo."""
        return self.tokens[self.current]
    
    def previous(self):
        """Devuelve el token anterior."""
        return self.tokens[self.current - 1]
    
    def consume(self, type, message):
        """Consume el token actual si es del tipo dado, sino genera un error."""
        if self.check(type):
            return self.advance()
        
        token = self.peek()
        raise ParserError(message, token)
    
    def backup(self):
        """Retrocede un token."""
        if self.current > 0:
            self.current -= 1
    
    def error(self, token, message):
        """Genera un error y lo registra."""
        error = ParserError(message, token)
        self.errors.append(str(error))
        return error
    
    def synchronize(self):
        """
        Recuperación de errores: avanza hasta encontrar un punto 
        sincronización (como ';' o el inicio de una declaración).
        """
        self.advance()
        
        while not self.is_at_end():
            # Si encontramos un ';', podemos sincronizar después de él
            if self.previous().type == TokenType.SEMICOLON:
                return
            
            # Sincronizar en el inicio de una declaración
            if self.peek().type in (
                TokenType.INT, TokenType.CHAR, TokenType.FLOAT, TokenType.VOID,
                TokenType.IF, TokenType.WHILE, TokenType.FOR, TokenType.RETURN,
                TokenType.LBRACE  # Inicio de bloque
            ):
                return
            
            self.advance()