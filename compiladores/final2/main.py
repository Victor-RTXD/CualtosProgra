# main.py
from lexer import Lexer
from parser import Parser
from semantic import SemanticAnalyzer
from codegen import CodeGenerator

def run_compiler(source_code):
    print("🔍 Análisis léxico...")
    lexer = Lexer(source_code)
    tokens = lexer.tokenize()
    for token in tokens:
        print(token)

    print("\n🧱 Análisis sintáctico...")
    parser = Parser(tokens)
    ast = parser.parse()
    
    print("\n🔬 Análisis semántico...")
    semantic = SemanticAnalyzer()
    semantic.analyze(ast)

    print("\n⚙️ Generación de código intermedio...")
    generator = CodeGenerator()
    code = generator.generate(ast)

    print("\n🎯 Código Intermedio:")
    for line in code:
        print(line)

if __name__ == "__main__":
    with open("sample_code.c", "r", encoding="utf-8") as f:
        source = f.read()
        run_compiler(source)
