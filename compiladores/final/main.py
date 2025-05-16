#!/usr/bin/env python3
import sys
from lexer import Lexer
from parser import Parser, ParserError
from semantic_analyzer import SemanticAnalyzer
from tac_generator import ThreeAddressCodeGenerator  # Nuevo módulo

def print_ast(node, level=0, prefix="", is_last=True):
    """Imprime el AST de forma gráfica en la terminal"""
    # Determinar el prefijo para este nivel
    connector = "└── " if is_last else "├── "
    
    # Mostrar información básica del nodo
    node_type = node.__class__.__name__
    print(prefix + connector + node_type, end="")
    
    # Añadir detalles específicos si existen
    if hasattr(node, 'name') and node.name:
        print(f" (name='{node.name}')", end="")
    if hasattr(node, 'value') and node.value is not None:
        print(f" (value={node.value})", end="")
    if hasattr(node, 'op') and node.op:
        print(f" (op={node.op})", end="")
    print()  # Salto de línea final
    
    # Preparar prefijo para hijos
    new_prefix = prefix + ("    " if is_last else "│   ")
    
    # Obtener hijos dinámicamente (para cualquier estructura de AST)
    children = []
    for attr in dir(node):
        if not attr.startswith('_') and attr not in ['coord', 'attributes']:  # Ignorar atributos especiales
            attr_value = getattr(node, attr)
            if isinstance(attr_value, (list, tuple)) and all(hasattr(c, '__class__') for c in attr_value):
                children.extend(attr_value)
            elif hasattr(attr_value, '__class__') and attr_value.__class__.__module__ != 'builtins':
                children.append(attr_value)
    
    # Imprimir hijos
    for i, child in enumerate(children):
        print_ast(child, level + 1, new_prefix, i == len(children) - 1)

def print_usage():
    print("Uso: python main.py <archivo_fuente.c>")
    print("Ejemplo: python main.py ejemplo.c")


def main():
    if len(sys.argv) != 2:
        print_usage()
        return 1
    
    source_file = sys.argv[1]
    output_tac_file = source_file.replace('.c', '.tac')  # Archivo de salida para el código TAC
    
    try:
        with open(source_file, 'r', encoding='utf-8') as file:
            source_code = file.read()
        
        print(f"Analizando el archivo: {source_file}")
        print("-" * 50)
        
        # Análisis léxico
        lexer = Lexer()
        tokens = lexer.tokenize(source_code)
        
        errors = lexer.get_errors()
        if errors:
            print("\nErrores léxicos:")
            print("-" * 50)
            for error_msg, line, column in errors:
                print(f"Línea {line}, Col {column}: {error_msg}")
            return 1
        
        print("\nAnálisis léxico completado con éxito.")

        # Análisis sintáctico
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
        
        # Obtener el AST
        ast = parser.program()
        
        # Nuevo: Visualización del AST para cualquier archivo
        print("\nÁrbol de Sintaxis Abstracta (AST):")
        print("-" * 50)
        print_ast(ast)
        print("-" * 50)
                
        # Análisis semántico
        print("\nIniciando análisis semántico...")
        semantic_analyzer = SemanticAnalyzer()
        semantic_errors = semantic_analyzer.analyze(ast)
        
        if semantic_errors:
            print("\nErrores semánticos:")
            print("-" * 50)
            for error in semantic_errors:
                print(error)
            return 1
        
        print("\nAnálisis semántico completado con éxito.")

        # Generación de código de tres direcciones (TAC)
        print("\nGenerando código de tres direcciones...")
        tac_generator = ThreeAddressCodeGenerator()
        tac_code = tac_generator.generate(ast)  # Genera el código TAC desde el AST
        
        # Escribir el código TAC en un archivo
        with open(output_tac_file, 'w', encoding='utf-8') as f:
            f.write(str(tac_code))
        
        print(f"\nCódigo de tres direcciones generado en: {output_tac_file}")
        print("-" * 50)
        print(tac_code)  # Mostrar en consola también
        
        return 0
        
    except FileNotFoundError:
        print(f"Error: No se pudo abrir el archivo '{source_file}'")
        return 1
    except Exception as e:
        print(f"Error inesperado: {e}")
        return 1

if __name__ == "__main__":
    sys.exit(main())