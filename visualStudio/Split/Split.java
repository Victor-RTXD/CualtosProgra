package Split;

import java.util.Arrays;

public class Split {
    public static void main(String[] args) {
        String cadena = "Hola,Mundo";
        String[] partes = cadena.split(",");
        System.out.println(Arrays.toString(partes));
    }
}