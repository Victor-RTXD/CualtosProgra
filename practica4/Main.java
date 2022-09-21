package practica4;

import java.util.Random;
/*hacer arreglo de 10 
 * llenar el arreglo con 10 numeros aleatorios del 1-100
 * mostrar el arreglo con for acumulador
 * sacar el promedio del array
 */
public class Main {
    public static void main(String[] args) {
        int randomArray[] = new int[10];
        Random random = new Random(100-0+1);
        int media = 0;

        //ingresando numeros random
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = random.nextInt(100);
            media += randomArray[i];
            System.out.println(i + 1 + ": " + randomArray[i]);
        }

        //media
        System.out.println("la media es: " + media / 10);
    }
}
