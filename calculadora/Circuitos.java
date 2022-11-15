package calculadora;

import java.util.Scanner;

public class Circuitos {
    Scanner sc = new Scanner(System.in);
    short aux;
    double a = 0;
    double b = 0;

    public void dialogoOhm(String objetivo, String com1, String com11, String com2, String com22, String com3, String com33) {
        System.out.println("saber " + objetivo + " con: \n");
                System.out.println("1: " + com1+ " y " + com11);
                System.out.println("2: " + com2 + " y " + com22);
                System.out.println("3: " + com3+ " y " + com33);
                aux = sc.nextShort();
    }

    public void elementosScanner(double a, double b) {
        this.a = sc.nextDouble();
        this.b = sc.nextDouble();
    }
    
    public void leyOhm() {
        System.out.println("escoge \n 1: Potencia \n 2: Intensidad \n 3: Tension \n 4: Resistencia");
        
        do {
            aux = sc.nextShort();

            switch (aux) {
                case 1:
                    dialogoOhm("potencia", "volts", "corriente", "resistencia", "corriente", "volts", "resistencia");
    
                    if (aux == 1) {
                        System.out.println("ingresa volts y luego corriente");
                        elementosScanner(a, b);
                        System.out.println("\n" + (a * b) + "w");
                    } else if (aux == 2) {
                        System.out.println("ingresa resistencia y luego correinte");
                        elementosScanner(a, b);
                        System.out.println("\n" + (a * (b * b)) + "w");
                    } else if (aux == 3) {
                        System.out.println("ingresa volts y luego resistencia");
                        elementosScanner(a, b);
                        System.out.println("\n" + ((a * a) / b) + "w");
                    }
                break;
                case 2:
                    dialogoOhm("intensidad", "volts", "resistencia", "potencia", "volts", "potencia", "resistencia");
    
                    if (aux == 1) {
                        System.out.println("ingresa volts y luego resistencia");
                        elementosScanner(a, b);
                        System.out.println("\n" + (a / b) + "A");
                    } else if (aux == 2) {
                        System.out.println("ingresa potencia y luego voltaje");
                        elementosScanner(a, b);
                        System.out.println("\n" + (a / b) + "A");
                    } else if (aux == 3) {
                        System.out.println("ingresa potencia y luego resistencia");
                        elementosScanner(a, b);
                        System.out.println("\n" + (Math.sqrt((a / b))) + "A");
                    }
                break;
                case 3:
                    dialogoOhm("tension", "potencia", "resistencia", "potencia", "corriente", "corriente", "resistencia");
    
                    if (aux == 1) {
                        System.out.println("ingresa potencia y luego resistencia");
                        elementosScanner(a, b);
                        System.out.println("\n" + (Math.sqrt((a * b))) + "v");
                    } else if (aux == 2) {
                        System.out.println("ingresa potencia y luego corriente");
                        elementosScanner(a, b);
                        System.out.println("\n" + (a / b) + "v");
                    } else if (aux == 3) {
                        System.out.println("ingresa resistencia y luego corriente");
                        elementosScanner(a, b);
                        System.out.println("\n" + (a * b) + "v");
                    }
                break;
                case 4:
                    dialogoOhm("resistencia", "potencia", "corriente", "potencia", "volts", "volts", "corriente");
    
                    if (aux == 1) {
                        System.out.println("ingresa potencia y luego corriente");
                        elementosScanner(a, b);
                        System.out.println("\n" + (a / (b * b)) + "Ω");
                    } else if (aux == 2) {
                        System.out.println("ingresa volts y luego potencia");
                        elementosScanner(a, b);
                        System.out.println("\n" + ((a * a) / b) + "Ω");
                    } else if (aux == 3) {
                        System.out.println("ingresa volts y luego corriente");
                        elementosScanner(a, b);
                        System.out.println("\n" + (a / b) + "Ω");
                    }
                break;
            }
        } while (aux != 0);
    }
}
