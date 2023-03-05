import javax.swing.JOptionPane;

/**
 *
 * @author Luis Ángel de la Torre Gómez
 */

/**
 * Anuario Ver 0.1
 * Contiene todos los visuales del programa, contando con las clases base de:
 * -Crear Usuario
 * -Principal
 * -frmIngresar
 * -frmVer
 * 
 * Anuario Ver 0.2
 * Se agrego la clase Nodo
 * 
 * Anuario Ver 0.3
 * Se agrego la clase User, asi como botenes para desplazamiento entre pestanas
 * 
 * Anuario Ver 1.0
 * Se le dio funcionalidad a frmIngresar, eso incluye botones e ingresar datos 
 * Se ingresaron metodos anteriormente contenidos en la clase nodo a cada pestana individual para mas facilidad de acceso
 * Creacion de las clase frmBorrarAlumno
 * 
 * Anuario Ver 1.1
 * Se puede ver unicamente el ultimo nodo existente en la clase frmVer (por alguna razon xd)
 * Se agregaron un par de botones para mayor facilidad de desplazamiento
 * 
 * Anuario 1.11 (Luis Angel de la Torre Gomez)
 * Funcionalidad de visualizar creada
 * Se agregaron correciones en las interfaces
 * Se crearon algunos parches en el programa
 * correccion variable i en funcion borrar
 * 
 * Trabajo faltante:
 * Que se guarden contrasenas y usuarios
 * Que se verifiquen que si se ingreso la contrasena y/o usuario
 * Hacer funcionar la clase frmBorrar
 * Mejorar los visuales de la clase Principal - Opcional
 * 
 */

public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    static Nodo fin, inicio;
    public static int contador = 0;
    public Principal() {
        initComponents();
        btnReturn.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIngr = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnReturn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnIngr.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnIngr.setMnemonic('i');
        btnIngr.setText("Ingresar alumno");
        btnIngr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngrActionPerformed(evt);
            }
        });

        btnVer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnVer.setMnemonic('v');
        btnVer.setText("Ver anuario");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        btnBorrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBorrar.setMnemonic('b');
        btnBorrar.setText("Borrar alumno");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setText("ANUARIO ESCOLAR");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        btnReturn.setText("Regresar");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnVer)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 36, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnReturn)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBorrar)
                                    .addComponent(btnIngr))))
                        .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(btnIngr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(68, 68, 68)))
                .addComponent(btnVer)
                .addGap(18, 18, 18)
                .addComponent(btnReturn)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngrActionPerformed
        frmIngresar insert = new frmIngresar();
        insert.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnIngrActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        if (inicio==null) {
            JOptionPane.showMessageDialog(null,"Debe ingresar alumnos primero","Aviso", JOptionPane.WARNING_MESSAGE);
        }else{
        frmVer ver = new frmVer();
        ver.setVisible(true);
        this.setVisible(false);
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        //String dato = JOptionPane.showInputDialog(null, "ingresa el dato que quieras borrar:", "mensaje", JOptionPane.PLAIN_MESSAGE);
        if (inicio==null) {
            JOptionPane.showMessageDialog(null,"Debe ingresar alumnos primero","Aviso", JOptionPane.WARNING_MESSAGE);
        }else{
        frmBorrarAlumno borrar = new frmBorrarAlumno();
        borrar.setVisible(true);
        this.setVisible(false);
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
       
        CrearUsuario rtn = new CrearUsuario();
        rtn.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnReturnActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnIngr;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnVer;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}