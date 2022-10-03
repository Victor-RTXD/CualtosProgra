package practica5;

import java.util.Scanner;

/*Implemente mediante los métodos de la clase String, una aplicación que cuente el número de vocales hay en una frase dada por el usuario.
Para ello lo pueden implementar en la parte del main del programa. 

Victor Eduardo Macias Macias
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String frase;
        short contador = 0;

        frase = sc.nextLine();
        frase = frase.toLowerCase();
        
        for (int i = 0; i < frase.length(); i++) {
            //rase.charAt(i) == 'a' funciona como un metodo que segun el indice de la posicion del string, puede verificar si esta el caracter requerido
            if(frase.charAt(i) == 'a' || frase.charAt(i) == 'e' || frase.charAt(i) == 'i' || frase.charAt(i) == 'o' || frase.charAt(i) == 'u') {
                contador++;
            }
        }

        System.out.println(contador);
    }
}