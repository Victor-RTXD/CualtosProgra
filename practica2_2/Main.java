package practica2_2;

import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Agenda terceroComputacion = new Agenda(null, null, null);

        System.out.println("Ingresa nombre, telefono y ubicacion: ");

        //sc.nextLine() sirve para ingresar el contenido de los atributos del objeto declarado
        
        terceroComputacion.setName(sc.nextLine());
        terceroComputacion.setPhone(sc.nextLine());
        terceroComputacion.setLocation(sc.nextLine());

        terceroComputacion.output();

    }
}