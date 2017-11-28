/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_textual_qytetet;
import java.util.ArrayList;
import qytetetjava.*;
/**
 *
 * @author vladislav
 */
public class ControladorQytetet {
   private Qytetet juego;
   private Jugador jugador;
   private Casilla casilla;
   private VistaTextualQytetet vista = new VistaTextualQytetet();
   
   public void desarrolloJuego(){
       int eleccion = -1;
       boolean finJuego = false;
       System.out.println("\n\n\n\ninterfaz_textual_qytetet.ControladorQytetet.desarrolloJuego()");
       vista.mostrar("Turno del jugador " +  jugador);
       vista.mostrar("\n\n\nInformacion de la casilla actual" + casilla);
       
       while(!finJuego){
           boolean libre = !(jugador.getEncarcelado());
           eleccion = -1;
           if(!libre){
             vista.mostrar("El jugador " + jugador.getNombre() + " está encarcelado");
             int metodo = vista.menuSalirCarcel();
             libre = juego.intentarSalirCarcel(metodo);
             
             if(libre)
                 vista.mostrar("has salido de la carcel ");
             else
                 vista.mostrar("no has salido de la carcel ");
           }
           
           if(libre){
               boolean noTienePropietario;
               noTienePropietario = !(juego.jugar());
               actualizarCasilla();
               vista.mostrar(jugador.getNombre() + " \n\nse desplaza hasta la casilla numero " + casilla.getNumeroCasilla());
               vista.mostrar("\n\nInformación de la casilla:" + casilla);
               vista.mostrar("jugador name::: " + jugador.getNombre());
               
               if(!bancarrota()){
                   if(!jugador.getEncarcelado()){
                       if(casilla.getTipo() == TipoCasilla.CALLE){
                           if(noTienePropietario){
                                int saldo = jugador.getSaldo();
                                vista.mostrar("El saldo disponible es de: " + saldo);
                                vista.mostrar("El precio de la propiedad es de: " +casilla.getCoste());
                                vista.mostrar("el saldo final seria de " +(saldo-casilla.getCoste()));
                                boolean quieroComprar = vista.elegirQuieroComprar();
                                
                                if(quieroComprar){
                                    boolean comprado = juego.comprarTituloPropiedad(casilla);
                                    vista.mostrar("El saldo actual del jugador " +jugador.getNombre() + " es de " + jugador.getSaldo());
                                    
                                }
                           }
                       }
                       else if(casilla.getTipo() == TipoCasilla.SORPRESA){
                           noTienePropietario = juego.aplicarSorpresa();
                           if(!jugador.getEncarcelado()){
                               if(!bancarrota()){
                                   if(casilla.getTipo() == TipoCasilla.CALLE){
                                       if(noTienePropietario){
                                           int saldo = jugador.getSaldo();
                                           vista.mostrar("El saldo disponible es de: " + saldo);
                                           vista.mostrar("El precio de la propiedad es de: " +casilla.getCoste());
                                           vista.mostrar("el saldo final seria de " +(saldo-casilla.getCoste()));
                                           boolean quieroComprar = vista.elegirQuieroComprar();
                                           
                                           if(quieroComprar){
                                                boolean comprado = juego.comprarTituloPropiedad(casilla);
                                                vista.mostrar("El saldo actual del jugador " +jugador.getNombre() + " es de " + jugador.getSaldo());
                                    
                                            }
                                       }
                                   }
                               }
                           }
                       }
                       //OTRO
                       if(!jugador.getEncarcelado() && !bancarrota() && jugador.tengoPropiedades()){
                           
                           ArrayList<TituloPropiedad> listaPropiedades = jugador.getPropiedades();
                           ArrayList<Casilla> casillas = new ArrayList();
                           ArrayList<String> names = new ArrayList();
                           
                           for( TituloPropiedad i : listaPropiedades){
                                   casillas.add(i.getCasilla());
                                   names.add(i.getNombre());
                               
                           }
                           
                           boolean correcto;
                           
                           while(eleccion != 0){
                               vista.mostrar("ELIGE PROPIEDADES\n");
                               Casilla casilla_metodo = elegirPropiedad(casillas);
                               
                               
                               vista.mostrar("\ncasilla_actual" + casilla_metodo);
                               
                               eleccion = vista.menuGestionInmobiliaria();

                               if(eleccion == 1){
                                   vista.mostrar("saldo previo: " + jugador.getSaldo());
                                   correcto = juego.edificarCasa(casilla_metodo);
                                   vista.mostrar("saldo despues: " + jugador.getSaldo());
                                   if(correcto){
                                      
                                      vista.mostrar("se ha edificado la casa en" + casilla_metodo);
                                      
                                   }
                                   else{
                                       vista.mostrar("Error al edificar la casa");
                                   }
                               }
                               if(eleccion == 2){
                                    vista.mostrar("saldo previo: " + jugador.getSaldo());                                  

                                   correcto = juego.edificarHotel(casilla_metodo);
                                   vista.mostrar("saldo despues: " + jugador.getSaldo());
                                   if(correcto){
                                      vista.mostrar("se ha edificado la hotel en" + casilla_metodo);
                                   }
                                   else{
                                       vista.mostrar("Error al edificar la hotel");
                                   }
                               }                               
                               if(eleccion == 3){
                                   vista.mostrar("saldo previo: " + jugador.getSaldo());
                                   correcto = juego.venderPropiedad(casilla_metodo);
                                   vista.mostrar("saldo despues: " + jugador.getSaldo());
                                   if(correcto){
                                      vista.mostrar("se ha vendido la propiedad de " + casilla_metodo);
                                   }
                                   else{
                                       vista.mostrar("Error al vender");
                                   }
                               }
                               if(eleccion == 4){
                                   vista.mostrar("saldo previo: " + jugador.getSaldo());
                                  correcto = juego.hipotecaPropiedad(casilla_metodo);
                                  vista.mostrar("saldo despues: " + jugador.getSaldo());
                                  if(correcto){
                                      vista.mostrar("se ha hipotecado la casilla " + casilla_metodo);
                                   }
                                   else{
                                       vista.mostrar("Error al hipotecar ");
                                   }
                               }
                               if(eleccion == 5){
                                   vista.mostrar("saldo previo: " + jugador.getSaldo());
                                   correcto= juego.cancelarHipoteca(casilla_metodo);
                                   vista.mostrar("saldo despues: " + jugador.getSaldo());
                                   if(correcto){
                                      vista.mostrar("se ha cancelado la hipoteca " + casilla_metodo);
                                   }
                                   else{
                                       vista.mostrar("Error al deshipotecar ");
                                   }
                               } 
                           }
                       }
                    }
                }
            }
            if(!bancarrota()){
                juego.siguienteJugador();
                jugador = juego.getJugadorActual();
                vista.mostrar("Siguente jugador ::: Nombre::: "+ jugador.getNombre());
            }
            if(bancarrota()){
                juego.obtenerRanking();
                finJuego = true;
            }
        }
   }
 
   boolean bancarrota(){
       return jugador.getSaldo() <= 0;
   }
   
   public void inicializacionJuego(){
       ArrayList<String> names = new ArrayList();
       juego = Qytetet.getInstance();
       names = vista.obtenerNombreJugadores();
       
       juego.inicializarJuego(names);
       jugador = juego.getJugadorActual();
       casilla = jugador.getCasillaActual();
       
       //vista.mostrar(casilla);
       
       System.out.println("---------------- JUEGO -------------");
       //System.out.print(juego.toString());
    
   }
      public static void main(String[] args) {
          ControladorQytetet k= new ControladorQytetet();
          k.inicializacionJuego();
          k.desarrolloJuego();

      }

    private void actualizarCasilla() {
        casilla = jugador.getCasillaActual();
    }
    
    public Casilla elegirPropiedad(ArrayList<Casilla> propiedades){ 
         //este metodo toma una lista de propiedades y genera una lista de strings, con el numero y nombre de las propiedades
         //luego llama a la vista para que el usuario pueda elegir.
        vista.mostrar("\tCasilla\tTitulo");
        int seleccion;
        ArrayList<String> listaPropiedades= new ArrayList();
        for ( Casilla casilla1: propiedades) {
            listaPropiedades.add( "\t"+casilla1.getNumeroCasilla()+"\t"+casilla1.getTitulo().getNombre()); 
        }
        seleccion=vista.menuElegirPropiedad(listaPropiedades);  
        
        return propiedades.get(seleccion);
    }
}

 
