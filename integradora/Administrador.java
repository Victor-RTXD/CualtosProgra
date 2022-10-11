package integradora;

import java.util.Scanner;

public class Administrador extends Persona{
    String area;
    String tarea;
    int horas;
    Scanner sc = new Scanner(System.in);

    public Administrador(String nombre, String nacimiento, String rfc, int codigo, String area, String ingreso, String tarea) {
        super(nombre, nacimiento, rfc, codigo, ingreso);
        this.area = area;
        this.tarea = tarea;
    }

    public void administrar() {
        System.out.println("administrara por: ");
        horas = sc.nextInt();
        
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
        System.out.println("ingrese area: ");
        this.area = sc.next();
        System.out.println("ingrese tarea: ");
        this.tarea = sc.next();
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre + ", RFC: " + rfc + ", codigo: " + codigo + ", ingreso: " + ingreso + ", nacimiento: " + nacimiento + ", area: " + area + ", tarea: " + tarea + ", administra por: " + horas + " horas");
    }
    
}
