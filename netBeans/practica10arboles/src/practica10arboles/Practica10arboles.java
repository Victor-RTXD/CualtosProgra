/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica10arboles;

/**
 *
 * @author ogonzalez
 */
public class Practica10arboles {
    static Nodo chalan=null;
    static Nodo RaizArbol;//puntero que guardara el primer nodo que crremos en la función insertar
    public int getRaiz()
    {
        return this.RaizArbol.valor;
    }
    
    static void insertarNodo(int dato)
    {
     if(RaizArbol==null){
        RaizArbol=new Nodo (dato, null, null, null);
         System.out.println("Primer nodo "+RaizArbol.valor);
     }else{
         chalan=RaizArbol;
         while(chalan.izda!=null || chalan.derecha!=null){
            if(dato<=chalan.valor && chalan.izda!=null){
                chalan=chalan.izda;
            }
            else if(dato <= chalan.valor && chalan.izda ==null){
                break;
            }
            else if(dato>chalan.valor && chalan.derecha!=null){
                chalan=chalan.derecha;
            }else if(dato>chalan.valor && chalan.derecha == null){
                break;
            }
         }
         if(dato<=chalan.valor){
             chalan.izda= new Nodo(dato,null,null,chalan);
             System.out.println("nodo insertado"+chalan.izda.valor);
         }
         if(dato > chalan.valor){
             chalan.derecha = new Nodo(dato, null, null, chalan);
             System.out.println("nodo insertado "+chalan.derecha.valor);
         }
     }
    
    }
    
    
    static public void insertar(int dato)
    {
       if(RaizArbol==null)
       {
           RaizArbol=new Nodo (dato, null,null,null);
           System.out.println("se inerto el valor"+dato);
       }else
       {
        Nodo chalan =RaizArbol;//auliar para recorrer el arbol desde la raíz
       while(chalan.derecha!=null ||chalan.izda!=null)
       {
           if(dato<=chalan.valor && chalan.izda!=null)
               chalan=chalan.izda;
       else if(dato<=chalan.valor && chalan.izda==null)
               {break;
             }else if(dato>chalan.valor && chalan.derecha!=null)
               chalan=chalan.derecha;
           else if(dato>chalan.valor && chalan.derecha==null)
               break;
    
       }
       if(dato<=chalan.valor)
       {
           chalan.izda=new Nodo (dato,null,null,chalan);
           System.out.println("se inserto el nodo"+dato+"   correctamente");
           System.out.println("["+chalan.izda.izda+","+chalan.izda.valor+","+chalan.izda.raiz+","+chalan.izda.derecha+","+chalan.izda.raiz);
       }
       else if(dato > chalan.valor)
       {
           chalan.derecha=new Nodo (dato,null,null,chalan);
       System.out.println("se inserto el nodo"+dato+"   correctamente");
           System.out.println("["+chalan.derecha.izda+","+chalan.derecha.valor+","+chalan.derecha.raiz+","+chalan.derecha.derecha+","+chalan.derecha.raiz);
       }
       }
 }
    
      
    

 public static int sucesor(Nodo x){// avanzar por la rama derecha el nodo mas izquiedo
 x=x.derecha;
 while(x.izda!=null)
     x=x.izda;
 return x.valor;
 }
 public static int predecesor(Nodo x){// avanzar por la rama izda mas a la derecha
 x=x.izda;
 while(x.derecha!=null)
     x=x.derecha;
 return x.valor;
 }
 
 public static Nodo buscar(Nodo x, int busqueda)//para buscar izquierda, derecha y tomar la ubicacion de x
 {
     if(x==null) //no se encontro
        return null;
     else if(x.valor==busqueda)
         return x;
     else if(busqueda<x.valor)
        return buscar(x.izda,busqueda);
     else if(busqueda>x.valor)
        return buscar(x.derecha,busqueda);
   return x;
 }
 
public static void eliminar(Nodo x,int busqueda)
{
if(x==null)
        System.out.println("no se encuentra el valor");
else// el nodo a borrar es una hoja
    if(x.izda==null&&x.derecha==null)
    {
    if(x.raiz.izda != null)
    if(x.raiz.izda.valor==busqueda)
    {
        x.raiz.izda=null; 
        x.raiz = null;
        System.out.println("se elimino " + x.valor);
        x= null;
        System.out.println("");
    } 
    else if(x.raiz.derecha.valor==busqueda){
        x.raiz.derecha=null;
     x.raiz=null;
        System.out.println("el nodo "+x.valor+" se ha borrado con éxito");
     x=null;
     }
    }
//eliminar cuando tiene un hijo derecho
else if(x.izda==null)
{
    if(x.raiz.valor>=x.valor){
        x.raiz.izda=x.derecha; //
        x.izda.raiz=x.raiz;
        System.out.println("el nodo "+x.valor+" se ha borrado con éxito");
        x=null;
    }else
    {
    x.raiz.derecha=x.derecha; //es hijo derecho
    x.derecha.raiz=x.raiz;
    System.out.println("el nodo "+x.valor+" se ha borrado con éxito");
    x=null;
    } 
}   else if(x.derecha != null && x.izda != null) {
    Nodo aux = x.izda;
    
    while(aux.derecha != null) {//buscar en lo mas profundo de la derecha
        aux = aux.derecha;
    }
    x.valor = aux.valor;
    eliminar(aux, aux.valor);
    
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

 
    public static void main(String[] args) {
       insertarNodo (4);
       insertarNodo(2);
       insertarNodo (3);
       insertarNodo(21);
       insertarNodo(7);
       insertarNodo(25);
       insertarNodo(27);
     /*  if(buscar (RaizArbol,21)==null)
     
            System.out.println("el nodo no existe"); 
       else
            System.out.println("el nodo se a encontrado en la dirección"+buscar(RaizArbol,21));  
*/    
    eliminar(buscar(RaizArbol,7),7);
    
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
}
