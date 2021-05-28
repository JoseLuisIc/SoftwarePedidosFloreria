/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Usuario;

import Controlador.controladorBaseDatos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joseluis.caamal
 */
public class vistaUsuarios extends javax.swing.JFrame {

    /**
     * Creates new form vistaPuestos
     */
    controladorBaseDatos cbd = new controladorBaseDatos();
    DefaultTableModel modeloUsuarios;
    String[]  columna;
    public String ID = "";
    int operacionExitoso = 0;
    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloComboS = new DefaultComboBoxModel();
    public vistaUsuarios() {
        getColumnas();
        cbd.openConnection();
        modeloUsuarios = cbd.modeloUsuario(columna); //Cargo el contenido por defecto
        cbd.closeConnection();
        initComponents();
        cbd.openConnection();
        llenar_comboS();
        llenar_comboP();
        cbd.closeConnection();
        tablaCatalogoUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tablaCatalogoUsuarios.rowAtPoint(evt.getPoint());
                int col = 0;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoUsuarios.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoID.setText(valor);
                    ID = valor;
                }
                
                col = 1;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoUsuarios.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoNumEmpleado.setText(valor);
                }
                
                col = 2;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoUsuarios.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoNombre.setText(valor);
                }
                
                col = 3;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoUsuarios.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoUsuario.setText(valor);
                }
                
                col = 4;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoUsuarios.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoPassword.setText(valor);
                }
                
                col = 5;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoUsuarios.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoSucursal.setSelectedIndex(Integer.parseInt(valor));
                }
                
                col = 6;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoUsuarios.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoPuesto.setSelectedIndex(Integer.parseInt(valor));
                }

            }
        });

    }
    public void llenar_comboS() {
        String Sql = "Select * from table_puesto";
        try {
            
            PreparedStatement us = cbd.Conexion.prepareStatement(Sql);
            ResultSet res = us.executeQuery(); //
            //modeloCombo.addElement("Seleccione un Campo");//es el primer registro q mostrara el combo
            modeloCombo.addElement("Seleccione un Campo");
            campoPuesto.setModel(modeloCombo);//con esto lo agregamos al objeto al jcombobox
                while (res.next()) {
                        System.out.println(res.getObject("Name"));
                       modeloCombo.addElement(res.getObject("Name"));
                       campoPuesto.setModel(modeloCombo);//con esto lo agregamos al objeto al jcombobox
                }
                res.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
    }
    public void llenar_comboP() {
        String Sql = "Select * from tabla_sucursal";
        try {
            
            PreparedStatement us = cbd.Conexion.prepareStatement(Sql);
            ResultSet res = us.executeQuery(); //
            //modeloCombo.addElement("Seleccione un Campo");//es el primer registro q mostrara el combo
            campoSucursal.setModel(modeloComboS);//con esto lo agregamos al objeto al jcombobox
            modeloComboS.addElement("Seleccione un Campo");
                while (res.next()) {
                        System.out.println(res.getObject("Name"));
                       modeloComboS.addElement(res.getObject("Name"));
                       campoSucursal.setModel(modeloComboS);//con esto lo agregamos al objeto al jcombobox
                }
                res.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
    }
    /*Inicio: Se adquieren las columnas para la tabla.
    Author: Jose Caamal Ic
    22/05/2021*/
    String[] getColumnas(){ //Columnas
           columna = new String[] {"id",
            "num_empleado",
            "nombre",
           "usuario",
           "password",
           "sucursal",
           "puesto",
           };
        return columna;
    }
    
     private void RefrescarCCctionPerformed(java.awt.event.ActionEvent evt) {                                            
        cbd.openConnection();
        modeloUsuarios = cbd.modeloUsuario(columna);
        cbd.closeConnection();
        tablaCatalogoUsuarios.setModel(modeloUsuarios);
        modeloUsuarios.fireTableDataChanged();
    }  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCatalogoUsuarios = new javax.swing.JTable();
        botonAnadir = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        Usuarios = new javax.swing.JLabel();
        campoID = new javax.swing.JTextField();
        campoNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        campoUsuario = new javax.swing.JTextField();
        campoNumEmpleado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        campoPuesto = new javax.swing.JComboBox<>();
        campoSucursal = new javax.swing.JComboBox<>();
        campoPassword = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tablaCatalogoUsuarios.setModel(modeloUsuarios);
        jScrollPane1.setViewportView(tablaCatalogoUsuarios);

        botonAnadir.setText("Añadir");
        botonAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnadirActionPerformed(evt);
            }
        });

        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonEditar.setText("Editar");
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });

        Usuarios.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Usuarios.setText("Catalógo Puestos");

        campoID.setEditable(false);

        jLabel2.setText("Nombre");

        jLabel3.setText("ID");

        jLabel4.setText("Password");

        jLabel5.setText("Usuario");

        jLabel6.setText("Sucursal");

        jLabel7.setText("Puesto");

        campoPuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        campoSucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("N Empleado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonAnadir)
                        .addGap(39, 39, 39)
                        .addComponent(botonEliminar)
                        .addGap(48, 48, 48)
                        .addComponent(botonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Usuarios)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(46, 46, 46)
                                        .addComponent(campoID, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(campoNombre)
                                            .addComponent(campoSucursal, 0, 233, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoUsuario)
                                    .addComponent(campoNumEmpleado)
                                    .addComponent(campoPuesto, 0, 233, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(campoPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Usuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(campoNumEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campoPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(campoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(campoSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAnadir)
                    .addComponent(botonEliminar)
                    .addComponent(botonEditar))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirActionPerformed
        // TODO add your handling code here:
        vistaAgregarUsuario vau = new vistaAgregarUsuario(this, rootPaneCheckingEnabled);
        vau.show();
        RefrescarCCctionPerformed(evt);
    }//GEN-LAST:event_botonAnadirActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        // TODO add your handling code here:
        cbd.openConnection();
        operacionExitoso = cbd.eliminarFila("tabla_empleados", ID);
        cbd.closeConnection();
        RefrescarCCctionPerformed(evt);
        if(operacionExitoso == 1){
            JOptionPane.showMessageDialog(null, "Proceso Exitoso");
        }
        else{
            JOptionPane.showMessageDialog(null, "Proceso, No Exitoso");
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        // TODO add your handling code here:
        if(campoPuesto.getSelectedIndex() != 0 && campoSucursal.getSelectedIndex() !=0){
                cbd.openConnection();
                operacionExitoso = cbd.actualizarUsuario(campoID.getText(),
                        campoNombre.getText(),
                        Integer.parseInt(campoNumEmpleado.getText()),
                        campoUsuario.getText(),
                        campoPassword.getText(),
                        campoSucursal.getSelectedIndex(),
                        campoPuesto.getSelectedIndex());
                cbd.closeConnection();
                RefrescarCCctionPerformed(evt);
                if(operacionExitoso == 1){
                    JOptionPane.showMessageDialog(null, "Proceso Exitoso");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Proceso, No Exitoso");
                }
        }
        else{
        
            JOptionPane.showMessageDialog(null, "Selecciona un campo en Sucursal y Puesto");
        }
    }//GEN-LAST:event_botonEditarActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(vistaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(vistaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(vistaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(vistaUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new vistaUsuarios().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Usuarios;
    private javax.swing.JButton botonAnadir;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JTextField campoID;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoNumEmpleado;
    private javax.swing.JTextField campoPassword;
    private javax.swing.JComboBox<String> campoPuesto;
    private javax.swing.JComboBox<String> campoSucursal;
    private javax.swing.JTextField campoUsuario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCatalogoUsuarios;
    // End of variables declaration//GEN-END:variables
}
