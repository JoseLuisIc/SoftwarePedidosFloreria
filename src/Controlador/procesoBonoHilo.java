/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author joseluis.caamal
 */
import Modelo.modeloBono;
import Modelo.modeloPedido;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class procesoBonoHilo extends JFrame implements Runnable{
Thread hilo;
boolean pausar;

/*Actualizo el tiempo y el pago del empleado de acuerdo a la comisión del arreglo,
para ello se necesita instanciar la clase bd*/
controladorBaseDatos cbd = new controladorBaseDatos();
modeloBono mb = new modeloBono();
public procesoBonoHilo()    {
	
}

    public void run(){
            /*SELECT numero_empleado, SUM(comision_eficiencia), SUM(diferencia_min) FROM floreriadb.tabla_bono 
                where fecha = "2021/05/28" group by numero_empleado order by SUM(diferencia_min)  asc  limit 1 ;*/
             while( !pausar ) {
                            // Ajuste al codigo: se elimina while y se anexa un if donde se niega la variable pausar	
                            
                try {
                        Thread.sleep( 1000 );
                        int operacionExitosa = 0;
                        cbd.openConnection();
                        operacionExitosa = cbd.validarFechaBono(cbd.obtenerFechaActualSoloFecha());
                        cbd.closeConnection();
                        if(operacionExitosa != 0){
                            //JOptionPane.showMessageDialog(null, "Existen pedidos de usuarios para está fecha, procesando Hilo.");
                            System.out.println("Procesando Hilo...");
                            cbd.openConnection();
                            mb =  cbd.obtenerEmpleadoBonoDiario(cbd.obtenerFechaActualSoloFecha());
                            cbd.closeConnection();
                            cbd.openConnection();
                            double comision_Aux = cbd.obtenComision(mb.getNumero_empleado());
                            comision_Aux = comision_Aux + ((mb.getComision_eficiencia()/100)*1); //Se añade el 1% a de comisión por eficiencia
                            operacionExitosa = cbd.actualizarBonoEficienciaEmpleado(mb.getNumero_empleado(),comision_Aux);
                            
                            cbd.closeConnection();
                            if(operacionExitosa !=0){
                                JOptionPane.showMessageDialog(null, "El Usuario más eficiente y se lleva el bono es:\n"
                                        + "Numero Empleado: "+mb.getNumero_empleado()+"\n"
                                                + "Comision Total: "+mb.getComision_eficiencia()+"\n"
                                                        + "Bono Ganado: "+((mb.getComision_eficiencia()/100)*1)+"\n"
                                                                + "Comision Acumulado Total: "+comision_Aux+"\n"
                                                        + "Diferencia Tiempo: "+mb.getDiferencia_min()+"\n"
                                                                + "Fecha: "+mb.getFecha());
                                detenerHilo();
                            }else{
                                JOptionPane.showMessageDialog(null, " No Existen pedidos de usuarios para está fecha, deteniendo Hilo.");
                                detenerHilo();
                            }
                        }else{
                             JOptionPane.showMessageDialog(null, " No Existen pedidos de usuarios para está fecha, deteniendo Hilo.");
                            //detenerHilo();
                             System.out.println("Procesando Hilo...");
                             detenerHilo();
                        }
                        System.out.println("...");

                     
                } catch (InterruptedException ex) {
                    Logger.getLogger(procesoBonoHilo.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
    }


    //Iniciar el cronometro poniendo cronometroActivo 
    //en verdadero para que entre en el while
    public int iniciarProcesoBono() {

            int proceso_Exito = 0;

              hilo = new Thread( this );
              pausar = false;                
              hilo.start();
              
            return proceso_Exito;
    }
    
    public void detenerHilo(){
        
        hilo.stop();
        
    }



    
}