package Recorrdio;

public class Proyecto10 {
    static Nodo RaizArbol;
    static Nodo chalan;
   
    public static void main(String[] args) {
        // TODO code application logic here
        insertarnodo(4);
        insertarnodo(2);
        insertarnodo(3);
        insertarnodo(21);
        insertarnodo(7);

        if (buscar(RaizArbol, 21)) {
            System.out.println("el nodo existe");
        } else {
            System.out.println("no existe nodo y arbol");
        }

        eliminar(RaizArbol, 7);
        
        System.out.println("Preorden");
        preorden(RaizArbol);
        System.out.println();
        System.out.println("Inorden");
        inorden(RaizArbol);
        System.out.println();
        System.out.println("Postorden");
        postorden(RaizArbol);
        System.out.println();
    }
    
    public int getRaiz(){
        return this.RaizArbol.valor;
    }
    
    static void insertarnodo(int dato) {
        if (RaizArbol == null) {
         RaizArbol = new Nodo(dato, null, null, null); // se crea el nuevo nodo en el que se establecen los nodos en null y se pone el dato del parametro como dato en el nodo
         System.out.println("Raiz" + RaizArbol.valor); // se retorna el valor del primero nodo, osea la raiz del arbol.         
        } else {
            chalan = RaizArbol;

            while(chalan.derecha != null || chalan.izda != null){
                if(dato <= chalan.valor && chalan.izda != null){
                    chalan = chalan.izda;
                }
                if(dato <= chalan.valor && chalan.izda == null){
                    break;
                }
                if (dato > chalan.valor && chalan.derecha != null) {
                    chalan = chalan.derecha;
                }
                if(dato > chalan.valor && chalan.derecha == null){
                    break;
                }          
            }

            if(dato <= chalan.valor){
                chalan.izda = new Nodo(dato, null, null, chalan);
                System.out.println("Nodo insertado " + chalan.izda.valor);
            
            }

            if (dato > chalan.valor) {
                chalan.derecha = new Nodo(dato, null, null, chalan);
                System.out.println("Nodo insertado " + chalan.derecha.valor);
            }
        }
    }
    
    public static void preorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            preorden(nodo.izda);
            preorden(nodo.derecha);
        }
    }

    public static void inorden(Nodo nodo){
        if (nodo != null) {
            inorden(nodo.izda);
            System.out.print(nodo.valor + " ");
            inorden(nodo.derecha);
        }
    }

    public static void postorden(Nodo nodo) {
        if (nodo != null) {
            postorden(nodo.izda);
            postorden(nodo.derecha);
            System.out.print(nodo.valor + " ");
        }
    }

    /**
     * @param nodo
     * @param chalan
     * @return
     * Descripcion: primero busca la existencia de ese nodo
     */
    public static boolean buscar(Nodo x, int busqueda) {
        if (x == null) {
            System.out.println("no existe el elemento");
            return false;
        } else if (x.valor == busqueda) {
            return true;
        } else if (busqueda <= x.valor) {
            return buscar(x.izda, busqueda);
        } else if (busqueda > x.valor) {
            return buscar(x.derecha, busqueda);
        }
        return false;
    }

    public static int predecesor(Nodo x) { //avanzar por la izquierda el nodo mas derecha
        x = x.izda;

        while (x.derecha != null) {
            x = x.derecha;
        }
        return x.valor;
    }

    public static int sucesor(Nodo x) { //avanzar por la derecha el nodo mas izquierdo
        x = x.derecha;

        while (x.izda != null) {
            x = x.izda;
        }
        return x.valor;
    }

    public static void eliminar(Nodo x, int busqueda) {
        if (x == null) {
            System.out.println("no existe");
        }

        if (busqueda < x.valor) {
            eliminar(x.izda, busqueda);
        } else if (busqueda > x.valor) {
            eliminar(x.derecha, busqueda);
        } else if (busqueda == x.valor) {
            //checar si esta solo, tiene un hijo o 2 hijos
            if (x.derecha == null && x.izda == null) {
                x.raiz.izda = null;
                x.raiz.derecha = null;
                x = null;
                System.out.println("nodo madafacker eliminado");
            } else if (x.derecha != null) {
                //x.valor = sucesor(x.derecha, x.valor);
            }
        }
    }
}



/*
 * al eliminar un nodo se tiene que prever el borrar un nofo sin hijos, borrar un nodo con un subarbol hijo y 
 * borrar un nodo con dos subarboles hijos
 */
/*
 * si el elemento a eliminar tiene 2 desendientes en tonces se tiene que sustituir por el nodo mas a la izquierda en el sub arbol derecho
 * o por el nodo que se encuentre mas a la derecha en el sub arbol izquierdo
 * Esto se debe cumplir para las reglas de :
 * si tiene 2 hojas se debe de ser la mayor clave menores al nodo que se busca
 * 
 */

 /*
  * 
  */