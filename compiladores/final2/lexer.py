import re

# Definimos los tipos de tokens
TOKEN_SPECIFICATION = [
    ("COMMENT_MULTI", r"/\*[\s\S]*?\*/"),
    ("COMMENT_SINGLE", r"//.*"),
    ("STRING_LITERAL", r'"(\\.|[^"\\])*"'),
    ("CHAR_LITERAL", r"'(\\.|[^'\\])'"),
    ("HEX_NUMBER", r"0[xX][0-9a-fA-F]+"),
    ("FLOAT", r"\d+\.\d+"),                   # <-- Agregado
    ("NUMBER", r"\d+"),                       # <-- Renombrado, solo enteros
    ("DEFINE", r"#define"),
    ("IDENTIFIER", r"[A-Za-z_]\w*"),
    ("SYMBOL", r"[{}()\[\];,]"),
    ("OPERATOR", r"[=+\-*/%<>!&|^~]+"),
    ("WHITESPACE", r"\s+"),
    ("UNKNOWN", r"."),
]

# Compilamos el patrón general de tokens
token_regex = re.compile(
    "|".join(f"(?P<{name}>{pattern})" for name, pattern in TOKEN_SPECIFICATION)
)

# Clase Token
class Token:
    def __init__(self, type_, value, line, column):
        self.type = type_
        self.value = value
        self.line = line
        self.column = column

    def __repr__(self):
        return f"{self.type}({self.value}) at {self.line}:{self.column}"

# Función del lexer
def tokenize(code):
    tokens = []
    line_num = 1
    line_start = 0

    for match in token_regex.finditer(code):
        kind = match.lastgroup
        value = match.group()
        column = match.start() - line_start

        if kind == "WHITESPACE":
            line_num += value.count("\n")
            if "\n" in value:
                line_start = match.end()
            continue
        elif kind in ("COMMENT_SINGLE", "COMMENT_MULTI"):
            continue  # Ignorar comentarios
        elif kind == "UNKNOWN":
            raise SyntaxError(f"Token desconocido '{value}' en línea {line_num}, columna {column}")


        token = Token(kind, value, line_num, column)
        tokens.append(token)

    return tokens

# === Ejemplo de uso ===
if __name__ == "__main__":
    with open("codigo1.c", "r", encoding="utf-8") as f:
        code = f.read()

    try:
        tokens = tokenize(code)
        for token in tokens:
            print(token)
    except SyntaxError as e:
        print("Error léxico:", e)
