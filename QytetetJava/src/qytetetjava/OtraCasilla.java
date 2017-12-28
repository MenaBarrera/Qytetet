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
public class OtraCasilla extends Casilla {

    public OtraCasilla(int numeroCasilla, TipoCasilla tipo) {
        super(numeroCasilla, tipo);
    }
    

    @Override
    public int getNumHoteles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumCasas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "OtraCasilla{" + '}';
    }

    
    
}
