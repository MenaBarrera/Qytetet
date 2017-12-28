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
public class Sorpresa {
    private String texto;
    private int valor;
    private TipoSorpresa tipo;

    Sorpresa(String texto, int valor, TipoSorpresa tipo) {
        this.texto = texto;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    int getValor() {
        return valor;
    }

    TipoSorpresa getTipo() {
        return tipo;
    }
        
    //No se puede cambiar la visibilidad del método
    //Override = reescribir
    @Override
    public String toString() {
        return "Sorpresa{" + "texto=" + texto + ", valor=" + valor + ", tipo=" + tipo + '}';
    }    
}
