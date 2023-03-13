package practica8;
public class Main {
    public static void main(String[] args) {
        System.out.println("resultado: " + factorial(5));
    }

    static int factorial(int numero) {
        if (numero == 1)
            return 1;
        else {
            return numero * factorial(numero - 1);
        }   
    }
}