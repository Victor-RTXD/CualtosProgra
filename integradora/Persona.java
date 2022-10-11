package integradora;

public abstract class Persona {
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

    public abstract void altaPersona();

    public abstract void mostrarDatos();

    public boolean autentificacion(int codigoComparador) {
        if (codigoComparador == this.codigo) {
            return true;
        } else {
            System.out.println("codigo incorrecto");
            return false;
        }
    }
}