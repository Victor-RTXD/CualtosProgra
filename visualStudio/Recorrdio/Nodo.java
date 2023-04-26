package Recorrdio;

public class Nodo {
    int valor;
    public Nodo izda, derecha, raiz;
    
    public Nodo(int valor, Nodo izda, Nodo derecha, Nodo raiz){
        this.valor = valor;
        this.derecha= derecha;
        this.izda= izda;
        this.raiz = raiz;
        
    }
    
    public Nodo(){
        
    }
}
