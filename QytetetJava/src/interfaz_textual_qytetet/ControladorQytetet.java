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
       boolean finJuego = false;
       System.out.println("\n\n\n\ninterfaz_textual_qytetet.ControladorQytetet.desarrolloJuego()");
       vista.mostrar("Turno del jugador " +  jugador);
       vista.mostrar("Informacion de la casilla actual" + casilla);
       
       //while(!finJuego){
           boolean libre = !jugador.getEncarcelado();
           
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
               noTienePropietario = juego.jugar();
               actualizarCasilla();
               vista.mostrar(jugador.getNombre() + " se desplaza hasta la casilla numero " + casilla.getNumeroCasilla());
               vista.mostrar("Información de la casilla:" + casilla);
               
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
                                   if(casilla.getTipo() == TipoCasilla.CALLE)
                               }
                           }
                       }
                   }
               }
           //}
               
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
       
       System.out.println("---------------- JUEGO -------------");
       System.out.print(juego.toString());
    
   }
      public static void main(String[] args) {
          ControladorQytetet k= new ControladorQytetet();
          k.inicializacionJuego();
          k.desarrolloJuego();

      }

    private void actualizarCasilla() {
        casilla = jugador.getCasillaActual();
    }
}

 