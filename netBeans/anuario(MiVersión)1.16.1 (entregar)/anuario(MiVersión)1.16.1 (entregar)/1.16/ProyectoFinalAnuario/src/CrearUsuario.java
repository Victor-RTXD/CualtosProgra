
import java.awt.Color;
import javax.swing.JOptionPane;



/**
 *
 * @author DELL
 */
public class CrearUsuario extends javax.swing.JFrame {

    String contra, usuario;
    User nuevo = new User(contra, usuario);
    static NodoAdmin objAdmin = new NodoAdmin();
    
    static int contAdmin = 0; //Contar el numero de administradores ingresados
    
    static boolean usad = true; // Creada para mostrar o eliminar botones segun los permisos
    //Con los que ingrese el usuario (Administrador o usuario) Verdadero indica que se trata de un administrador
      
    public CrearUsuario() {
        initComponents();
        btnEntrar.setVisible(false);
        if (contAdmin == 0) 
        {
            btnCrearUsuario.setVisible(true);
            btnIngresar.setVisible(false);
        }else {
            btnCrearUsuario.setVisible(false);
            btnIngresar.setVisible(true);
            lblTitulo.setText("Ingresar al anuario");
                
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        lblContra = new javax.swing.JLabel();
        btnCrearUsuario = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();
        selUsuario = new javax.swing.JComboBox<>();
        btnEntrar = new javax.swing.JButton();
        txtContra = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(68, 114, 196));
        setResizable(false);

        lblTitulo.setBackground(new java.awt.Color(68, 114, 196));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Crear usuario");
        lblTitulo.setOpaque(true);

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsuario.setText("Usuario:");

        lblContra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblContra.setText("Contraseña:");

        btnCrearUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCrearUsuario.setMnemonic('c');
        btnCrearUsuario.setText("Crear");
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });

        btnIngresar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnIngresar.setMnemonic('i');
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        selUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        selUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Usuario" }));
        selUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selUsuarioActionPerformed(evt);
            }
        });

        btnEntrar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEntrar.setMnemonic('c');
        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        txtContra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario)
                    .addComponent(lblContra)
                    .addComponent(btnIngresar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnEntrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(btnCrearUsuario))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(selUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(txtContra, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContra)
                    .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearUsuario)
                    .addComponent(btnIngresar)
                    .addComponent(btnEntrar))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        
        nuevo.setUser(txtUsuario.getText());
        //usuario[1] = nuevo.getUser();
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
        /**
         * Crea el primer administrador al primer ingreso del programa
         */
        if(txtUsuario.getText().equals("")|| txtContra.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Por favor ingresa datos");
        }else{
            objAdmin.insertarCola(txtUsuario.getText(), txtContra.getText());
            contAdmin++;
            Principal rtn = new Principal();
            rtn.setVisible(true);
            this.setVisible(false);
            usad = true;
        }
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        /**
         * Cuando ya existen usuarios
         *      ** Funcion para ingresar al sistema segun los administradoes ingresados
         */
        if (objAdmin.getInicio() != null) //Existen administradores
        {
            NodoAdmin aux = new NodoAdmin();
            aux = objAdmin.getInicio();
            if(txtUsuario.getText().equals("") || txtContra.getText().equals("")){//Cambps vacios
                JOptionPane.showMessageDialog(null, "Debe de rellenar ambos campos");
            }
            for (int i = 0; i < contAdmin; i++) {//Busqueda de administradores
                if(txtUsuario.getText().equals(aux.getNombreUsuario()) && txtContra.getText().equals(aux.getContrasenya())) //Admin encontrado
                 {//Accsede como administrador
                    Principal rtn = new Principal();
                     rtn.setVisible(true);
                    this.setVisible(false);
                    usad = true;
                    break;
                }else
                {
                    if (aux.getSiguiente() != null) 
                    {
                        aux = aux.getSiguiente();
                    }else
                    {
                        JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }    
        }else
        {
          JOptionPane.showMessageDialog(null, "No existen administradores en el programa, crea uno","Aviso",JOptionPane.WARNING_MESSAGE);
        }
            
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void selUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selUsuarioActionPerformed
        /**
         * JComboBox 
         * Selecciona el tipo de usuario que va ingresar el programa
         */
        if (selUsuario.getSelectedItem() == "Usuario") {
            lblUsuario.setVisible(false);
            lblContra.setVisible(false);
            txtUsuario.setVisible(false);
            txtContra.setVisible(false);
            btnCrearUsuario.setVisible(false);
            btnIngresar.setVisible(false);
            btnEntrar.setVisible(true);
            usad = false;//Usuario vista
        }else
        {
            lblUsuario.setVisible(true);
            lblContra.setVisible(true);
            txtUsuario.setVisible(true);
            txtContra.setVisible(true);
            btnCrearUsuario.setVisible(true);
            btnIngresar.setVisible(true);
            btnEntrar.setVisible(false);
            usad = true;//Usuario administrador
        }
    }//GEN-LAST:event_selUsuarioActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        Principal en = new Principal();
        en.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnEntrarActionPerformed

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
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel lblContra;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JComboBox<String> selUsuario;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
