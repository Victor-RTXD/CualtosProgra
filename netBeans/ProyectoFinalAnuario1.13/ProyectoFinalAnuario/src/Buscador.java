import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Buscador extends javax.swing.JFrame {

    /**
     * Descripcion: desde el constructor llena los campos de nombre, edad, codigo y frase del
     * alumno encontrado en la clase frmVer con binary search
     */
    public Buscador() {
        initComponents();
        
        ImageIcon foto;
            foto = new ImageIcon(frmIngresar.array[frmVer.index].dirtmp);
            Icon icono;
            icono = new ImageIcon(foto.getImage().getScaledInstance(480,320, Image.SCALE_DEFAULT));
            lblFoto.setText(" ");
            lblFoto.setIcon(icono);
            
            nombreEncontrado.setText(frmIngresar.array[frmVer.index].nombre);
            edadEncontrado.setText(frmIngresar.array[frmVer.index].edad);
            codigoEncontrado.setText(Integer.toString(frmIngresar.array[frmVer.index].codigos));
            fraseEncontrado.setText(frmIngresar.array[frmVer.index].frase);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombreEncontrado = new javax.swing.JTextField();
        codigoEncontrado = new javax.swing.JTextField();
        fraseEncontrado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        edadEncontrado = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();
        buscadorFoto = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nombreEncontrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreEncontradoActionPerformed(evt);
            }
        });

        jLabel1.setText("Codgio: ");

        jLabel2.setText("Nombre: ");

        jLabel3.setText("Edad: ");

        jLabel4.setText("Frase: ");

        lblFoto.setBackground(new java.awt.Color(255, 255, 255));
        lblFoto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setText("Sin foto");
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblFoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFoto.setOpaque(true);

        btnRegresar.setBackground(new java.awt.Color(255, 255, 102));
        btnRegresar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRegresar.setText("<");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        buscadorFoto.setText("Buscar foto");
        buscadorFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscadorFotoActionPerformed(evt);
            }
        });

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edadEncontrado)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(codigoEncontrado, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreEncontrado, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fraseEncontrado)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnRegresar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(Guardar)
                        .addGap(32, 32, 32)
                        .addComponent(buscadorFoto)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombreEncontrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(codigoEncontrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edadEncontrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(23, 23, 23)
                        .addComponent(fraseEncontrado, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnRegresar)
                        .addGap(78, 78, 78)
                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guardar)
                    .addComponent(buscadorFoto))
                .addContainerGap(201, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        frmVer rtn = new frmVer();
        rtn.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void nombreEncontradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreEncontradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreEncontradoActionPerformed

    private void buscadorFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscadorFotoActionPerformed
        frmIngresar.insertarFoto();//El usuario inserta la foto
        try{
            if(Principal.fin==null){
                lblFoto.setText("Lista vacia");
                lblFoto.setIcon(null);
                }else
                {    
                ImageIcon foto;
                foto = new ImageIcon(frmIngresar.dirtmp);
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
        lblFoto.setText("");
    }//GEN-LAST:event_buscadorFotoActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        if (nombreEncontrado.getText().equals("") || edadEncontrado.getText().equals("") || fraseEncontrado.getText().equals("") || frmIngresar.dirtmp == "") {
            JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacío. Vuelva a ingresar de nuevo.");
            //Borra campos de textos e reinicializa
                nombreEncontrado.setText("");
                edadEncontrado.setText("");
                fraseEncontrado.setText("");
                lblFoto.setIcon(null);
                lblFoto.setText("Sin foto");
        } else {
            try {
                frmIngresar.array[frmVer.index].nombre = nombreEncontrado.getText();
                frmIngresar.array[frmVer.index].edad = edadEncontrado.getText();
                frmIngresar.array[frmVer.index].frase = fraseEncontrado.getText();
                frmIngresar.array[frmVer.index].dirtmp = frmIngresar.dirtmp;

                nombreEncontrado.setText(frmIngresar.array[frmVer.index].nombre + "se guardo");
                edadEncontrado.setText(frmIngresar.array[frmVer.index].edad);
                fraseEncontrado.setText(frmIngresar.array[frmVer.index].frase);
                
                int x = Integer.parseInt(edadEncontrado.getText()); //Comprueba que haya insertado un numero
                JOptionPane.showMessageDialog(null, "Los datos se han guardado con exito");
                
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "ERROR. Debe ingresar valores numericos en Codigo y/o edad");
            }
            
        }
    }//GEN-LAST:event_GuardarActionPerformed

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
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Buscador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Guardar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton buscadorFoto;
    private javax.swing.JTextField codigoEncontrado;
    private javax.swing.JTextField edadEncontrado;
    private javax.swing.JTextField fraseEncontrado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JTextField nombreEncontrado;
    // End of variables declaration//GEN-END:variables
}