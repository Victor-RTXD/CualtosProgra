package practica3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CuentaBancaria bbva[] = new CuentaBancaria[10]; //aqui declaramos objeto array, luego se llenara sus atributos
        boolean bandera = true;
        int opciones;
        short indice = 0;

        do {
            System.out.println("1 crear cuenta");
            System.out.println("2 iniciar secion ");
            System.out.println("3 salir");
            opciones = sc.nextInt();

            switch(opciones) {
                case 1: {
                    if(indice < bbva.length) {
                        bbva[indice] = new CuentaBancaria(0, "", 0, 0); 
                        bbva[indice].crearCuenta();
                        indice++;
                    }
                }break;
                case 2: {
                    short i = 0;

                    while(i < indice) {
                        if(bbva[i].autentificacion() == true) {
                            do{
                                System.out.println("Bienvenido a BBVA");
                                System.out.println("1 Depositos");
                                System.out.println("2 retiros");
                                System.out.println("3 consulta de saldo");
                                System.out.println("4 regresar al menu");
                                opciones = sc.nextInt();
        
                                switch(opciones) {
                                    case 1:
                                        bbva[i].depsoitar();
                                    break;
                                    case 2:
                                        bbva[i].retirar();
                                    break;
                                    case 3:
                                        bbva[i].printData();
                                    break;
                                }
                            } while(opciones != 4);
                        } else {
                            i++;
                        }
                    }
                }break;
                case 3: 
                    bandera = false;
                    break;
            }
        } while (bandera == true);
        
    }
}
