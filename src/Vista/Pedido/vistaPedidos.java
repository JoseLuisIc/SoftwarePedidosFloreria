/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Pedido;

import Vista.Usuario.*;
import Controlador.controladorBaseDatos;
import Controlador.cronometroHilo;
import Modelo.modeloPedido;
import Modelo.modeloUsuario;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joseluis.caamal
 */
public class vistaPedidos extends javax.swing.JFrame {

    /**
     * Creates new form vistaPuestos
     */
    controladorBaseDatos cbd = new controladorBaseDatos();
    DefaultTableModel modeloPedidos;
    String[]  columna;
    public String ID = "";
    int operacionExitoso = 0;
    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloComboS = new DefaultComboBoxModel();
    DefaultComboBoxModel modeloComboE = new DefaultComboBoxModel();
    public modeloUsuario mu = new modeloUsuario();
    public modeloPedido mp = new modeloPedido();
    public vistaPedidos() {
        getColumnas();
        cbd.openConnection();
        modeloPedidos = cbd.modeloPedidos(columna); //Cargo el contenido por defecto
        cbd.closeConnection();
        initComponents();
        campoArreglo.removeAllItems();
        campoEstatus.removeAllItems();
        campoEmpleado.removeAllItems();
        cbd.openConnection();
        llenar_comboSt();
        cbd.closeConnection();
        cbd.openConnection();
        llenar_comboA();
        cbd.closeConnection();
        cbd.openConnection();
        llenar_comboE();
        cbd.closeConnection();
        tablaCatalogoPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tablaCatalogoPedidos.rowAtPoint(evt.getPoint());
                int col = 0;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoID.setText(valor);
                    ID = valor;
                }
                
                col = 1;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoEmpleado.setSelectedItem(Integer.parseInt(valor));
                }
                
                
                col = 2;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoArreglo.setSelectedIndex(Integer.parseInt(valor));
                }
                col = 3;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoEstatus.setSelectedIndex(Integer.parseInt(valor));
                }
                
                col = 4;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoTiempoEstimado.setText(valor);
                }
                
                col = 5;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoFechaInicio.setDate(cbd.convertirFechaString(valor));
                }
                
                col = 6;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoFechaFin.setDate(cbd.convertirFechaString(valor));
                }

            }
        });

    }
    
    public vistaPedidos(modeloUsuario mu) {
        this.mu = mu;
        getColumnas();
        cbd.openConnection();
        //modeloPedidos = cbd.modeloPedidos(columna); //Cargo el contenido por defecto
        if(mu.getPuesto() == 3){
            modeloPedidos = cbd.modeloPedidos(columna);
        }
        else{
            modeloPedidos = cbd.modeloPedidosUser(columna, mu.getNum_empleado());
        }
        cbd.closeConnection();
        initComponents();
        campoArreglo.removeAllItems();
        campoEstatus.removeAllItems();
        campoEmpleado.removeAllItems();
        cbd.openConnection();
        llenar_comboSt();
        cbd.closeConnection();
        cbd.openConnection();
        llenar_comboA();
        cbd.closeConnection();
        cbd.openConnection();
        llenar_comboE();
        cbd.closeConnection();
        tablaCatalogoPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tablaCatalogoPedidos.rowAtPoint(evt.getPoint());
                int col = 0;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoID.setText(valor);
                    ID = valor;
                }
                
                col = 1;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoEmpleado.setSelectedItem(Integer.parseInt(valor));
                }
                
                
                col = 2;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoArreglo.setSelectedIndex(Integer.parseInt(valor));
                }
                col = 3;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoEstatus.setSelectedIndex(Integer.parseInt(valor));
                }
                
                col = 4;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoTiempoEstimado.setText(valor);
                }
                
                col = 5;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoFechaInicio.setDate(cbd.convertirFechaString(valor));
                }
                
                col = 6;
                if (row >= 0 && col >= 0) {
                    String valor = tablaCatalogoPedidos.getModel().getValueAt(row, col).toString(); //Tomo el valor de el modelo de la tabla
                    campoFechaFin.setDate(cbd.convertirFechaString(valor));
                }

            }
        });
        /*Desactivo el boton añadir y eliminar si no es administrador*/
        if(mu.getPuesto() == 1 ){
            botonAnadir.setEnabled(false);
            botonEliminar.setEnabled(false);
            campoArreglo.setEnabled(false);
            campoEmpleado.setEnabled(false);
            campoFechaInicio.setEnabled(false);
            campoFechaFin.setEnabled(false);
        } else{
            if( mu.getPuesto() == 2){
                botonAnadir.setEnabled(false);
                botonEliminar.setEnabled(false);
                campoArreglo.setEnabled(false);
                campoEmpleado.setEnabled(false);
                campoFechaInicio.setEnabled(false);
                campoFechaFin.setEnabled(false);
            }
        }
        
         campoEstatus.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if ( e.getStateChange() == ItemEvent.SELECTED ) 
                {
                    int item = campoEstatus.getSelectedIndex();
                    //item = cbd.obtenTiempoEstimado(item);
                    System.out.println("El item es:"+item);
                    if(item != 2){
                        botonEditar.setEnabled(false);
                        //campoID.setText("");
                    }
                    else{
                        botonEditar.setEnabled(true);
                        
                    }
                    //campoTiempoEstimado.setText(String.valueOf(item));
                }
            }
        });
         
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1500);
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
    public void llenar_comboA() {
        String Sql = "Select * from tabla_arreglos";
        try {
            
            PreparedStatement us = cbd.Conexion.prepareStatement(Sql);
            ResultSet res = us.executeQuery(); //
            //modeloCombo.addElement("Seleccione un Campo");//es el primer registro q mostrara el combo
            campoArreglo.setModel(modeloComboS);//con esto lo agregamos al objeto al jcombobox
            modeloComboS.addElement("Seleccione un Campo");
                while (res.next()) {
                        System.out.println(res.getObject("nombre"));
                       modeloComboS.addElement(res.getObject("nombre"));
                       campoArreglo.setModel(modeloComboS);//con esto lo agregamos al objeto al jcombobox
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
            "id_arreglo",
           "estatus",
           "tiempo_estimado",
           "hora_inicio",
           "hora_final",
           };
        return columna;
    }
    
    private void RefrescarCCctionPerformed() {                                            
        cbd.openConnection();
        // System.out.println(this.mu.getPuesto());
        if(mu.getPuesto() == 3){
            modeloPedidos = cbd.modeloPedidos(columna);
            
        }else{
           modeloPedidos = cbd.modeloPedidosUser(columna, mu.getNum_empleado());
        }
        cbd.closeConnection();
        tablaCatalogoPedidos.setModel(modeloPedidos);
        modeloPedidos.fireTableDataChanged();
    }  
    
     private void RefrescarCCctionPerformed(java.awt.event.ActionEvent evt) {                                            
        cbd.openConnection();
        // System.out.println(this.mu.getPuesto());
        if(mu.getPuesto() == 3){
            modeloPedidos = cbd.modeloPedidos(columna);
            
        }else{
           modeloPedidos = cbd.modeloPedidosUser(columna, mu.getNum_empleado());
        }
        cbd.closeConnection();
        tablaCatalogoPedidos.setModel(modeloPedidos);
        modeloPedidos.fireTableDataChanged();
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
        tablaCatalogoPedidos = new javax.swing.JTable();
        botonAnadir = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        Usuarios = new javax.swing.JLabel();
        campoID = new javax.swing.JTextField();
        Arreglo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        campoEmpleado = new javax.swing.JComboBox<>();
        campoTiempoEstimado = new javax.swing.JTextField();
        campoFechaFin = new com.toedter.calendar.JDateChooser();
        campoFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        campoArreglo = new javax.swing.JComboBox<>();
        campoEstatus = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tablaCatalogoPedidos.setModel(modeloPedidos);
        jScrollPane1.setViewportView(tablaCatalogoPedidos);

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
        Usuarios.setText("Catalógo Pedidos");

        campoID.setEditable(false);

        Arreglo.setText("Arreglo");

        jLabel3.setText("ID");

        jLabel4.setText("Tiempo Estimado");

        jLabel5.setText("Estatus");

        jLabel6.setText("Empleado");

        campoEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        campoTiempoEstimado.setEditable(false);

        jLabel9.setText("Fecha Inicio");

        jLabel10.setText("Fecha Fin");

        campoArreglo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        campoArreglo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                campoArregloItemStateChanged(evt);
            }
        });

        campoEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("*Nota: Selecciona el campo y cambia el estatus para editar.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(Arreglo)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoEmpleado, 0, 233, Short.MAX_VALUE)
                                    .addComponent(campoArreglo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoEstatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoTiempoEstimado, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonAnadir)
                        .addGap(45, 45, 45)
                        .addComponent(botonEliminar)
                        .addGap(59, 59, 59)
                        .addComponent(botonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(Usuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(campoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(campoTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Arreglo)
                                .addComponent(campoArreglo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(campoEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))))
                    .addComponent(campoFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAnadir)
                    .addComponent(botonEliminar)
                    .addComponent(botonEditar)
                    .addComponent(jLabel1))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnadirActionPerformed
        // TODO add your handling code here:
        vistaAgregarPedido vap = new vistaAgregarPedido(this, rootPaneCheckingEnabled);
        vap.show();
        RefrescarCCctionPerformed(evt);
    }//GEN-LAST:event_botonAnadirActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        // TODO add your handling code here:
        cbd.openConnection();
        operacionExitoso = cbd.eliminarFila("tabla_pedidos", ID);
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
        if(campoEmpleado.getSelectedIndex() != 0 && campoEstatus.getSelectedIndex() ==2 && campoArreglo.getSelectedIndex() !=0){
                cbd.openConnection();
                operacionExitoso = cbd.actualizarPedidos(campoID.getText(),
                        (int)campoEmpleado.getSelectedItem(),
                        campoArreglo.getSelectedIndex(),
                        campoEstatus.getSelectedIndex(),
                        Integer.parseInt(campoTiempoEstimado.getText()),
                        campoFechaInicio.getDate(),
                        campoFechaFin.getDate());
                /*
                String id, 
                int nempleado, 
                int id_arreglo, 
                int estatus,
                int tiempo_estimado,
                Date hora_inicio,
                Date hora_final
                */
                cbd.closeConnection();
                RefrescarCCctionPerformed(evt);
                if(operacionExitoso == 1){
                    JOptionPane.showMessageDialog(null, "Proceso Exitoso");
                    /*Incia el Hilo de acuerdo al CampoSeleccionado*/
                    cronometroHilo ch = null;
                    String total = "";
                    if(campoEstatus.getSelectedIndex() == 2){
                       cbd.openConnection();
                       ch = new cronometroHilo();
                       mp = cbd.obtenerModeloPedido(campoID.getText(), String.valueOf(campoEmpleado.getSelectedItem()));
                       cbd.closeConnection();
                       total = ch.iniciarCronometro(mp);
                       RefrescarCCctionPerformed(evt);
                    }
                    RefrescarCCctionPerformed(evt);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Proceso, No Exitoso");
                }
        }
        else{
        
            JOptionPane.showMessageDialog(null, "Selecciona un campo en Sucursal, Puesto y Estatus: En Elaboración");
        }
    }//GEN-LAST:event_botonEditarActionPerformed

    private void campoArregloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_campoArregloItemStateChanged
        // TODO add your handling code here:
        cbd.openConnection();
                if ( evt.getStateChange() == ItemEvent.SELECTED ) 
                {
                    int item = campoArreglo.getSelectedIndex();
                    item = cbd.obtenTiempoEstimado(item);
                    System.out.println("El item es:"+item);
                    campoTiempoEstimado.setText(String.valueOf(item));
                }
       cbd.closeConnection();
    }//GEN-LAST:event_campoArregloItemStateChanged

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
//            java.util.logging.Logger.getLogger(vistaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(vistaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(vistaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(vistaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new vistaPedidos().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Arreglo;
    private javax.swing.JLabel Usuarios;
    private javax.swing.JButton botonAnadir;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JComboBox<String> campoArreglo;
    private javax.swing.JComboBox<String> campoEmpleado;
    private javax.swing.JComboBox<String> campoEstatus;
    private com.toedter.calendar.JDateChooser campoFechaFin;
    private com.toedter.calendar.JDateChooser campoFechaInicio;
    private javax.swing.JTextField campoID;
    private javax.swing.JTextField campoTiempoEstimado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCatalogoPedidos;
    // End of variables declaration//GEN-END:variables
}
