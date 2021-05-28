/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author joseluis.caamal
 */
public class modeloUsuario {
    
    private int usu_id; 
    private int num_empleado; 
    private String nombre;
    private String usuario; 
    private String password; 
    private int sucursal; 
    private int puesto;  
    private double comision;  
    private int tiempo_trabajo;
        
    public modeloUsuario(){
    
    }
    
    public modeloUsuario(int usu_id, int num_empleado, String nombre, String usuario, String password, int sucursal, int puesto, double comision, int tiempo_trabajo) {
        this.usu_id = usu_id;
        this.num_empleado = num_empleado;
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.sucursal = sucursal;
        this.puesto = puesto;
        this.comision = comision;
        this.tiempo_trabajo = tiempo_trabajo;
    }

    public int getUsu_id() {
        return usu_id;
    }

    public int getNum_empleado() {
        return num_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public int getSucursal() {
        return sucursal;
    }

    public int getPuesto() {
        return puesto;
    }

    public double getComision() {
        return comision;
    }

    public int getTiempo_trabajo() {
        return tiempo_trabajo;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }

    public void setNum_empleado(int num_empleado) {
        this.num_empleado = num_empleado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public void setTiempo_trabajo(int tiempo_trabajo) {
        this.tiempo_trabajo = tiempo_trabajo;
    }

    @Override
    public String toString() {
        return "modeloUsuario{" + "usu_id=" + usu_id + ", num_empleado=" + num_empleado + ", nombre=" + nombre + ", usuario=" + usuario + ", password=" + password + ", sucursal=" + sucursal + ", puesto=" + puesto + ", comision=" + comision + ", tiempo_trabajo=" + tiempo_trabajo + '}';
    }
    
}
