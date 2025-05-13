# lexer.py
import re

# Especificación de tokens: se incluyen números, identificadores, operadores y símbolos.
TOKEN_SPECIFICATION = [
    ("NUMBER",    r'\d+(\.\d*)?'),   # Números enteros o decimales
    ("ID",        r'[A-Za-z_]\w*'),   # Identificadores y palabras clave
    ("PLUS",      r'\+'),
    ("MINUS",     r'-'),
    ("TIMES",     r'\*'),
    ("DIVIDE",    r'/'),
    ("EQUALS",    r'='),
    ("SEMICOLON", r';'),
    ("LPAREN",    r'\('),
    ("RPAREN",    r'\)'),
    ("SKIP",      r'[ \t]+'),        # Espacios y tabulaciones se ignoran
    ("NEWLINE",   r'\n'),
    ("MISMATCH",  r'.'),             # Cualquier carácter inesperado
]

TOKEN_REGEX = '|'.join('(?P<%s>%s)' % pair for pair in TOKEN_SPECIFICATION)

class Token:
    def __init__(self, type, value, position):
        self.type = type
        self.value = value
        self.position = position  # (línea, columna)

    def __repr__(self):
        return f'Token({self.type}, {self.value}, Pos: {self.position})'

def tokenize(code):
    tokens = []
    line_num = 1
    line_start = 0
    for mo in re.finditer(TOKEN_REGEX, code):
        kind = mo.lastgroup
        value = mo.group()
        col = mo.start() - line_start
        if kind == "NUMBER":
            value = int(value)
            tokens.append(Token("NUMBER", value, (line_num, col)))
        elif kind == "ID":
            tokens.append(Token("ID", value, (line_num, col)))
        elif kind in ("PLUS", "MINUS", "TIMES", "DIVIDE", "EQUALS", "SEMICOLON", "LPAREN", "RPAREN"):
            tokens.append(Token(kind, value, (line_num, col)))
        elif kind == "NEWLINE":
            line_num += 1
            line_start = mo.end()
        elif kind == "SKIP":
            continue
        elif kind == "MISMATCH":
            raise RuntimeError(f'Carácter inesperado {value!r} en línea {line_num}, columna {col}')
    return tokens
