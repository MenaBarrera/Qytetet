/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIQytetet;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import qytetetjava.MetodoSalirCarcel;
import qytetetjava.Qytetet;
import qytetetjava.Casilla;
import qytetetjava.TipoCasilla;
import qytetetjava.TituloPropiedad;

/**
 *
 * @author mena
 */
public class ControladorQytetet extends javax.swing.JFrame {
    public Qytetet modeloQytetet;
    /**
     * Creates new form ControladorQytetet
     */
    public ControladorQytetet() {
        initComponents();
    }
    
    public void actualizar(Qytetet q){        
        vistaQytetet2.Actualizar(q);
        modeloQytetet = q;
        habilitarComenzarTurno();
    }
    
    public void habilitarComenzarTurno(){
        this.jbComprar.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(false);
        this.jbAplicarSorpresa.setEnabled(false);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        this.jbGestionInmobiliaria.setEnabled(false);
        if(modeloQytetet.getJugadorActual().getEncarcelado()){
            this.jbSalirCarcelPagando.setEnabled(true);
            this.jbSalirCarcelDado.setEnabled(true);
        } else {
            this.jbJugar.setEnabled(true);            
        }
    }
    
    public void finJuego() {
        deshabilitarBotones();
        JOptionPane.showMessageDialog(this, modeloQytetet.obtenerRanking().toString());        
    }
    
    public void deshabilitarBotones() {
        this.jbComprar.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(false);
        this.jbAplicarSorpresa.setEnabled(false);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        this.jbJugar.setEnabled(false);   
        this.jbGestionInmobiliaria.setEnabled(false);
    }
    
    private void comprobarCambios(int saldo, boolean encarcelado) {
        if (saldo != modeloQytetet.getJugadorActual().getSaldo()) {
            JOptionPane.showMessageDialog(this, "Tu saldo se ha visto modificado en " + (modeloQytetet.getJugadorActual().getSaldo() - saldo));
        }
        
        if (encarcelado != modeloQytetet.getJugadorActual().getEncarcelado()) {
            JOptionPane.showMessageDialog(this, "Has sido encarcelado");
        }
    }
    
    private void habilitarGestionInmobiliaria() {
        if (modeloQytetet.getJugadorActual().tengoPropiedades()) {
            this.jbGestionInmobiliaria.setEnabled(true);
        } else {
            this.jbGestionInmobiliaria.setEnabled(false);
        }
    }
    
    private void accionesGestionInmobiliaria(int eleccion) {
        boolean accionCompletada = false;
        
        Casilla casilla = elegirPropiedad();
        
        if (eleccion == 0) {
            accionCompletada = modeloQytetet.edificarCasa(casilla);
            
            if (!accionCompletada) {
                JOptionPane.showMessageDialog(this, "No se ha podido edificar la casa porque o no se dispone de suficiente dinero o porque se ha alcanzado el numero maximo de casas.");
            }
        } else if (eleccion == 1) {
            accionCompletada = modeloQytetet.edificarHotel(casilla);
            
            if (!accionCompletada) {
                JOptionPane.showMessageDialog(this, "No se ha podido edificar el hotel porque no se dispone de suficiente dinero, se ha alcanzado el numero maximo de hoteles o porque no se disponen de suficientes casas.");
            }
        } else if (eleccion == 2) {
            accionCompletada = modeloQytetet.venderPropiedad(casilla);
            
            if (!accionCompletada) {
                JOptionPane.showMessageDialog(this, "No se ha podido vender la propiedad.");
            }
        } else if (eleccion == 3) {
            accionCompletada = modeloQytetet.hipotecaPropiedad(casilla);
            
            if (!accionCompletada) {
                JOptionPane.showMessageDialog(this, "No se ha podido hipotecar la propiedad.");
            }
        } else if (eleccion == 4) {
            accionCompletada = modeloQytetet.cancelarHipoteca(casilla);
            
            if (!accionCompletada) {
                JOptionPane.showMessageDialog(this, "No se ha podido cancelar la hipoteca.");
            }
        }

        this.vistaQytetet2.Actualizar(modeloQytetet);
    }
    
    private Casilla elegirPropiedad() {
        ArrayList<TituloPropiedad> propiedades = modeloQytetet.getJugadorActual().getPropiedades();
        ArrayList<String> nombresPropiedades = new ArrayList();
        
        String[] propiedadesMenu = new String[propiedades.size()];
        
        for (TituloPropiedad propiedad: propiedades) {
            nombresPropiedades.add(propiedad.getNombre());
        }
        
        propiedadesMenu = nombresPropiedades.toArray(propiedadesMenu);
        
        int eleccion = JOptionPane.showOptionDialog(null, "Selecciona la propiedad que deseas:", "Elección Propiedad", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, null, propiedadesMenu, propiedadesMenu[0]);
        
        return propiedades.get(eleccion).getCasilla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbSalirCarcelDado = new javax.swing.JButton();
        jbSalirCarcelPagando = new javax.swing.JButton();
        jbJugar = new javax.swing.JButton();
        jbAplicarSorpresa = new javax.swing.JButton();
        jbComprar = new javax.swing.JButton();
        jbSiguienteJugador = new javax.swing.JButton();
        jbGestionInmobiliaria = new javax.swing.JButton();
        vistaQytetet2 = new GUIQytetet.VistaQytetet();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbSalirCarcelDado.setText("Salir Tirando Dado");
        jbSalirCarcelDado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirCarcelDadoActionPerformed(evt);
            }
        });

        jbSalirCarcelPagando.setText("Salir Pagando");
        jbSalirCarcelPagando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirCarcelPagandoActionPerformed(evt);
            }
        });

        jbJugar.setText("Jugar");
        jbJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbJugarActionPerformed(evt);
            }
        });

        jbAplicarSorpresa.setText("Aplicar sorpresa");
        jbAplicarSorpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAplicarSorpresaActionPerformed(evt);
            }
        });

        jbComprar.setText("Comprar Propiedad");
        jbComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbComprarActionPerformed(evt);
            }
        });

        jbSiguienteJugador.setText("Siguiente Jugador");
        jbSiguienteJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSiguienteJugadorActionPerformed(evt);
            }
        });

        jbGestionInmobiliaria.setText("Gestion Inmobiliaria");
        jbGestionInmobiliaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGestionInmobiliariaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vistaQytetet2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbSalirCarcelDado)
                            .addComponent(jbSalirCarcelPagando, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jbJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbAplicarSorpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbComprar))
                        .addGap(18, 18, 18)
                        .addComponent(jbSiguienteJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbGestionInmobiliaria, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(vistaQytetet2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jbAplicarSorpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbSiguienteJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbGestionInmobiliaria, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jbSalirCarcelDado, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbSalirCarcelPagando, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbJugar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirCarcelDadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirCarcelDadoActionPerformed
      boolean resultado = modeloQytetet.intentarSalirCarcel(0);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        if(resultado){
            JOptionPane.showMessageDialog(this, "Sales de la cárcel");
            this.jbJugar.setEnabled(true);
        }else {
            JOptionPane.showMessageDialog(this, "NO sales de la carcel");
            this.jbSiguienteJugador.setEnabled(true);
        }
        this.vistaQytetet2.Actualizar(modeloQytetet); 
    }//GEN-LAST:event_jbSalirCarcelDadoActionPerformed

    private void jbSalirCarcelPagandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirCarcelPagandoActionPerformed
        boolean resultado = modeloQytetet.intentarSalirCarcel(1);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        if(resultado){
            JOptionPane.showMessageDialog(this, "Sales de la cárcel");
            this.jbJugar.setEnabled(true);
        }else {
            JOptionPane.showMessageDialog(this, "NO sales de la carcel");
            this.jbSiguienteJugador.setEnabled(true);
        }
        this.vistaQytetet2.Actualizar(modeloQytetet);
    }//GEN-LAST:event_jbSalirCarcelPagandoActionPerformed

    private void jbAplicarSorpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAplicarSorpresaActionPerformed
        JOptionPane.showMessageDialog(this, modeloQytetet.getTextoCarta());
        int saldoActual = modeloQytetet.getJugadorActual().getSaldo();        
        boolean encarceladoActual = modeloQytetet.getJugadorActual().getEncarcelado();
        
        boolean res = modeloQytetet.aplicarSorpresa();        
        
        this.vistaQytetet2.Actualizar(modeloQytetet);
        
        comprobarCambios(saldoActual, encarceladoActual);
        
        if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == TipoCasilla.CALLE) {
            // Se habilita el boton de compra con el resultado contrario al obtenido en
            // aplicarSorpresa
            this.jbComprar.setEnabled(!res);
        }
        
        this.jbAplicarSorpresa.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(true);
        
        habilitarGestionInmobiliaria();
    }//GEN-LAST:event_jbAplicarSorpresaActionPerformed

    private void jbJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbJugarActionPerformed
        boolean activarSorpresa = false, activarSiguienteJugador = false, activarComprar = false, activarGestion = false;
        int saldoActual = modeloQytetet.getJugadorActual().getSaldo();
        boolean encarceladoActual = modeloQytetet.getJugadorActual().getEncarcelado();
        
        boolean tienePropietario = modeloQytetet.jugar();
        
        this.vistaQytetet2.Actualizar(modeloQytetet);        
        
        if (modeloQytetet.getJugadorActual().getSaldo() > 0) {
            if (!modeloQytetet.getJugadorActual().getEncarcelado()) {
                if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == qytetetjava.TipoCasilla.CALLE) {
                    if (!tienePropietario) {
                        activarComprar = true;                        
                    } else {
                        // Como no se puede comprar, se habilita la capacidad de
                        // pasar al siguiente jugador
                        String propietario = ((qytetetjava.Calle)modeloQytetet.getJugadorActual().getCasillaActual()).getTitulo().getPropietario().getNombre();
                        
                        if (propietario != modeloQytetet.getJugadorActual().getNombre()) {
                            JOptionPane.showMessageDialog(this, "Has caido en la casilla de " + propietario);
                        }
                        
                        activarSiguienteJugador = true;
                        activarGestion = true;
                    }
                } else if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == qytetetjava.TipoCasilla.SORPRESA) {
                    // Si se trata de una casilla de sorpresa, se habilita
                    // ese boton
                    JOptionPane.showMessageDialog(this, "Has caido en una casilla de tipo Sorpresa");
                    activarSorpresa = true;
                } else if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == qytetetjava.TipoCasilla.IMPUESTO){
                    JOptionPane.showMessageDialog(this, "Has caido en una casilla de tipo Impuesto");
                    activarSiguienteJugador = true;   
                    activarGestion = true;
                } else {
                    activarSiguienteJugador = true;
                    activarSorpresa = false;
                    activarGestion = true;
                }
                
                if (modeloQytetet.getJugadorActual().getEncarcelado()) {
                    JOptionPane.showMessageDialog(this, "El Juez te ha mandado a la cárcel");
                    activarSiguienteJugador = true;
                }                
            }
        } else {
            finJuego();
        }
        
        comprobarCambios(saldoActual, encarceladoActual);
                
        this.jbJugar.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(activarSiguienteJugador);
        this.jbAplicarSorpresa.setEnabled(activarSorpresa);
        this.jbComprar.setEnabled(activarComprar);
                
        if (activarGestion) {
            habilitarGestionInmobiliaria();
        }
    }//GEN-LAST:event_jbJugarActionPerformed

    private void jbComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbComprarActionPerformed
        int saldo = modeloQytetet.getJugadorActual().getSaldo();
        qytetetjava.Calle calle = (qytetetjava.Calle) modeloQytetet.getJugadorActual().getCasillaActual();
        int opcion = JOptionPane.showConfirmDialog(null,  "¿Deseas comprar esta propiedad?\nSaldo Disponible: " + saldo, "Menú Comprar", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            boolean comprado = modeloQytetet.comprarTituloPropiedad(calle);
            
            if (comprado) {
                this.vistaQytetet2.Actualizar(modeloQytetet);
                JOptionPane.showMessageDialog(this, "Gracias por la compra. Se ha añadido a tu lista de propiedades.");                
            }
        }
        
        this.jbComprar.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(true);
        
        habilitarGestionInmobiliaria();
    }//GEN-LAST:event_jbComprarActionPerformed

    private void jbSiguienteJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteJugadorActionPerformed
        modeloQytetet.siguienteJugador();
        actualizar(modeloQytetet);
        
        if (modeloQytetet.getJugadorActual().getSaldo() <= 0) {
            finJuego();
        }
    }//GEN-LAST:event_jbSiguienteJugadorActionPerformed

    private void jbGestionInmobiliariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGestionInmobiliariaActionPerformed
        // TODO add your handling code here:
        Object[] opciones = {"Edificar Casa", "Edificar Hotel", "Vender Propiedad", "Hipotecar Propiedad", "Cancelar Hipoteca"};
        
        int eleccion = JOptionPane.showOptionDialog(null, "Selecciona la acción que deseas hacer:", "Menú Gestión Inmobiliaria", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        
        accionesGestionInmobiliaria(eleccion);
        
        habilitarGestionInmobiliaria();
    }//GEN-LAST:event_jbGestionInmobiliariaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        ControladorQytetet controladorQytetet= new ControladorQytetet();
        
        
        CapturaNombreJugadores capturaNombres = new CapturaNombreJugadores(controladorQytetet, true);
        ArrayList<String> nombres= capturaNombres.obtenerNombres();
        
        Dado.createInstance(controladorQytetet);
        Qytetet juego = Qytetet.getInstance();
        juego.inicializarJuego(nombres);
        
       
        controladorQytetet.actualizar(juego);
        

        controladorQytetet.setVisible(true); //Esta debe ser la última
                                             //línea de código del main
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAplicarSorpresa;
    private javax.swing.JButton jbComprar;
    private javax.swing.JButton jbGestionInmobiliaria;
    private javax.swing.JButton jbJugar;
    private javax.swing.JButton jbSalirCarcelDado;
    private javax.swing.JButton jbSalirCarcelPagando;
    private javax.swing.JButton jbSiguienteJugador;
    private GUIQytetet.VistaQytetet vistaQytetet2;
    // End of variables declaration//GEN-END:variables
}
