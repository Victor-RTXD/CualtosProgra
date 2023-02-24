package ejerciciosRecursivos;

public class Sumando {
    public static int sumArrayRecursive(int[] arr, int n) {
        if (n == 0) {
            return arr[0];
        }
        return arr[n] += sumArrayRecursive(arr, n - 1);
    }
    
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int n = numbers.length-1;
        int sum = sumArrayRecursive(numbers, n);
        System.out.println("Sum of array elements: " + sum);
        // Output: Sum of array elements: 15
    }
}
