package Pila;

import java.util.Scanner;

public class Stack {
    Scanner sc = new Scanner(System.in);
    public short pointer = -1;

    class Usuario {
        String id;
        String constraseña;
    }
    Usuario stack[] = new Usuario[5];

    void pushElement() {
        if (pointer < 5) {
            if (pointer == -1)
            pointer = 0;

            stack[pointer] = new Usuario();
            System.out.println("ingresa el id: ");
            stack[pointer].id = sc.nextLine();
            System.out.println("ingresa la contraseña: ");
            stack[pointer].constraseña = sc.nextLine();
            System.out.println("se inserto el elemento " + stack[pointer].id);
            pointer++;
        } else {
            System.out.println("esta lleno, borra algo");
        }
    }

    void popElement() {
        if (pointer == -1) {
            System.out.println("el stack esta vacio");
        } else {
        pointer--;
        System.out.println("se eliminara id: " + stack[pointer].id + " contraseña " + stack[pointer].constraseña);
        stack[pointer].constraseña = "";
        stack[pointer].id = "";
        }
    }

    void mostrar() {
        for (int i = 0; i < pointer; i++) {
            System.out.println("id " + stack[i].id + " contraseña: " + stack[i].constraseña);
            System.out.println("___________");
        }
    }
}
