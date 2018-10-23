/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1ep2;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author javier Arnau y Adrian Bonilla
 */

/*
    Clase de alquilar objetos cpontiene funciones las cuales se utilizaran para poder alquilar un determinado objeto

*/
public class AlquilerObjetos {
    private String nombre; 
    private Date fechaInicio, fechaFin;
    private float coste;
    private float startup;

    
    public AlquilerObjetos(String nombre, Date fechaInicio, Date fechaFin, float coste){
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.coste = coste;
        obtenerStartup(coste);

    }
    
    private float obtenerStartup(float coste){
        startup = (float) (0.1 * coste);
        return startup;
    }
    
    public float getCoste(){
        return coste;
    }
    public float getStartup(){
        return obtenerStartup(getCoste());
    }
    
       public String toString(){

        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        String fini = fecha.format(fechaInicio);
        String ffin = fecha.format(fechaFin);
        return "\n\tNombre del cliente: "+ nombre + "\n\tFechas del prestamo: " + fini + " - " + ffin + "\n\tImporte para el propietario: "+
                coste + " euros\n\tImporte para la startup: "+ startup + " euros\n";
    }
}

