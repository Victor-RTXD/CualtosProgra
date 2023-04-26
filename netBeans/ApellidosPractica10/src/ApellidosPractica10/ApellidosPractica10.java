
package ApellidosPractica10;

/**
 *
 * @author Apellidos
 */
public class ApellidosPractica10 {
    static Nodo RaizArbol;//NODO RAIZDEL ÁRBOL
    static Nodo chalan;//NODO AUXILIAR
    //Declaracion de nodos

   
    public static void main(String[] args) {
        // Inicio del main
        insertarnodo(4);//Insertar elementos(raiz)
        insertarnodo(2);
        insertarnodo(3);
        insertarnodo(21);
        insertarnodo(7);
        insertarnodo(88);
        insertarnodo(6);
        insertarnodo(13);
        
        System.out.println("Post - orden");//Nombre del método
        postorden(RaizArbol);//Metodo Post - orden iniciando con la raiz
        System.out.println();
    }
    
    
    public int getRaiz(){
        return this.RaizArbol.valor;
    }
    
    static void insertarnodo(int dato){
     if(RaizArbol == null){
         RaizArbol = new Nodo(dato, null, null, null); // se crea el nuevo nodo en el que se establecen los nodos en null y se pone el dato del parametro como dato en el nodo
         System.out.println("Raiz" + RaizArbol.valor); // se retorna el valor del primero nodo, osea la raiz del arbol.         
     }  
     else{
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
        if(dato > chalan.valor){
            chalan.derecha = new Nodo(dato, null, null, chalan);
            System.out.println("Nodo insertado " + chalan.derecha.valor);
        }
        
     }
    }
    


public static void postorden(Nodo nodo){//Método post - Orden
    if(nodo != null){//Mientras el nodo no esté vacio avanzará a la izquierda
        postorden(nodo.izda);//Recorre recursivamente a la izquierda
        postorden(nodo.derecha);//Recorre recursivamente a la derecha
        System.out.print(nodo.valor + " ");//imprimee el valor de cada nodo
    }//Fin del if
}//Fin del método
}
