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
    
    public Especulador(Jugador jugador, int fianza) {
        super(jugador);
        this.fianza = fianza;
    }
}
