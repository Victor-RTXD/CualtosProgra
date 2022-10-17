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

    /**
     * Descripcion: Funcion abstracta que da de alta de diferente forma a los objetos de las clases
     */
    public abstract void altaPersona();

    /**
     * Descripcion: Muestra los datos de cada objeto de las clases instanciadas
     */
    public abstract void mostrarDatos();

    /**
     * Descripcion: verifica que el codigo ingreado corresponde a de alguna persona
     * de la escuela
     * @param codigoComparador
     * @return true o false
     */
    public boolean autentificacion(int codigoComparador) {
        if (codigoComparador == this.codigo) {
            return true;
        } else {
            System.out.println("codigo incorrecto");
            return false;
        }
    }
}