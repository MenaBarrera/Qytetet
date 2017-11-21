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

    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    
    public Casilla getCasillaActual(){
        return casillaActual;
    }
    
    public boolean getEncarcelado(){
        return encarcelado;
    }
    
    public boolean tengoPropiedades(){
        boolean ret = false;
        if(propiedades.size() > 0){
            ret = true;
        }
        return ret;
    }
    
    boolean actualizarPosicion(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean comprarTitulo(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    Sorpresa devolverCartaLibertad(){
        Sorpresa cartaAux = new Sorpresa(cartaLibertad.getTexto(), cartaLibertad.getValor(), cartaLibertad.getTipo());
        cartaLibertad = null;
        return cartaAux;
    }
    
    void irACarcel(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
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
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean pagarLibertad(int cantidad){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean puedoVenderPropiedad(Casilla casilla){
        boolean ret = false;
        
        ret = esDeMiPropiedad(casilla);
        
        return ret;
    }
    
    boolean puedoEdificarCasa(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean puedoEdificarHotel(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
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
        if (casilla.getTipo() == TipoCasilla.CALLE)
            casillaActual = new Casilla(casilla.getNumeroCasilla(), casilla.getCoste(), casilla.getTitulo());
        else
            casillaActual = new Casilla(casilla.getNumeroCasilla(),casilla.getTipo());
    }
    
    void setEncarcelado(boolean encarcelado){
        this.encarcelado = encarcelado;
    }
    
    boolean tengoCartaLibertad(){
        if( cartaLibertad != null ){
            return true;
        }
        else{
            return false;
        }
    }
    
    void venderPropiedad(Casilla casilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private int cuantasCasasHotelesTengo(){
        int result = 0;
        
        for (TituloPropiedad prop: propiedades){
            result+= prop.getCasilla().getNumCasas() + prop.getCasilla().getNumHoteles();
        }
        
        return result;
    }
    
    private void eliminarDeMisPropiedades(Casilla casilla){

        TituloPropiedad titulo = casilla.getTitulo();
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
    
    @Override
    public String toString() {
        return "Jugador{" + "encarcelado=" + encarcelado + ", nombre=" + nombre + ", saldo=" + saldo + ", casillaActual=" + casillaActual + ", cartaLibertad=" + cartaLibertad + ", propiedades=" + propiedades + '}';
    }
    
    
           
    
} // FIN CLASE
