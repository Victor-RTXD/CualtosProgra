#!/usr/bin/env python3

class Token:
    def __init__(self, type, value, line, column):
        self.type = type
        self.value = value
        self.line = line
        self.column = column

class TokenType:
    def __init__(self, name):
        self.name = name

class Parser:
    def __init__(self, tokens=None):
        self.tokens = tokens or []
        self.current_token_index = 0
        self.errors = []
        
    def set_tokens(self, tokens):
        self.tokens = tokens
        self.current_token_index = 0
        self.errors = []
    
    def get_errors(self):
        return self.errors
    
    def current_token(self):
        if self.current_token_index < len(self.tokens):
            return self.tokens[self.current_token_index]
        return None
    
    def peek_next_token(self):
        if self.current_token_index + 1 < len(self.tokens):
            return self.tokens[self.current_token_index + 1]
        return None
    
    def advance(self):
        self.current_token_index += 1
        return self.current_token() if self.current_token_index < len(self.tokens) else None
    
    def add_error(self, message):
        token = self.current_token()
        if token:
            self.errors.append((message, token.line, token.column))
        else:
            self.errors.append((message, -1, -1))
    
    def expect(self, token_type):
        current = self.current_token()
        if current and current.type.name == token_type:
            return self.advance()
        
        if current:
            self.add_error(f"Se esperaba {token_type}, pero se encontró {current.type.name} ('{current.value}')")
        else:
            self.add_error(f"Se esperaba {token_type}, pero se llegó al final del archivo")
        return None
    
    def parse(self):
        """Inicia el proceso de análisis sintáctico."""
        self.current_token_index = 0
        self.errors = []
        self.parse_program()
        return len(self.errors) == 0
    
    def parse_program(self):
        """Analiza el programa completo."""
        while self.current_token() and self.current_token().type.name != 'EOF':
            if self.current_token().type.name == 'PREPROCESSOR':
                self.parse_preprocessor()
            elif self.current_token().type.name in ['INT', 'FLOAT', 'CHAR', 'VOID']:
                self.parse_function_or_declaration()
            else:
                self.add_error(f"Elemento inesperado en nivel global: {self.current_token().type.name}")
                self.advance()
    
    def parse_preprocessor(self):
        """Analiza directivas de preprocesador."""
        token = self.current_token()
        # Simplemente avanzar por ahora, en una implementación completa haría más validaciones
        self.advance()
    
    def parse_function_or_declaration(self):
        """Decide si es una declaración de función o variable."""
        # Guardamos el tipo para la declaración
        type_token = self.current_token()
        self.advance()  # Consumir el tipo
        
        # Esperamos un identificador
        if not self.current_token() or self.current_token().type.name != 'IDENTIFIER':
            self.add_error("Se esperaba un identificador después del tipo")
            return
        
        identifier = self.current_token().value
        self.advance()  # Consumir el identificador
        
        # Si hay un paréntesis, es una función
        if self.current_token() and self.current_token().type.name == 'LEFT_PAREN':
            self.parse_function_definition(type_token, identifier)
        else:
            self.parse_variable_declaration(type_token, identifier)
    
    def parse_function_definition(self, return_type, name):
        """Analiza la definición de una función."""
        # Ya sabemos que el token actual es LEFT_PAREN
        self.advance()  # Consumir '('
        
        # Analizar parámetros (simplificado)
        self.parse_parameters()
        
        # Esperar paréntesis de cierre
        if not self.current_token() or self.current_token().type.name != 'RIGHT_PAREN':
            self.add_error("Se esperaba ')' después de los parámetros")
        else:
            self.advance()  # Consumir ')'
        
        # Esperar bloque de código
        self.parse_block()
    
    def parse_parameters(self):
        """Analiza los parámetros de una función."""
        # Si el siguiente token es RIGHT_PAREN, no hay parámetros
        if self.current_token() and self.current_token().type.name == 'RIGHT_PAREN':
            return
            
        while self.current_token() and self.current_token().type.name != 'RIGHT_PAREN':
            # Esperar tipo de dato
            if self.current_token().type.name not in ['INT', 'FLOAT', 'CHAR', 'VOID']:
                self.add_error("Se esperaba un tipo de dato en parámetro")
                break
            
            self.advance()  # Consumir tipo
            
            # Esperar identificador
            if not self.current_token() or self.current_token().type.name != 'IDENTIFIER':
                self.add_error("Se esperaba un identificador en parámetro")
                break
                
            self.advance()  # Consumir identificador
            
            # Si hay una coma, continuar con el siguiente parámetro
            if self.current_token() and self.current_token().type.name == 'COMMA':
                self.advance()  # Consumir coma
            elif self.current_token() and self.current_token().type.name != 'RIGHT_PAREN':
                self.add_error("Se esperaba ',' o ')' después del parámetro")
                break
    
    def parse_variable_declaration(self, type_token, identifier):
        """Analiza una declaración de variable."""
        # Verificar si hay inicialización
        if self.current_token() and self.current_token().type.name == 'ASSIGN':
            self.advance()  # Consumir '='
            self.parse_expression()
            
        # Esperar punto y coma
        if not self.current_token() or self.current_token().type.name != 'SEMICOLON':
            self.add_error("Se esperaba ';' después de la declaración")
        else:
            self.advance()  # Consumir ';'
    
    def parse_block(self):
        """Analiza un bloque de código entre llaves."""
        if not self.current_token() or self.current_token().type.name != 'LEFT_BRACE':
            self.add_error("Se esperaba '{' para iniciar un bloque")
            return
            
        self.advance()  # Consumir '{'
        
        # Analizar contenido del bloque
        while self.current_token() and self.current_token().type.name != 'RIGHT_BRACE':
            self.parse_statement()
            
            # Si llegamos al final sin encontrar la llave de cierre
            if not self.current_token():
                self.add_error("Se esperaba '}' para cerrar el bloque")
                return
                
        # Consumir '}'
        self.advance()
    
    def parse_statement(self):
        """Analiza una instrucción."""
        if not self.current_token():
            return
            
        token_type = self.current_token().type.name
        
        # Declaración de variable
        if token_type in ['INT', 'FLOAT', 'CHAR', 'VOID']:
            type_token = self.current_token()
            self.advance()  # Consumir tipo
            
            if not self.current_token() or self.current_token().type.name != 'IDENTIFIER':
                self.add_error("Se esperaba un identificador después del tipo")
                return
                
            identifier = self.current_token().value
            self.advance()  # Consumir identificador
            
            self.parse_variable_declaration(type_token, identifier)
            
        # If statement
        elif token_type == 'IF':
            self.parse_if_statement()
            
        # For loop
        elif token_type == 'FOR':
            self.parse_for_loop()
            
        # While loop
        elif token_type == 'WHILE':
            self.parse_while_loop()
            
        # Return statement
        elif token_type == 'RETURN':
            self.parse_return_statement()
            
        # Expression statement (como llamadas a función)
        elif token_type == 'IDENTIFIER':
            self.parse_expression_statement()
            
        # Block
        elif token_type == 'LEFT_BRACE':
            self.parse_block()
            
        # Otro tipo de token
        else:
            self.add_error(f"Elemento inesperado en instrucción: {token_type}")
            self.advance()
    
    def parse_if_statement(self):
        """Analiza una instrucción if."""
        self.advance()  # Consumir 'if'
        
        # Esperar paréntesis de apertura
        if not self.current_token() or self.current_token().type.name != 'LEFT_PAREN':
            self.add_error("Se esperaba '(' después de 'if'")
        else:
            self.advance()  # Consumir '('
            
        # Analizar la condición
        self.parse_expression()
        
        # Esperar paréntesis de cierre
        if not self.current_token() or self.current_token().type.name != 'RIGHT_PAREN':
            self.add_error("Se esperaba ')' después de la condición")
        else:
            self.advance()  # Consumir ')'
            
        # Analizar el cuerpo del if
        self.parse_statement()
        
        # Verificar si hay un else
        if self.current_token() and self.current_token().type.name == 'ELSE':
            self.advance()  # Consumir 'else'
            self.parse_statement()
    
    def parse_for_loop(self):
        """Analiza un bucle for."""
        self.advance()  # Consumir 'for'
        
        # Esperar paréntesis de apertura
        if not self.current_token() or self.current_token().type.name != 'LEFT_PAREN':
            self.add_error("Se esperaba '(' después de 'for'")
        else:
            self.advance()  # Consumir '('
            
        # Inicialización
        if self.current_token().type.name in ['INT', 'FLOAT', 'CHAR']:
            type_token = self.current_token()
            self.advance()  # Consumir tipo
            
            if not self.current_token() or self.current_token().type.name != 'IDENTIFIER':
                self.add_error("Se esperaba un identificador después del tipo")
            else:
                identifier = self.current_token().value
                self.advance()  # Consumir identificador
                self.parse_variable_declaration(type_token, identifier)
        else:
            self.parse_expression_statement()
            
        # Condición
        self.parse_expression()
        
        # Esperar punto y coma
        if not self.current_token() or self.current_token().type.name != 'SEMICOLON':
            self.add_error("Se esperaba ';' después de la condición")
        else:
            self.advance()  # Consumir ';'
            
        # Incremento
        self.parse_expression()
        
        # Esperar paréntesis de cierre
        if not self.current_token() or self.current_token().type.name != 'RIGHT_PAREN':
            self.add_error("Se esperaba ')' al final del bucle for")
        else:
            self.advance()  # Consumir ')'
            
        # Cuerpo del bucle
        self.parse_statement()
    
    def parse_while_loop(self):
        """Analiza un bucle while."""
        self.advance()  # Consumir 'while'
        
        # Esperar paréntesis de apertura
        if not self.current_token() or self.current_token().type.name != 'LEFT_PAREN':
            self.add_error("Se esperaba '(' después de 'while'")
        else:
            self.advance()  # Consumir '('
            
        # Condición
        self.parse_expression()
        
        # Esperar paréntesis de cierre
        if not self.current_token() or self.current_token().type.name != 'RIGHT_PAREN':
            self.add_error("Se esperaba ')' después de la condición")
        else:
            self.advance()  # Consumir ')'
            
        # Cuerpo del bucle
        self.parse_statement()
    
    def parse_return_statement(self):
        """Analiza una instrucción return."""
        self.advance()  # Consumir 'return'
        
        # Puede haber una expresión o no
        if self.current_token() and self.current_token().type.name != 'SEMICOLON':
            self.parse_expression()
            
        # Esperar punto y coma
        if not self.current_token() or self.current_token().type.name != 'SEMICOLON':
            self.add_error("Se esperaba ';' después de return")
        else:
            self.advance()  # Consumir ';'
    
    def parse_expression_statement(self):
        """Analiza una instrucción de expresión."""
        self.parse_expression()
        
        # Esperar punto y coma
        if not self.current_token() or self.current_token().type.name != 'SEMICOLON':
            self.add_error("Se esperaba ';' después de la expresión")
        else:
            self.advance()  # Consumir ';'
    
    def parse_expression(self):
        """Analiza una expresión."""
        self.parse_assignment_expression()
    
    def parse_assignment_expression(self):
        """Analiza una expresión de asignación."""
        self.parse_logical_or_expression()
        
        # Si hay un operador de asignación
        if self.current_token() and self.current_token().type.name == 'ASSIGN':
            self.advance()  # Consumir operador
            self.parse_assignment_expression()
    
    def parse_logical_or_expression(self):
        """Analiza una expresión OR lógica."""
        self.parse_logical_and_expression()
        
        while self.current_token() and self.current_token().type.name == 'LOGICAL_OR':
            self.advance()  # Consumir operador
            self.parse_logical_and_expression()
    
    def parse_logical_and_expression(self):
        """Analiza una expresión AND lógica."""
        self.parse_equality_expression()
        
        while self.current_token() and self.current_token().type.name == 'LOGICAL_AND':
            self.advance()  # Consumir operador
            self.parse_equality_expression()
    
    def parse_equality_expression(self):
        """Analiza una expresión de igualdad."""
        self.parse_relational_expression()
        
        while self.current_token() and self.current_token().type.name in ['EQUAL', 'NOT_EQUAL']:
            self.advance()  # Consumir operador
            self.parse_relational_expression()
    
    def parse_relational_expression(self):
        """Analiza una expresión relacional."""
        self.parse_additive_expression()
        
        while self.current_token() and self.current_token().type.name in ['LESS', 'LESS_EQUAL', 'GREATER', 'GREATER_EQUAL']:
            self.advance()  # Consumir operador
            self.parse_additive_expression()
    
    def parse_additive_expression(self):
        """Analiza una expresión aditiva."""
        self.parse_multiplicative_expression()
        
        while self.current_token() and self.current_token().type.name in ['PLUS', 'MINUS']:
            self.advance()  # Consumir operador
            self.parse_multiplicative_expression()
    
    def parse_multiplicative_expression(self):
        """Analiza una expresión multiplicativa."""
        self.parse_unary_expression()
        
        while self.current_token() and self.current_token().type.name in ['MULTIPLY', 'DIVIDE', 'MODULO']:
            self.advance()  # Consumir operador
            self.parse_unary_expression()
    
    def parse_unary_expression(self):
        """Analiza una expresión unaria."""
        if self.current_token() and self.current_token().type.name in ['MINUS', 'NOT']:
            self.advance()  # Consumir operador
            self.parse_unary_expression()
        else:
            self.parse_primary_expression()
    
    def parse_primary_expression(self):
        """Analiza una expresión primaria."""
        if not self.current_token():
            self.add_error("Se esperaba una expresión primaria")
            return
            
        token_type = self.current_token().type.name
        
        if token_type == 'IDENTIFIER':
            self.advance()  # Consumir identificador
            
            # Si es una llamada a función
            if self.current_token() and self.current_token().type.name == 'LEFT_PAREN':
                self.parse_function_call()
                
        elif token_type in ['INTEGER', 'FLOAT_LITERAL', 'CHARACTER', 'STRING']:
            self.advance()  # Consumir literal
            
        elif token_type == 'LEFT_PAREN':
            self.advance()  # Consumir '('
            self.parse_expression()
            
            # Esperar paréntesis de cierre
            if not self.current_token() or self.current_token().type.name != 'RIGHT_PAREN':
                self.add_error("Se esperaba ')' para cerrar la expresión")
            else:
                self.advance()  # Consumir ')'
                
        else:
            self.add_error(f"Token inesperado en expresión primaria: {token_type}")
            self.advance()
    
    def parse_function_call(self):
        """Analiza una llamada a función."""
        # Ya se consumió el identificador y estamos en '('
        self.advance()  # Consumir '('
        
        # Si no hay argumentos
        if self.current_token() and self.current_token().type.name == 'RIGHT_PAREN':
            self.advance()  # Consumir ')'
            return
            
        # Analizar argumentos
        self.parse_argument_list()
        
        # Esperar paréntesis de cierre
        if not self.current_token() or self.current_token().type.name != 'RIGHT_PAREN':
            self.add_error("Se esperaba ')' después de los argumentos")
        else:
            self.advance()  # Consumir ')'
    
    def parse_argument_list(self):
        """Analiza una lista de argumentos."""
        self.parse_expression()
        
        while self.current_token() and self.current_token().type.name == 'COMMA':
            self.advance()  # Consumir ','
            self.parse_expression()