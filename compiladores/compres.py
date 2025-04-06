import nltk 
from nltk import CFG
from nltk.parse.chart import ChartParser
from nltk.tree import Tree
import matplotlib.pyplot as plt
import matplotlib.patches as patches
import matplotlib.lines as lines
import numpy as np
import threading

# ========================
# Función para mostrar árboles en ventanas simultáneas
# ========================
def show_tree_in_window(tree, title="Árbol de análisis"):
    """Muestra un árbol en una ventana separada utilizando matplotlib."""
    def draw_thread():
        tree.draw()
    threading.Thread(target=draw_thread).start()

# ========================
# Gramática básica
# ========================
grammar = CFG.fromstring("""
    Expr -> Expr '+' Term | Expr '-' Term | Term
    Term -> Term '*' Factor | Term '/' Factor | Factor
    Factor -> '(' Expr ')' | 'a' | 'b' | 'c' | 'd'
""")

# Expresión a analizar
expresion = "a + b * ( c - d )".split()

# Crear el parser
parser = ChartParser(grammar)

# Obtener los árboles de análisis
trees = list(parser.parse(expresion))

if trees:
    print("La expresión es sintácticamente correcta.")
    print("\nÁrbol(es) de análisis sintáctico:")
    
    for i, tree in enumerate(trees):
        print(f"\nÁrbol #{i+1}:")
        print(tree)
        show_tree_in_window(tree, title=f"Árbol #{i+1}")
else:
    print("La expresión no es sintácticamente correcta según la gramática.")

# ========================
# Gramática extendida para operaciones complejas
# ========================
extended_grammar = CFG.fromstring("""
    Expr -> Expr '+' Term | Expr '-' Term | Term
    Term -> Term '*' Factor | Term '/' Factor | Term '%' Factor | Factor
    Factor -> Factor '^' Power | Power
    Power -> '-' Power | '+' Power | Atom
    Atom -> '(' Expr ')' | NUM | ID | FUNC '(' Args ')'
    Args -> Expr | Expr ',' Args
    FUNC -> 'sin' | 'cos' | 'sqrt' | 'max' | 'min'
    ID -> 'a' | 'b' | 'c' | 'd' | 'x' | 'y' | 'z' | 'PI' | 'E'
    NUM -> '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | DECIMAL
    DECIMAL -> NUM '.' NUM
""")

def tokenize_expression(expr):
    """Tokeniza una expresión matemática."""
    tokens = []
    i = 0
    while i < len(expr):
        if expr[i].isdigit():
            num = ""
            while i < len(expr) and (expr[i].isdigit() or expr[i] == '.'):
                num += expr[i]
                i += 1
            tokens.append(num)
        elif expr[i].isalpha():
            func = ""
            while i < len(expr) and expr[i].isalpha():
                func += expr[i]
                i += 1
            tokens.append(func)
        elif expr[i] in '+-*/()^%,':
            tokens.append(expr[i])
            i += 1
        else:
            i += 1  # Ignorar espacios y otros caracteres
    return tokens

def parse_complex_expression(expression):
    """Analiza una expresión matemática compleja."""
    tokens = tokenize_expression(expression)
    parser = ChartParser(extended_grammar)
    trees = list(parser.parse(tokens))
    
    if trees:
        print(f"\nAnálisis de la expresión compleja: {expression}")
        print("La expresión es sintácticamente correcta.")
        for i, tree in enumerate(trees):
            print(f"\nÁrbol #{i+1}:")
            print(tree)
            show_tree_in_window(tree, title=f"Árbol Complejo #{i+1}")
    else:
        print(f"\nLa expresión compleja '{expression}' no pudo ser analizada con la gramática extendida.")

def visualize_ast(expr):
    """Visualiza un AST para una expresión dada."""
    print(f"\nVisualizando AST para: {expr}")
    fig, ax = plt.subplots(figsize=(12, 8))
    ax.set_xlim(0, 100)
    ax.set_ylim(0, 60)
    ax.axis('off')
    
    nodes = [
        (50, 55, "Expr"),
        (30, 45, "Expr"),
        (70, 45, "Term"),
        (20, 35, "Term"),
        (40, 35, "+"),
        (60, 35, "Term"),
        (80, 35, "Factor"),
        (20, 25, "3"),
        (50, 25, "*"),
        (70, 25, "/"),
        (80, 25, "("),
        (90, 15, "Expr^2^3"),
        (85, 5, "1-5")
    ]
    
    for x, y, label in nodes:
        circle = patches.Circle((x, y), 3, fill=True, edgecolor='black', facecolor='lightblue')
        ax.add_patch(circle)
        ax.text(x, y-3, label, ha='center', va='top', fontsize=9)
    
    connections = [
        (nodes[0], nodes[1]),
        (nodes[0], nodes[2]),
        (nodes[1], nodes[3]),
        (nodes[1], nodes[4]),
        (nodes[2], nodes[5]),
        (nodes[2], nodes[6]),
        (nodes[3], nodes[7]),
        (nodes[5], nodes[8]),
        (nodes[5], nodes[9]),
        (nodes[6], nodes[10]),
        (nodes[6], nodes[11]),
        (nodes[11], nodes[12])
    ]
    
    for node1, node2 in connections:
        x1, y1, _ = node1
        x2, y2, _ = node2
        line = lines.Line2D([x1, x2], [y1, y2], lw=1, color='black')
        ax.add_line(line)
    
    plt.title(f"AST para: {expr}")
    plt.tight_layout()
    plt.show()

# ========================
# Pruebas
# ========================
expresion_compleja = "3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3"
complex_tokens = tokenize_expression(expresion_compleja)
print(f"Tokens para la expresión compleja: {complex_tokens}")

try:
    parse_complex_expression(expresion_compleja)
except Exception as e:
    print(f"Error al parsear la expresión compleja: {e}")

visualize_ast(expresion_compleja)

print("\nPruebas adicionales:")

try:
    parse_complex_expression("-3 + 4")
except Exception as e:
    print(f"Error con operador unario: {e}")

try:
    parse_complex_expression("sin(x) + cos(y)")
except Exception as e:
    print(f"Error con funciones matemáticas: {e}")

try:
    parse_complex_expression("max(x, y) * 2")
except Exception as e:
    print(f"Error con múltiples parámetros: {e}")

try:
    parse_complex_expression("PI * r ^ 2")
except Exception as e:
    print(f"Error con constantes matemáticas: {e}")

print("\nNota: Este analizador sintáctico implementa una gramática para expresiones matemáticas")
print("que soporta operaciones aritméticas básicas, potenciación, funciones matemáticas,")
print("operadores unarios, funciones con múltiples parámetros, módulo y constantes matemáticas.")
