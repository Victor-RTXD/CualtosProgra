package calculadora;

import java.util.Scanner;

public class CampoElectrico {
    short i;
    Scanner sc = new Scanner(System.in);
    double k = (9 * Math.pow(10, 9));
    double exponente;
    float q1;
    float r;

    public double caluclo() {
        System.out.println("ingresa la carga ");
                q1 = sc.nextFloat();
                System.out.println("ingresa distancia en metros ");
                r = sc.nextFloat();
                System.out.println("ingresa exponente positivamente ");
                exponente = sc.nextDouble();
                exponente = Math.pow(10, -exponente);

                return (k * q1 * exponente) / (r*r);
    }

    void campoElectrico() {
        do {
            System.out.println("oprime 0 para salir");
            System.out.println(caluclo());
        } while (i != 0);
    }
}
