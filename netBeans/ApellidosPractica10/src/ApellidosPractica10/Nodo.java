/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ApellidosPractica10;

/**
 *
 * @author werol
 */


public class Nodo {
    int valor;
    Nodo izda, derecha, raiz;
    
    public Nodo(int valor, Nodo izda, Nodo derecha, Nodo raiz){
        this.valor = valor;
        this.derecha= derecha;
        this.izda= izda;
        this.raiz = raiz;
        
    }
    
    public Nodo(){
        
    }
}
