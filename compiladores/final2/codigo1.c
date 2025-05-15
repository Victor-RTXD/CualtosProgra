int main() {
    int a = 10;
    float b = 3.14;
    char c = 'x';
    
    // Errores semánticos a detectar:
    z = a + b;      // Variable no declarada
    a = b + c;      // Tipos incompatibles
    c = a + b;      // Tipos incompatibles
    x = funcion_no_declarada();  // Función no declarada
    
    return 0;
}