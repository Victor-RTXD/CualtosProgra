package practica3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CuentaBancaria BBVA = new CuentaBancaria(0, null, 0, 0);
        boolean bandera = true;
        int opciones;

        do {
            System.out.println("1 crear cuenta");
            System.out.println("2 iniciar secion ");
            System.out.println("3 salir");
            opciones = sc.nextInt();
            /*BBVA.crearCuenta();
            BBVA.printData();
            BBVA.retirar();*/

            switch(opciones) {
                case 1: 
                    BBVA.crearCuenta(); 
                    break;
                case 2: {
                    if(BBVA.autentificacion() == true) {
                        do{
                            System.out.println("Bienvenido a BBVA");
                            System.out.println("1 Depositos");
                            System.out.println("2 retiros");
                            System.out.println("3 consulta de saldo");
                            System.out.println("4 regresar al menu");
                            opciones = sc.nextInt();
    
                            switch(opciones) {
                                case 1:
                                    BBVA.depsoitar();
                                break;
                                case 2:
                                    BBVA.retirar();
                                break;
                                case 3:
                                    BBVA.printData();
                                break;
                            }
                        } while(opciones != 4);
                    }

                    }break;
                case 3: 
                    bandera = false;
                    break;
            }
        } while (bandera == true);
        
    }
}
