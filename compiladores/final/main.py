# main.py
import sys
from lexer import tokenize
from parser import Parser
from semantic import semantic_analysis
from codegen import CodeGenerator

def main():
    if len(sys.argv) != 2:
        print("Uso: python main.py <archivo_fuente.c>")
        sys.exit(1)
    
    with open(sys.argv[1], 'r') as f:
        source_code = f.read()
    
    # Fase 1: Análisis Léxico
    try:
        tokens = tokenize(source_code)
        print("Tokens obtenidos:")
        for token in tokens:
            print(token)
    except Exception as e:
        print("Error en el análisis léxico:", e)
        sys.exit(1)
    
    # Fase 2: Análisis Sintáctico
    try:
        parser = Parser(tokens)
        ast = parser.parse()
        print("\nEl árbol de sintaxis abstracta (AST) fue generado exitosamente.")
    except Exception as e:
        print("Error en el análisis sintáctico:", e)
        sys.exit(1)
    
    # Fase 3: Análisis Semántico
    try:
        symbol_table = semantic_analysis(ast)
        print("\nAnálisis semántico completado. Tabla de símbolos:", symbol_table)
    except Exception as e:
        print("Error en el análisis semántico:", e)
        sys.exit(1)
    
    # Fase 4: Generación de Código Intermedio
    codegen = CodeGenerator()
    codegen.generate(ast)
    instructions = codegen.get_instructions()
    print("\nCódigo intermedio generado:")
    for instr in instructions:
        print(instr)

if __name__ == "__main__":
    main()
