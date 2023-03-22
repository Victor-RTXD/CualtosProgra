package practica8;

public class QuickSort {

    /**
     * 
     * @param array array
     * @param low izquierda
     * @param high derecha
     * Descripcion: se parte dos arrays, uno con el num inferior al index y otro con el num mayor
     */
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    /**
     * 
     * @param array
     * @param low
     * @param high
     * @return
     * Descripcion: aqui es donde se trabaja en las particiones
     * el pivote toma el numero de hasta la derecha e i el de la izquierda
     * se hace un for para cambiar los numeros que sean menores o igales al pivote
     * 
     */
    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }
/**
 * cambia de posicion los datos
 * @param array
 * @param i
 * @param j
 */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    // test
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 3, 7, 6, 1, 8, 4};
        quickSort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}