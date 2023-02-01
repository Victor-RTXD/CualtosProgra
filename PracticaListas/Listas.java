package PracticaListas;

/*
    documentacion rara de la maestra
 * 1 insertar nodo actual
 * 2 agregar valor
 * 3 inicio = acutal
 * 4 fin = actual
 * 5 si no
 * 6 fin.siguiente = actual
 * fin = actual
 */

public class Listas {
    static Nodo actual = null, inicio = null, fin = null;
    static int contador = 0;

    static void instertarCola(int x) {
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
}