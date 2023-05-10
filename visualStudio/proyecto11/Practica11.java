package proyecto11;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Practica11 {
    static Anuario datos = new Anuario(null, null, null, null);
    static File archivo = new File("BasesDeDatos.txt");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opc;

        System.out.println("1 insertar");
        System.out.println("2 mostrar");
        System.out.println("3 salir");

        opc = sc.nextInt();
        if (opc == 1) {
            if (archivo.exists()) {
                try {
                    archivo.createNewFile();
                    System.out.println(archivo.getName() + " archivo creado");
                    insertar();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }  
            } else {
                insertar();
            }
        } else if (opc == 2){
            if (!archivo.exists()) {
                System.out.println("el archivo no existe");
            } else {
                mostrar();
            }
        } else if (opc == 3){
            System.out.println("adios");

        }
    }

    public static void insertar() {
        try {
            RandomAccessFile fichero = new RandomAccessFile("BasesDeDatos.txt", "rw");

            datos.nombre = JOptionPane.showInputDialog("nombre");
            datos.generacion = JOptionPane.showInputDialog("generacion");
            datos.fechaCumple = JOptionPane.showInputDialog("fecha Cumple");
            datos.direccionFoto = "pendiente";

            fichero.writeBytes(datos.nombre + ",");
            fichero.writeBytes(datos.generacion + ",");
            fichero.writeBytes(datos.fechaCumple + ",");
            fichero.writeBytes(datos.direccionFoto + "\n");
            JOptionPane.showConfirmDialog(null, "registo completado");
            fichero.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void mostrar() {

    }
}