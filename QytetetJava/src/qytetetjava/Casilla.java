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
public abstract class Casilla {
    protected int numeroCasilla;
    protected int coste;
    protected TipoCasilla tipo;
    
    
    // Constructor para casillas de tipo calle
    public Casilla(int ncas, int coste, TipoCasilla tipo){
        this.numeroCasilla = ncas;
        this.coste = coste;
        this.tipo = tipo; 
    }
    
    public Casilla(int numeroCasilla, TipoCasilla tipo) {
        this.numeroCasilla = numeroCasilla;
        this.tipo = tipo;
        if (tipo == TipoCasilla.IMPUESTO)
            this.coste = 200;
    }
    
    public int getNumeroCasilla() {
        return numeroCasilla;
    }
    
    public int getCoste() {
        return coste;
    }

    public TipoCasilla getTipo() {
        return tipo;
    }
    
    boolean soyEdificable() {
        boolean ret = false;
        
        if (this.tipo == TipoCasilla.CALLE)
            ret = true;
        else
            ret =  false;
        return ret;
    }
    
    public abstract int getNumHoteles();

    public abstract int getNumCasas();
    
    public void setNumeroCasilla(int numeroCasilla) {
        this.numeroCasilla = numeroCasilla;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    @Override
    public String toString() {
        
        return  "Casilla" + "\n Numero Casilla=" + numeroCasilla + "\n Coste=" + coste + "\n Tipo=" + tipo;
    }
    
}
