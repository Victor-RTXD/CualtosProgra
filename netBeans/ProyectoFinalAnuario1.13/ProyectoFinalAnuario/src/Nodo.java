public class Nodo {
    private String nombre;
    private String rutaImagen;
    private String edad;
    private String frase;
    private Nodo anterior;
    private Nodo siguiente;
    private int codigo;
    private Nodo fin, inicio, nuevo, aux;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Nodo getCabeza() {
        return fin;
    }

    public void setCabeza(Nodo cabeza) {
        this.fin = cabeza;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public Nodo getNuevo() {
        return nuevo;
    }

    public void setNuevo(Nodo nuevo) {
        this.nuevo = nuevo;
    }

    public Nodo getAux() {
        return aux;
    }

    public void setAux(Nodo aux) {
        this.aux = aux;
    }

    public Nodo(String nombre, String rutaImagen, String edad, String frase, Nodo anterior, Nodo siguiente, int codigo) {
        System.out.print("se ha creado un nuevo nodo");
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
        this.edad = edad;
        this.frase = frase;
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
}