package practica6;

import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static practica6.Nodo.codigo;
import static practica6.Nodo.rutaImagen;

public class Insertar extends javax.swing.JFrame {
    static Nodo nuevo = null, inicio = null, fin = null, auxiliar = null;
    
    public Insertar() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCodigo = new javax.swing.JTextField();
        btBuscar = new javax.swing.JButton();
        btInsertar = new javax.swing.JButton();
        imagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Codigo");

        btBuscar.setText("Buscar imagen");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        btInsertar.setText("insertar");
        btInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInsertarActionPerformed(evt);
            }
        });

        imagen.setText("Sin imagen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btInsertar)
                        .addGap(18, 18, 18)
                        .addComponent(btBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                        .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btInsertar)
                            .addComponent(btBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(139, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInsertarActionPerformed
        insertarfoto();
    }//GEN-LAST:event_btInsertarActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        insertarNodo(codigo, rutaImagen);
        mostrarImagen(rutaImagen);
        mostrar();
    }//GEN-LAST:event_btBuscarActionPerformed

    void insertarfoto() {
        JFileChooser explorador = new JFileChooser();
        String dirtmp;

        try{

            explorador.addChoosableFileFilter(
              new FileNameExtensionFilter
                ("Imágenes","jpg","png","jpeg","gif"));

            explorador.showOpenDialog(null);//ventana de diálogo esta inicializada en null
            File auxFile;
            auxFile = explorador.getSelectedFile();
            dirtmp= auxFile.getAbsolutePath();

        } catch(Exception ex){
                    JOptionPane.showMessageDialog(null,
           "Error al abrir el archivo","Advertencia",
           JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
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
    
    void mostrarImagen(String ruta_imagen){
    try{
    if(fin==null){
      imagen.setText("pila vacia");
      imagen.setIcon(null);
    }else
    {    
    ImageIcon foto;
    foto = new ImageIcon(ruta_imagen);
    Icon icono;
    icono = new ImageIcon(foto.getImage()
            .getScaledInstance(imagen.getWidth(),imagen.getHeight()
                    ,Image.SCALE_DEFAULT));//adapatación al tamaño de la etiqueta
        imagen.setIcon(icono);
    }
    }catch(Exception ex){
            JOptionPane.showMessageDialog(null,
   "Error al abrir el archivo","Advertencia",
   JOptionPane.WARNING_MESSAGE);
        }

    }
    
    Nodo vacio() {
        return inicio = null;
    }
    
    void insertarNodo(String codigo, String rutaImagen) {
        nuevo = new Nodo(codigo, rutaImagen, null, fin);
        
        if (vacio() != vacio()) {
            nuevo.setAnterior(fin);
            fin.setSiguiente(nuevo);
            fin = nuevo;
        } else {
            inicio = nuevo;
            fin = nuevo;
        }
    }
    
    void mostrar() {
        Nodo auxiliar = inicio;
        
        if(vacio() != vacio()) {
            while(auxiliar != null) {
                System.out.println("codigo " + auxiliar.getCodigo());
                System.out.println("ruta imagen" + auxiliar.getRutaImagen());
                System.out.println("nodo anterior" + auxiliar.getAnterior());
                System.out.println("nodo siguiente" + auxiliar.getSiguiente());
                auxiliar.setSiguiente(auxiliar.getSiguiente());
            } 
        } else {
            System.out.println("vacio");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btInsertar;
    private javax.swing.JLabel imagen;
    private javax.swing.JTextField jCodigo;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}