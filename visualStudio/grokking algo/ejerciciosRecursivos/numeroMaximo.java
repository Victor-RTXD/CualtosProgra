package ejerciciosRecursivos;

public class numeroMaximo {

    public static int maximo(int[] list, int index) {
        if (index == 1) {
           return list[0];
        } else {
            return Math.max(list[index -1], maximo(list, index-1));
        }
    }

    public static void main(String[] args) {
        int[] lista = {1,2,3,88,2,99};
        int index = lista.length ;
        System.out.println(maximo(lista, index )); 
    }
}
