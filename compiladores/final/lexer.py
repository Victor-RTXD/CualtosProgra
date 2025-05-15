import re
from enum import Enum, auto

class TokenType(Enum):
    # Palabras clave
    INT = auto()
    CHAR = auto()
    FLOAT = auto()
    VOID = auto()
    RETURN = auto()
    IF = auto()
    ELSE = auto()
    FOR = auto()
    WHILE = auto()
    DO = auto()
    BREAK = auto()
    CONTINUE = auto()
    SWITCH = auto()
    CASE = auto()
    DEFAULT = auto()
    
    # Identificadores y literales
    IDENTIFIER = auto()
    INTEGER_LITERAL = auto()
    FLOAT_LITERAL = auto()
    CHAR_LITERAL = auto()
    STRING_LITERAL = auto()
    
    # Operadores
    PLUS = auto()           # +
    MINUS = auto()          # -
    MULTIPLY = auto()       # *
    DIVIDE = auto()         # /
    MOD = auto()            # %
    ASSIGN = auto()         # =
    EQUAL = auto()          # ==
    NOT_EQUAL = auto()      # !=
    LESS_THAN = auto()      # <
    GREATER_THAN = auto()   # >
    LESS_EQUAL = auto()     # <=
    GREATER_EQUAL = auto()  # >=
    AND = auto()            # &&
    OR = auto()             # ||
    NOT = auto()            # !
    BITAND = auto()         # &
    BITOR = auto()          # |
    BITXOR = auto()         # ^
    BITNOT = auto()         # ~
    LSHIFT = auto()         # <<
    RSHIFT = auto()         # >>
    
    # Incremento/Decremento
    INCREMENT = auto()      # ++
    DECREMENT = auto()      # --
    
    # Asignaciones compuestas
    PLUS_ASSIGN = auto()    # +=
    MINUS_ASSIGN = auto()   # -=
    MULT_ASSIGN = auto()    # *=
    DIV_ASSIGN = auto()     # /=
    MOD_ASSIGN = auto()     # %=
    BITAND_ASSIGN = auto()  # &=
    BITOR_ASSIGN = auto()   # |=
    BITXOR_ASSIGN = auto()  # ^=
    LSHIFT_ASSIGN = auto()  # <<=
    RSHIFT_ASSIGN = auto()  # >>=
    
    # Delimitadores
    SEMICOLON = auto()      # ;
    COMMA = auto()          # ,
    LPAREN = auto()         # (
    RPAREN = auto()         # )
    LBRACE = auto()         # {
    RBRACE = auto()         # }
    LBRACKET = auto()       # [
    RBRACKET = auto()       # ]
    DOT = auto()            # .
    ARROW = auto()          # ->
    
    # Directivas del preprocesador
    HASH = auto()           # #
    
    # Otros
    EOF = auto()            # Fin de archivo
    ERROR = auto()          # Error léxico
    
    # Comentarios (normalmente no se generan tokens para estos)
    COMMENT = auto()        # Comentarios


class Token:
    def __init__(self, type, value, line, column):
        self.type = type
        self.value = value
        self.line = line
        self.column = column
    
    def __str__(self):
        return f"Token({self.type}, '{self.value}', line={self.line}, col={self.column})"
    
    def __repr__(self):
        return self.__str__()


class Lexer:
    def __init__(self):
        # Palabras reservadas de C
        self.keywords = {
            'int': TokenType.INT,
            'char': TokenType.CHAR,
            'float': TokenType.FLOAT,
            'void': TokenType.VOID,
            'return': TokenType.RETURN,
            'if': TokenType.IF,
            'else': TokenType.ELSE,
            'for': TokenType.FOR,
            'while': TokenType.WHILE,
            'do': TokenType.DO,
            'break': TokenType.BREAK,
            'continue': TokenType.CONTINUE,
            'switch': TokenType.SWITCH,
            'case': TokenType.CASE,
            'default': TokenType.DEFAULT
        }
        
        # Patrones para tokens
        self.token_specs = [
            # Whitespace (ignorado)
            ('WHITESPACE', r'[ \t\r\f]+'),
            
            # Comentarios (pueden ser procesados o ignorados)
            ('COMMENT_LINE', r'//.*?(?:\n|$)'),
            ('COMMENT_BLOCK', r'/\*.*?\*/'),
            
            # Identificadores y palabras clave
            ('IDENTIFIER', r'[a-zA-Z_][a-zA-Z0-9_]*'),
            
            # Literales numéricos
            ('HEX_INTEGER', r'0[xX][0-9a-fA-F]+'),
            ('INTEGER', r'[0-9]+'),
            ('FLOAT', r'[0-9]+\.[0-9]*([eE][+-]?[0-9]+)?|[0-9]*\.[0-9]+([eE][+-]?[0-9]+)?|[0-9]+[eE][+-]?[0-9]+'),
            
            # Literales de cadena y carácter
            ('STRING', r'"(\\.|[^"\\])*"'),
            ('CHAR', r"'(\\.|[^'\\])'"),
            
            # Operadores de dos caracteres
            ('INCREMENT', r'\+\+'),
            ('DECREMENT', r'--'),
            ('ARROW', r'->'),
            ('LESS_EQUAL', r'<='),
            ('GREATER_EQUAL', r'>='),
            ('EQUAL', r'=='),
            ('NOT_EQUAL', r'!='),
            ('AND', r'&&'),
            ('OR', r'\|\|'),
            ('LSHIFT', r'<<'),
            ('RSHIFT', r'>>'),
            
            # Asignaciones compuestas
            ('PLUS_ASSIGN', r'\+='),
            ('MINUS_ASSIGN', r'-='),
            ('MULT_ASSIGN', r'\*='),
            ('DIV_ASSIGN', r'/='),
            ('MOD_ASSIGN', r'%='),
            ('BITAND_ASSIGN', r'&='),
            ('BITOR_ASSIGN', r'\|='),
            ('BITXOR_ASSIGN', r'\^='),
            ('LSHIFT_ASSIGN', r'<<='),
            ('RSHIFT_ASSIGN', r'>>='),
            
            # Operadores de un carácter
            ('PLUS', r'\+'),
            ('MINUS', r'-'),
            ('MULTIPLY', r'\*'),
            ('DIVIDE', r'/'),
            ('MOD', r'%'),
            ('LESS_THAN', r'<'),
            ('GREATER_THAN', r'>'),
            ('ASSIGN', r'='),
            ('NOT', r'!'),
            ('BITAND', r'&'),
            ('BITOR', r'\|'),
            ('BITXOR', r'\^'),
            ('BITNOT', r'~'),
            ('HASH', r'#'),
            
            # Delimitadores
            ('SEMICOLON', r';'),
            ('COMMA', r','),
            ('LPAREN', r'\('),
            ('RPAREN', r'\)'),
            ('LBRACE', r'{'),
            ('RBRACE', r'}'),
            ('LBRACKET', r'\['),
            ('RBRACKET', r'\]'),
            ('DOT', r'\.'),
            
            # Nueva línea (para seguimiento de líneas)
            ('NEWLINE', r'\n'),
        ]
        
        # Compilar los patrones en una expresión regular
        self.regex = '|'.join('(?P<%s>%s)' % (name, pattern) for name, pattern in self.token_specs)
        self.regex_obj = re.compile(self.regex)
        
        # Variables para seguimiento de posición
        self.text = ""
        self.pos = 0
        self.line = 1
        self.column = 1
        
        # Variables para detectar errores
        self.errors = []
    
    def tokenize(self, text):
        """
        Convierte el texto de entrada en una lista de tokens.
        """
        self.text = text
        self.pos = 0
        self.line = 1
        self.column = 1
        self.errors = []
        tokens = []
        
        while self.pos < len(text):
            match = self.regex_obj.match(text, self.pos)
            if match:
                group_name = match.lastgroup
                value = match.group(group_name)
                column = self.column
                
                # Actualizar posición
                self.pos = match.end()
                
                # Manejar saltos de línea
                if group_name == 'NEWLINE':
                    self.line += 1
                    self.column = 1
                    continue
                
                # Manejar espacios en blanco
                if group_name == 'WHITESPACE':
                    self.column += len(value)
                    continue
                
                # Manejar comentarios
                if group_name in ('COMMENT_LINE', 'COMMENT_BLOCK'):
                    # Contar líneas en comentarios multilinea
                    newlines = value.count('\n')
                    if newlines > 0:
                        self.line += newlines
                        self.column = len(value) - value.rfind('\n')
                    else:
                        self.column += len(value)
                    
                    # Si se quiere generar tokens para comentarios, descomentar esta línea
                    # tokens.append(Token(TokenType.COMMENT, value, self.line, column))
                    continue
                
                # Procesar identificadores y palabras clave
                if group_name == 'IDENTIFIER':
                    if value in self.keywords:
                        token_type = self.keywords[value]
                    else:
                        token_type = TokenType.IDENTIFIER
                    
                    token = Token(token_type, value, self.line, column)
                    tokens.append(token)
                
                # Procesar literales numéricos
                elif group_name in ('INTEGER', 'HEX_INTEGER'):
                    # Validar que el entero no sea demasiado grande
                    try:
                        if group_name == 'HEX_INTEGER':
                            int(value, 16)
                        else:
                            int(value)
                        token = Token(TokenType.INTEGER_LITERAL, value, self.line, column)
                        tokens.append(token)
                    except ValueError:
                        error_msg = f"Entero demasiado grande: {value}"
                        self.errors.append((error_msg, self.line, column))
                        token = Token(TokenType.ERROR, value, self.line, column)
                        tokens.append(token)
                
                elif group_name == 'FLOAT':
                    # Validar que el float no sea demasiado grande
                    try:
                        float(value)
                        token = Token(TokenType.FLOAT_LITERAL, value, self.line, column)
                        tokens.append(token)
                    except ValueError:
                        error_msg = f"Float demasiado grande o con formato incorrecto: {value}"
                        self.errors.append((error_msg, self.line, column))
                        token = Token(TokenType.ERROR, value, self.line, column)
                        tokens.append(token)
                
                # Procesar literales de cadena y carácter
                elif group_name == 'STRING':
                    # Validar la cadena
                    try:
                        # Verificar si hay secuencias de escape inválidas
                        processed_str = self._process_escapes(value)
                        token = Token(TokenType.STRING_LITERAL, processed_str, self.line, column)
                        tokens.append(token)
                    except ValueError as e:
                        error_msg = f"Error en cadena: {e}"
                        self.errors.append((error_msg, self.line, column))
                        token = Token(TokenType.ERROR, value, self.line, column)
                        tokens.append(token)
                
                elif group_name == 'CHAR':
                    # Validar el carácter
                    try:
                        # El valor debe ser de la forma 'x' donde x es un solo carácter
                        # o una secuencia de escape como '\n'
                        processed_char = self._process_escapes(value)
                        # Verificar longitud válida para un carácter
                        if len(processed_char) != 3:  # 'x' tiene longitud 3 ('', 'x', '')
                            raise ValueError(f"Carácter no válido: {value}")
                        token = Token(TokenType.CHAR_LITERAL, processed_char, self.line, column)
                        tokens.append(token)
                    except ValueError as e:
                        error_msg = f"Error en carácter: {e}"
                        self.errors.append((error_msg, self.line, column))
                        token = Token(TokenType.ERROR, value, self.line, column)
                        tokens.append(token)
                
                # Procesar todos los demás tokens
                else:
                    token_type = getattr(TokenType, group_name, None)
                    if token_type:
                        token = Token(token_type, value, self.line, column)
                        tokens.append(token)
                
                # Actualizar columna para el siguiente token
                self.column += len(value)
            
            else:
                # Manejar carácter no reconocido
                char = text[self.pos]
                error_msg = f"Carácter no reconocido: '{char}'"
                self.errors.append((error_msg, self.line, self.column))
                tokens.append(Token(TokenType.ERROR, char, self.line, self.column))
                self.pos += 1
                self.column += 1
        
        # Agregar token de fin de archivo
        tokens.append(Token(TokenType.EOF, "", self.line, self.column))
        return tokens
    
    def _process_escapes(self, value):
        """
        Procesa las secuencias de escape en cadenas y caracteres.
        Devuelve el valor procesado o lanza ValueError si hay un error.
        """
        # Sólo realizar verificaciones básicas por ahora
        if value.startswith('"') and value.endswith('"'):
            content = value[1:-1]
        elif value.startswith("'") and value.endswith("'"):
            content = value[1:-1]
        else:
            return value
        
        # Verificar secuencias de escape
        i = 0
        result = ""
        while i < len(content):
            if content[i] == '\\' and i + 1 < len(content):
                escape_char = content[i+1]
                if escape_char in 'ntrfvabef"\'\\':
                    # Secuencias de escape normales
                    result += content[i:i+2]
                    i += 2
                elif escape_char == 'x' and i + 3 < len(content) and all(c in '0123456789abcdefABCDEF' for c in content[i+2:i+4]):
                    # Secuencia \xHH (hex)
                    try:
                        hex_val = int(content[i+2:i+4], 16)
                        if hex_val > 255:
                            raise ValueError(f"Valor hexadecimal fuera de rango: \\x{content[i+2:i+4]}")
                        result += content[i:i+4]
                        i += 4
                    except ValueError:
                        raise ValueError(f"Secuencia de escape hexadecimal inválida: \\x{content[i+2:i+4]}")
                else:
                    raise ValueError(f"Secuencia de escape desconocida: \\{escape_char}")
            else:
                result += content[i]
                i += 1
        
        # Reconstruir el literal con comillas
        if value.startswith('"'):
            return f'"{result}"'
        else:
            return f"'{result}'"

    def get_errors(self):
        """
        Devuelve la lista de errores encontrados durante el análisis léxico.
        """
        return self.errors