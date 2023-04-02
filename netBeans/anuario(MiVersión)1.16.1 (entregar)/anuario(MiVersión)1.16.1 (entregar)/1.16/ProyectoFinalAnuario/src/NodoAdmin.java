/**
 *
 * @author DELL
 */
public class NodoAdmin 
{
    private String nombreUsuario;
    private String contrasenya;
    private NodoAdmin siguiente;
    private NodoAdmin inicio;
    private NodoAdmin fin;
    private NodoAdmin actual;

    public NodoAdmin(String nombreUsuario, String contrasenya, NodoAdmin siguiente) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenya = contrasenya;
        this.siguiente = siguiente;
    }

    public NodoAdmin() {
        this.nombreUsuario = "";
        this.contrasenya = "";
        this.siguiente = null;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public NodoAdmin getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoAdmin siguiente) {
        this.siguiente = siguiente;
    }

    public NodoAdmin getInicio() {
        return inicio;
    }

    public void setInicio(NodoAdmin inicio) {
        this.inicio = inicio;
    }
    
    void insertarCola(String nomAd, String contra)
    {/**
     * 1.- Instanciar el NodoAdmin
       2.- Agregar un dato a valor
       3.- Inicio = actual
       4.- this.Fin = actual
       5.- si no
            this.Fin.siguiente = actual
            this.Fin = actual
     */
        actual = new NodoAdmin(nomAd, contra, null);
        if (this.fin != null) {
            this.fin.setSiguiente(actual);
            this.fin = actual;
            System.out.println("Se inserto el elemento");
        }else
        {
            this.inicio = actual;
            this.fin = actual;
            System.out.println("Se inserto el primer elemento");
            
        }
    }
    void mostrarListaCola()
    {
        NodoAdmin chalan = inicio;
        if (chalan != null) 
        {
            while(chalan != null)
            {
                System.out.println("Nombre de administrador: " + chalan.getNombreUsuario());
                System.out.println("Constrasenya: " + chalan.getContrasenya());
                chalan = chalan.getSiguiente();
            }
        }
    }
    void borrarElemento()
    {
        NodoAdmin chalan = inicio;
        if(inicio!=null)
        {
            inicio=chalan.getSiguiente();
            chalan=null;
           
        }else
        {
            System.out.println("lista vacia");
        }
    }
}
