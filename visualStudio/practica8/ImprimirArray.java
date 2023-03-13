package practica8;

public class ImprimirArray {
    static int array[] = {0,1,2,4,5};
    static int i = array.length - 1;

    public static void main(String[] args) {
        printArrayRecursively(array, i);
    }
    
    public static void printArrayRecursively(int[] array, int i) {
        if (i >= 0) {
            printArrayRecursively(array, i-1);
            System.out.println(array[i]);
        }
    }
}