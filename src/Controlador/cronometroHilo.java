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
import Modelo.modeloPedido;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class cronometroHilo extends JFrame implements Runnable, ActionListener{
public static int onoff = 0;

// Ajuste al codigo: estas variables se pasan al comienzo de la clase.
JLabel tiempo;
Thread hilo;
boolean cronometroActivo;
boolean pausar;

// Ajuste al codigo: se crea variable iniciado de tipo booleano se inicializa en verdadero.
boolean iniciado = true;
//*****************************************************************************************
/*Declaro pública la información de los minutos, mil y seg*/
public int minutos = 0 , segundos = 0, milesimas = 0;
/*Obtengo el modeloPedido*/
modeloPedido mp = new modeloPedido();
//Activa y Desactiva el Aviso de tiempo 90%
boolean flagAnuncio = true;
/*Obtiene los segundos totales*/
int segundosTotal = 0;
/*Actualizo el tiempo y el pago del empleado de acuerdo a la comisión del arreglo,
para ello se necesita instanciar la clase bd*/
controladorBaseDatos cbd = new controladorBaseDatos();
public cronometroHilo()    {
	setTitle("Procesando Pedido");
	setSize( 500, 200 );
	setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	setLayout( new BorderLayout() );

	//Etiqueta donde se colocara el tiempo 
	tiempo = new JLabel( "00:00:000" );
	tiempo.setFont( new Font( Font.SERIF, Font.BOLD, 50 ) );
	tiempo.setHorizontalAlignment( JLabel.CENTER );
	tiempo.setForeground( Color.WHITE );
	tiempo.setBackground( Color.BLACK );
	tiempo.setOpaque( true );

	add( tiempo, BorderLayout.CENTER );

//	//Boton iniciar
//	JButton btn = new JButton( "Iniciar" );
//	btn.addActionListener( this );
//	add( btn, BorderLayout.NORTH );
//
//	//Boton reiniciar inicia nuevamente desde 0
//	JButton btnP = new JButton( "Reiniciar" );
//	btnP.addActionListener( this );
//	add( btnP, BorderLayout.EAST );

	JButton btnD = new JButton( "Detener" );
	// btnD.setVisible(false);
	btnD.addActionListener( this );
	add( btnD, BorderLayout.SOUTH );

	this.setLocationRelativeTo( null );
	setVisible( true );
}

    public void run(){
            Integer minutos = 0 , segundos = 0, milesimas = 0;
            //min es minutos, seg es segundos y mil es milesimas de segundo
            String min="", seg="", mil="";
            try {

                    //Mientras cronometroActivo sea verdadero entonces seguira
                    //aumentando el tiempo
                    while( cronometroActivo ) {
                            // Ajuste al codigo: se elimina while y se anexa un if donde se niega la variable pausar	
                            if (!pausar) {
                                    //**************************************************************************************
                                    Thread.sleep( 4 );
                                    //Incrementamos 4 milesimas de segundo
                                    milesimas += 4;
                                    //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                                    //y las milesimas de segundo de nuevo a 0
                                    if( milesimas == 1000 ) {
                                            milesimas = 0;
                                            segundos += 1;
                                            //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                                            //y los segundos vuelven a 0
                                            if( segundos == 60 )
                                            {
                                                    segundos = 0;
                                                    minutos++;
                                            }
                                    }

                                    //Esto solamente es estetica para que siempre este en formato
                                    //00:00:000
                                    if( minutos < 10 ) min = "0" + minutos;
                                    else min = minutos.toString();
                                    if( segundos < 10 ) seg = "0" + segundos;
                                    else seg = segundos.toString();

                                    if( milesimas < 10 ) mil = "00" + milesimas;
                                    else if( milesimas < 100 ) mil = "0" + milesimas;
                                    else mil = milesimas.toString();

                                    //Colocamos en la etiqueta la informacion
                                    tiempo.setText( min + ":" + seg + ":" + mil );
//                                    System.out.println(min + ":" + seg + ":" + mil);
                                    //System.out.println(mp.toString());
                                    
                                    this.minutos = minutos; this.segundos = segundos; this.milesimas= milesimas;
                                    float tiempo_estimado = mp.getTiempo_estimado();
                                    tiempo_estimado = ((tiempo_estimado/100)*90);
                                    int tiempo_en_seg_actual = (minutos * 60) + segundos;
                                    segundosTotal = tiempo_en_seg_actual;
                                    System.out.println("Tiempo_Estimado:" + tiempo_estimado +
                                            " Tiempo Actual "+tiempo_en_seg_actual
                                            +"SegundoActuales "+segundosTotal);
                                    
                                    if(tiempo_en_seg_actual >= tiempo_estimado){
                                            if(flagAnuncio == true){
                                                JOptionPane.showMessageDialog(null, "Ya hemos llegado al 90% del tiempo en segundos recomendado");
                                                flagAnuncio = false;
                                            }
                                    }
                                    
                                    /*Se habilita si se requiere en minutos */
//                                    if(tiempo_estimado == 1){
//                                       if(segundos > 50  ){
//                                            if(flagAnuncio == true){
//                                                JOptionPane.showMessageDialog(null, "Ya hemos llegado al 90% del tiempo recomendado");
//                                                flagAnuncio = false;
//                                            }
//                                        }
//                                    }else{
//                                    
//                                        if(minutos > ((tiempo_estimado/100)*90) ){
//                                            if(flagAnuncio == true){
//                                            JOptionPane.showMessageDialog(null, "Ya hemos llegado al 90% del tiempo recomendado");
//                                            flagAnuncio = false;
//                                            }
//                                        }
//                                    }
                            }
                    }
                    tiempo.setText( min + ":" + seg + ":" + mil );
//                    System.out.println(min + ":" + seg + ":" + mil);
                    
                    this.minutos = minutos; this.segundos = segundos; this.milesimas= milesimas;
            }catch(Exception e){System.out.println("Error al correr metodo run");}
            //Cuando se reincie se coloca nuevamente en 00:00:000
            tiempo.setText( "00:00:000" );
            System.out.println("00:00:000");
    }

    //Esto es para el boton iniciar y reiniciar
    public void actionPerformed( ActionEvent evt ) {
            Object o = evt.getSource();
            if( o instanceof JButton ) {
                    JButton btn = (JButton)o;
                    /*Solo se usa detener*/
//                    if( btn.getText().equals("Iniciar") ){
//                            iniciarCronometro();
//                    }
//                    if( btn.getText().equals("Reiniciar") ) {
//                            reiniciarCronometro();
//                    }
                    if( btn.getText().equals("Detener") ) {
                            pararCronometro();
                            this.dispose();
                    }
            }
    }

    //Iniciar el cronometro poniendo cronometroActivo 
    //en verdadero para que entre en el while
    public String iniciarCronometro(modeloPedido mp) {
            // Ajuste al código: se crea if esto para que cuando se vuelva oprimir iniciar no llame a otro hilo e impida que se vea como si estuviera en error.
            //String time = minutos + ":" + segundos + ":" + milesimas;
            this.mp = mp;
            System.out.println("Inicia en"+minutos + ":" + segundos + ":" + milesimas);
            //System.out.println(mp.toString());
            if (iniciado) {
                    hilo = new Thread( this ); 	
                    cronometroActivo = true;
                    pausar = false;       
                    hilo.start();
                    iniciado = false;
            }else {
            }
            return minutos + ":" + segundos + ":" + milesimas;
    }

//Esto es para detener el cronometro
    public void pararCronometro(){
            pausar = true ;
            // Ajuste al codigo: cada vez que el cronometro sea detenido se habilita varaible iniciado para que se pueda crear una nueva instancia de hilo
            iniciado = true;
            System.out.println("Paro en: "+minutos + ":" + segundos + ":" + milesimas);
//            double comision = 
            cbd.openConnection();
            /*El timpo de trabajo se guarda en minutos*/
            double costo_Aux = cbd.obtenerCostoArreglo(mp.getId_arreglo());
            costo_Aux = costo_Aux * 0.5; /*Se obtiene el 5% de ganancia*/
            double comision_Aux = cbd.obtenComision(mp.getNum_empleado());
            comision_Aux = comision_Aux + costo_Aux;
            System.out.println("Comision Auxiliar"+comision_Aux);
            cbd.actualizarPedidoTime(mp.getId(),mp.getNum_empleado(), mp.getHora_final());
            int tiempo_Aux = cbd.obtenTiempoTrabajado(mp.getNum_empleado());
            tiempo_Aux = tiempo_Aux + segundos; //En Minutos pero puede ser en segundos
            System.out.println("Tiempo Auxiliar: "+tiempo_Aux);
                cbd.actualizarTrabajoEmpleado(mp.getNum_empleado(), comision_Aux, tiempo_Aux);
            cbd.closeConnection();
            
            int ok = (segundosTotal-mp.getTiempo_estimado());
            cbd.openConnection();
                cbd.insertarBono(mp.getNum_empleado(),comision_Aux,ok,
                        cbd.obtenerFechaActualSoloFecha(),mp.getId());
            cbd.closeConnection();
            JOptionPane.showMessageDialog(null, "Ha Finalizado la elaboración,\n se cambia el estatus a terminado,\n "
                    + "la diferencia de tiempo estimado y actual (en segundos)"
                    + " fue \n "+(segundosTotal-mp.getTiempo_estimado()));
            //********************************************************************************************************************************************
    }

    public void reiniciarCronometro(){
            cronometroActivo = false;
            iniciado = true;
            // Ajuste al codigo: se adiciona esta linea para que deje el label en formato "00:00:00" 
            tiempo.setText( "00:00:000" );
            System.out.println("00:00:000");
            //****************************************************************************************
    }

//    public static void main(String[] args) {		
//            new cronometroHilo();
//    }
}