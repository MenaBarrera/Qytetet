/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qytetetjava;

/**
 *
 * @author mena
 */

public class Calle extends Casilla {
    private TituloPropiedad titulo;
    private int numHoteles = 0;
    private int numCasas = 0;
    
    public Calle(int ncas, int coste, TituloPropiedad titulo){
        super(ncas,coste,TipoCasilla.CALLE);
        setTituloPropiedad(titulo);
        this.titulo.setCasilla(this);
    }
    
    private void setTituloPropiedad(TituloPropiedad titulo){
        this.titulo = titulo;
    }
    
    public int getNumHoteles() {
        return numHoteles;
    }

    public int getNumCasas() {
        return numCasas;
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
    
    public void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }
    
    int calcularValorHipoteca() {
        int hipotecaBase = this.titulo.getHipotecaBase();
        
        int cantidadRecibida;
        cantidadRecibida = hipotecaBase + (int)(numCasas * (float)0.5 * hipotecaBase + numHoteles * hipotecaBase);
        
        return cantidadRecibida;
    }
    
    int cancelarHipoteca() {
        titulo.setHipotecada(false);
        int cantidadPagar = (int) (calcularValorHipoteca() * 1.1);
        
        return cantidadPagar;
    }
    
    int cobrarAlquiler() {
        int costeAlquilerBase = titulo.getAlquilerBase();
        int costeAlquiler = (costeAlquilerBase + (int)(numCasas*0.5 + numHoteles*2) );
        titulo.cobrarAlquiler(costeAlquiler);
        
        return costeAlquiler;
    }
    
    int edificarCasa() {
        int nuevoNum = numCasas+1;
        this.setNumCasas(nuevoNum);
        int costeEdificarCasa = titulo.getPrecioEdificar();
        
        return costeEdificarCasa;
    }
    
    int edificarHotel() {
        int nuevoNum = numHoteles + 1;
        numHoteles = nuevoNum;
        numCasas = 0;
        int costeEdificarHotel = titulo.getPrecioEdificar();
        
        return costeEdificarHotel;
    }
    
    boolean estaHipotecada() {
        return this.titulo.getHipotecada();
    }
    
    int getPrecioEdificar() {
        int costeEdificarCasa = titulo.getPrecioEdificar();
        return costeEdificarCasa;
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
        boolean encarcelado = titulo.propietarioEncarcelado();
        return encarcelado;
    }
    
    boolean sePuedeEdificarCasa(int factor) {
        return numCasas < 4 * factor;
    }
    
    boolean sePuedeEdificarHotel(int factor) {
        return numHoteles < (4 * factor) && numCasas == 4 * factor;
    }
    
    TituloPropiedad asignarPropietario(Jugador jugador) {
        titulo.setPropietario(jugador);
        
        return this.titulo;
    }

    boolean tengoPropietario() {
        boolean tengoPropietario = titulo.tengoPropietario();
        return tengoPropietario;
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
    
    
    
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////
    
    
    
    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////

    @Override
    public String toString() {
        return super.toString() + "  Calle"  + "\n  Num Hoteles=" + numHoteles + "\n  NumCasas=" + numCasas + "\n  Titulo=" + titulo;
    }
}
