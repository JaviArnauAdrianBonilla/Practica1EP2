/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1ep2;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author javier Arnau y Adrian Bonilla
 */

/*
    Clase objeto contiene funciones que hacen referencia a un objeto y se utilizaran tanto en baja objeto, alta objeto y alquiler objeto

*/
public class Objeto {
    private int codigoObjeto;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private float coste;
    private ArrayList<AlquilerObjetos> alquilerObjetos;

    public Objeto(int codigoObjeto, String descripcion, Date fechaInicio, Date fechaFin, float coste)
    {
        this.codigoObjeto = codigoObjeto;
        this.descripcion=descripcion;
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        this.coste=coste;
        alquilerObjetos = new ArrayList<AlquilerObjetos>();  

    }
    
    public void setDescripcion(String des){
        this.descripcion = des;
    }
    public void setFechaInicio(Date fini){
        this.fechaInicio = fini;
    }
    public void setFechaFin(Date ffin){
        this.fechaFin = ffin;
    }
    public void setCoste(float cost){
        this.coste = cost;
    }
    public float getCoste(){
        return coste;
    }
    
    public Date getFechaInicio(){
        return fechaInicio;
    }
    public Date getFechaFin(){
        return fechaFin;
    }
    public int getcodigoObjeto(){
        return codigoObjeto;
    }
    public String getDescripcion(){
        return descripcion;
    }
            
    /*
        Te muestra los saldos totales de los objetos alquilados de un determinado usuario
    */
    public void mostrarSaldosAlquilado(){
        float saldo = 0;
        
        for(int i = 0; i < alquilerObjetos.size(); i++){
            saldo += alquilerObjetos.get(i).getStartup();
        }
        System.out.println("\nEl saldo total del objeto alquilado es: "+saldo+" euros.\n");
    }
    
    
    public void addAlquilerObjetos(AlquilerObjetos alquiler)
    {   
        alquilerObjetos.add(alquiler);            
    }
    public String toString()
    {
        String codigo = String.format("%03d", codigoObjeto);
        SimpleDateFormat formaFecha= new SimpleDateFormat("dd/mm/yyyy");
        String fechaIni = formaFecha.format(fechaInicio);
        String fechaFi = formaFecha.format(fechaFin);
        return "\n\tCodigo del objeto: "+ codigo +"\n\tDescripcion del objeto: "+descripcion+"\n\tFecha de inicio de disponibilidad: "
                + fechaIni +"\n\tFecha de fin de disponibilidad: "+ fechaFi+"\n\tCoste del objeto: "+coste +
                "\n\n\tPRESTAMOS DEL OBJETO: " + codigoObjeto +"\n"+ alquilerObjetos.toString();
    }
    
}

