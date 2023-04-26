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
public class Nodo {

   
   int valor;
   Nodo izda,derecha,raiz;
    public Nodo(int valor, Nodo izda, Nodo derecha, Nodo raiz) {
        this.valor = valor;
        this.izda = izda;
        this.derecha = derecha;
        this.raiz = raiz;
    }
    public Nodo ()
    {
    this.valor=0;
    this.derecha=null;
    this.izda=null;
    this.raiz=null;
    }
}
