package bubbleSort;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arreglo[], nElementos, aux = 0;

        nElementos = Integer.parseInt(JOptionPane.showInputDialog("digite los elementos del array"));

        arreglo = new int[nElementos]; // le asignamos el numero de elementos del array

        for (int i = 0; i < nElementos; i++) {
            System.out.println("digita numero: ");
            arreglo[i] = sc.nextInt();
        }

        //metodo burbuja
        for (int i = 0; i < nElementos - 1; i++) {//le ponemos -1 para que el programa no de una vuelta inecesaria, si tenemos 5 elementos no hace falta que de 5 vueltas
            for (int j = 0; j < nElementos - 1; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    aux = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j+1] = aux;
                }
            }
        }

        for (int i = 0; i < arreglo.length; i++) {
            System.out.print( arreglo[i] + " ");
        }
    }
}
