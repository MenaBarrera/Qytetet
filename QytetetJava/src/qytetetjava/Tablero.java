/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qytetetjava;
import java.util.ArrayList;

/**
 *
 * @author vladislav
 */
public class Tablero {
    private ArrayList<Casilla> casillas;
    private Casilla carcel;
    
    public Tablero(){
        inicializar();
    }

    Casilla getCarcel() {
        return carcel;
    }
    
    boolean esCasillaCarcel(int numeroCasilla){
        return this.casillas.get(numeroCasilla).getTipo() == TipoCasilla.CALLE;
    }
    
    // precondicion: 0 <= numeroCasilla <= 19
    Casilla obtenerCasillaNumero(int numeroCasilla){
        return casillas.get(numeroCasilla);
    }
    
    Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento){
        int numCasilla = casilla.getNumeroCasilla(); 
        numCasilla += (numCasilla + desplazamiento) % casillas.size()  ;
        
        return this.casillas.get(numCasilla);
    }

    
    @Override
    public String toString() {
        return "Tablero{" + "casillas=" + casillas + ", carcel=" + carcel + '}';
    }
    
    public void inicializar(){
        this.casillas = new ArrayList();
        
        // Creamos las casillas que son calles
        int i = 0;
        ArrayList<TituloPropiedad> titulosCalles = new ArrayList();
        titulosCalles.add(new TituloPropiedad("Calle Segovia", 350, 0.17f, 80, 190));
        titulosCalles.add(new TituloPropiedad("Calle Arosa", 350, -0.14f, 90, 675));
        titulosCalles.add(new TituloPropiedad("Calle Salamanca", 400, 0.13f, 95, 340));
        titulosCalles.add(new TituloPropiedad("Calle Cuenca", 250, 0.17f, 65, 645));
        titulosCalles.add(new TituloPropiedad("Avenida de Móstoles", 650, 0.2f, 100, 1000));
        titulosCalles.add(new TituloPropiedad("Calle del Metal", 550, -0.2f, 75, 900));
        titulosCalles.add(new TituloPropiedad("Calle Santander", 350, 0.15f, 75, 625));
        titulosCalles.add(new TituloPropiedad("Calle Guadalajara", 400, 0.1f, 65, 970));
        titulosCalles.add(new TituloPropiedad("Calle Burgos", 500, 0.19f, 55, 310));
        titulosCalles.add(new TituloPropiedad("Calle Ciudad Real", 450, -0.14f, 75, 860));
        titulosCalles.add(new TituloPropiedad("Calle del Trueno", 600, 0.1f, 55, 650));
        titulosCalles.add(new TituloPropiedad("Calle de los Nardos", 750, 0.2f, 100, 1000));
        
        // Añadimos todas las casillas en orden menos la carcel
        this.casillas.add(new Casilla(0, TipoCasilla.SALIDA));
        this.casillas.add(new Casilla(1, 400, titulosCalles.get(i++)));
        this.casillas.add(new Casilla(2, 360, titulosCalles.get(i++)));
        this.casillas.add(new Casilla(3, 380, titulosCalles.get(i++)));
        this.casillas.add(new Casilla(4, 300, titulosCalles.get(i++)));
        this.casillas.add(new Casilla(5, TipoCasilla.CARCEL));
        this.carcel = this.casillas.get(5);
        this.casillas.add(new Casilla(6, TipoCasilla.SORPRESA));
        this.casillas.add(new Casilla(7, 500, titulosCalles.get(i++)));
        this.casillas.add(new Casilla(8, 490, titulosCalles.get(i++)));
        this.casillas.add(new Casilla(9, 400, titulosCalles.get(i++)));
        this.casillas.add(new Casilla(10, TipoCasilla.IMPUESTO));
        this.casillas.add(new Casilla(11, 550, titulosCalles.get(i++)));
        this.casillas.add(new Casilla(12, TipoCasilla.SORPRESA));
        this.casillas.add(new Casilla(13, 600, titulosCalles.get(i++)));
        this.casillas.add(new Casilla(14, TipoCasilla.PARKING));
        this.casillas.add(new Casilla(15, TipoCasilla.JUEZ));
        this.casillas.add(new Casilla(16, 550, titulosCalles.get(i++)));
        this.casillas.add(new Casilla(17, 700, titulosCalles.get(i++)));
        this.casillas.add(new Casilla(18, TipoCasilla.SORPRESA)); 
        this.casillas.add(new Casilla(19, 790, titulosCalles.get(i)));        
    }
}
