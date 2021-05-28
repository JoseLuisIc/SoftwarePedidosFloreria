/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Supervisor;

import Controlador.controladorBaseDatos;
import Controlador.procesoBonoHilo;
import Modelo.modeloUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author joseluis.caamal
 */
public class vistaSupervisor extends javax.swing.JFrame {

    /**
     * Creates new form vistaSupervisor
     */
    DefaultComboBoxModel modeloComboE = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloComboFE = new DefaultComboBoxModel();
    controladorBaseDatos cbd = new controladorBaseDatos();
    DefaultTableModel modeloSupervisor;
    String[]  columna;
    public int ID;
    public int nEmpleado;
    public int filtros;
    public int estatusActual;
    public modeloUsuario mu = new modeloUsuario();
    public vistaSupervisor() {
    }
    
    public vistaSupervisor(modeloUsuario mu) {
        this.mu = mu;
        getColumnas();
        System.out.println(mu.toString());
        cbd.openConnection();
        modeloSupervisor = cbd.modeloSupervisores(columna, mu.getSucursal()); //Cargo el contenido por defecto
        cbd.closeConnection();
        
        initComponents();
        campoEstatus.removeAllItems();
        campoEmpleado.removeAllItems();
        cbd.openConnection();
        llenar_comboFE();
        cbd.closeConnection();
        cbd.openConnection();
        llenar_comboSt();
        cbd.closeConnection();
        cbd.openConnection();
        llenar_comboE();
        cbd.closeConnection();
        cbd.openConnection();
        campoNombreSucursal.setText(cbd.obtenerNombreSucursal(mu.getSucursal()));
        cbd.closeConnection();
        
        tablaCatalogoSupervisor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tablaCatalogoSupervisor.rowAtPoint(evt.getPoint());
                int col = 0;
                
                //col = 4;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoSupervisor.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoID.setText(valor);
                    //campoFiltroEstatus.setSelectedIndex(Integer.parseInt(valor));
                    ID = Integer.parseInt(valor);
                    System.out.println(valor);
                }
                
                col = 1;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoSupervisor.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoEmpleado.setSelectedItem(Integer.parseInt(valor));
                    nEmpleado = Integer.parseInt(valor);
                    System.out.println(valor);
                }
                
                col = 4;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoSupervisor.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoEstatus.setSelectedIndex(Integer.parseInt(valor));
                    campoFiltroEstatus.setSelectedIndex(Integer.parseInt(valor));
                    estatusActual = Integer.parseInt(valor);
                    System.out.println("estatusActual"+valor);
                }

            }
        });

        //DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100000);//Cada Minuto Se Actualiza 
                        RefrescarCCctionPerformed();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }
    
//    public void llenar_comboE() {
//        String Sql = "Select * from tabla_empleados where puesto = 1";
//        try {
//            
//            PreparedStatement us = cbd.Conexion.prepareStatement(Sql);
//            ResultSet res = us.executeQuery(); //
//            //modeloCombo.addElement("Seleccione un Campo");//es el primer registro q mostrara el combo
//            campoEmpleado.setModel(modeloComboE);//con esto lo agregamos al objeto al jcombobox
//            modeloComboE.addElement("Seleccione un Campo");
//                while (res.next()) {
//                       System.out.println(res.getObject("num_empleado"));
//                       modeloComboE.addElement(res.getObject("num_empleado"));
//                       campoEmpleado.setModel(modeloComboE);//con esto lo agregamos al objeto al jcombobox
//                }
//                res.close();
//            }
//            catch(SQLException ex){
//                System.out.println(ex.getMessage());
//            }
//    }
    public void llenar_comboSt() {
        String Sql = "Select * from tabla_estatus";
        try {
            
            PreparedStatement us = cbd.Conexion.prepareStatement(Sql);
            ResultSet res = us.executeQuery(); //
            //modeloCombo.addElement("Seleccione un Campo");//es el primer registro q mostrara el combo
            modeloCombo.addElement("Seleccione un Campo");
            campoEstatus.setModel(modeloCombo);//con esto lo agregamos al objeto al jcombobox
                while (res.next()) {
                        System.out.println(res.getObject("Name"));
                       modeloCombo.addElement(res.getObject("Name"));
                       campoEstatus.setModel(modeloCombo);//con esto lo agregamos al objeto al jcombobox
                }
                res.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
    }
    
    public void llenar_comboFE() {
        String Sql = "Select * from tabla_estatus";
        try {
            
            PreparedStatement us = cbd.Conexion.prepareStatement(Sql);
            ResultSet res = us.executeQuery(); //
            //modeloCombo.addElement("Seleccione un Campo");//es el primer registro q mostrara el combo
            modeloComboFE.addElement("Seleccione un Campo");
            campoFiltroEstatus.setModel(modeloComboFE);//con esto lo agregamos al objeto al jcombobox
                while (res.next()) {
//                       System.out.println(res.getObject("Name"));
                       modeloComboFE.addElement(res.getObject("Name"));
                       campoFiltroEstatus.setModel(modeloComboFE);//con esto lo agregamos al objeto al jcombobox
                }
                res.close();
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
    }
    
    public void llenar_comboE() {
        String Sql = "Select * from tabla_empleados where puesto = 1";
        try {
            
            PreparedStatement us = cbd.Conexion.prepareStatement(Sql);
            ResultSet res = us.executeQuery(); //
            //modeloCombo.addElement("Seleccione un Campo");//es el primer registro q mostrara el combo
            campoEmpleado.setModel(modeloComboE);//con esto lo agregamos al objeto al jcombobox
            modeloComboE.addElement("Seleccione un Campo");
                while (res.next()) {
                       System.out.println(res.getObject("num_empleado"));
                       modeloComboE.addElement(res.getObject("num_empleado"));
                       campoEmpleado.setModel(modeloComboE);//con esto lo agregamos al objeto al jcombobox
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
            "sucursal",
            "id_arreglo",
            "estatus",
            "tiempo_estimado",
            "hora_inicio",
            "hora_final",
           };
        return columna;
    }
    
    /*
        Refrescar la tabla :D
    */
    private void RefrescarCCctionPerformed() {                                            
        cbd.openConnection();
        modeloSupervisor = cbd.modeloSupervisores(columna, mu.getSucursal());
        cbd.closeConnection();
        tablaCatalogoSupervisor.setModel(modeloSupervisor);
        modeloSupervisor.fireTableDataChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campoNombreSucursal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        campoEstatus = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        campoEmpleado = new javax.swing.JComboBox<>();
        campoID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campoFiltroEstatus = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCatalogoSupervisor = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Catálogo Supervisor");

        jLabel3.setText("Sucursal:");

        campoNombreSucursal.setText("jLabel4");

        jLabel5.setText("Estatus:");

        campoEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Proceso:");

        jButton2.setText("Procesar Bono");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("N Empleado");

        campoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        campoID.setEditable(false);

        jLabel7.setText("ID");

        jButton3.setText("Cambiar Estatus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(campoEmpleado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(campoNombreSucursal)
                        .addGap(105, 105, 105))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(campoEstatus, 0, 177, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoID, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(campoNombreSucursal))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(campoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(campoEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Filtros:");

        campoFiltroEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Estatus:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(10, 10, 10)
                            .addComponent(campoFiltroEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoFiltroEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tablaCatalogoSupervisor.setModel(modeloSupervisor);
        jScrollPane1.setViewportView(tablaCatalogoSupervisor);

        jLabel9.setText("*Nota: Al actualizar a autorizado ya pueden procesar el bono actual.");

        jLabel10.setText("*Nota: La tabla se actualiza cada minuto.");

        jLabel11.setText("*Solo se puede cambiar el estatus si  la columna está terminada.");

        jLabel12.setText("*Solo se puede cambiar el estatus si  la columna está terminada.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(5, 5, 5)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cbd.openConnection();
        int filtro = campoFiltroEstatus.getSelectedIndex();
//        System.out.println("Filtros: "+filtros);
        modeloSupervisor = cbd.modeloSupervisores(columna, mu.getSucursal(),filtro);
        cbd.closeConnection();
        tablaCatalogoSupervisor.setModel(modeloSupervisor);
        modeloSupervisor.fireTableDataChanged();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(campoEstatus.getSelectedIndex() == 4 && estatusActual == 3){
        cbd.openConnection();
        int e = cbd.actualizarEstatusAutorizado(ID, nEmpleado,4);
        cbd.closeConnection();
        if(e == 0){
            JOptionPane.showMessageDialog(null, "El proceso no se ha autorizado.");
        }
        else{
            cbd.openConnection();
            cbd.actualizaBono(ID, nEmpleado, 1);
            cbd.closeConnection();
            JOptionPane.showMessageDialog(null, "El proceso ha terminado con éxito, se ha autorizado.");
            RefrescarCCctionPerformed();
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "El proceso debe estar en terminado para actualizar.");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        procesoBonoHilo pbh = new procesoBonoHilo();
        pbh.iniciarProcesoBono();
    }//GEN-LAST:event_jButton2ActionPerformed

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
//            java.util.logging.Logger.getLogger(vistaSupervisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(vistaSupervisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(vistaSupervisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(vistaSupervisor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new vistaSupervisor().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> campoEmpleado;
    private javax.swing.JComboBox<String> campoEstatus;
    private javax.swing.JComboBox<String> campoFiltroEstatus;
    private javax.swing.JTextField campoID;
    private javax.swing.JLabel campoNombreSucursal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tablaCatalogoSupervisor;
    // End of variables declaration//GEN-END:variables
}
