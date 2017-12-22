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
public class Especulador extends Jugador {
    private int fianza;
    static int FactorEspeculador = 2;
    
    public Especulador(Jugador jugador, int fianza) {
        super(jugador);
        this.fianza = fianza;
    }
    
    @Override
    protected void irACarcel(Casilla casilla) {
        boolean evitarCarcel = this.pagarFianza(fianza);
        
        if (!evitarCarcel) {
            this.setCasillaActual(casilla);
            this.setEncarcelado(true);
        }        
    }
    
    private boolean pagarFianza(int cantidad) {
        boolean puedePagar = false;
        
        if (this.getSaldo() > fianza) {
            puedePagar = true;
            // se supone que hay que pasar la fianza como parametro
            this.modificarSaldo(-cantidad);
        }
        
        return puedePagar;
    }
    
    @Override
    protected void pagarImpuestos(int cantidad) {
        this.modificarSaldo(cantidad/2);
    }
    
    @Override
    protected Especulador convertirme(int fianza) {
        return this;
    }    

    public int getFianza() {
        return fianza;
    }
    
    @Override
    public int getFactorEspeculador() {
        return FactorEspeculador;
    }
    
    
    
}
