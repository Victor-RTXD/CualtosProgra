package Practica3;

import java.util.Scanner;

public class Practica3Pilas {
    int tope;
    Scanner sc = new Scanner(System.in);
    public static int max = 10;
    usuario pila[] = new usuario[max];

    class usuario {
        String id;
        String constraseña;
    }

    void push(int tope, int max) {
        if (tope == max) {
            System.out.println("la pila esta llena");
        } else {
            pila[tope] = new usuario();
            pila[tope].id = sc.nextLine();
            pila[tope].constraseña = sc.nextLine();
            System.out.println("se inserto un elemento");
            this.tope++;
        }
    }

    void mostrar(int max) {
        for (int i = 0; i < max; i++) {
            System.out.println("id " + pila[i].id + " contraseña: " + pila[i].constraseña);
            System.out.println("___________");
        }
    }

    public static void main(String[] args) {
        int tope = 0;
        Practica3Pilas pila = new Practica3Pilas();
        pila.push(tope, max);
        pila.mostrar(max);
    }
}
