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
                if (contador < 5) {
                    cola.enQueue();
                    contador++;
                } else {
                    System.out.println("se pasa");
                }
            } else if (i == 2) {
                cola.deQueue();
                contador--;
            } else if (i == 3) {
                if (contador <= 5) {
                    cola.print(contador);
                }
            }
        } while (i != 0);
    }
}