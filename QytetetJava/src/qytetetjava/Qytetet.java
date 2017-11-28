/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qytetetjava;

// Importamos ArrayList
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedHashMap;
/**
 *
 * @author vladislav
 */

// Para hacer un singleton (un único objeto) se hace que el constructor sea privado
public class Qytetet {
    public static int MAX_JUGADORES = 4;
    static int MAX_CARTAS = 10;
    static int MAX_CASILLAS = 20;
    static int PRECIO_LIBERTAD = 200;
    static int SALDO_INICIAL = 1000;
    private Sorpresa cartaActual;
    private ArrayList<Sorpresa> mazo;
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorActual;
    private Tablero tablero;
    private Dado dado;
    
    // Atributo de clase que contiene el singleton Qytetet
    private static Qytetet qytetet;
    
    // Constructor privado
    
    private Qytetet() throws IllegalArgumentException{
        
        inicializarTablero();       
        inicializarCartasSorpresa();
        dado = Dado.getInstance();
       // salidaJugadores();
    }
    public Jugador getJugadorActual(){
        return jugadorActual;
    }
    public static Qytetet getInstance() throws IllegalArgumentException{
        qytetet = new Qytetet();
        return qytetet;
    }
    
    public boolean aplicarSorpresa(){
        boolean tienePropietario = false;
        
        if (cartaActual.getTipo() == TipoSorpresa.PAGAROBRAR) {
            int cantidad = cartaActual.getValor();
            jugadorActual.modificarSaldo(cantidad);
        }
        
        else if (cartaActual.getTipo() == TipoSorpresa.IRACASILLA) {
            int numeroCasilla = cartaActual.getValor();
            boolean esCarcel = tablero.esCasillaCarcel(numeroCasilla);
            
            if (esCarcel)
                encarcelarJugador();
            
            else {
                Casilla nuevaCasilla = tablero.obtenerCasillaNumero(numeroCasilla);
                tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
            }
        }
        
        else if(cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL) {
            int cantidad = cartaActual.getValor();
            jugadorActual.pagarCobrarPorCasaYHotel(cantidad);
        }
        
        else if (cartaActual.getTipo() == TipoSorpresa.PORJUGADOR) {
            for (Jugador jugador: jugadores) {
                if (jugador != jugadorActual) {
                    int cantidad = cartaActual.getValor();
                    jugador.modificarSaldo(cantidad);
                    jugadorActual.modificarSaldo(-cantidad);
                }
            }
        }
        
        if (cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL) {
            jugadorActual.setCartaLibertad(cartaActual);
        }
        
        else {
            mazo.add(cartaActual);          // hay que hacer esto?
        }
        
        return tienePropietario;
    }
    
    public boolean cancelarHipoteca(Casilla casilla){
        boolean puedoCancelar = false;
        
        if (casilla.soyEdificable()) {
            boolean sePuedeCancelar = casilla.estaHipotecada();
            
            if (sePuedeCancelar) {
                puedoCancelar = jugadorActual == casilla.getTitulo().getPropietario();
                
                if (puedoCancelar) {
                    int costeCancelar = casilla.cancelarHipoteca();
                    jugadorActual.modificarSaldo(costeCancelar);
                }
            }
        }
        
        return puedoCancelar;
    }
    
    public boolean comprarTituloPropiedad(Casilla casilla){
        boolean puedoComprar = jugadorActual.comprarTitulo();
        return puedoComprar;
    }
    
    public boolean edificarCasa(Casilla casilla){
        boolean puedoEdificar = false;
        
        if(casilla.soyEdificable()){
            boolean sePuedeEdificar = casilla.sePuedeEdificarCasa();
            
            if(sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarCasa(casilla);
                
                if(puedoEdificar){
                    int costeEdificarCasa = casilla.edificarCasa();
                    jugadorActual.modificarSaldo(-costeEdificarCasa);
                }
            }
        }
        return puedoEdificar;
    }
    
    public boolean edificarHotel(Casilla casilla){
        boolean puedoEdificar = false;
        
        if (casilla.soyEdificable()) {
            boolean sePuedeEdificar = casilla.sePuedeEdificarHotel();
            
            if (sePuedeEdificar) {
                puedoEdificar = jugadorActual.puedoEdificarHotel(casilla);
                
                if (puedoEdificar) {
                    int costeEdificarHotel = casilla.edificarHotel();
                    jugadorActual.modificarSaldo(-costeEdificarHotel);
                }
            }
        }
        
        return puedoEdificar;
    }
    
    public Sorpresa getCartaActual(){
        return cartaActual;
    }
    
    
    public boolean hipotecaPropiedad(Casilla casilla){
        boolean sePuedeHipotecar, puedoHipotecar = false;
        if (casilla.soyEdificable()) {
            sePuedeHipotecar = !casilla.estaHipotecada();
            
            if (sePuedeHipotecar) {
                puedoHipotecar = jugadorActual.puedoHipotecar(casilla);
                
                if (puedoHipotecar) {
                    int cantidadRecibida = casilla.hipotecar();
                    jugadorActual.modificarSaldo(cantidadRecibida);
                }
            }
        }
        
        return puedoHipotecar;
    }
    
    public void inicializarJuego(ArrayList<String> nombres){
        inicializarJugadores(nombres); 
        inicializarCartasSorpresa();
        inicializarTablero();
        salidaJugadores();
    }
    
    public boolean intentarSalirCarcel(int metodo){
        boolean libre;
        
        if (metodo == 1) {
            int valorDado = dado.tirar();
            libre = (valorDado > 5);
        }
        
        else {
            int cantidad = Qytetet.PRECIO_LIBERTAD;
            boolean tengoSaldo = jugadorActual.pagarLibertad(cantidad);
            libre = tengoSaldo;
        }
        
        if (libre)
            jugadorActual.setEncarcelado(false);
        
        return libre;
    }
    
    public boolean jugar(){
        int valorDado = dado.tirar();
        
        System.out.println("valorDado: " + valorDado);
        Casilla casillaPosicion = jugadorActual.getCasillaActual();
        Casilla nuevaCasilla = tablero.obtenerNuevaCasilla(casillaPosicion, valorDado);
        System.out.println("\n\nCasilla nueva ---" + nuevaCasilla);
                
        boolean tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
        
        if (!nuevaCasilla.soyEdificable()) {
            if (nuevaCasilla.getTipo() == TipoCasilla.JUEZ)
                encarcelarJugador();
            
            else if(nuevaCasilla.getTipo() == TipoCasilla.SORPRESA) {
                cartaActual = mazo.get(0);
                mazo.remove(0);
            }
        }
        
        return tienePropietario;
    }
    
    public LinkedHashMap obtenerRanking(){
        LinkedHashMap ranking = new LinkedHashMap();
        ArrayList<Jugador> aux = (ArrayList<Jugador>)jugadores.clone();
        
        
        // ESTO ES EXPERIMENTAL, NO SE SI FUNCIONARA
        aux.sort((j1, j2) -> j1.obtenerCapital() - j2.obtenerCapital());
        
        for (Jugador jugador: aux) {
            int capital = jugador.obtenerCapital();
            ranking.put(jugador.getNombre(), capital);
        }
        
        return ranking;
    }
    
    public ArrayList<Casilla> propiedadesHipotecadasJugador(boolean hipotecadas){
        ArrayList<Casilla> casillas = new ArrayList();
        
        ArrayList<TituloPropiedad> prop = jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas);
        
        for (int i = 0; i < MAX_CASILLAS; i++){
            for (TituloPropiedad aux: prop){
                if (aux == tablero.obtenerCasillaNumero(i).getTitulo())
                    casillas.add(tablero.obtenerCasillaNumero(i));
            }            
        }
            
        return casillas;
    }
    
    public Jugador siguienteJugador(){
        int pos = 0;
        boolean encontrado = false;
        
        for (int i = 0; i < jugadores.size() && !encontrado; i++){
            if (jugadorActual.equals(jugadores.get(i))){
                encontrado = true;
                pos = i;
            }                
        }
        
        jugadorActual = jugadores.get((pos + 1) % jugadores.size());
        
        return jugadorActual;
    }
    
    public boolean venderPropiedad(Casilla casilla){
        boolean puedoVender = jugadorActual.puedoVenderPropiedad(casilla);
        
        if (puedoVender) {
            jugadorActual.venderPropiedad(casilla);
        }
        
        return puedoVender;
    }
    
    private void encarcelarJugador(){
        if (!jugadorActual.tengoCartaLibertad()) {
            Casilla carcel = tablero.getCarcel();
            jugadorActual.irACarcel(carcel);
        }
        else {
            Sorpresa carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);                                        // esto es incluirAlFinal()?
        }
    }
    
    public ArrayList<Jugador> getJugadores(){
        return this.jugadores;
    }
    
    private void inicializarCartasSorpresa(){
        mazo = new ArrayList();
        
        mazo.add(new Sorpresa ("Te han pillado evadiendo impuestos. Te vas derechito a la cárcel.", tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Un diplomático anónimo ha pagado tu salida de la cárcel. Eres libre de irte." , 0, TipoSorpresa.SALIRCARCEL));
        mazo.add(new Sorpresa ("Te has dejado a tu perro en una tienda de la Avenida de Móstoles.", 7, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Decides ir a visitar los jardines de la Calle de los Nardos.", 19, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Has recibido una jugosa transacción de una gran empresa.", 450, TipoSorpresa.PAGAROBRAR));
        mazo.add(new Sorpresa ("No has hecho bien la declaración de la renta y tienes que pagar los impuestos.", -300, TipoSorpresa.PAGAROBRAR));
        mazo.add(new Sorpresa ("Es tu cumpleaños. Entre todos hte han regalado una suma de dinero para que la gastes en lo que quieras.", 125, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Has roto la estatua que te prestaron tus amigos para tu fiesta. Debes pagarles a cada uno lo que le pertoque.", -100, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Tienes que devolver el préstamo que pediste para poder cada una de tus propiedaes edificar." , -20, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Tus propiedades han sido muy bien valoradas y están llegando muchos inquilinos nuevos. Obtienes ganancias por cada propiedad.", 15, TipoSorpresa.PORCASAHOTEL));
    }
    
    private void inicializarJugadores(ArrayList<String> nombres) throws IllegalArgumentException{
        if (nombres.size() < 2 || nombres.size() > MAX_JUGADORES)
            throw new IllegalArgumentException("Número de jugadores inválido. Mínimo 2.");
        
        jugadores = new ArrayList();
        for (String iter: nombres)
            jugadores.add(new Jugador(iter));
    }
    
    private void inicializarTablero(){
        tablero = new Tablero();
    }
    
    private void salidaJugadores(){
        for (Jugador iter: jugadores)
            iter.setCasillaActual(tablero.obtenerCasillaNumero(0));
        
        Random rand = new Random();
        jugadorActual = jugadores.get(rand.nextInt(jugadores.size()));
       
    }
    
    public static Qytetet getQytetet(){
        return qytetet;
    }

    @Override
    public String toString() {
        return "Qytetet{" + "\ncartaActual=" + cartaActual + "\nmazo=" + mazo.toString() + "\njugadores=" + jugadores.toString() + "\njugadorActual=" + jugadorActual.toString() + "\ntablero=" + tablero.toString() + "\ndado=" + dado.toString() + '}';
    }  
 
}
