package practica6;

public class Nodo {
    static public String codigo;
    static public String rutaImagen;
    static public Nodo siguiente;
    static public Nodo anterior;
    
    public Nodo(String codigo, String rutaImagen, Nodo siguiente, Nodo anterior) {
        this.codigo = codigo;
        this.rutaImagen = rutaImagen;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
    
}
