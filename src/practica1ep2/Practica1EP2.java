/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1ep2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * @author javier
 */
public class Practica1EP2 {
    ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
    
   
    public static void menu(){
        String opcion;
        Practica1EP2 ep2 = new Practica1EP2();

        int numInt, idUsuario = 1, idObjeto = 1;
        do{
            do{
                System.out.println("\n1. Alta Usuario" +"\n2. Alta objeto"+"\n3. Alquiler Objeto"+"\n4. Todos los Objetos"
                                    +"\n5. Baja Objetos"+"\n6. Mostrar Saldos"+"\n7. Salir");
                System.out.print("Elige la opcion que desee: ");

                Scanner sc = new Scanner(System.in);
                opcion = sc.nextLine();
            
            }while(ComprobarInt(opcion) == false);
            numInt = Integer.parseInt(opcion);
        
            switch(numInt){
                case 1:
                    ep2.altaUsuario(idUsuario);
                    idUsuario++;
                    break;
                case 2:
                    ep2.altaObjeto(idObjeto);
                    idObjeto++;
                    break;
                case 3:
                    ep2.alquilerObjeto();
                    break;        
                case 4:
                    ep2.listarTodosObjetos();
                    break;
                case 5:
                    ep2.bajaObjeto();
                    break;
                case 6:
                    ep2.mostrarSaldos();
                    break;
                default:
                    System.out.println("Por favor introduce un numero entre el 1 y el 7. Gracias!!!");
                    break;
            }
        }while(numInt != 7);
        
    }
    public void altaUsuario(int idUsuario){
        
        Usuario usuario;
        //Iterator<Usuario> iterator = listaUsuario.iterator();

        String nombre, correo;
        System.out.print("Escribe el nombre: ");
        nombre = escribirDatos();
        do{
            System.out.print("Escribe el correo electronico: ");
            correo = escribirDatos();
        }while(ValidarCorreo(correo) == false);

        usuario = new Usuario(idUsuario, nombre, correo); 
        listaUsuario.add(usuario);
        System.out.println(listaUsuario);

    }
    
    public void altaObjeto(int idObjeto){
        Objeto o;
        String numUsuario, descripcion, fechaInicio, fechaFin, coste;
        int idUsuario;
        float costeObjeto;
        boolean ok = false;
        Date fini = null, ffin = null;
        
        if(listaUsuario.isEmpty()){
            System.out.println("Lo sentimos la lista de usuarios está vacia. Introduzca antes los usuarios.");
        }
        else{
            System.out.println(listaUsuario);
            do{
                do{
                    System.out.println("Inserta el identificador de usuario que desee: ");
                    numUsuario = escribirDatos();
                }while(ComprobarInt(numUsuario) == false);
                
                idUsuario = Integer.parseInt(numUsuario);
                
            }while(idUsuario > listaUsuario.size());
            
            
            if(idUsuario > 0 && idUsuario <= listaUsuario.size()){
        
                System.out.print("Escribe la descripcion del objeto: ");
                descripcion = escribirDatos();

                do{
                    System.out.print("Escribe la fecha de inicio: ");
                    fechaInicio = escribirDatos();

                    System.out.print("Escribe la fecha de fin: ");
                    fechaFin = escribirDatos();


                    try{
                            fini = ComprobacionFecha(fechaInicio);
                            ffin = ComprobacionFecha(fechaFin);

                            if(fini.before(ffin))
                            {
                                ok = true;
                            }
                            else
                                System.out.println("Vuelve a escribir la fecha bien!!");

                        }catch(ParseException e){
                            System.out.println("Ha ocurrido una excepcion tipo: "+e);
                        }
                }while(ok != true);
                
                do{
                    System.out.print("Escribe el coste del producto: ");
                    coste = escribirDatos();
                }while(ComprobarFloat(coste) == false);
                
                    costeObjeto = Float.parseFloat(coste);
                    
                    o = new Objeto(idObjeto, descripcion, fini, ffin, costeObjeto);
                    for(int i = 0; i < listaUsuario.size(); i++)
                        if(i == idUsuario-1)
                            listaUsuario.get(i).addObjeto(o);
            }
        }
    }
    
    
     /*
        Funcion que alquila un objeto de un determinado usuario. Siempre se le pasa la lista de objetos y la de usuarios ya que
        para poder asi enlazar el objeto alquilado de la lisya objetos con el usuario que tiene ese objeto
    */
    
    public void alquilerObjeto(){
        
        AlquilerObjetos alquilerObjetos;
        String numUsuario,numObjeto, nombre, fechaInicio, fechaFin, coste;
        int idUsuario, idObjeto;
        float costeObjeto;        
        Date fechaini, fechafin;
        
        if(listaUsuario.isEmpty()){
            System.out.println("Lo sentimos la lista de usuarios está vacia. Introduzca antes los usuarios.");
        }        
        else{
            System.out.println(listaUsuario);
            do{
                do{
                    System.out.println("Inserta el identificador de usuario que desee: ");
                    numUsuario = escribirDatos();
                }while(ComprobarInt(numUsuario) == false);

                idUsuario = Integer.parseInt(numUsuario);

            }while(idUsuario > listaUsuario.size());
            
            for(int i = 0; i < listaUsuario.size(); i++){
                if(i == listaUsuario.get(idUsuario - 1).getCodigo())
                    listaUsuario.get(i).toString();      
            }
            
            for(int posActualUs = 0; posActualUs < listaUsuario.size(); posActualUs++) 
                if(posActualUs == idUsuario -1){
                    do{
                        do{
                            System.out.println("Inserta el identificador del objeto que desee: ");
                            numObjeto = escribirDatos();
                        }while(ComprobarInt(numObjeto) == false);

                        idObjeto = Integer.parseInt(numObjeto);
                    }while(idObjeto > listaUsuario.get(posActualUs).obtenerTamListaObjeto());
                    
                    
                    for(int i = 0; i < listaUsuario.get(posActualUs).obtenerTamListaObjeto(); i++){
                        if(i == listaUsuario.get(posActualUs).ObtenerCodigoObjeto(idObjeto)){
                            System.out.println("\nIntroduce el nombre del cliente para realizar el alquiler: ");
                            nombre = escribirDatos();
                            System.out.println("\nIntroduce la fecha de inicio del alquiler del objeto con el formato (dd/mm/aaaa): ");
                            fechaInicio = escribirDatos();
                            System.out.println("\nIntroduce la fecha de fin del alquiler del objeto con el formato (dd/mm/aaaa):  ");
                            fechaFin = escribirDatos();
                            
                            
                            do{
                                System.out.print("Escribe el importe para el propietario: ");
                                coste = escribirDatos();
                            }while(ComprobarFloat(coste) == false);

                                costeObjeto = Float.parseFloat(coste);

                            try{
                                fechaini = ComprobacionFecha(fechaInicio);
                                fechafin = ComprobacionFecha(fechaFin);
                                alquilerObjetos = new AlquilerObjetos(nombre, fechaini, fechafin, costeObjeto);
                                        listaUsuario.get(posActualUs).addAlquilerObjeto(alquilerObjetos, idObjeto);
                            }catch(ParseException e){
                                System.out.println("El tipo de excepcion es: " + e);
                            }
                            
                        }     
                    }
                }
        }
     }
    
    public void listarTodosObjetos(){
        
        for(int i = 0; i < listaUsuario.size(); i++)
            System.out.println(listaUsuario.get(i));
    }
    
    public void bajaObjeto(){
        String numUsuario,numObjeto;
        int idUsuario, idObjeto;

        
        if(listaUsuario.isEmpty()){
            System.out.println("Lo sentimos la lista de usuarios está vacia. No se puede borrar ningun objeto");
        }        
        else{
            System.out.println(listaUsuario);
            do{
                do{
                    System.out.println("Inserta el identificador de usuario que desee: ");
                    numUsuario = escribirDatos();
                }while(ComprobarInt(numUsuario) == false);

                idUsuario = Integer.parseInt(numUsuario);

            }while(idUsuario > listaUsuario.size());
            
            for(int i = 0; i < listaUsuario.size(); i++){
                if(i == listaUsuario.get(idUsuario - 1).getCodigo())
                    listaUsuario.get(i).toString();      
            }
            
            for(int posActualUs = 0; posActualUs < listaUsuario.size(); posActualUs++) 
                if(posActualUs == idUsuario -1){
                    do{
                        do{
                            System.out.println("Inserta el identificador del objeto que desee para darle de baja: ");
                            numObjeto = escribirDatos();
                        }while(ComprobarInt(numObjeto) == false);

                        idObjeto = Integer.parseInt(numObjeto);
                    }while(idObjeto > listaUsuario.get(posActualUs).obtenerTamListaObjeto());
                
                    for(int i = 0; i < listaUsuario.get(posActualUs).obtenerTamListaObjeto(); i++){                   
                        if(i == listaUsuario.get(posActualUs).ObtenerCodigoObjeto(idObjeto)){
                            listaUsuario.get(posActualUs).borrarObjeto(idObjeto);
                            System.out.println("El objeto se ha borrado correctamente");
                        }
                    }
                }
        }
    }
    
      
    /*
        Te muestra los saldos totales de los objetos alquilados de un determinado usuario
    */
    public void mostrarSaldos(){
        for(int i = 0; i < listaUsuario.size(); i++){
            System.out.println(listaUsuario.get(i));
            listaUsuario.get(i).mostrarSaldoAlquiler();
        }
    }
    
    public void modificarImporte(){
        String numUsuario, numObjeto, nuevoDato;
        int idUsuario, idObjeto, posicion;
        float nuevoCoste;
        
        if(listaUsuario.isEmpty()){
            System.out.println("Lo sentimos la lista de usuarios está vacia. Introduzca antes los usuarios.");
        }        
        else{
            System.out.println(listaUsuario);
            do{
                do{
                    System.out.println("Inserta el identificador de usuario que desee: ");
                    numUsuario = escribirDatos();
                }while(ComprobarInt(numUsuario) == false);

                idUsuario = Integer.parseInt(numUsuario);

            }while(idUsuario > listaUsuario.size());
            
            for(int i = 0; i < listaUsuario.size(); i++){
                if(i == listaUsuario.get(idUsuario - 1).getCodigo())
                    listaUsuario.get(i).toString();      
            }
            
            for(int posActualUs = 0; posActualUs < listaUsuario.size(); posActualUs++) 
                if(posActualUs == idUsuario -1){
                    do{
                        do{
                            System.out.println("Inserta el identificador del objeto que desee: ");
                            numObjeto = escribirDatos();
                        }while(ComprobarInt(numObjeto) == false);

                        idObjeto = Integer.parseInt(numObjeto);
                    }while(idObjeto > listaUsuario.get(posActualUs).obtenerTamListaObjeto());
                    
                    
                    for(int i = 0; i < listaUsuario.get(posActualUs).obtenerTamListaObjeto(); i++){
                        if(i == listaUsuario.get(posActualUs).ObtenerCodigoObjeto(idObjeto)){
                            posicion = listaUsuario.get(i).buscarposObjetos(idObjeto);
                            if(posicion != 1){
                                do{
                                System.out.println("Introduce el nuevo coste del objeto: ");
                                nuevoDato = escribirDatos();
                                }while(ComprobarFloat(nuevoDato) == false);
                                nuevoCoste = Float.parseFloat(nuevoDato);
                                if(nuevoCoste >=0){
                                    listaUsuario.get(i).nuevoCoste(nuevoCoste, idObjeto);
                                    System.out.println("NUEVO COSTE ACTUALIZADO");
                                }
                                else
                                    System.out.println("El coste no se ha podido actualizar");
                            }
                            else
                                System.out.println("OBJETO NO ENCONTRADO");
                        }
                    }
                }
        }
    }
    

    public static String escribirDatos(){
        String datos;
        
        Scanner introducir = new Scanner(System.in);
        datos = introducir.nextLine();
        
        return datos;
    }
 
    public static boolean ComprobarInt(String numero) {
        int numEntero = 0;
        boolean ok = false;
        String s;
       
        try{
            numEntero = Integer.parseInt(numero);
            ok = true;
            if(numEntero <= 0){
                System.out.println("Introduce un numero positivo o mayor de 0.");
                ok = false;
            }
        }catch(NumberFormatException e){
            System.out.println("El numero no se ha podido convertir a un entero. La excepcion es: " + e);
        }
        return ok;
    }
    
    public static boolean ComprobarFloat(String numero) {
        float numFloat = 0;
        boolean ok = false;
        try{
            numFloat = Float.parseFloat(numero);
            ok = true;
            if(numFloat <= 0.0){
                System.out.println("Introduzca un numero positivo mayor o igual que 0.");
                ok = false;
            }
            // Tener en cuenta tanto aqui o en otra parte del codigo de que el precio debe de ser positivo, nunca NEGATIVO!!!!
        }catch(NumberFormatException e){
            System.out.println("El numero no se ha podido convertir a un entero. La excepcion es: " + e);
        }
        return ok;
    }

    
    public int devolverTamañoLista(){
        return listaUsuario.size();
    }
    
    public boolean devolverListaUsuario(){
        boolean ok = false;
        if(listaUsuario.isEmpty()){
            ok = false;
            System.out.println("Lo sentimos la lista de usuarios está vacia. Introduzca antes los usuarios.");
        }
        else{
            ok = true;
            System.out.println(listaUsuario);
        }
        return ok;
    }
     public void insertarObjetoListaUsuarios(Objeto o, int idUsuario){
        Iterator<Usuario> iteratorUsuarios = listaUsuario.iterator();

         for(int i = 0; i < listaUsuario.size(); i++)
            if(i == idUsuario)
                iteratorUsuarios.next().addObjeto(o);
     }
       /*
        Esta funcion es la de validar un correo electronico para que nos obligue a poner el @ y el . del dominio y tenga el formato de un correo electronico
        Esta hecho con expresiones regulares. Esta funcion esta sacada de la pagina web siguiente: https://fluidattacks.com/web/es/defends/java/validar-correo-electronico/
     */
    private static boolean ValidarCorreo(String email){
        boolean ok = false;
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
 
        // El email a validar
        //String email = "info@programacionextrema.com";
 
        Matcher mather = pattern.matcher(email);
 
        if(mather.find() == true) {
            ok = true;
        } else {
            ok = false;
        }
        return ok;
    }

    /*
        Esta funcion comprueba que la fecha introducida en otras funciones tengan el formato correcto que queremos dd/mm/yyyy . De no ser asi devolverá una excepcion
    
    */
     private static Date ComprobacionFecha(String tiposfechas ) throws ParseException{
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/mm/yyyy");
            Date comprobarfecha = formatoFecha.parse(tiposfechas);
        return comprobarfecha;
    }
     
    public static void main(String[] args) {
       menu();
    }
}
