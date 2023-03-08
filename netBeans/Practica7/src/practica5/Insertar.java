package practica5;
import javax.swing.*;
import java.io.File;
import java.awt.Color;
import java.awt.Image;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Insertar extends javax.swing.JFrame {
    static JFileChooser explorador = new JFileChooser();
    static Nodo auxiliar=null, cabeza = null, nuevo = null, inicio = null;
    static String dirtmp;
    static int contador = 0;
    int aux = 0;
    //ArregloObjetos array[] = new ArregloObjetos[10];
    static ArregloObjetos array[];
    static boolean flag = false;
    
    /*
    actual sirve para instanciar objetos nuevos y reservar memoria
    fin recibe la direccion nuevos de actual 
    
    */
     static void insertarPila(String codigo, String rutaImagen, Nodo siguiente) {
        nuevo = new Nodo(codigo,rutaImagen,siguiente);
        if (cabeza == null) {
            nuevo.setAnterior(nuevo);
            nuevo.setSiguiente(nuevo);
            inicio = nuevo;
            cabeza = nuevo;
        } else
            nuevo.setAnterior(cabeza);
            nuevo.setSiguiente(inicio);
            cabeza.setSiguiente(nuevo);
            inicio.setAnterior(nuevo);
            cabeza = nuevo;
    }
     
    static void mostrar() {
        if (cabeza != null) {
            nuevo = cabeza;
            while (nuevo != null) {
                System.out.println(nuevo.getCodigo());
                nuevo.getRutaImagen();
                //nuevo.setSiguiente(cabeza);
                nuevo = nuevo.getAnterior();
            }
        } else {
            System.out.println("la lista esta vacia");
        }
    }
     
    void insertarFoto(String dirImg) {
         try{
             /*
             explorador.addChoosableFileFilter(new FileNameExtensionFilter("imagenes", "jpg", "png", "jpeg", "gif"));
             explorador.showOpenDialog(null); //ventana de dialogo esta inici????
             File auxFile;
             auxFile = explorador.getSelectedFile();
             dirtmp = auxFile.getAbsolutePath();
             //visualizar imagen en el label
             */
             mostrarImagen(dirtmp);
             mostrar();
             contador++;
         } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "error al abrir el archivo", "advertencia", JOptionPane.WARNING_MESSAGE);
         }
    }
     
    void mostrarImagen(String dirImg) {
       ImageIcon foto;
       foto = new ImageIcon(dirImg);
       Icon icono;
       icono = new ImageIcon(foto.getImage().getScaledInstance(480,320, Image.SCALE_DEFAULT));
       labImagen.setText(" ");
       labImagen.setIcon(icono);
       System.out.println(auxiliar);
    }
    
    public Insertar() {
        initComponents();
        panel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btInsertarImagen = new javax.swing.JButton();
        btIzquierda = new javax.swing.JButton();
        imagen = new javax.swing.JPanel();
        labImagen = new javax.swing.JLabel();
        btBuscar = new javax.swing.JButton();
        btRegresar = new javax.swing.JButton();
        btLista = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        shell = new javax.swing.JButton();
        bubble = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labCodigo.setText("Código");

        btInsertarImagen.setText("Insertar imagen");
        btInsertarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInsertarImagenActionPerformed(evt);
            }
        });

        btIzquierda.setText("<--");
        btIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIzquierdaActionPerformed(evt);
            }
        });

        labImagen.setText("Sin imagen");
        labImagen.setMaximumSize(new java.awt.Dimension(1, 1));
        labImagen.setMinimumSize(new java.awt.Dimension(1, 1));

        javax.swing.GroupLayout imagenLayout = new javax.swing.GroupLayout(imagen);
        imagen.setLayout(imagenLayout);
        imagenLayout.setHorizontalGroup(
            imagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        imagenLayout.setVerticalGroup(
            imagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        btBuscar.setText("buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        btRegresar.setText("Regresar al menu");
        btRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegresarActionPerformed(evt);
            }
        });

        btLista.setText("ordenar");
        btLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListaActionPerformed(evt);
            }
        });

        shell.setText("shell");
        shell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shellActionPerformed(evt);
            }
        });

        bubble.setText("bubble");
        bubble.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubbleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(shell)
                .addGap(48, 48, 48)
                .addComponent(bubble)
                .addGap(0, 50, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shell)
                    .addComponent(bubble))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labCodigo)
                            .addGap(84, 84, 84)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btInsertarImagen)
                            .addGap(34, 34, 34)
                            .addComponent(btBuscar)
                            .addGap(60, 60, 60)
                            .addComponent(btIzquierda)
                            .addGap(94, 94, 94)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btLista)
                                .addComponent(btRegresar)))))
                .addContainerGap(401, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btInsertarImagen)
                            .addComponent(btIzquierda)
                            .addComponent(btBuscar))
                        .addGap(217, 217, 217)
                        .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labCodigo)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btRegresar)
                        .addGap(18, 18, 18)
                        .addComponent(btLista)
                        .addGap(26, 26, 26)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(379, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btInsertarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInsertarImagenActionPerformed
        //insertarFoto(dirtmp);//insertarFoto()
        mostrarImagen(dirtmp);
        auxiliar = cabeza;
        txtCodigo.setText("");
        
        if (inicio == null) {
           System.out.println("no hay nada");
       } else {
               System.out.println("codigo  " + auxiliar.getCodigo());
               System.out.println("codigo  " + auxiliar.getRutaImagen());
               System.out.println("acual  " + auxiliar);
               System.out.println("anterior  " + auxiliar.getAnterior());
               System.out.println("siguiente  " + auxiliar.getSiguiente());
               auxiliar = auxiliar.getSiguiente();
               flag = false;
       }
    }//GEN-LAST:event_btInsertarImagenActionPerformed

    private void btIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIzquierdaActionPerformed
        if (auxiliar == null) {
            auxiliar = cabeza;
            txtCodigo.setText(auxiliar.getCodigo());
            mostrarImagen(auxiliar.getRutaImagen());
        } else {
            auxiliar = auxiliar.getAnterior();
            txtCodigo.setText(auxiliar.getCodigo());
            mostrarImagen(auxiliar.getRutaImagen());
        }
    }//GEN-LAST:event_btIzquierdaActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
         explorador.addChoosableFileFilter(new FileNameExtensionFilter("imagenes", "jpg", "png", "jpeg", "gif"));
             explorador.showOpenDialog(null); //ventana de dialogo esta inici????
             File auxFile;
             auxFile = explorador.getSelectedFile();
             dirtmp = auxFile.getAbsolutePath();
        insertarPila(txtCodigo.getText(), dirtmp, null);
        contador++;
        System.out.println(contador);
    }//GEN-LAST:event_btBuscarActionPerformed

    private void btRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegresarActionPerformed
        Main ventana = new Main();
        ventana.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btRegresarActionPerformed

    private void btListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListaActionPerformed
        //se debe ordenar con shell y bubble
        /*
        String option = JOptionPane.showInputDialog(null, "1: shell sort o 2: bubble sort");
        int e = Integer.parseInt(option);
        if (e == 2) {
            burbuja();
        } else if (e == 1) {
            shell();
        } else {
            System.out.println("selecciona bien las opciones");
        }
        */
        panel.setVisible(true);
    }//GEN-LAST:event_btListaActionPerformed

    private void shellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shellActionPerformed
        shell();
        flag = true;
    }//GEN-LAST:event_shellActionPerformed

    private void bubbleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubbleActionPerformed
        burbuja();
        flag = true;
    }//GEN-LAST:event_bubbleActionPerformed

    void shell() {
        auxiliar = inicio;
        int size = contador;
        array = new ArregloObjetos[contador];

        if (inicio == null) {
            System.out.println("no hay nada");
        } else {
             for(int i = 0; i < contador; i++ ) {
             array[i] = new ArregloObjetos();
             array[i].codigos = Integer.parseInt(auxiliar.getCodigo());
             array[i].dirtmp = auxiliar.getRutaImagen();
             System.out.println(array[i].codigos);
             System.out.println(array[i].dirtmp);
                
             auxiliar = auxiliar.getSiguiente();
             }
        }

        for (int gap = size/2; gap > 0; gap /= 2) {//ordenamiento shell
            for (int i = gap; i < size; i += 1) {
                int temp = array[i].codigos;
                int j;
                for (j = i; j >= gap && array[j - gap].codigos > temp; j -= gap)
                array[j].codigos = array[j - gap].codigos;
                array[j].codigos = temp;
            }
        }

        for(int i = 0; i < contador ; i++) {
            System.out.println("codigos: **** " + array[i].codigos);
        }
    }
    
    void burbuja() {
        auxiliar = inicio;
        array = new ArregloObjetos[contador];
        
        if (inicio == null) {
           System.out.println("no hay nada");
       } else {
            for(int i = 0; i < contador; i++ ) {
            array[i] = new ArregloObjetos();
            array[i].codigos = Integer.parseInt(auxiliar.getCodigo());
            array[i].dirtmp = auxiliar.getRutaImagen();
            System.out.println(array[i].codigos);
            System.out.println(array[i].dirtmp);
               
            auxiliar = auxiliar.getSiguiente();
            }
       }
        
        for (int i = 0; i < contador - 1; i++) {
                for (int j = 0; j < contador - i - 1; j++) {
                    if (array[j].codigos > array[j + 1].codigos) {
                        // Intercambia los elementos en las posiciones j y j+1
                        int temp = array[j].codigos;
                        array[j].codigos = array[j + 1].codigos;
                        array[j + 1].codigos = temp;
                    }
                }
            }
        for(int i = 0; i < contador ; i++) {
            System.out.println("codigos: **** " + array[i].codigos);
        }
    }
    
    
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Insertar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btInsertarImagen;
    private javax.swing.JButton btIzquierda;
    private javax.swing.JButton btLista;
    private javax.swing.JButton btRegresar;
    private javax.swing.JButton bubble;
    private javax.swing.JPanel imagen;
    private javax.swing.JLabel labCodigo;
    private javax.swing.JLabel labImagen;
    private javax.swing.JPanel panel;
    private javax.swing.JButton shell;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}