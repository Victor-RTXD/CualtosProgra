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

    static void mostrarLista() {
        Nodo chalan = inicio;

        if (chalan != null) {
            while (chalan != null) {
                System.out.println("valor " + chalan.getValor());
                chalan = chalan.getSiguiente();
            }
        }
    }
}

/*
 * TAREA
 * 1 entregar cola circular array
 * 2 hacer funcion borrar de listas raras
 * 3 en cuaderno mostrar la estructura del proyecto final del anuario :(((((((((((((((((
 * primera pantalla para precentar el programa
 * segunda patalla para mostrar imagen de las personas del grupo con botones de avanzar y retroceder
 */
