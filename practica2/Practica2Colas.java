package practica2;

import java.util.Scanner;

public class Practica2Colas {
    class Usuario {
        String id;
        String contraseña;
    }

    Usuario cola[] = new Usuario[5];
    Scanner sc = new Scanner(System.in);

    void insertar(int fin, int max) {
        if (fin < max) {
            cola[fin] = new Usuario();
            System.out.println("dame tu id: ");
            cola[fin].id = sc.nextLine();
            System.out.println("dame tu contraseña: ");
            cola[fin].contraseña = sc.nextLine();
            System.out.println("id: " + cola[fin].id + " contraseña: " + cola[fin].contraseña);
        } else {
            System.out.println("cola llena");
        }
    }
    
    void mostrar(int frente, int fin) {
        if (fin != 0) {
            for (int i = 0; i < fin; i++) {
                System.out.println("id " + cola[i].id + " contraseña: " + cola[i].contraseña);
                System.out.println("___________");
            }
        } else {
            System.out.println("la esctructura esta vacia");
        }
    }

    void eliminar(int frente, int fin) {
        if (frente == fin) {
            System.out.println("no hay datos");
        } else {
            System.out.println("se eliminara el ultimo dato ");
            cola[frente].id = "";
            cola[frente].contraseña = "";
        }
    }

    public static void main(String[] args) {
        int Frente = 0, MAX = 5, Final = 0;
        Practica2Colas colas = new Practica2Colas();
        colas.insertar(Final, MAX);
        Final++;
        colas.insertar(Final, MAX);
        Final++;
        //colas.extrer(frente, final)
        //frente++

        colas.insertar(Final, MAX);
        Final++;
        colas.mostrar(Frente, Final);
        colas.eliminar(Frente, Final);
        Frente++;
        colas.mostrar(Frente, Final);
    }
}