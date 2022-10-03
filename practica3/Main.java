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
                    short aux = 0;
                    int cuentaComparador, nipComparador;
                    boolean bandera2 = true;

                    System.out.println("teclea numero de cuenta: ");
                    cuentaComparador = sc.nextInt();
                    System.out.println("teclea nip: ");
                    nipComparador = sc.nextInt();

                    //comparar datos correctos
                    while(i < indice) {
                        if(bbva[i].autentificacion(cuentaComparador, nipComparador) == true) {
                            System.out.println("valor encontrado");
                            bandera2 = true;
                            aux = i;
                            i = indice;
                        } else {
                            bandera2 = false;
                            i++;
                        }
                    }

                    //ingresando al menu si los datos son correctos
                    if (bandera2 == true) {
                        do{
                            System.out.println("Bienvenido a BBVA");
                            System.out.println("1 Depositos");
                            System.out.println("2 retiros");
                            System.out.println("3 consulta de saldo");
                            System.out.println("4 regresar al menu");
                            opciones = sc.nextInt();
            
                            switch(opciones) {
                                case 1:
                                    bbva[aux].depsoitar();
                                break;
                                case 2:
                                    bbva[aux].retirar();
                                break;
                                case 3:
                                    bbva[aux].printData();
                                break;
                                }
                        } while(opciones != 4);
                    } else {
                        System.out.println("error en los datos");
                    }
                    
                }break;
                case 3: 
                    //salir del programa
                    bandera = false;
                break;
            }
        } while (bandera == true);
    }
}
