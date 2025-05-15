#!/usr/bin/env python3
import sys
from lexer import Lexer
from parser import Parser, ParserError
from semantic_analyzer import SemanticAnalyzer

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
        
        # Mostrar errores léxicos si hay alguno
        errors = lexer.get_errors()
        if errors:
            print("\nErrores léxicos:")
            print("-" * 50)
            for error_msg, line, column in errors:
                print(f"Línea {line}, Col {column}: {error_msg}")
            # return 1
        
        print("\nAnálisis léxico completado con éxito.")

        # Realizar análisis sintáctico
        print("\nIniciando análisis sintáctico...")
        parser = Parser(tokens)
        parser_errors = parser.parse()

        if parser_errors:
            print("\nErrores sintácticos:")
            print("-" * 50)
            for error in parser_errors:
                print(error)
            return 1

        print("\nAnálisis sintáctico completado con éxito.")
        
        # Realizar análisis semántico
        print("\nIniciando análisis semántico...")
        ast = parser.program()  # Obtener el AST generado
        semantic_analyzer = SemanticAnalyzer()
        semantic_errors = semantic_analyzer.analyze(ast)
        
        if semantic_errors:
            print("\nErrores semánticos:")
            print("-" * 50)
            for error in semantic_errors:
                print(error)
            return 1
        
        print("\nAnálisis semántico completado con éxito.")
        
        return 0
        
    except FileNotFoundError:
        print(f"Error: No se pudo abrir el archivo '{source_file}'")
        return 1
    except Exception as e:
        print(f"Error inesperado: {e}")
        return 1

if __name__ == "__main__":
    sys.exit(main())