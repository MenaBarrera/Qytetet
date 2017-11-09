/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qytetetjava;

import java.util.Random;

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
        Random rand = new Random();
        
        return rand.nextInt(6) + 1;
        
    }

    @Override
    public String toString() {
        return "Dado{" + '}';
    }
    
    
}
