package integradora;

import java.util.Scanner;

public class Estudiante extends Persona {
    String carrera;
    int horas;
    Scanner sc = new Scanner(System.in);

    public Estudiante(String nombre, String nacimiento, String rfc, int codigo, String carrera, String ingreso) {
        super(nombre, nacimiento, rfc, codigo, ingreso);
        this.carrera = carrera;
    }

    public void estudiar() {
        System.out.println("horas estudiar: ");
        this.horas = sc.nextInt();
        System.out.println("estudiando por: " + horas);
    }

    @Override
    public void altaPersona() {
        System.out.println("ingrese nombre: ");
        this.nombre = sc.next();
        System.out.println("ingrese RFC: ");
        this.rfc = sc.next();
        System.out.println("ingrese codigo: ");
        this.codigo = sc.nextInt();
        System.out.println("ingrese ingreso: ");
        this.ingreso = sc.next();
        System.out.println("ingrese nacimiento: ");
        this.nacimiento = sc.next();
        System.out.println("ingrese carrera: ");
        this.carrera = sc.next();
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre + ", RFC: " + rfc + ", codigo: " + codigo + ", ingreso: " + ingreso + ", nacimiento: " + nacimiento + ", carrera: " + carrera);
    }
}