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
public class Casilla {
    private int numeroCasilla;
    private int coste;
    private int numHoteles = 0;
    private int numCasas = 0;
    private TipoCasilla tipo;
    private TituloPropiedad titulo;
    
    // Constructor para casillas de tipo calle
    public Casilla(int numeroCasilla, int coste, TituloPropiedad titulo) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        setTituloPropiedad(titulo);
        this.titulo.setCasilla(this);
        this.tipo = TipoCasilla.CALLE;
    }
    
    // Constructor para casillas que no so de tipo calle
    public Casilla(int numeroCasilla, TipoCasilla tipo) {
        this.numeroCasilla = numeroCasilla;
        this.tipo = tipo;
    }

    public int getNumeroCasilla() {
        return numeroCasilla;
    }

    public int getCoste() {
        return coste;
    }

    public int getNumHoteles() {
        return numHoteles;
    }

    public int getNumCasas() {
        return numCasas;
    }

    public TipoCasilla getTipo() {
        return tipo;
    }

    public TituloPropiedad getTitulo() {
        return titulo;
    }

    public void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }

    public void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }

    public void setNumeroCasilla(int numeroCasilla) {
        this.numeroCasilla = numeroCasilla;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }
    
    private void setTituloPropiedad(TituloPropiedad titulo){
        this.titulo = titulo;
    }
    
    TituloPropiedad asignarPropietario(Jugador jugador) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int calcularValorHipoteca() {
        int hipotecaBase = this.titulo.getHipotecaBase();
        
        int cantidadRecibida;
        cantidadRecibida = hipotecaBase + (int)(numCasas * (float)0.5 * hipotecaBase + numHoteles * hipotecaBase);
        
        return cantidadRecibida;
    }
    
    int cancelarHipoteca() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int cobrarAlquiler() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int edificarCasa() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int edificarHotel() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean estaHipotecada() {
        return this.titulo.getHipotecada();
    }
    
    int getPrecioEdificar() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int hipotecar() {
        this.titulo.setHipotecada(true);
        int cantidadRecibida = calcularValorHipoteca();
        
        return cantidadRecibida;
    }
    
    int precioTotalComprar() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean propietarioEncarcelado() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean sePuedeEdificarCasa() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean sePuedeEdificarHotel() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean soyEdificable() {
        if (this.tipo == TipoCasilla.CALLE)
            return true;
        else
            return false;
    }
    
    boolean tengoPropietario() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int venderTitulo() {
        int precioVenta, precioCompra;
        precioCompra = this.coste + (this.numCasas + this.numHoteles) * this.titulo.getPrecioEdificar();
        precioVenta = (int) (precioCompra + titulo.getFactorRevalorizacion() * precioCompra);
        
        this.titulo.setPropietario(null);
        setNumHoteles(0);
        setNumCasas(0);
        
        return precioVenta;
    }
    
    void asignarTituloPropiedad() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    @Override
    public String toString() {
        if (this.tipo == TipoCasilla.CALLE){
            return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", coste=" + coste + ", numHoteles=" + numHoteles + ", numCasas=" + numCasas + ", tipo=" + tipo + ", titulo=" + titulo.toString() + '}';
        }
        else{
            return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", tipo=" + tipo + '}';
        }
    }
    
    
}
