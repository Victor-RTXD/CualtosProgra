package calculadora;

import java.util.Scanner;

public class CargaElectrica {
    Scanner sc = new Scanner(System.in);
    double exponente1;
    float q2;
    float q1;
    float r;
    double k = 9 * Math.pow(10, 9);
    short i;
    double f1;
    double f2;

    public void procedimiento(float q1, float q2, float r, double exponente) {
        System.out.println("ingresa q1");
        this.q1 = sc.nextFloat();
        System.out.println("ingresa q2");
        this.q2 = sc.nextFloat();
        System.out.println("ingresa el exponente: ");
        exponente1 = sc.nextShort();
        exponente1 = Math.pow(10, -exponente1);
        System.out.println("ingresa distancia: ");
        this.r = sc.nextFloat();
    }
    public void leyCoulomb() {
        System.out.println("favor de no poner numeros negativos");

        do {
            procedimiento(q1, q2, r, exponente1);
            f1 = (k * ((q1 * exponente1)  * (q2 * exponente1)))/(r*r);
            System.out.println("f1: " + f1);

            System.out.println("oprime 1 para ingresar otra fuerza, otro numero para acabar ");
            i = sc.nextShort();

            if (i == 1) {
                procedimiento(q1, q2, r, exponente1);
                f2 = (k * ((q1 * exponente1)  * (q2 * exponente1)))/(r*r);
                System.out.println("f2: " + f2);
                
                System.out.println("Magnitud resultante: " + Math.sqrt((f1 * f1) + (f2 * f2)));
                k = Math.atan(f1 / f2);
                System.out.println("grados con f1 sobre f2: " + Math.toDegrees(k));
                k = Math.atan(f2 / f1);
                System.out.println("grados con f2 sobre f1: " + Math.toDegrees(k));

                System.out.println("oprime 0 para salir o 1 para intentar de nuevo");
                i = sc.nextShort();
            }
        } while (i != 0);
    }
}