#!/usr/bin/env python3
import sys
from lexer import Lexer
from parser import Parser

def print_usage():
    print("Uso: python main.py <archivo_fuente.c>")
    print("Ejemplo: python main.py ejemplo.c")

def main():
    # Verificar argumentos
    if len(sys.argv) != 2:
        print_usage()
        return 1

    # Obtener el nombre del archivo fuente
    source_file = sys.argv[1]

    try:
        # Leer el archivo fuente
        with open(source_file, 'r', encoding='utf-8') as file:
            source_code = file.read()

        print(f"Analizando el archivo: {source_file}")
        print("-" * 50)

        # Realizar análisis léxico
        lexer = Lexer()
        tokens = lexer.tokenize(source_code)

        # Mostrar los tokens encontrados
        # print("\nTokens encontrados:")
        # print("-" * 50)
        # for token in tokens:
        #     if token.type.name != 'EOF':
        #         print(f"Línea {token.line}, Col {token.column}: {token.type.name} - '{token.value}'")

        # Mostrar errores léxicos si hay alguno
        errors = lexer.get_errors()
        if errors:
            print("\n*****Errores léxicos:")
            print("-" * 50)
            for error_msg, line, column in errors:
                print(f"Línea {line}, Col {column}: {error_msg}")
            # return 1

        print("\nAnálisis léxico completado con éxito.")
        
        # Realizar análisis sintáctico
        parser = Parser(tokens)
        if parser.parse():
            print("\nAnálisis sintáctico completado con éxito.")
        else:
            print("\n*****Errores sintácticos:")
            print("-" * 50)
            for error_msg, line, column in parser.get_errors():
                print(f"Línea {line}, Col {column}: {error_msg}")
            # return 1

        return 0

    except FileNotFoundError:
        print(f"Error: No se pudo abrir el archivo '{source_file}'")
        return 1
    except Exception as e:
        print(f"Error inesperado: {e}")
        return 1

if __name__ == "__main__":
    sys.exit(main())