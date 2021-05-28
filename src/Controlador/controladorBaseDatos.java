/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.modeloBono;
import Modelo.modeloPedido;
import Modelo.modeloSupervisor;
import Modelo.modeloUsuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joseluis.caamal
 */
public class controladorBaseDatos {
    public Connection Conexion; //Abro la conexión
/*Inciamos el constructor*/
/*  ----------------------------------------------------------------------------------
    Nombre: Metodo conex()
    Función: Apertura La Conexión con la BD/ Utilizado para la consulta de tablas
    Aut@r: José Luis Caamal Ic
    Parametros: 
    ----------------------------------------------------------------------------------
    Nota: Para la url de la conexión usar el driver compatible con SQL 5.5 o 8.0
    ----------------------------------------------------------------------------------
Crecenciales de DB
*/
    public Connection openConnection() {
        
        try {
            //Como obtener la información desde un archivo properties
            String db_nam = "floreriadb";
            String use = "root";
            String pas = "SAKAI";
            //For MySql 5.5
            //Class.forName("com.mysql.jdbc.Driver");
            //For MySql 8.0
            Class.forName("com.mysql.cj.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_nam + "?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC", use, pas);
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Conexion;
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo closeConnection
    Función: Cierra La Conexión con la BD
    Aut@r: José Luis Caamal Ic
    Parametros: 
    ----------------------------------------------------------------------------------
*/
    public void closeConnection() {
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo modeloPuestos
    Función: Se realiza la implementación del modelo par el llenado de tabla
    Date: 19/08/2020
    Aut@r: Jose Luis Caamal Ic
    Parametros: String columna[]
    ----------------------------------------------------------------------------------
    */
    public DefaultTableModel modeloPuesto(String columna[]){
    DefaultTableModel modeloRetorno;
    modeloRetorno = new DefaultTableModel(null, columna); 
        try{
                String Query = "SELECT * FROM floreriadb.table_puesto;";

                System.out.println("Contenido en ejecución: "+Query);

                PreparedStatement us = Conexion.prepareStatement(Query);
                ResultSet res = us.executeQuery();
                Object objDatos[] = new Object[columna.length]; //Siempre debe cconexoincidir con el numero de columnas!

                while(res.next()){
                    for (int i = 0; i<columna.length; i++){
                        objDatos[i] = res.getObject(i+1);
                        System.out.println(objDatos[i]);
                    }
                    modeloRetorno.addRow(objDatos);
                }
            }
            catch(SQLException ex){
                Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
    
        return modeloRetorno;
    }
    
    /* Metodo insertarPuestos
    Inserta el catalogo de controladorBD
    22/05/2021
        Función para insertar la información de los puestos.
    */
    public int insertarPuestos(String name){
        int procesoExitoso = 0;
        try {
            
                PreparedStatement pps=Conexion.prepareStatement("INSERT INTO "
                        + "table_puesto(id,"
                        + "name ) "
                        + "VALUES (default,?) ");
                pps.setString(1,name);
                System.out.println(pps.toString());
                pps.executeUpdate();
                procesoExitoso = 1;
            
        } catch (SQLException e) {
            procesoExitoso = 0;
            
        }
        return procesoExitoso;
    }
    
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo eliminarFila
    Función: Elimina los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_pacientes
                Columns:
    Date: 27/06/2020
    ----------------------------------------------------------------------------------
*/

    public int eliminarFila(String table_name, String ID) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");return 0;
        }
        return 1;
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo actualizar Puesto
    Función: Actualiza los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_puesto
                Columns:
    Date: 31/08/2020
    ----------------------------------------------------------------------------------
    */
    public int actualizarPuesto(String id, String  name) {
        try {
            //int response;
            String sql = ("UPDATE table_puesto "
                    + "SET "
                    + "name = '" + name + "' "
                    + "WHERE id = '"+id +"'");
            /*
            UPDATE `floreriadb`.`table_puesto`
                    SET
                    `id` = <{id: }>,
                    `name` = <{name: }>
                    WHERE `id` = <{expr}>;
            
            */
            System.out.println("consulta actualizarPuesto: "+sql);
            Statement st = Conexion.createStatement();
            st.executeUpdate(sql);	
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo modeloUsuario
    Función: Se realiza la implementación del modelo par el llenado de tabla
    Date: 19/08/2020
    Aut@r: Jose Luis Caamal Ic
    Parametros: String columna[]
    ----------------------------------------------------------------------------------
    */
    public DefaultTableModel modeloUsuario(String columna[]){
    DefaultTableModel modeloRetorno;
    modeloRetorno = new DefaultTableModel(null, columna); 
        try{
                String Query = "SELECT * FROM floreriadb.tabla_empleados;";

                System.out.println("Contenido en ejecución: "+Query);

                PreparedStatement us = Conexion.prepareStatement(Query);
                ResultSet res = us.executeQuery();
                Object objDatos[] = new Object[columna.length]; //Siempre debe cconexoincidir con el numero de columnas!

                while(res.next()){
                    for (int i = 0; i<columna.length; i++){
                        objDatos[i] = res.getObject(i+1);
                        System.out.println(objDatos[i]);
                    }
                    modeloRetorno.addRow(objDatos);
                }
            }
            catch(SQLException ex){
                Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
    
        return modeloRetorno;
    }
    
    /* Metodo insertarUsuarios
    Inserta el catalogo de controladorBD
    22/05/2021
        Función para insertar la información de los Usuarios.
    */
    public int insertarUsuarios(String name, 
            int nempleado, 
            String user, 
            String password, 
            int sucursal, 
            int puesto,
            double comision,
            int tiempo_trabajo){
        
        int procesoExitoso = 0;
        try {
            
                PreparedStatement pps=Conexion.prepareStatement("INSERT INTO "
                        + "tabla_empleados(id,"
                        + "num_empleado,nombre,usuario,password,sucursal,puesto,comision,tiempo_trabajo ) "
                        + "VALUES (default,?,?,?,?,?,?,?,?) ");
                pps.setInt(1,nempleado);
                pps.setString(2,name);
                pps.setString(3,user);
                pps.setString(4,password);
                pps.setInt(5,sucursal);
                pps.setInt(6,puesto);
                pps.setDouble(7,comision);
                pps.setInt(8,tiempo_trabajo);
                System.out.println(pps.toString());
                pps.executeUpdate();
                procesoExitoso = 1;
            
        } catch (SQLException e) {
            procesoExitoso = 0;
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.toString());
        }
        return procesoExitoso;
    }
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo actualizarUsuario
    Función: Actualiza los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_empleados
                Columns:
    Date: 31/08/2020
    ----------------------------------------------------------------------------------
    */
    public int actualizarUsuario(String id, 
            String name, 
            int nempleado, 
            String user, 
            String password, 
            int sucursal, 
            int puesto) {
        try {
            //int response;
            String sql = ("UPDATE tabla_empleados "
                    + "SET "
                    + "nombre = '" + name + "', "
                    + "num_empleado = '" + nempleado + "', "
                    + "usuario = '" + user + "', "
                    + "password = '" + password + "', "
                    + "sucursal = '" + sucursal + "', "
                    + "puesto = '" + puesto + "' "
                    + "WHERE id = '"+id +"'");
            
            Statement st = Conexion.createStatement();
            st.executeUpdate(sql);	
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo modeloUsuario
    Función: Se realiza la implementación del modelo par el llenado de tabla
    Date: 19/08/2020
    Aut@r: Jose Luis Caamal Ic
    Parametros: String columna[]
    ----------------------------------------------------------------------------------
    */
    public DefaultTableModel modeloPedidos(String columna[]){
    DefaultTableModel modeloRetorno;
    modeloRetorno = new DefaultTableModel(null, columna); 
        try{
                String Query = "SELECT * FROM floreriadb.tabla_pedidos;";

                System.out.println("Contenido en ejecución: "+Query);

                PreparedStatement us = Conexion.prepareStatement(Query);
                ResultSet res = us.executeQuery();
                Object objDatos[] = new Object[columna.length]; //Siempre debe cconexoincidir con el numero de columnas!

                while(res.next()){
                    for (int i = 0; i<columna.length; i++){
                        objDatos[i] = res.getObject(i+1);
                        System.out.println(objDatos[i]);
                    }
                    modeloRetorno.addRow(objDatos);
                }
            }
            catch(SQLException ex){
                Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
    
        return modeloRetorno;
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo modeloUsuario
    Función: Se realiza la implementación del modelo par el llenado de tabla
    Date: 19/08/2020
    Aut@r: Jose Luis Caamal Ic
    Parametros: String columna[]
    ----------------------------------------------------------------------------------
    */
    public DefaultTableModel modeloPedidosUser(String columna[],int num_empleado){
    DefaultTableModel modeloRetorno;
    modeloRetorno = new DefaultTableModel(null, columna); 
        try{
                String Query = "SELECT * FROM floreriadb.tabla_pedidos where num_empleado = "+num_empleado+";";

                System.out.println("Contenido en ejecución: "+Query);

                PreparedStatement us = Conexion.prepareStatement(Query);
                ResultSet res = us.executeQuery();
                Object objDatos[] = new Object[columna.length]; //Siempre debe cconexoincidir con el numero de columnas!

                while(res.next()){
                    for (int i = 0; i<columna.length; i++){
                        objDatos[i] = res.getObject(i+1);
                        System.out.println(objDatos[i]);
                    }
                    modeloRetorno.addRow(objDatos);
                }
            }
            catch(SQLException ex){
                Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
    
        return modeloRetorno;
    }
    
    /* Metodo insertarPedidos
    Inserta el catalogo de controladorBD
    22/05/2021
        Función para insertar la información de los Pedidos.
    */
    public int insertarPedidos(
            int nempleado, 
            int id_arreglo, 
            int estatus,
            int tiempo_estimado,
            Date hora_inicio,
            Date hora_final){
        
        int procesoExitoso = 0;
        try {
            
                PreparedStatement pps=Conexion.prepareStatement("INSERT INTO "
                        + "tabla_pedidos(id,"
                        + "num_empleado,id_arreglo,estatus,tiempo_estimado,hora_inicio,hora_final ) "
                        + "VALUES (default,?,?,?,?,?,?) ");
                pps.setInt(1,nempleado);
                pps.setInt(2,id_arreglo);
                pps.setInt(3,estatus);
                pps.setInt(4,tiempo_estimado);
                pps.setString(5, convertirFecha(hora_inicio));
                pps.setString(6, convertirFecha(hora_final));
                System.out.println(pps.toString());
                pps.executeUpdate();
                procesoExitoso = 1;
            
        } catch (SQLException e) {
            procesoExitoso = 0;
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.toString());
        }
        return procesoExitoso;
    }
    
    /*Metodo convertirFecha
    26/05/2021
    Convierte el valor de los datos de un DATE A UN DATATIME */
    public String convertirFecha(Date dato){
        //java.util.Date dt = new java.util.Date();//Se utiliza si no tienes el Date para generar uno nuevo.
        java.text.SimpleDateFormat sdf = 
            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dato);
        return currentTime;
    }
    
    /*Metodo obtenerFechaActual
    26/05/2021
    Convierte el valor de los datos de un DATE A UN DATATIME fecha y hora*/
    public String obtenerFechaActual(){
        java.util.Date dt = new java.util.Date();//Se utiliza si no tienes el Date para generar uno nuevo.
        java.text.SimpleDateFormat sdf = 
            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        return currentTime;
    }
    
    /*Metodo obtenerFechaActual
    26/05/2021
    Convierte el valor de los datos de un DATE A UN DATATIME fecha y hora*/
    public String obtenerFechaActualSoloFecha(){
        java.util.Date dt = new java.util.Date();//Se utiliza si no tienes el Date para generar uno nuevo.
        java.text.SimpleDateFormat sdf = 
            new java.text.SimpleDateFormat("yyyy/MM/dd");
        String currentTime = sdf.format(dt);
        return currentTime;
    }
    
    /*Metodo convertirFecha
    26/05/2021
    Convierte el valor de los datos de un DATE A UN DATATIME */
    public Date convertirFechaString(String dato){
        //java.util.Date dt = new java.util.Date();//Se utiliza si no tienes el Date para generar uno nuevo.
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(dato);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex.getMessage());
        }
        return fechaDate;
    }
    
    /* metodo diferenciaFechas
    Obtiene la diferencia de los minutos entre dos fechas
    26/05/2021
    */
    public Date diferenciaFechas(Date dateInicio, Date dateFinal) {
        long milliseconds = dateFinal.getTime() - dateInicio.getTime();
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.SECOND, seconds);
        c.set(Calendar.MINUTE, minutes);
        c.set(Calendar.HOUR_OF_DAY, hours);
        return c.getTime();
    }
    /*Metodo obtenerTiempoEstimado
    Obtiene el valor de tiempo estimado de un arreglo
    26/05/2021*/
    public int obtenTiempoEstimado(int id){
                int aux = 0;
                try{
                    PreparedStatement stmt;
                    stmt = Conexion.prepareStatement("SELECT tiempo_elaboracion FROM tabla_arreglos WHERE id = '" +id+ "'");
                    java.sql.ResultSet res;
                    res = stmt.executeQuery();
                    if(res.next()){
                            aux = res.getInt("tiempo_elaboracion");
                            return aux;
                    }
                    else{
                            res.close();
                            return aux;
                    }
                } catch(SQLException a){
                    Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, a);
                    JOptionPane.showMessageDialog(null, a.getMessage());
                    return aux = 0;
                }
    
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo actualizarPedidos
    Función: Actualiza los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_pedidos
                Columns:
    Date: 26/05/2021
    ----------------------------------------------------------------------------------
    */
    public int actualizarPedidos(String id, 
            int nempleado, 
            int id_arreglo, 
            int estatus,
            int tiempo_estimado,
            Date hora_inicio,
            Date hora_final) {
        try {
            //int response;
            String sql = ("UPDATE tabla_pedidos "
                    + "SET "
                    + "id_arreglo = '" + id_arreglo + "', "
                    + "num_empleado = '" + nempleado + "', "
                    + "estatus = '" + estatus + "', "
                    + "tiempo_estimado = '" + tiempo_estimado + "', "
                    //+ "hora_inicio = '" + convertirFecha(hora_inicio) + "', " /*Actualiza de acuerdo al parametro que envias*/
                    + "hora_inicio = '" + obtenerFechaActual() + "', " //Se cambia por que el proceso necesita obtner la fecha de inicio de la elaboración
                    + "hora_final = '" + convertirFecha(hora_final) + "' "
                    + "WHERE id = '"+id +"'");
            
            Statement st = Conexion.createStatement();
            st.executeUpdate(sql);	
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error desconocido."+ex.getMessage());
            return 0;
        }
    }
    
    /*Metodo validarUsuarioInicio
    Obtiene el valor de tiempo estimado de un arreglo
    26/05/2021*/
    public modeloUsuario validarUsuarioInicio(String user, String password){
                modeloUsuario mu = null;
                try{
                    PreparedStatement stmt;
                    String sql = "SELECT * FROM tabla_empleados WHERE usuario = ? AND password = ? ";
                    stmt = Conexion.prepareStatement(sql);
                    stmt.setString(1,user);
                    stmt.setString(2,password);
                    java.sql.ResultSet res;
                    res = stmt.executeQuery();
                    if(res.next()){
                            mu = new modeloUsuario(res.getInt("id"),
                                    res.getInt("num_empleado"),
                                    res.getString("nombre"),
                                    res.getString("usuario"),
                                    res.getString("password"),
                                    res.getInt("sucursal"),
                                    res.getInt("puesto"),
                                    res.getDouble("comision"),
                                    res.getInt("tiempo_trabajo"));
                            
                    }
                    else{
                            res.close();
                            return mu;
                    }
                } catch(SQLException a){
                    Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, a);
                    JOptionPane.showMessageDialog(null, a.getMessage());
                    return mu;
                }
        
        return mu;
    }
    
    /*Metodo obtenerModeloPedido
    Obtiene los datos de la consulta actual haciendo match con el id y num_empleado
    26/05/2021*/
    public modeloPedido obtenerModeloPedido (String id, String num_empleado){
                modeloPedido mp = null;
                try{
                    PreparedStatement stmt;
                    String sql = "SELECT * FROM tabla_pedidos tbl_ped LEFT JOIN tabla_empleados tbl_emp ON tbl_ped.num_empleado = tbl_emp.num_empleado" +
                    " where tbl_emp.puesto = 1 AND tbl_ped.id = ? AND tbl_ped.num_empleado = ? ";
                    System.out.println(sql);
                    stmt = Conexion.prepareStatement(sql);
                    stmt.setString(1,id);
                    stmt.setString(2,num_empleado);
                    java.sql.ResultSet res;
                    res = stmt.executeQuery();
                    if(res.next()){
                            mp = new modeloPedido(res.getInt("id"),
                                    res.getInt("num_empleado"),
                                    res.getInt("id_arreglo"),
                                    res.getInt("estatus"),
                                    res.getInt("tiempo_estimado"),
                                    res.getDate("hora_inicio"),
                                    res.getDate("hora_final"));
                            
                    }
                    else{
                            res.close();
                            return mp;
                    }
                } catch(SQLException a){
                    Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, a);
                    JOptionPane.showMessageDialog(null, a.getMessage());
                    return mp;
                }
        
        return mp;
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo actualizarPedidoTime
    Función: Actualiza los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_pedidos
                Columns:
    Date: 26/05/2021
    ----------------------------------------------------------------------------------
    */
    public int actualizarPedidoTime(int id, 
            int nempleado, 
            Date hora_fin) {
        try {
            //int response;
            String sql = ("UPDATE tabla_pedidos "
                    + "SET "
                    + "num_empleado = '" + nempleado + "', "
                    + "estatus = '" + 3 + "', "
                    + "hora_final = '" + obtenerFechaActual() + "' "
                    + "WHERE id = '"+ id +"' and num_empleado = '"+nempleado +"'");
            
            Statement st = Conexion.createStatement();
            st.executeUpdate(sql);	
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error desconocido."+ex.getMessage());
            return 0;
        }
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo actualizarTrabajoEmpleado
    Función: Actualiza los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_pedidos
                Columns:
    Date: 26/05/2021
    ----------------------------------------------------------------------------------
    */
    public int actualizarTrabajoEmpleado( 
            int num_empleado, 
            double comision,
            int tiempo_trabajo) {
        try {
            //int response;
            String sql = ("UPDATE tabla_empleados "
                    + "SET "
                    + "comision = '" + comision + "', "
                    + "tiempo_trabajo = '" + tiempo_trabajo + "' "
                    + "WHERE num_empleado = '"+ num_empleado +"'");
            
            Statement st = Conexion.createStatement();
            st.executeUpdate(sql);	
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error desconocido."+ex.getMessage());
            return 0;
        }
    }
    
    /*Metodo obtenTiempoTrabajado
    Obtiene el valor de tiempo trabajado de un arreglo para asignarlo a un empleado
    26/05/2021*/
    public int obtenTiempoTrabajado(int num_empleado){
                int aux = 0;
                try{
                    PreparedStatement stmt;
                    stmt = Conexion.prepareStatement("SELECT tiempo_trabajo FROM tabla_empleados WHERE num_empleado = '" +num_empleado+ "'");
                    java.sql.ResultSet res;
                    res = stmt.executeQuery();
                    if(res.next()){
                            aux = res.getInt("tiempo_trabajo");
                            return aux;
                    }
                    else{
                            res.close();
                            return aux;
                    }
                } catch(SQLException a){
                    Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, a);
                    JOptionPane.showMessageDialog(null, a.getMessage());
                    return aux = 0;
                }
    
    }
    
    /*Metodo obtenTiempoTrabajado
    Obtiene el valor de tiempo trabajado de un arreglo para asignarlo a un empleado
    26/05/2021*/
    public double obtenComision(int num_empleado){
                double aux = 0;
                try{
                    PreparedStatement stmt;
                    stmt = Conexion.prepareStatement("SELECT comision FROM tabla_empleados WHERE num_empleado = '" +num_empleado+ "'");
                    java.sql.ResultSet res;
                    res = stmt.executeQuery();
                    if(res.next()){
                            aux = res.getDouble("comision");
                            return aux;
                    }
                    else{
                            res.close();
                            return aux;
                    }
                } catch(SQLException a){
                    Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, a);
                    JOptionPane.showMessageDialog(null, a.getMessage());
                    return aux = 0;
                }
    
    }
    
    /*Metodo obtenTiempoTrabajado
    Obtiene el valor de tiempo trabajado de un arreglo para asignarlo a un empleado
    26/05/2021*/
    public int obtenerCostoArreglo(int id_arreglo){
                int aux = 0;
                try{
                    PreparedStatement stmt;
                    stmt = Conexion.prepareStatement("SELECT costo FROM tabla_arreglos WHERE id = '" +id_arreglo+ "'");
                    java.sql.ResultSet res;
                    res = stmt.executeQuery();
                    if(res.next()){
                            aux = res.getInt("costo");
                            return aux;
                    }
                    else{
                            res.close();
                            return aux;
                    }
                } catch(SQLException a){
                    Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, a);
                    JOptionPane.showMessageDialog(null, a.getMessage());
                    return aux = 0;
                }
    
    }
    
    /*Metodo obtenTiempoTrabajado
    Obtiene el valor de tiempo trabajado de un arreglo para asignarlo a un empleado
    26/05/2021*/
    public String obtenerNombreSucursal(int id_sucursal){
                String aux = "";
                try{
                    PreparedStatement stmt;
                    stmt = Conexion.prepareStatement("SELECT name FROM tabla_sucursal WHERE id = '" +id_sucursal+ "'");
                    java.sql.ResultSet res;
                    res = stmt.executeQuery();
                    if(res.next()){
                            aux = res.getString("name");
                            return aux;
                    }
                    else{
                            res.close();
                            return aux;
                    }
                } catch(SQLException a){
                    Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, a);
                    JOptionPane.showMessageDialog(null, a.getMessage());
                    return aux = "Sin Sucursal";
                }
    
    }
    
    /*Metodo obtenerModeloSupervisor
    Obtiene los datos de la consulta actual haciendo match con el id y num_empleado
    26/05/2021
    Integra en tu programa la interfaz gráfica del supervisor, esta tendrá que tener un
    usuario con contraseña para acceder a una pantalla donde pueda de forma gráfica
    ver un listado de los pedidos de su punto de venta, sus estatus y los diseñadores
    asignados a su elaboración, dicha pantalla deberá actualizarse cada minuto
    automáticamente (mediante un hilo), también se debe contar con la posibilidad de
    filtrar los pedidos de acuerdo a su estatus y deberá poder cambiar el estatus de los
    pedidos de “terminado” a “autorizado”, debe contar con una opción para actualizar las
    comisiones de los empleados con pedidos autorizados mediante un hilo (se deberá
    mostrar la comisión del empleado con la suma de todos los pedidos con estatus de
    autorizado). Integrar un botón donde mediante otro hilo calcule automáticamente la
    comisión del bono de eficiencia con los criterios descritos en el caso.
    */
    public modeloSupervisor obtenerModeloSupervisor(String id, String sucursal){
                modeloSupervisor ms = null;
                try{
                    PreparedStatement stmt;
                    String sql = "SELECT tbl_ped.id as id, "
                            + "tbl_ped.num_empleado as num_empleado,"
                            + "tbl_emp.sucursal as sucursal,"
                            + "tbl_ped.id_arreglo as id_arreglo,"
                            + "tbl_ped.estatus as estatus,"
                            + "tbl_ped.tiempo_estimado as tiempo_estimado,"
                            + "tbl_ped.hora_inicio as hora_inicio,"
                            + "tbl_ped.hora_final as hora_final "
                            + "FROM tabla_pedidos tbl_ped \n" +
                                 "LEFT JOIN tabla_empleados tbl_emp ON tbl_ped.num_empleado = tbl_emp.num_empleado \n" +
                                  "where tbl_emp.sucursal = ?; ";
                    stmt = Conexion.prepareStatement(sql);
                    //stmt.setString(1,sucursal);
                    stmt.setString(1,sucursal);
                    java.sql.ResultSet res;
                    res = stmt.executeQuery();
                    if(res.next()){
                            ms = new modeloSupervisor(res.getInt("id"),
                                    res.getInt("num_empleado"),
                                    res.getInt("sucursal"),
                                    res.getInt("id_arreglo"),
                                    res.getInt("estatus"),
                                    res.getInt("tiempo_estimado"),
                                    res.getDate("hora_inicio"),
                                    res.getDate("hora_final"));
                            
                    }
                    else{
                            res.close();
                            return ms;
                    }
                } catch(SQLException a){
                    Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, a);
                    JOptionPane.showMessageDialog(null, a.getMessage());
                    return ms;
                }
        
        return ms;
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo modeloSupervisores
    Función: Se realiza la implementación del modelo par el llenado de tabla
    Date: 19/08/2020
    Aut@r: Jose Luis Caamal Ic
    Parametros: String columna[]
    ----------------------------------------------------------------------------------
    */
    public DefaultTableModel modeloSupervisores(String columna[], int sucursal){
    DefaultTableModel modeloRetorno;
    modeloRetorno = new DefaultTableModel(null, columna); 
        try{
                String Query = "SELECT tbl_ped.id as id, "
                            + "tbl_ped.num_empleado as num_empleado,"
                            + "tbl_emp.sucursal as sucursal,"
                            + "tbl_ped.id_arreglo as id_arreglo,"
                            + "tbl_ped.estatus as estatus,"
                            + "tbl_ped.tiempo_estimado as tiempo_estimado,"
                            + "tbl_ped.hora_inicio as hora_inicio,"
                            + "tbl_ped.hora_final as hora_final "
                            + "FROM tabla_pedidos tbl_ped " +
                                 "LEFT JOIN tabla_empleados tbl_emp ON tbl_ped.num_empleado = tbl_emp.num_empleado \n" +
                                  "where tbl_emp.sucursal = ?; ";

                System.out.println("Contenido en ejecución: "+Query);

                PreparedStatement us = Conexion.prepareStatement(Query);
                us.setInt(1,sucursal);
                ResultSet res = us.executeQuery();
                Object objDatos[] = new Object[columna.length]; //Siempre debe cconexoincidir con el numero de columnas!
               
                while(res.next()){
                    for (int i = 0; i<columna.length; i++){
                        objDatos[i] = res.getObject(i+1);
                        System.out.println(objDatos[i]);
                    }
                    modeloRetorno.addRow(objDatos);
                }
            }
            catch(SQLException ex){
                Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
    
        return modeloRetorno;
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo modeloSupervisores
    Función: Se realiza la implementación del modelo par el llenado de tabla
    Date: 19/08/2020
    Aut@r: Jose Luis Caamal Ic
    Parametros: String columna[]
    ----------------------------------------------------------------------------------
    */
    public DefaultTableModel modeloSupervisores(String columna[], int sucursal, int filtro){
    DefaultTableModel modeloRetorno;
    modeloRetorno = new DefaultTableModel(null, columna); 
        try{
                String Query = "SELECT tbl_ped.id as id, "
                            + "tbl_ped.num_empleado as num_empleado,"
                            + "tbl_emp.sucursal as sucursal,"
                            + "tbl_ped.id_arreglo as id_arreglo,"
                            + "tbl_ped.estatus as estatus,"
                            + "tbl_ped.tiempo_estimado as tiempo_estimado,"
                            + "tbl_ped.hora_inicio as hora_inicio,"
                            + "tbl_ped.hora_final as hora_final "
                            + "FROM tabla_pedidos tbl_ped " +
                                 "LEFT JOIN tabla_empleados tbl_emp ON tbl_ped.num_empleado = tbl_emp.num_empleado \n" +
                                  "where tbl_emp.sucursal = ? and tbl_ped.estatus = ?";
//                if(filtro!=0){
//                    Query = "AND tbl_ped.estatus = '"+filtro+"'";
//                }

                System.out.println("Contenido en ejecución: "+Query);

                PreparedStatement us = Conexion.prepareStatement(Query);
                us.setInt(1,sucursal);
                us.setInt(2,filtro);
                ResultSet res = us.executeQuery();
                Object objDatos[] = new Object[columna.length]; //Siempre debe cconexoincidir con el numero de columnas!
               
                while(res.next()){
                    for (int i = 0; i<columna.length; i++){
                        objDatos[i] = res.getObject(i+1);
                        System.out.println(objDatos[i]);
                    }
                    modeloRetorno.addRow(objDatos);
                }
            }
            catch(SQLException ex){
                Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
    
        return modeloRetorno;
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo actualizarEstatusTerminado
    Función: Actualiza los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_puesto
                Columns:
    Date: 31/08/2020
    ----------------------------------------------------------------------------------
    */
    public int actualizarEstatusAutorizado(int id, int nEmpleado, int  estatus) {
        try {
            
            String sql = ("UPDATE tabla_pedidos "
                    + "SET "
                    + "estatus = " + estatus + " "
                    + "WHERE id = "+id +" and num_empleado = "+nEmpleado+"");
            
            System.out.println("consulta actualizarPuesto: "+sql);
            Statement st = Conexion.createStatement();
            st.executeUpdate(sql);
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    /*Actualizar bONO
    28/05/2021
    Actualiza la tabla_bonos y autoriza según el ID del pedido
    */
    
    public int actualizaBono(int id, int nEmpleado, int  estatus) {
        try {
            
            String sql = ("UPDATE tabla_bono "
                    + "SET "
                    + "autorizado = " + estatus + " "
                    + "WHERE id_pedido = "+id +" and numero_empleado = "+nEmpleado+"");
            
            System.out.println("consulta actualizarPuesto: "+sql);
            Statement st = Conexion.createStatement();
            st.executeUpdate(sql);
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Metodo actualizarTrabajoEmpleado
    Función: Actualiza los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_pedidos
                Columns:
    Date: 26/05/2021
    ----------------------------------------------------------------------------------
    */
    public int insertarBono( 
            int num_empleado, 
            double comision_eficiencia,
            int tiempo_trabajo,String fecha,int id_pedido) {
        try {
            
                PreparedStatement pps=Conexion.prepareStatement("INSERT INTO "
                        + "tabla_bono(id,"
                        + "numero_empleado,comision_eficiencia,diferencia_min,fecha,id_pedido ) "
                        + "VALUES (default,?,?,?,?,?) ");
                pps.setInt(1,num_empleado);
                pps.setDouble(2,comision_eficiencia);
                pps.setInt(3,tiempo_trabajo);
                pps.setString(4,fecha);
                pps.setInt(5,id_pedido);
                
                System.out.println(pps.toString());
                pps.executeUpdate();
                return 1;
            
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            System.out.println(e.toString());
            return 0;
        }
    }
    
    /*Metodo validarFechaBono
    Obtiene el valor de tiempo trabajado de un arreglo para asignarlo a un empleado
    26/05/2021*/
    public int validarFechaBono(String fechaBono){
                int aux = 0;
                try{
                    PreparedStatement stmt;
                    stmt = Conexion.prepareStatement("SELECT count(*) as total FROM tabla_bono WHERE fecha = '" +fechaBono+ "'");
                    java.sql.ResultSet res;
                    res = stmt.executeQuery();
                    if(res.next()){
                            aux = res.getInt("total");
                            return aux;
                    }
                    else{
                            res.close();
                            return aux;
                    }
                } catch(SQLException a){
                    Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, a);
                    JOptionPane.showMessageDialog(null, a.getMessage());
                    return aux = 0;
                }
    
    }
    public modeloBono obtenerEmpleadoBonoDiario(String fecha){
        modeloBono mb = null;
         /*SELECT numero_empleado, SUM(comision_eficiencia), SUM(diferencia_min) FROM floreriadb.tabla_bono 
                where fecha = "2021/05/28" and autorizado = 1 group by numero_empleado order by SUM(diferencia_min)  asc  limit 1 */
        int aux = 0;
                try{
                    String Qry = "SELECT numero_empleado, SUM(comision_eficiencia) as comision, SUM(diferencia_min) as diferencia, fecha FROM floreriadb.tabla_bono " +
"                where fecha = '"+fecha+"' and autorizado = 1 group by numero_empleado order by SUM(diferencia_min)  asc  limit 1";
                    PreparedStatement stmt;
                    stmt = Conexion.prepareStatement(Qry);
                    java.sql.ResultSet res;
                    res = stmt.executeQuery();
                    if(res.next()){
                            mb = new modeloBono(res.getInt("numero_empleado"),res.getInt("comision"),res.getInt("diferencia"),res.getString("fecha"));
                            return mb;
                    }
                    else{
                            res.close();
                            return mb;
                    }
                } catch(SQLException a){
                    Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, a);
                    JOptionPane.showMessageDialog(null, a.getMessage());
                    return mb;
                }
    }
    
        /*  ----------------------------------------------------------------------------------
    Nombre: Metodo actualizarBonoEficienciaEmpleado
    Función: Actualiza los valores en la tabla correspondiente.
    Aut@r: 
    Parametros: Table: tabla_pedidos
                Columns:
    Date: 26/05/2021
    ----------------------------------------------------------------------------------
    */
    public int actualizarBonoEficienciaEmpleado( 
            int num_empleado, 
            double comision) {
        try {
            //int response;
            String sql = ("UPDATE tabla_empleados "
                    + "SET "
                    + "comision = '" + comision + "' "
//                    + "tiempo_trabajo = '" + tiempo_trabajo + "' "
                    + "WHERE num_empleado = '"+ num_empleado +"'");
            
            Statement st = Conexion.createStatement();
            st.executeUpdate(sql);	
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(controladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error desconocido."+ex.getMessage());
            return 0;
        }
    }
    
    
}
