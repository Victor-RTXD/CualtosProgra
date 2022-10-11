package integradora;

import java.util.Scanner;

public class Maestro extends Persona {
    String departamento;
    String materiaImpartir;
    int horas;
    Scanner sc = new Scanner(System.in);

    public Maestro(String nombre, String nacimiento, String rfc, int codigo, String ingreso, String departamento, String materalImpartir) {
        super(nombre, nacimiento, rfc, codigo, ingreso);
        this.departamento = departamento;
        this.materiaImpartir = materalImpartir;
    }

    public void enseñar() {
        System.out.println("ingrese horas a enseñar: ");
        this.horas = sc.nextInt();
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
        System.out.println("ingrese departamento: ");
        this.departamento = sc.next();
        System.out.println("ingrese materia a impartir: ");
        this.materiaImpartir = sc.next();
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre + ", RFC: " + rfc + ", codigo: " + codigo + ", ingreso: " + ingreso + ", nacimiento: " + nacimiento + ", departamento: " + departamento + ", materia a impartir: " + materiaImpartir + ", trabaja por: " + horas + " horas");
    }
}
    
