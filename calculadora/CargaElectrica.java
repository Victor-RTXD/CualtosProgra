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

    public void leyCoulomb() {
        System.out.println("favor de no poner numeros negativos");

        System.out.println("ingresa q1");
        q1 = sc.nextFloat();
        System.out.println("ingresa el exponente: ");
        exponente1 = sc.nextShort();
        exponente1 = Math.pow(10, -exponente1);
        System.out.println("ingresa q2");
        q2 = sc.nextFloat();
        System.out.println("ingresa distancia: ");
        r = sc.nextFloat();
        f1 = (k * ((q1 * exponente1)  * (q2 * exponente1)))/(r*r);

        System.out.println(f1);

        System.out.println("quieres saber conocer otra fuerza?  Y/n");

        System.out.println("ingresa q1");
        q1 = sc.nextFloat();
        System.out.println("ingresa el exponente: ");
        exponente1 = sc.nextShort();
        exponente1 = Math.pow(10, -exponente1);
        System.out.println("ingresa q2");
        q2 = sc.nextFloat();
        System.out.println("ingresa distancia: ");
        r = sc.nextFloat();
        f2 = (k * ((q1 * exponente1)  * (q2 * exponente1)))/(r*r);

        System.out.println(f2);

        System.out.println("quieres saber magnitud y direccion resultante?  Y/n");

        i = sc.nextShort();

        if (i == 1) {
            System.out.println("Magnitud resultante: " + Math.sqrt((f1 * f1) + (f2 * f2)));
            k = Math.atan(f1 / f2);
            System.out.println("grados con f1 sobre f2: " + Math.toDegrees(k));
            k = Math.atan(f2 / f1);
            System.out.println("grados con f2 sobre f1: " + Math.toDegrees(k));
        } 


    }
}
