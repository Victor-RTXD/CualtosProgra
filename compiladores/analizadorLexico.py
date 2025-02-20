import re

# VICTOR EDUARDO MACIAS MACIAS

# Definir los tipos de tokens y sus expresiones regulares
TOKENS = [
    ("PALABRA_CLAVE", r"\b(if|else|while|for|return|int|float|char|void|double|switch|case|break|continue)\b"),
    ("NUMERO", r"\b\d+(\.\d+)?\b"),
    ("IDENTIFICADOR", r"\b[a-zA-Z_]\w*\b"),
    ("OPERADOR", r"[+\-*/=<>!&|]+"),
    ("PARENTESIS", r"[()]"),
    ("LLAVE", r"[{}]"),
    ("CORCHETE", r"[\[\]]"),
    ("PUNTO_Y_COMA", r";"),
    ("COMENTARIO", r"//.*|/\*[^*]*\*+(?:[^/*][^*]*\*+)*/"),
    ("ESPACIO", r"\s+"),
]

def analizador_lexico(codigo):
    tokens_encontrados = []
    while codigo:
        match = None
        for token_tipo, token_regex in TOKENS:
            regex = re.compile(token_regex)
            match = regex.match(codigo)
            if match:
                texto_encontrado = match.group(0)
                if token_tipo != "ESPACIO":  # Ignorar espacios
                    tokens_encontrados.append((token_tipo, texto_encontrado))
                codigo = codigo[len(texto_encontrado):]  # Avanzar en el código
                break
        if not match:
            raise ValueError(f"Token no reconocido: {codigo[:10]}")
    return tokens_encontrados

# Código de prueba en C
ejemplo_codigo = """
int main() {
    int a = 5;
    float b = 10.5;
    char nombre[30];
    if (a < b) {
        a = a + 1;
    }
    return 0;
}
"""

# Ejecutar el analizador
resultado = analizador_lexico(ejemplo_codigo)
for token in resultado:
    print(token)
