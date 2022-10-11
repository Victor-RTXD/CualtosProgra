package integradora;

import java.util.Scanner;

public class Estudiante extends Persona {
    String carrera;

    public Estudiante(String nombre, String nacimiento, String rfc, int codigo, String carrera, String ingreso) {
        super(nombre, nacimiento, rfc, codigo, ingreso);
        this.carrera = carrera;
    }

    public void estudiar() {
        int horas;
        Scanner sc = new Scanner(System.in);

        horas = sc.nextInt();
        System.out.println("estudiando por: " + horas);
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public void altaPersona() {
        super.altaPersona();
        this.carrera = sc.nextLine();
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        
    }
    
}