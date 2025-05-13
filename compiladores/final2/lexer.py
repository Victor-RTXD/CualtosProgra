# lexer.py
import re

class Token:
    def __init__(self, type_, value, line, column):
        self.type = type_
        self.value = value
        self.line = line
        self.column = column

    def __repr__(self):
        return f"Token({self.type}, {self.value}, Line={self.line}, Col={self.column})"

class LexerError(Exception):
    pass

class Lexer:
    def __init__(self, code):
        self.code = code
        self.tokens = []
        self.current_line = 1

    def tokenize(self):
        token_specification = [
            ('COMMENT',     r'//.*'),
            ('MCOMMENT',    r'/\*[\s\S]*?\*/'),
            ('PREPROCESSOR', r'#\s*[a-zA-Z_]+\s*[^ \n]*'),  # <-- Mueve esta línea arriba
            ('DEFINE',      r'#define'),
            ('KEYWORD',     r'\b(int|char|return|printf|main)\b'),
            ('HEX_STRING',  r'"(\\x[0-9A-Fa-f]{2})*"'),
            ('STRING',      r'"[^"\n]*"?'),
            ('NUMBER',      r'0[xX][0-9a-fA-F]+|\d+'),
            ('ID',          r'[A-Za-z_]\w*'),
            ('OP',          r'[\+\-\*/=]'),
            ('SEMICOLON',   r';'),
            ('COMMA',       r','),
            ('LPAREN',      r'\('),
            ('RPAREN',      r'\)'),
            ('LBRACE',      r'\{'),
            ('RBRACE',      r'\}'),
            ('NEWLINE',     r'\n'),
            ('SKIP',        r'[ \t]+'),
            ('MISMATCH',    r'.'),  # <-- Siempre al final
        ]


        tok_regex = '|'.join(f'(?P<{name}>{pattern})' for name, pattern in token_specification)
        get_token = re.compile(tok_regex).match

        line_num = 1
        line_start = 0
        pos = 0
        mo = get_token(self.code, pos)

        while mo:
            kind = mo.lastgroup
            value = mo.group()
            column = mo.start() - line_start

            if kind == 'NEWLINE':
                line_num += 1
                line_start = mo.end()
            elif kind == 'SKIP' or kind == 'COMMENT' or kind == 'MCOMMENT':
                pass
            elif kind == 'MISMATCH':
                raise LexerError(f'Caracter no válido {value!r} en línea {line_num}')
            else:
                if kind == 'STRING' and not value.endswith('"'):
                    raise LexerError(f'String sin cerrar en línea {line_num}')
                if kind == 'NUMBER' and len(value) > 30:
                    raise LexerError(f'Número demasiado grande en línea {line_num}: {value}')
                self.tokens.append(Token(kind, value, line_num, column))

            pos = mo.end()
            mo = get_token(self.code, pos)

        return self.tokens
