from lexer import tokenize
from parser import Parser
from semantic import semantic_analysis
from codegen import generate_code
import sys

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Uso: python main.py <archivo.c>")
        sys.exit(1)

    with open(sys.argv[1], "r", encoding="utf-8") as f:
        code = f.read()

    try:
        tokens = tokenize(code)
        parser = Parser(tokens)
        ast = parser.parse()
        semantic_analysis(ast)
        result = generate_code(ast)

        print("=== Código Intermedio ===")
        print(result)

    except Exception as e:
        print("Error:", e)
