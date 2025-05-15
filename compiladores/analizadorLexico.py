import re

# VICTOR EDUARDO MACIAS MACIAS

# Definir los tipos de tokens y sus expresiones regulares
TOKENS = [
    ("PALABRA_CLAVE", r"\b(if|else|while|for|return|int|float|char|void|double|switch|case|break|continue|do|enum|extern|goto|static|sizeof|struct|typedef|union|unsigned|signed|const|volatile|default)\b"),
    ("NUMERO", r"\b\d+(\.\d+)?\b"),
    ("CADENA", r'"(.*?)"'),  # Detecta cadenas de caracteres entre comillas dobles
    ("CARACTER", r"'(.)'"),  # Detecta caracteres entre comillas simples
    ("IDENTIFICADOR", r"\b[a-zA-Z_]\w*\b"),
    ("OPERADOR", r"(\+\+|--|\+=|-=|\*=|/=|==|!=|<=|>=|&&|\|\||&|\||\^|~|<<|>>|%|[+\-*/=<>!])"),
    ("PARENTESIS", r"[()]"),
    ("LLAVE", r"[{}]"),
    ("CORCHETE", r"[\[\]]"),
    ("PUNTO_Y_COMA", r";"),
    ("COMA", r","),
    ("PUNTO", r"\."),
    ("FLECHA", r"->"),
    ("ALMOHADILLA", r"#"),  # Para preprocesadores como #include
    ("DOS_PUNTOS", r":"),  # Detectamos ":" para verificar si es un error
    ("COMENTARIO", r"//.*|/\*[^*]*\*+(?:[^/*][^*]*\*+)*/"),
    ("ESPACIO", r"\s+"),  # No se guarda, pero ayuda en la detección de tokens
]

def analizador_lexico(codigo):
    tokens_encontrados = []
    lineas = codigo.split("\n")  # Dividir el código en líneas
    errores = []

    for numero_linea, linea in enumerate(lineas, start=1):
        columna = 1
        tiene_signo_pregunta = False  # Para detectar operador ternario en la línea

        while linea:
            match = None
            for token_tipo, token_regex in TOKENS:
                regex = re.compile(token_regex)
                match = regex.match(linea)
                if match:
                    texto_encontrado = match.group(0)
                    if token_tipo != "ESPACIO":  # Ignorar espacios
                        tokens_encontrados.append((token_tipo, texto_encontrado, numero_linea, columna))

                        # Verificar si hay "?" antes de ":" en la misma línea
                        if token_tipo == "OPERADOR" and texto_encontrado == "?":
                            tiene_signo_pregunta = True
                        elif token_tipo == "DOS_PUNTOS" and not tiene_signo_pregunta:
                            errores.append((numero_linea, columna, "Uso incorrecto de ':' en la línea."))

                    columna += len(texto_encontrado.split())  # Contar palabras, no caracteres
                    linea = linea[len(texto_encontrado):]  # Avanzar en la línea
                    break
            
            if not match:  # Si no se encontró ningún token válido
                errores.append((numero_linea, columna, f"Símbolo '{linea[0]}' no reconocido."))
                linea = linea[1:]  # Saltar el carácter inválido
                columna += 1

    return tokens_encontrados, errores

# Código de prueba en C con error de ":" mal usado
ejemplo_codigo = """
#include <stdio.h>
int main() {
    int a : 5;  // debe dar error
    float b = 10.5;
    char nombre[30] = "Victor";
    if (a < b) {
        a += 1;
    }
c = (a < b) ? a : b;
    return 0;
    @ // error esperado
}
"""

# Ejecutar el analizador
resultado, errores = analizador_lexico(ejemplo_codigo)

# Imprimir tokens encontrados
print("Tokens encontrados:")
for token in resultado:
    print(token)

# Imprimir errores encontrados
if errores:
    print("\nErrores encontrados:")
    for error in errores:
        print(f"Error en línea {error[0]}, columna {error[1]}: {error[2]}")
else:
    print("\nNo se encontraron errores.")
