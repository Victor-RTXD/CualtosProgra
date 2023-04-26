package Proyecto10;

public class Nodo {
    Nodo izda, derecha, raiz;
    int valor;

    public Nodo(Nodo izda, Nodo derecha, Nodo raiz, int valor) {
        this.izda = izda;
        this.derecha = derecha;
        this.raiz = raiz;
        this.valor = valor;
    }

    public Nodo() {
        this.valor = 0;
        this.izda = null;
        this.derecha = null;
        this.raiz = null;
    }
    
}