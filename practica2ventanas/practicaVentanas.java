package practica2ventanas;
import javax.swing.*;
import java.awt.*;

public class practicaVentanas {
    JTextField edad;
    JLabel mostrarEdad;

    public practicaVentanas() {
        JFrame practicaVentanas = new JFrame("inserta");
        edad = new JTextField(10);
        mostrarEdad = new JLabel("edad");
        practicaVentanas.setLayout(new FlowLayout());
        practicaVentanas.setSize(450,400);
        practicaVentanas.setVisible(true);
        practicaVentanas.add(edad);
        practicaVentanas.add(mostrarEdad);
        edad.setText(JOptionPane.showInputDialog(null, "mete edad", "edad"));
    }

    public static void main(String[] args) {
        new practicaVentanas();

        /* 
        JOptionPane.showMessageDialog(null,"error","ventana0",0); 
        JOptionPane.showMessageDialog(null,"seguro?","ventana1",1); 
        JOptionPane.showMessageDialog(null,"bien","ventana2",2); 
        JOptionPane.showMessageDialog(null,"adevertencia","erroventana3",3); 
        */

        int edad;
        edad = Integer.parseInt(JOptionPane.showInputDialog(null, "introduce tu edad", "(81)"));
        System.out.println("edad = " + edad);
    }
}
