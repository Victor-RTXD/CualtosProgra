package practica5;

public class Nodo {
   private String codigo;
   private String rutaImagen;
   private Nodo siguiente;
   private Nodo anterior;

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
    
    public Nodo(String codigo, String rutaImagen, Nodo siguiente){
        this.codigo = codigo;
        this.rutaImagen = rutaImagen;
        this.siguiente = siguiente;
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
}