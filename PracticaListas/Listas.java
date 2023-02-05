package PracticaListas;

public class Listas {
    static Nodo actual = null, inicio = null, fin = null;
    static int contador = 0;

     void instertarCola(int x) {
        actual = new Nodo(x,null);

        if (fin != null) {
            fin.setSiguiente(actual);
            fin = actual;
            contador++;
            System.out.println("se inserto el elemento");
        } else {
            fin = actual;
            inicio = actual;
            System.out.println("se inserto el elemento");
            contador++;
        }
    }

    static void mostrarLista() {
        Nodo chalan = inicio;

        if (chalan != null) {
            while (chalan != null) {
                System.out.println("valor " + chalan.getValor());
                chalan = chalan.getSiguiente();
            }
        }
    }

    static void eliminar(int x) {
        // Nodo para recorrer la lista
        Nodo anterior = null, actual = inicio;
    
        // Recorrer la lista hasta encontrar el elemento x o llegar al final de la lista
        while (actual != null && actual.getValor() != x) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
    
        // Si se encontró el elemento x
        if (actual != null) {
            // Si es el primer elemento de la lista
            if (anterior == null) {
                inicio = actual.getSiguiente();
            } else {
                // Enlazar el nodo anterior con el siguiente después del nodo actual (eliminando así al nodo actual)
                anterior.setSiguiente(actual.getSiguiente());
            }
            // Decrementar el contador de elementos en la lista
            contador--;
            System.out.println("Se eliminó el elemento " + x);
        } else {
            System.out.println("No se encontró el elemento " + x);
        }
    }
    
    public static void main(String[] args) {
        
    }
}