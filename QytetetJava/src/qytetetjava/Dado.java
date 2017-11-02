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
public class Dado {
    private static final Dado dado = new Dado();
    
    private Dado(){}
    
    public static Dado getInstance(){
        return dado;
    }
    
    int tirar(){
        throw new UnsupportedOperationException("Sin implementar");
    }

    @Override
    public String toString() {
        return "Dado{" + '}';
    }
    
    
}
