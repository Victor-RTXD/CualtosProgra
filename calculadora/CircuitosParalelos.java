package calculadora;

import java.util.Scanner;

public class CircuitosParalelos {
    Scanner sc = new Scanner(System.in);
    short i = 0;
    short aux = 0;
    double auxiliarResistencias = 0;
    float ampere;
    float r1;
    float r2;

    /**
     * Descripcion: 
     */
    public void circuitosParalelos() {
        System.out.println("selecciona lo que deseas saber, \n 1: RT \n 2: corriente");
        i = sc.nextShort();

        switch (i) {
            case 1:
                double resistencia[] = new double[5];
                resistencia[0] = 0;
                resistencia[1] = 0;
                resistencia[2] = 0;
                resistencia[3] = 0;
                resistencia[4] = 0;
                i = 0;

                System.out.println("ingresa las resistencias, maximo 5");

                for (i = 0; i < resistencia.length; i++) {
                    System.out.println("ingresa resistencia");
                    resistencia[i] = sc.nextDouble();
                    auxiliarResistencias += (1 / resistencia[i]);

                    System.out.println("ingresa 0 para salir o 1 para continuar");
                    aux = sc.nextShort();
                    if (aux == 0) {
                        break;
                    }
                }

                System.out.println((1 / auxiliarResistencias));
            break;
            case 2:
                System.out.println("1: corriente total \n 2: divisor de corriente");
                i = sc.nextShort();

                if (i == 1) {
                    while (i != 0) {
                        ampere += sc.nextFloat();
                        System.out.println("ingresar otra corriente? ");
                        i = sc.nextShort();
                    }

                    System.out.println("la corriente total es: " + ampere);
                } else if (i == 2) {
                    System.out.println("1: 2 resistores \n 2: n resistores");
                    i = sc.nextShort();

                    if (i == 1) {//ocupa revision por si acaso
                        System.out.println("ingresa corriente IT");
                        ampere = sc.nextFloat();
                        System.out.println("ingresa una resistencia Ry");
                        r2 = sc.nextFloat();
                        System.out.println("ingresa una resistencia Rx");
                        r1 = sc.nextFloat();

                        ampere = ((ampere * r2) / (r1 + r2));
                        System.out.println(ampere);
                    } else {//funciona
                        System.out.println("ingresa corriente IT");
                        ampere = sc.nextFloat();
                        System.out.println("ingresa resistencia total");
                        r1 = sc.nextFloat();
                        System.out.println("ingresa resistencia Rx");
                        r2 = sc.nextFloat();

                        ampere = (ampere * r1 / r2);
                        System.out.println("la corriente es: " + ampere);
                    }
                }
            break;
            default:
            System.out.println("intentalo otra vez, regresando al menu");
            break;
        }
    }
}