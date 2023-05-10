package proyecto11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Archivos {

    public static void main(String[] args) {
        String MensajeLeido = "";
        File archivo = new File("miarchivo.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                System.out.println(archivo.getName() + " ha sido creado");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                RandomAccessFile fichero = new RandomAccessFile("miarchivo.txt", "rw");
                long numero;
                numero = fichero.getFilePointer();
                System.out.println(archivo.getName() + " ha sido creado");
                fichero.seek(fichero.length());
                fichero.writeBytes("hola mundo allw ackbar \n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            FileReader leerArchivo = new FileReader("miarchivo.txt");
            BufferedReader BR = new BufferedReader(leerArchivo);
            MensajeLeido = BR.readLine();
            leerArchivo.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("La primera linea es: " + MensajeLeido);
    }
}