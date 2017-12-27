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
    
    public abstract void metodo();
    
    /*
    public Casilla(int numeroCasilla, int coste, TituloPropiedad titulo) {
        this.numeroCasilla = numeroCasilla;
        this.coste = coste;
        setTituloPropiedad(titulo);
        this.titulo.setCasilla(this);
        this.tipo = TipoCasilla.CALLE;
    }
    */
    
    // Constructor para casillas que no so de tipo calle
    
    /*
    public Casilla(int numeroCasilla, TipoCasilla tipo) {
        this.numeroCasilla = numeroCasilla;
        this.tipo = tipo;
        if (tipo == TipoCasilla.IMPUESTO)
            this.coste = 200;
    }
    */
    
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
    
    
    /*
    @Override
    public String toString() {
        if (this.tipo == TipoCasilla.CALLE){
            return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", coste=" + coste + ", numHoteles=" + numHoteles + ", numCasas=" + numCasas + ", tipo=" + tipo + ", titulo=" + titulo.toString() + '}';
        }
        else{
            return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", tipo=" + tipo + '}';
        }
    }
    */

    @Override
    public String toString() {
        return "Casilla{" + "numeroCasilla=" + numeroCasilla + ", coste=" + coste + ", tipo=" + tipo + '}';
    }
    
}
