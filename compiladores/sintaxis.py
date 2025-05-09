import nltk
from nltk import CFG

grammar = CFG.fromstring("""
    Expr -> Expr '+' Term | Expr '-' Term | Term
    Term -> Term '*' Factor | Factor
    Factor -> '(' Expr ')' | 'a' | 'b' | 'c' | 'd'
""")

parser = nltk.ChartParser(grammar)

expression = ['a', '+', 'b', '*', '(', 'c', '-', 'd', ')']

for tree in parser.parse(expression):
    print(tree)
    tree.pretty_print()


"""
Usar herramientas adecuadas para el análisis léxico y sintáctico
Si el objetivo es procesar código más avanzado, lo mejor es apoyarse en herramientas como lex y yacc (o sus versiones más modernas, Flex y Bison). También hay opciones como ANTLR si se busca una solución más flexible y en otros lenguajes.

Soporte para números grandes
En el Código 1, la variable x tiene un valor tan grande que probablemente cause errores en compilación. Para manejar esto, lo ideal es usar unsigned long long o directamente bibliotecas como GMP (#include <gmp.h>) si se necesitan números aún más grandes.

Arreglar errores en los defines y cadenas
En el Código 2, hay problemas con #define y una cadena mal cerrada. Esto haría que el código ni siquiera compile correctamente. Hay que asegurarse de cerrar correctamente las cadenas y de que los #define no sean demasiado largos o estén escritos de forma incorrecta.

Soporte para más tipos de datos
Si se quiere ampliar el análisis a más tipos de datos, hay que considerar float, double, struct, enum, arreglos y punteros. También es importante verificar límites de tamaño y conversiones.

Manejo de caracteres fuera del rango ASCII
Si el código va a trabajar con caracteres especiales, lo mejor es usar wchar_t y wprintf, o convertir todo a UTF-8 para evitar problemas.

Mejor manejo de comentarios
Un problema común en los analizadores es no detectar correctamente comentarios sin cerrar (/* ...). Es importante asegurarse de que el código reconozca bien // y /* ... */ para evitar errores.
"""