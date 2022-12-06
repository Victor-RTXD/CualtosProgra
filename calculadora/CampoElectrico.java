package calculadora;

import java.util.Scanner;

public class CampoElectrico {
    short i, y;
    Scanner sc = new Scanner(System.in);
    double k = (9 * Math.pow(10, 9));
    double exponente;
    double q1, q2, f1, f2, ft;
    float r;

    public double caluclo() {
        System.out.println("ingresa la carga ");
                q1 = sc.nextFloat();
                System.out.println("ingresa distancia en metros ");
                r = sc.nextFloat();
                System.out.println("Deseas ingresar exponente?");
                System.out.println("si = 1   ||   no = 0");
                y = sc.nextShort();
                if(y==1){
                System.out.println("ingresa exponente positivamente ");
                exponente = sc.nextDouble();
                exponente = Math.pow(10, -exponente);}
                else{exponente =1;
                    }

                return (k * q1 * exponente) / (r*r);
    }
    
        public void caluclo2() {
                System.out.println("ingresa la carga 1: ");
                q1 = sc.nextFloat();
                System.out.println("ingresa distancia en metros ");
                r = sc.nextFloat();
                System.out.println("Deseas ingresar exponente?");
                System.out.println("si = 1   ||   no = 0");
                y = sc.nextShort();
                if(y==1){
                System.out.println("ingresa exponente positivamente ");
                exponente = sc.nextDouble();
                exponente = Math.pow(10, -exponente);}
                else{exponente =1;
                    }
                f1=(k * q1 * exponente) / (r*r);
            
                System.out.println("ingresa la carga 2: ");
                q2 = sc.nextFloat();
                System.out.println("ingresa distancia en metros ");
                r = sc.nextFloat();
                System.out.println("Deseas ingresar exponente?");
                System.out.println("si = 1   ||   no = 0");
                y = sc.nextShort();
                if(y==1){
                System.out.println("ingresa exponente positivamente ");
                exponente = sc.nextDouble();
                exponente = Math.pow(10, -exponente);
                } else {
                    exponente =1;
                }
                f2 = (k * q2 * exponente) / (r*r);
            
                ft = (f1+f2);
            if(ft>0){
                System.out.println("El campo electrico es: "+ ft);
                System.out.println("La fuerza es de repulsion");}
            else if(ft<0){
                System.out.println("El campo electrica es "+ ft);
                System.out.println("La fuerza es de atraccion");}
            else {
                System.out.println("Campo electrico es 0");
            }
            
    }
    
    void campoElectrico() {
        do {
            System.out.println("¿cuantos puntos se usaran?, (min 1 y max 2 \n oprime 0 para salir");
            i = sc.nextShort();

            switch(i) {
            case 1:
                System.out.println(caluclo());
            break;
            case 2:
                caluclo2();
            break;
            default:

            break;
            }
        } while (i != 0);
    }
}