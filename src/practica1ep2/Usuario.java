/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1ep2;

import java.util.ArrayList;
import java.util.HashSet;
/**
 *
 * @author javier Arnau y Adrian Bonilla
 */

/*
    Clase Usuario contiene funciones que hacen referencia a un usuario determinado y se utilizaran tanto en alta usuario y alquiler usuario ademas de mostrar las listas

*/
public class Usuario {
    private String nombre;
    private int codigoUsuario;
    private String correo;
    private ArrayList<Objeto> listaObjetos;

    
    public Usuario(int codigoUsuario, String nombre, String correo)
    {
        this.codigoUsuario = codigoUsuario;
        this.nombre = nombre;
        this.correo = correo;
        listaObjetos = new ArrayList<Objeto>();
    }
    
    public void addAlquilerObjeto(AlquilerObjetos alquiler, int idObjeto){
        for(int i = 0; i < listaObjetos.size(); i++){
            if( i == idObjeto - 1){ // le ponemos -1 por el tamaño del vector que empieza en cero y mostramos los identificadores a partir del 1
                listaObjetos.get(i).addAlquilerObjetos(alquiler);
            }
        }
    }
    public void addObjeto(Objeto obje)
    {   
        listaObjetos.add(obje);            
    }
    
    public void borrarObjeto(int objeto){
        
        listaObjetos.remove(objeto-1);
    }
    
    public void mostrarSaldoAlquiler(){
        for(int i = 0; i < listaObjetos.size(); i++){
            listaObjetos.get(i).mostrarSaldosAlquilado();
        }
    }
    
    public int obtenerTamListaObjeto(){
      
        return listaObjetos.size();     
    }
    public int ObtenerCodigoObjeto(int num){
        int res = 0;
        for(int i = 0; i < listaObjetos.size(); i++){
            if(i == listaObjetos.get(num - 1).getcodigoObjeto()) // le ponemos -1 por el tamaño del vector que empieza en cero y mostramos los identificadores a partir del 1
                res = listaObjetos.get(i).getcodigoObjeto();
        }
        return res;
    }
    
    
    public String toString()
    { 
        if(listaObjetos.isEmpty())
            return "\n\nNombre del usuario: "+nombre+"\n"+"Correo del usuario: "+correo+"\n"+"Codigo de usuario: "+ codigoUsuario + "\n\n";
        else{
            return "\n\nNombre del usuario: "+nombre+"\n"+"Correo del usuario: "+correo+"\n"+"Codigo de usuario: "+ codigoUsuario + "\n\nOBJETOS DEL PROPIETARIO "+ codigoUsuario 
                    +"\n"+ listaObjetos.toString();
        }
    }
    public int getCodigo(){
        //Aqui si no funciona con el next poner un codigoUsucario Y borrar el de         codigoUsuarioNext = codigoUsuario++;

        return codigoUsuario;
    }
    public String getNombre(){
        return nombre;
    }
    public String getCorreo(){
        return correo;
    }
    public void setCodigo(int codi){
        this.codigoUsuario = codi;
    }
    public void setNombre(String nom){
        this.nombre = nom;
    }
    public void setCorreo(String c){
        this.correo = c;
    }
}

