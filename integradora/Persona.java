package integradora;

import java.util.Scanner;

public class Persona {
    public String nacimiento;
    public String rfc;
    public String nombre;
    public int codigo;
    public String ingreso;

    public Persona (String nombre, String nacimiento, String rfc, int codigo, String ingreso) {
        this.nacimiento = nacimiento;
        this.rfc = rfc;
        this.nombre = nombre;
        this.codigo = codigo;
        this.ingreso = ingreso;
    }

    public void altaPersona() {
        Scanner sc = new Scanner(System.in);

        System.out.println("ingrese nombre: ");
        this.nombre = sc.next();
        System.out.println("ingrese RFC: ");
        this.rfc = sc.next();
        System.out.println("ingrese codigo: ");
        this.codigo = sc.nextInt();
        System.out.println("ingrese saldo: ");
        this.ingreso = sc.next();
        System.out.println("ingrese nacimiento: ");
        this.nacimiento = sc.next();
        
    }

    public void mostrarDatos() {

    }
}