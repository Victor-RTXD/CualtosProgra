package act1;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int tCodigo, tCalificacion;
        String tMateria;
        Scanner sc = new Scanner(System.in);
        Clase cualtos[]= new Clase[2];

        for (int i = 0; i < cualtos.length; i++) {
            System.out.println("ingresa la calificacion: ");
            tCalificacion = sc.nextInt();

            System.out.println("ingresa el codigo: ");
            tCodigo = sc.nextInt();

            System.out.println("ingresa la materia: ");
            tMateria = sc.next();

            cualtos[i] = new Clase(tCalificacion, tCodigo, tMateria);
        }
        
        for (int i = 0; i < cualtos.length; i++) {
            System.out.println("materia: " + cualtos[i].materia + "\n" + "calificacion: " + cualtos[i].calificacion +  "\n " + "codigo: " + cualtos[i].codigo);
        }
    }
}



