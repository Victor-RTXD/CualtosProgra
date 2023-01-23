package act1;
import java.util.Scanner;

public class Clase {
    Scanner sc = new Scanner(System.in);
    int codigo;
    String materia;
    int calificacion;
    int front = -1;
    int rear = -1;
    int item;
    int size = 5;
    Integer queue[] = new Integer[size];

    //metodos
    public Clase(int calificacion, int codgio, String materia) {
        this.calificacion = calificacion;
        this.materia = materia;
        this.codigo = codgio;
    }
}
