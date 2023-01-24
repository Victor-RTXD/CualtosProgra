package act1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int tCodigo, tCalificacion;
        String tMateria;
        Scanner sc = new Scanner(System.in);
        Clase cualtos[]= new Clase[2];
        Queue cola = new Queue();
        short i;

        for (i = 0; i < cualtos.length; i++) {
            System.out.println("ingresa la calificacion: ");
            tCalificacion = sc.nextInt();

            System.out.println("ingresa el codigo: ");
            tCodigo = sc.nextInt();

            System.out.println("ingresa la materia: ");
            tMateria = sc.next();

            cualtos[i] = new Clase(tCalificacion, tCodigo, tMateria);
        }
        
        for (i = 0; i < cualtos.length; i++) {
            System.out.println("materia: " + cualtos[i].materia + "\n" + "calificacion: " + cualtos[i].calificacion +  "\n " + "codigo: " + cualtos[i].codigo);
        }
        
        
        while (true) {
            System.out.println("1, 2, 3");
            i = sc.nextShort();
            if (i == 1) {
                System.out.println("Ingresa elemento: ");
                i = sc.nextShort();
                cola.enQueue(i);
            } else if (i == 2) {
                cola.deQueue();
            } else {
                cola.print();
            }
        }
    }
}



