package Proyecto10;

public class Practica10 {
    static Nodo raizArbol; //puntero para guardar el primer nodo

    public static void main(String[] args) {
        insertar(5);
        insertar(3);
        insertar(1);
        insertar(4);
        insertar(8);
        insertar(9);
        insertar(666);
    }

    /**
     * 
     * @param dato
     * Descripcion: inserta los datos al arbol, si no hay datos, el primero lo toma como raiz y de ahi
     * va llenando el arbol
     */
    public static void insertar(int dato) {
        if (raizArbol == null) {  //si no hay un valor, se ingresa el primero como la raiz de arbol
            raizArbol = new Nodo(null, null, null, dato);
            System.out.println("se inserto: " + raizArbol.valor);
        } else {
            Nodo chalan = raizArbol; //auxiliar para recorrer el arbol hasta la raiz que utilizaremos

            while (chalan.derecha != null || chalan.izda != null) {
                if (dato <= chalan.valor && chalan.izda != null) {  //recorrer izquierda
                     chalan = chalan.izda;
                } else if (dato <= chalan.valor && chalan.izda == null) {
                    break; 
                }

                if (dato > chalan.valor && chalan.derecha != null) {  //recorrer derecha
                    chalan = chalan.derecha;
                } else if (dato > chalan.valor && chalan.derecha == null) {
                   break; 
                }
            }
            if (dato <= chalan.valor) {  //inserta e imprime los datos de la izquierda
                chalan.izda = new Nodo(null, null, chalan, dato);
                System.out.println("se inserto el nodo " + dato + " correctamente");
                System.out.println("[" + chalan.izda.izda + " , " + chalan.izda.valor + " , " +  chalan.izda.raiz + " , " + chalan.izda.derecha + "]");
            } else if (dato > chalan.valor) {  //inserta e imprime los datos de la izquierda
                chalan.derecha = new Nodo(null, null, chalan, dato);
                System.out.println("se inserto el nodo " + dato + " correctamente"); 
                System.out.println("[" + chalan.derecha.derecha + " , " + chalan.derecha.valor + " , " +  chalan.derecha.raiz + " , " + chalan.derecha.izda + "]");
            }
        }
    }

    public static int getRaizArbol() {
        return raizArbol.valor;
    }
}