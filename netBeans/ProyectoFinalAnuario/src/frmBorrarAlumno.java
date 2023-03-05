
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Ángel de la Torre Gómez
 */
public class frmBorrarAlumno extends javax.swing.JFrame {

    /**
     * Creates new form frmBorrarAlumno
     */
    Nodo aux;
    public frmBorrarAlumno() {
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

        btnBorrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblFrase = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnVer = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnBorrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Borrar alumno");

        lblFoto.setBackground(new java.awt.Color(255, 255, 255));
        lblFoto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblFoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFoto.setOpaque(true);

        lblEdad.setBackground(new java.awt.Color(255, 255, 255));
        lblEdad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEdad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEdad.setText("\"\"");
        lblEdad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblEdad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblEdad.setOpaque(true);

        lblFrase.setBackground(new java.awt.Color(255, 255, 255));
        lblFrase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFrase.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFrase.setText("\"\"");
        lblFrase.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblFrase.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblFrase.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFrase.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Edad:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Frase:");

        lblNombre.setBackground(new java.awt.Color(255, 255, 255));
        lblNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNombre.setText("\"\"");
        lblNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblNombre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblNombre.setOpaque(true);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Código:");

        btnVer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(255, 255, 102));
        btnRegresar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRegresar.setText("<");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBorrar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(6, 6, 6)
                                        .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5)
                                    .addComponent(lblFrase, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnVer))))
                            .addComponent(btnRegresar))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addComponent(btnRegresar)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(btnVer)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lblNombre)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lblEdad)))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addComponent(lblFrase, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(btnBorrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        if(txtCodigo.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Debe ingresar un código.","Aviso",JOptionPane.WARNING_MESSAGE);
        }else
        {
        eliminar(Principal.contador);
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        if (Principal.inicio != null) { //Hay espacios para borrar
            try
            {
                String dato = txtCodigo.getText();
                int del = Integer.parseInt(dato);
                aux = Principal.inicio;
                boolean datoEncontrado = false;
                int i = 0;
                while (i < Principal.contador && datoEncontrado == false) 
                //Mientras i sea menor a contador y el dato no haya sido encontrado, ejecuta el codigo
                {
                    if (del == aux.getCodigo()) 
                    {
                        datoEncontrado = true;
                    }else
                    {
                        datoEncontrado = false;
                        i++;
                        aux = aux.getSiguiente();
                    }
                }
                if (datoEncontrado==true) 
                {
                    lblNombre.setText(aux.getNombre());
                    lblEdad.setText(aux.getEdad());
                    lblFrase.setText(aux.getFrase());
                    Mostrar_imagen(aux.getRutaImagen());
                }else
                {
                    JOptionPane.showMessageDialog(null,"El codigo no existe. Prueba con otro nuevo");
                }
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, "ERROR. El código debe ser númerico");
            }
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        Principal rtn = new Principal();
        rtn.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(frmBorrarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmBorrarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmBorrarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmBorrarAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmBorrarAlumno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnVer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblFrase;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
    
 /***************************************
 * 
 *      METODOS
 * 
 ***************************************/
    public void eliminar(int contador) {
            String dato = txtCodigo.getText();
            int del = Integer.parseInt(dato);
            boolean datoEncontrado = false;
            aux = Principal.inicio;
            int i = 0;

            while(i < Principal.contador) 
            {
                 if (del == aux.getCodigo()) 
                    {
                        datoEncontrado = true;
                    }else
                    {
                        datoEncontrado = false;
                        aux = aux.getSiguiente();
                    }
                    i++;
            }

            if (datoEncontrado==true && Principal.contador == 1) {
                JOptionPane.showMessageDialog(null, "Minimo una imagen");
            }
            else if (datoEncontrado==true)
            {
                lblNombre.setText(" ");
                lblEdad.setText("");
                lblFrase.setText("");
                lblFoto.setIcon(null);
                lblFoto.setText("Sin foto");
                txtCodigo.setText("");
                aux.setCodigo(aux.getSiguiente().getCodigo());
                aux.setNombre(aux.getSiguiente().getNombre());
                aux.setFrase(aux.getSiguiente().getFrase());
                aux.setRutaImagen(aux.getSiguiente().getRutaImagen());
                aux.setEdad(aux.getSiguiente().getEdad());
                contador--;
                JOptionPane.showMessageDialog(null, "Dato eliminado con éxito.");
            }else
            {
                JOptionPane.showMessageDialog(null,"El codigo no existe. Prueba con otro nuevo");
            }
            /*
            if(aux == null) {
                aux = Principal.inicio;
                //txtCodigo.setText(auxiliar.getCodigo());
                //mostrarImagen(auxiliar.getRutaImagen());
            } else {
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
            }*/

    }
    void Mostrar_imagen(String ruta_imagen)
    {
        try{
            if(Principal.fin==null){
                lblFoto.setText("Lista vacia");
                lblFoto.setIcon(null);
                }else
                {    
                ImageIcon foto;
                foto = new ImageIcon(ruta_imagen);
                Icon icono;
                icono = new ImageIcon(foto.getImage()
                .getScaledInstance(lblFoto.getWidth(),lblFoto.getHeight()
                ,Image.SCALE_DEFAULT));//adapatación al tamaño de la etiqueta
                lblFoto.setIcon(icono);
                }
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,
                "Error al abrir el archivo","Advertencia",
                JOptionPane.WARNING_MESSAGE);
            }
    }
}


