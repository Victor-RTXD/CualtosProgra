package integradora;

import java.util.Scanner;

public class Maestro extends Persona {
    String departamento;
    String materiaImpartir;

    public Maestro(String nombre, String nacimiento, String rfc, int codigo, String ingreso, String departamento, String materalImpartir) {
        super(nombre, nacimiento, rfc, codigo, ingreso);
        this.departamento = departamento;
        this.materiaImpartir = materalImpartir;
    }

    public void enseñar() {
        Scanner sc = new Scanner(System.in);
        int horas;

        horas = sc.nextInt();
        System.out.println("horas enseñándo: " + horas);
    }
}
    
