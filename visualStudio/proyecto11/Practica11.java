package proyecto11;
import java.io.*;
import javax.swing.JOptionPane;

public class Practica11 {
    static Anuario datos = new Anuario(null, null, null, null);
    static File archivo = new File("BasesDeDatos.txt");

    public static void main(String[] args) {
        int opc;

        do {
            System.out.println("1 insertar");
            System.out.println("2 mostrar");
            System.out.println("3 salir");
            String texto = JOptionPane.showInputDialog("1 para insertar, 2 para ver y 3 salir");
            
            opc = Integer.parseInt(texto);

            if (opc == 1) {
                if (archivo.exists() == true) {
                    try {
                        archivo.createNewFile();
                        System.out.println(archivo.getName() + "archivo creado");
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
        } while (opc != 3);
    }

    public static void insertar() {
        try {
            RandomAccessFile fichero = new RandomAccessFile("BasesDeDatos.txt", "rw");

            datos.nombre = JOptionPane.showInputDialog("nombre");
            datos.generacion = JOptionPane.showInputDialog("generacion");
            datos.fechaCumple = JOptionPane.showInputDialog("fecha Cumple");
            datos.direccionFoto = "pendiente";

            fichero.seek(fichero.length()); // Posicionar al final del archivo

            fichero.writeBytes(datos.nombre + ",");
            fichero.writeBytes(datos.generacion + ",");
            fichero.writeBytes(datos.fechaCumple + ",");
            fichero.writeBytes(datos.direccionFoto + "\n");
            System.out.println();

            JOptionPane.showConfirmDialog(null, "Registro completado");
            fichero.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void mostrar() {
        try {
            RandomAccessFile fichero = new RandomAccessFile("BasesDeDatos.txt", "rw");
            FileReader leerArchivo = new FileReader("BasesDeDatos.txt");
            long puntero = fichero.getFilePointer();
            BufferedReader br = new BufferedReader(leerArchivo);
            String registro;


            while (puntero != fichero.length()) {
                registro = br.readLine();
                System.out.println("registro " + registro);
                fichero.readLine();
                puntero = fichero.getFilePointer();
                System.out.println("puntero" + puntero + "tamaño del archivo" + fichero);
            }

            fichero.close();
            leerArchivo.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
} //investigar funcion split como funciona