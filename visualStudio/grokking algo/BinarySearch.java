public class BinarySearch {
    public static void main(String[] args) {
        // Crea un array de enteros ordenados
        int[] array = {1, 2, 3, 4, 5};

        // Llamamos a la función de búsqueda binaria y buscamos el número 3
        int index = binarySearch(array, 3);

        // Si el número se encontró, mostramos su índice
        if (index != -1) {
            System.out.println("El número se encontró en el índice " + index);
        } else {
            System.out.println("El número no se encontró en el array");
        }
    }

    public static int binarySearch(int[] array, int item) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            if (array[middle] == item) {
                return middle;
            } else if (array[middle] < item) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        return -1;
    }
}