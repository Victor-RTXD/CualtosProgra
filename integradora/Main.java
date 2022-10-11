package integradora;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estudiante estudiantes[] = new Estudiante[20];
        Administrador administradores[] = new Administrador[5];
        Maestro maestros[] = new Maestro[5];

        int respuesta = 0;
        Scanner sc = new Scanner(System.in);
        int indice = 0;

        do {
            System.out.println("Selecciona la opcion requerida: ");
            System.out.println("1: Dar de alta ");
            System.out.println("2: Mostrar datos");
            System.out.println("0: salir");
            
            respuesta = sc.nextInt();

            switch (respuesta) {
                case 1:
                //aqui hace falta verificar que funciona e ingresarle a los otros puestos, ademas de ver si jala la opcion dal alta
                    System.out.println("1: Estudaintes, 2: Maestros, 3: Admins, otro: salir");
                    respuesta = sc.nextInt();

                    if (respuesta == 1) {
                        if(indice < estudiantes.length) {
                            estudiantes[indice] = new Estudiante("","","",0,"",""); 
                            estudiantes[indice].altaPersona();
                            indice++;
                        }

                    } else if (respuesta == 2) {
                        if(indice < maestros.length) {
                            maestros[indice] = new Maestro(null, null, null, indice, null, null, null); 
                            maestros[indice].altaPersona();
                            indice++;
                        }
                    } else if (respuesta == 3) {
                        if(indice < administradores.length) {
                            administradores[indice] = new Administrador(null, null, null, indice, null, null, null); 
                            administradores[indice].altaPersona();
                            indice++;
                        }
                    }
                break;
                case 2: {
                //aqui hace falta meter un while para elegir el alumno correcto
                    System.out.println("1: Estudaintes, 2: Maestros, 3: Admins, 0: salir");
                    respuesta = sc.nextInt();
                    int i = 0;
                    int aux = 0;
                    boolean bandera2 = true;
                    int codigoComparador = 0;

                    System.out.println("Ingresa el codigo ");
                    codigoComparador = sc.nextInt();

                    if(respuesta == 1) {
                        while(i < indice) {
                            if(estudiantes[i].autentificacion(codigoComparador) == true) {
                                System.out.println("Bienvenido");
                                bandera2 = true;
                                aux = i;
                                i = indice;
                            } else {
                                bandera2 = false;
                                i++;
                            }
                        }

                        if (bandera2 == true)
                            estudiantes[aux].mostrarDatos();
                        
                    } else if (respuesta == 2) {
                        while(i < indice) {
                            if(maestros[i].autentificacion(codigoComparador) == true) {
                                System.out.println("Bienvenido");
                                bandera2 = true;
                                aux = i;
                                i = indice;
                            } else {
                                bandera2 = false;
                                i++;
                            }
                        }

                        if (bandera2 == true) 
                            maestros[aux].mostrarDatos();
                            
                    } else if (respuesta == 3) {
                        while(i < indice) {
                            if(administradores[i].autentificacion(codigoComparador) == true) {
                                System.out.println("Bienvenido");
                                bandera2 = true;
                                aux = i;
                                i = indice;
                            } else {
                                bandera2 = false;
                                i++;
                            }
                        }

                        if (bandera2 == true) 
                            administradores[aux].mostrarDatos();
                    }
                }break;
                case 0:
                    System.out.println("Gracias");
                break;
                default:
                    System.out.println("Intentalo otra vez");
                break;
            }

        } while (respuesta != 0);
    }
}