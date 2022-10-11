package integradora;

import java.util.Scanner;

public class Administrador extends Persona{
    String area;
    String tarea;

    public Administrador(String nombre, String nacimiento, String rfc, int codigo, String area, String ingreso, String tarea) {
        super(nombre, nacimiento, rfc, codigo, ingreso);
        this.area = area;
        this.tarea = tarea;
    }

    public void trabajar() {
        Scanner sc = new Scanner(System.in);
        int horas;

        horas = sc.nextInt();
        System.out.println("trabajando por: " + horas);
    }
    
}
