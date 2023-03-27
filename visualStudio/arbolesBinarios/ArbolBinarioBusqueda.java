package arbolesBinarios;

public class ArbolBinarioBusqueda {
    Nodo raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo nodo, int valor) {
        if (nodo == null) {
            nodo = new Nodo(valor);
            return nodo;
        }

        if (valor < nodo.valor)
            nodo.izquierda = insertarRec(nodo.izquierda, valor);
        else if (valor > nodo.valor)
            nodo.derecha = insertarRec(nodo.derecha, valor);

        return nodo;
    }

    public void inorder() {
        inorderRec(raiz);
    }

    private void inorderRec(Nodo nodo) {
        if (nodo != null) {
            inorderRec(nodo.izquierda);
            System.out.print(nodo.valor + " ");
            inorderRec(nodo.derecha);
        }
    }

    public static void main(String[] args) {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(70);
        arbol.insertar(60);
        arbol.insertar(80);

        System.out.println("Recorrido inorder del árbol: ");
        arbol.inorder();
    }
}