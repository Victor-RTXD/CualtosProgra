package Pila;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Stack pila = new Stack();
        short i;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\n 1: insertar elementos max 5\n2: eliminar elemento\n3: mostrar elementos\n 0: salir");
            i = sc.nextShort();
            if (i == 1) {
                pila.pushElement();
            } else if (i == 2) {
                pila.popElement();
            } else if (i == 3) {
                pila.mostrar();
            }
        } while (i != 0);
    }
}
