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
public class modeloBono {
//    Columns:
//id int AI PK 
//numero_empleado int 
//comision_eficiencia double 
//diferencia_min int 
//fecha varchar(100) 
//id_pedido varchar(45) 
//autorizado int
    private int id;
    private int numero_empleado;
    private double  comision_eficiencia;
    private int diferencia_min;
    private String fecha;
    private int autorizado;

    public modeloBono() {
    }

    public modeloBono(int numero_empleado, double comision_eficiencia, int diferencia_min, String fecha) {
        this.numero_empleado = numero_empleado;
        this.comision_eficiencia = comision_eficiencia;
        this.diferencia_min = diferencia_min;
        this.fecha = fecha;
    }
    
    public modeloBono(int id, int numero_empleado, double comision_eficiencia, int diferencia_min, String fecha, int autorizado) {
        this.id = id;
        this.numero_empleado = numero_empleado;
        this.comision_eficiencia = comision_eficiencia;
        this.diferencia_min = diferencia_min;
        this.fecha = fecha;
        this.autorizado = autorizado;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero_empleado() {
        return numero_empleado;
    }

    public void setNumero_empleado(int numero_empleado) {
        this.numero_empleado = numero_empleado;
    }

    public double getComision_eficiencia() {
        return comision_eficiencia;
    }

    public void setComision_eficiencia(double comision_eficiencia) {
        this.comision_eficiencia = comision_eficiencia;
    }

    public int getDiferencia_min() {
        return diferencia_min;
    }

    public void setDiferencia_min(int diferencia_min) {
        this.diferencia_min = diferencia_min;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(int autorizado) {
        this.autorizado = autorizado;
    }

    @Override
    public String toString() {
        return "modeloBono{" + "id=" + id + ", numero_empleado=" + numero_empleado + ", comision_eficiencia=" + comision_eficiencia + ", diferencia_min=" + diferencia_min + ", fecha=" + fecha + ", autorizado=" + autorizado + '}';
    }
    
}
