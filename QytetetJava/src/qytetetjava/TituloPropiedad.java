/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qytetetjava;

/**
 *
 * @author vladislav
 */


public class TituloPropiedad {
    private String nombre;
    private boolean hipotecada = false;
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private int precioEdificar;
    private Jugador propietario; 
    private Calle casilla;

    public TituloPropiedad(String nombre, int alquilerBase, float factorRevalorizacion, int hipotecaBase, int precioEdificar) {
        this.nombre = nombre;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
    }
    void cobrarAlquiler(int coste){
        propietario.modificarSaldo(coste);
    }
    
    public String getNombre() {
        return nombre;
    }

     boolean getHipotecada() {
        return hipotecada;
    }

     int getAlquilerBase() {
        return alquilerBase;
    }

     float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

     int getHipotecaBase() {
        return hipotecaBase;
    }

    int getPrecioEdificar() {
        return precioEdificar;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public Jugador getPropietario() {
        return propietario;
    }
    
        
    void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }
    
    boolean propietarioEncarcelado(){
        return propietario.getEncarcelado();
    }
    
    boolean tengoPropietario(){
        boolean ret = false;
        
        if(propietario != null)
            ret = true;
        
        return ret;
    }
    
    void setPropietario(Jugador propietario){
        this.propietario = propietario;
    }
    
    void setCasilla(Casilla casilla){
        this.casilla = (Calle) casilla;
    }
    
    
    @Override
    public String toString() {
        return "TituloPropiedad" + "\n    Nombre=" + nombre + "\n    Hipotecada=" + hipotecada + "\n    Alquiler Base=" + alquilerBase + "\n    Factor Revalorizacion=" + factorRevalorizacion + "\n    Hipoteca Base=" + hipotecaBase + "\n    Precio Edificar=" + precioEdificar;
    }
    
    
}
