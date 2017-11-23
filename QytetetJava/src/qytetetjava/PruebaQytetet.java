/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package qytetetjava;

//Hace falta importar el paquete de la clase
import java.util.ArrayList;

/**
 *
 * @author vladislav
 
public class PruebaQytetet {
    private static ArrayList<Sorpresa> mazo = new ArrayList();
    private static Tablero tablero = new Tablero();
    private static Qytetet qytetet;
    
    private static void inicializarSorpresas(){
        mazo.add(new Sorpresa ("Te hemos pillado con chanclas y calcetines,lo sentimos, ¡debes ir a la carcel!", tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Un fan anónimo ha pagado tu fianza. Sales de la cárcel", 0, TipoSorpresa.SALIRCARCEL));
        mazo.add(new Sorpresa ("Sorpresa: has activado mi carta trampa", 0, TipoSorpresa.IRACASILLA));
    }
    
    // Metodo que devuelve las cartas Sorpresa con un valor mayor estricto que 0
    private static ArrayList<Sorpresa> sorpresaMayorQueCero(){
        ArrayList<Sorpresa> arrayMayores = new ArrayList();
        
        for (Sorpresa sorpresa : mazo){
            if (sorpresa.getValor() > 0)
                arrayMayores.add(sorpresa);            
        }
        
        return arrayMayores;
    }
    
    // Metodo que devuelve las cartas Sorpresa con el tipo IrCasilla
    private static ArrayList<Sorpresa> sorpresasIrCasilla(){
        ArrayList<Sorpresa> arrayIrCasillas = new ArrayList();
        
        for (Sorpresa sorpresa : mazo){
            if (sorpresa.getTipo() == TipoSorpresa.IRACASILLA)
                arrayIrCasillas.add(sorpresa);
        }
        
        return arrayIrCasillas;
    }
    
    // Metodo que devuelve las cartas Sorpresa con el tipo pasado por parámetro
    private static ArrayList<Sorpresa> sorpresasTipoCasilla(TipoSorpresa tipoSorpresa){
        ArrayList<Sorpresa> arraySorpresas = new ArrayList();
        
        for (Sorpresa sorpresa : mazo){
            if (sorpresa.getTipo() == tipoSorpresa)
                arraySorpresas.add(sorpresa);
        }
        
        return arraySorpresas;
    }
    /**
     * @param args the command line arguments
     
    
    public static void main(String[] args) {
        // TODO code application logic here
        inicializarSorpresas();
        System.out.println(mazo.toString());
        System.out.println("Cartas del mazo:");
        for (Sorpresa sorpresa : mazo) {
            System.out.println(sorpresa.toString());            
        }
        
        ArrayList<Sorpresa> mayor = sorpresaMayorQueCero();
        System.out.println("Sorpresas mayores que 0:");
        for (Sorpresa sorpresa : mayor) {
            System.out.println(sorpresa.toString());            
        }
        
        ArrayList<Sorpresa> casillas = sorpresasIrCasilla();
        System.out.println("Sorpresas de IrCasilla:");
        for (Sorpresa sorpresa : casillas){
            System.out.println(sorpresa.toString());
        }
        
        ArrayList<Sorpresa> tipoCasilla = sorpresasTipoCasilla(TipoSorpresa.PAGAROBRAR);
        System.out.println("Cartas tipo SalirCarcel");
        for (Sorpresa sorpresa : tipoCasilla){
            System.out.println(sorpresa.toString());
        }
        
        System.out.print(tablero.toString());        
        
        ArrayList<String> nombres = new ArrayList();
        nombres.add("pepe");
        nombres.add("pablo");
        
        
        try{
            qytetet = Qytetet.getInstance();
            System.out.println("\nBIENVENIDOS A QYTETET");
            System.out.print(qytetet.toString());

            System.out.print(tablero.toString());
        }
        catch (IllegalArgumentException i){
            System.out.println("\nERROR. 2 <= NUM_JUGADORES <= 4");
        }
        
        
        
    }
    
}
*/