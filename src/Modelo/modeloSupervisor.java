/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author joseluis.caamal
 */
public class modeloSupervisor {
    
    private int id;
    private int num_empleado;
    private int sucursal;
    private int id_arreglo;
    private int estatus;
    private int tiempo_estimado;
    private Date hora_inicio;
    private Date hora_final; 
    
    public modeloSupervisor(){
    //Normalmente este constructor sirve para inicializar el modelo
    }
    
    public modeloSupervisor(int id, int num_empleado,int sucursal, int id_arreglo, int estatus, int tiempo_estimado, Date hora_inicio, Date hora_final) {
        this.id = id;
        this.num_empleado = num_empleado;
        this.sucursal = sucursal;
        this.id_arreglo = id_arreglo;
        this.estatus = estatus;
        this.tiempo_estimado = tiempo_estimado;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getNum_empleado() {
        return num_empleado;
    }

    public void setNum_empleado(int num_empleado) {
        this.num_empleado = num_empleado;
    }

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    public int getId_arreglo() {
        return id_arreglo;
    }

    public void setId_arreglo(int id_arreglo) {
        this.id_arreglo = id_arreglo;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(int tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Date getHora_final() {
        return hora_final;
    }

    public void setHora_final(Date hora_final) {
        this.hora_final = hora_final;
    }

    @Override
    public String toString() {
        return "modeloSupervisor{" + "id=" + id + ", num_empleado=" + num_empleado + ", sucursal=" + sucursal + ", id_arreglo=" + id_arreglo + ", estatus=" + estatus + ", tiempo_estimado=" + tiempo_estimado + ", hora_inicio=" + hora_inicio + ", hora_final=" + hora_final + '}';
    }
    
}
