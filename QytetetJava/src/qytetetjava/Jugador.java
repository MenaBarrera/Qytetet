/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qytetetjava;

import java.util.ArrayList;

/*  *
    *
    * @author Miguel Angel Mena Barrera
    *
*/

public class Jugador {
    private boolean encarcelado = false;
    private String nombre;
    private int saldo = 7500;
    private Casilla casillaActual;
    private Sorpresa cartaLibertad;
    private ArrayList<TituloPropiedad> propiedades = new ArrayList();
    static int FactorEspeculador = 1;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    
    protected Jugador(Jugador jugador) {
        this.encarcelado = jugador.encarcelado;
        this.nombre = jugador.nombre;
        this.saldo = jugador.saldo;
        this.casillaActual = jugador.casillaActual;
        this.cartaLibertad = jugador.cartaLibertad;
        this.propiedades = jugador.propiedades;
    }
    
    public ArrayList<TituloPropiedad> getPropiedades(){
        return propiedades;
    }
    
    public Casilla getCasillaActual(){
        return casillaActual;
    }
    
    public boolean getEncarcelado(){
        return encarcelado;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFactorEspeculador() {
        return FactorEspeculador;
    }    

    public Sorpresa getCartaLibertad() {
        return cartaLibertad;
    }   
    
    public boolean tengoPropiedades(){
        boolean ret = false;
        if(propiedades.size() > 0){
            ret = true;
        }
        return ret;
    }
    
    protected boolean actualizarPosicion(Casilla casilla){
        boolean tengoPropietario = false;
        if(casilla.getNumeroCasilla() < casillaActual.getNumeroCasilla() ){
            this.modificarSaldo(Qytetet.SALDO_INICIAL);
        }
        
        this.setCasillaActual(casilla);
        
        if(casilla.soyEdificable()){
            Calle calle = (Calle)casilla;
            tengoPropietario = calle.tengoPropietario(); 
        
            if(calle.tengoPropietario()){
                encarcelado = calle.propietarioEncarcelado();
            
                if(!encarcelado){
                    if (!calle.estaHipotecada()) {
                        int costeAlquiler = calle.cobrarAlquiler();
                        this.modificarSaldo(-costeAlquiler);
                    }                   
                }
            }
        }
        else if(casilla.getTipo() == TipoCasilla.IMPUESTO){
            int coste = casilla.getCoste();
            this.pagarImpuestos(-coste);
        }
       return tengoPropietario; // QUE DEVUELVE ESTO ???
    }
    
    boolean comprarTitulo(){
        boolean puedoComprar = false;
        
        if(casillaActual.soyEdificable()){
            
            Calle calle = (Calle)casillaActual;
            boolean tengoPropietario = calle.tengoPropietario();
            
            if(!tengoPropietario){
                int costeCompra = calle.getCoste();
                
                if(costeCompra <= saldo){
                    TituloPropiedad titulo = calle.asignarPropietario(this);
                    titulo.setPropietario(this);
                    propiedades.add(titulo);
                    this.modificarSaldo(-costeCompra);
                    puedoComprar = true;
                }
            }
        }
        return puedoComprar;    
    }

    public int getSaldo() {
        return saldo;
    }
    
    Sorpresa devolverCartaLibertad(){
        Sorpresa cartaAux = new Sorpresa(cartaLibertad.getTexto(), cartaLibertad.getValor(), cartaLibertad.getTipo());
        cartaLibertad = null;
        return cartaAux;
    }
    
    void irACarcel(Casilla casilla){
        setCasillaActual(casilla);
        setEncarcelado(true);
    }
    
    void modificarSaldo(int cantidad){
        saldo = saldo + cantidad;
    }
    
    int obtenerCapital(){
        int capital = saldo;
        
        for (TituloPropiedad prop: propiedades){
            capital += (prop.getCasilla().getNumCasas() + prop.getCasilla().getNumHoteles()) * prop.getPrecioEdificar() + prop.getCasilla().getCoste();
        }
        
        return capital;
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada){
        ArrayList<TituloPropiedad> ret = new ArrayList();
        for (TituloPropiedad t : propiedades){
            if (t.getHipotecada() == hipotecada){
                ret.add(t);
            }
        }
        return ret;
    }
    
    void pagarCobrarPorCasaYHotel(int cantidad){
        int numeroTotal = cuantasCasasHotelesTengo();
        modificarSaldo(cantidad * numeroTotal);
    }
    
    boolean pagarLibertad(int cantidad){
        boolean tengoSaldo = tengoSaldo(cantidad);
        
        if (tengoSaldo)
            modificarSaldo(-cantidad);
        
        return tengoSaldo;
    }
    
    boolean puedoVenderPropiedad(Casilla casilla){
        boolean esMia, hipotecada;
        
        
        esMia = esDeMiPropiedad(casilla);
        Calle calle = (Calle)casilla;
        hipotecada = calle.estaHipotecada();
        
        return esMia && !hipotecada;
    }
    
    boolean puedoEdificarCasa(Casilla casilla){
        boolean esMia = this.esDeMiPropiedad(casilla);
        boolean tengoSaldo = false;
        
        Calle calle = (Calle)casilla;
        
        if(esMia){
            int costeEdificarCasa = calle.getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(-costeEdificarCasa);
        }
        return esMia && tengoSaldo;

    }
    
    boolean puedoEdificarHotel(Casilla casilla){
        boolean tengoSaldo = false;
        boolean esMia = esDeMiPropiedad(casilla);
        
        Calle calle = (Calle)casilla;
        
        if (esMia){
            int costeEdificar = calle.getPrecioEdificar();
            tengoSaldo = tengoSaldo(costeEdificar);
        }
        
        return esMia && tengoSaldo;
    }
    
    boolean puedoHipotecar(Casilla casilla){
        boolean esMia = esDeMiPropiedad(casilla);
        
        return esMia;
    }
    
    boolean puedoPagarHipoteca(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void setCartaLibertad(Sorpresa carta){
        cartaLibertad = new Sorpresa(carta.getTexto(), carta.getValor(), carta.getTipo());
    }
    
    void setCasillaActual(Casilla casilla){
            casillaActual = casilla; 
    }
    
    void setEncarcelado(boolean encarcelado){
        this.encarcelado = encarcelado;
    }
    
    boolean tengoCartaLibertad(){
        if( cartaLibertad != null ){
            return true;
        } else{
            return false;
        }
    }
    
    void venderPropiedad(Casilla casilla){
        Calle calle = (Calle)casilla;
        
        int precioVenta = calle.venderTitulo();
        modificarSaldo(precioVenta);
        eliminarDeMisPropiedades(casilla);
    }
    
    private int cuantasCasasHotelesTengo(){
        int result = 0;
        
        for (TituloPropiedad prop: propiedades){
            result+= prop.getCasilla().getNumCasas() + prop.getCasilla().getNumHoteles();
        }
        
        return result;
    }
    
    private void eliminarDeMisPropiedades(Casilla casilla){
        Calle calle = (Calle)casilla;
        TituloPropiedad titulo = calle.getTitulo();
        int index;

        if(this.propiedades.contains(titulo)){
            index = this.propiedades.indexOf(titulo);
            this.propiedades.remove(index);
        }
    }
    
    private boolean esDeMiPropiedad(Casilla casilla){
        boolean poseido = false;
        
        for (TituloPropiedad prop: propiedades){
            if (prop.getCasilla() == casilla)
                poseido = true;
        }
        
        return poseido;
    }
    
    private boolean tengoSaldo(int cantidad){
        boolean ret = false;
        
        if(saldo >= cantidad ){
            ret = true;
        }
        return ret;
    }
    
    protected void pagarImpuestos(int cantidad) {
        this.modificarSaldo(cantidad);
    }
    
    protected Especulador convertirme(int fianza) {
        Especulador espec = new Especulador(this, fianza);
        
        for (TituloPropiedad propiedad: espec.getPropiedades())
            propiedad.setPropietario(this);
        
        return espec;
    }
    
    @Override
    public String toString() {
        return "Jugador" + "\n Encarcelado=" + encarcelado + "\n Nombre=" + nombre + "\n Saldo=" + saldo  + "\n CartaLibertad=" + cartaLibertad + "\n Propiedades=" + propiedades;
    }   
} // FIN CLASE
