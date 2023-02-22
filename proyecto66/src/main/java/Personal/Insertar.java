package Personal;
import javax.swing.*;
import java.io.File;
import java.awt.Color;
import java.awt.Image;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.LinkedList;

/**
 *
 * @author victor
 */
public class Insertar extends javax.swing.JFrame {
    LinkedList<ImageIcon> lista = new LinkedList<ImageIcon>();
    LinkedList<String> codigos = new LinkedList<String>();
    static JFileChooser explorador = new JFileChooser();
    static String dirtmp;
    static int contador = 0, contador2 = 0;
    
    public Insertar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btInsertar = new javax.swing.JButton();
        imagen = new javax.swing.JPanel();
        labImagen = new javax.swing.JLabel();
        btDerecha = new javax.swing.JButton();
        btIzquierda = new javax.swing.JButton();
        btBorrarFinal = new javax.swing.JButton();
        btBorrar = new javax.swing.JButton();
        btMostrar = new javax.swing.JButton();
        labCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btBorrarCodigo = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btInsertar.setText("insertar");
        btInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInsertarActionPerformed(evt);
            }
        });

        labImagen.setText("Sin imagen");

        javax.swing.GroupLayout imagenLayout = new javax.swing.GroupLayout(imagen);
        imagen.setLayout(imagenLayout);
        imagenLayout.setHorizontalGroup(
            imagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagenLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(labImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );
        imagenLayout.setVerticalGroup(
            imagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagenLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addContainerGap())
        );

        btDerecha.setText("-->");
        btDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDerechaActionPerformed(evt);
            }
        });

        btIzquierda.setText("<--");
        btIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIzquierdaActionPerformed(evt);
            }
        });

        btBorrarFinal.setText("borrar final");
        btBorrarFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBorrarFinalActionPerformed(evt);
            }
        });

        btBorrar.setText("borrar actual");
        btBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBorrarActionPerformed(evt);
            }
        });

        btMostrar.setText("mostrar en consola");

        labCodigo.setText("Codigo");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        btBorrarCodigo.setText("borrar por codigo");
        btBorrarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBorrarCodigoActionPerformed(evt);
            }
        });

        btBuscar.setText("buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labCodigo)
                .addGap(40, 40, 40)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btInsertar)
                    .addComponent(btBorrarFinal))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btBorrarCodigo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btBorrar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btDerecha))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btMostrar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btIzquierda))))
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btBuscar)
                .addGap(126, 126, 126))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btBorrar)
                    .addComponent(btInsertar)
                    .addComponent(btDerecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btMostrar)
                    .addComponent(btBorrarFinal)
                    .addComponent(btIzquierda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btBorrarCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btBuscar)
                .addGap(50, 50, 50)
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInsertarActionPerformed
        try{
             explorador.addChoosableFileFilter(new FileNameExtensionFilter("imagenes", "jpg", "png", "jpeg", "gif"));
             explorador.showOpenDialog(null); //ventana de dialogo esta inici????
             File auxFile;
             auxFile = explorador.getSelectedFile();
             dirtmp = auxFile.getAbsolutePath();
             //visualizar imagen en el label
             ImageIcon foto;
            foto = new ImageIcon(dirtmp);
            Icon icono;
            icono = new ImageIcon(foto.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_DEFAULT));
            labImagen.setText(" ");
            labImagen.setIcon(icono);
            lista.add(foto);
            codigos.add(txtCodigo.getText());
            contador++;
            txtCodigo.setText("");
         } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "error al abrir el archivo", "advertencia", JOptionPane.WARNING_MESSAGE);
         }
    }//GEN-LAST:event_btInsertarActionPerformed

    private void btIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIzquierdaActionPerformed
        if (contador < 1) {
            contador = codigos.size();
            labImagen.setIcon(lista.getLast());
            txtCodigo.setText(codigos.getLast());
        } else {
        contador--;
        labImagen.setIcon(lista.get(contador));
        txtCodigo.setText(codigos.get(contador));
        }
    }//GEN-LAST:event_btIzquierdaActionPerformed

    private void btDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDerechaActionPerformed
        if (contador < codigos.size()-1) {
            contador++;
            txtCodigo.setText(codigos.get(contador));
            labImagen.setIcon(lista.get(contador));
        } else {
            contador = 0;
            labImagen.setIcon(lista.getFirst());
            txtCodigo.setText(codigos.getFirst());
        }
    }//GEN-LAST:event_btDerechaActionPerformed

    private void btBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBorrarActionPerformed
        lista.remove(contador);
        codigos.remove(contador);
        labImagen.setIcon(null);
        labImagen.setText("");
    }//GEN-LAST:event_btBorrarActionPerformed

    private void btBorrarFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBorrarFinalActionPerformed
        lista.removeLast();
        codigos.removeLast();
        labImagen.setIcon(null);
    }//GEN-LAST:event_btBorrarFinalActionPerformed

    private void btBorrarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBorrarCodigoActionPerformed
        String dato = JOptionPane.showInputDialog(null, "ingresa el dato que quieras borrar:", "mensaje", JOptionPane.PLAIN_MESSAGE);
        boolean contiene = codigos.contains(dato);
        if (contiene == true) {
            codigos.indexOf(dato);
            lista.indexOf(dato);
            System.out.println(codigos.indexOf(dato));
            System.out.println(lista.indexOf(dato));
            lista.remove(codigos.indexOf(dato));
            codigos.remove(codigos.indexOf(dato));
            JOptionPane.showMessageDialog(null, "Se borro con exito", "Borrado", JOptionPane.INFORMATION_MESSAGE);
            labImagen.setIcon(null);
            labImagen.setText("");
        }
    }//GEN-LAST:event_btBorrarCodigoActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        if(lista.isEmpty() == true) {
            System.out.println("vacio");
        } else {
                System.out.println("codigo " + codigos.get(contador));
                System.out.println("ruta imagen  " + lista.get(contador));
                
                if(contador > 0 && lista.get(contador-1) != null){
                    System.out.println("nodo anterior: " + lista.get(contador-1));
                } else {
                    System.out.println("nodo anterior: no hay nada");
                }
                if(contador < lista.size()-1 && lista.get(contador+1) != null) {
                    System.out.println("nodo siguiente: " + lista.get(contador+1));
                } else {
                    System.out.println("nodo siguiente: no hay nada");
                }   
        }
    }//GEN-LAST:event_btBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Insertar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBorrar;
    private javax.swing.JButton btBorrarCodigo;
    private javax.swing.JButton btBorrarFinal;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btDerecha;
    private javax.swing.JButton btInsertar;
    private javax.swing.JButton btIzquierda;
    private javax.swing.JButton btMostrar;
    private javax.swing.JPanel imagen;
    private javax.swing.JLabel labCodigo;
    private javax.swing.JLabel labImagen;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}