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
       
   }
   /*
   private Casilla elegirPropiedad(ArrayList<Casilla> propiedades){
       
   }
   */
   
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

      }
}

 