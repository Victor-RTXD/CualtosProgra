package F;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Queue cola = new Queue();
        short i = 0, contador = 0;
        Scanner sc = new Scanner(System.in);

        
        do {
            System.out.println("\n1: insertar elementos max 5\n2: eliminar elemento\n3: mostrar elementos\n 0: salir");
            i = sc.nextShort();
            if (i == 1) {
                cola.enQueue();
            } else if (i == 2) {
                cola.deQueue();
            } else if (i == 3) {
                cola.print(contador);
                if (contador <= 5) {
                    contador++;
                }
            }
        } while (i != 0);
    }
}