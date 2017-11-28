#encoding :utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "tipo_sorpresa"
require_relative "sorpresa"
require_relative "casilla"
require_relative "tablero"
require_relative "titulo_propiedad"
require_relative "tipo_casilla"
require_relative "qytetet"
require_relative "metodo_salir_carcel"
require_relative "jugador"
require_relative "dado"
require_relative "vista_textual_qytetet"

module InterfazTextualQytetet
  class ControladorQytetet
    include ModeloQytetet
    
    def initialize
      
    end
    
    def inicializacion_juego
      @vista = VistaTextualQytetet.new  
      @juego = Qytetet.instance
      
      nombres = Array.new
      nombres = @vista.obtener_nombre_jugadores
      
      @juego.inicializar_juego(nombres)
      @jugador = @juego.jugadorActual
      @casilla = @jugador.casilla_actual
      
      @vista.mostrar("---------------- JUEGO -------------")
      @vista.mostrar(@juego.to_s)
      @vista.esperar
    end
    
    def desarrollo_juego
      fin_juego = false
      
      while (!fin_juego)
        @vista.mostrar("Turno de " + @jugador.nombre)
        @vista.mostrar("Informacion de la casilla actual:\n#{@casilla}")
        @vista.esperar
        
        # Comprobamos el estado del jugador
        # antes de continuar
        libre = !@jugador.encarcelado
        
        if (!libre)
          @vista.mostrar("#{@jugador.nombre}, estas encarcelado/a")
          metodo = @vista.menu_salir_carcel
          libre = @juego.intentar_salir_carcel(metodo)
          @vista.mostrar((libre ? "Has" : "No has") + " logrado salir de la carcel")
        end
        
        if (libre)
          @vista.mostrar("#{@jugador.nombre} tira el dado")
          no_tiene_propietario = @juego.jugar
          actualizar_casilla
          
          @vista.mostrar("#{@jugador.nombre} se desplaza hasta la casilla numero #{@casilla.numeroCasilla}")
          @vista.mostrar("Información de la casilla:\n#{@casilla.to_s}")
          
          if (!bancarrota)
            if (!@jugador.encarcelado)
              if (@casilla.tipo == TipoCasilla::CALLE)
                if (!no_tiene_propietario)
                  @vista.mostrar("La casilla actul se puede adquirir")
                  quiero_comprar = @vista.elegir_quiero_comprar
                  saldo = @jugador.saldo
                  @vista.mostrar("El saldo actual de #{@jugador.nombre} es #{saldo}")

                  if (quiero_comprar)
                    comprado = @juego.comprar_titulo_propiedad(@casilla)
                    @vista.mostrar("Gracias por tu compra.")
                    @vista.mostrar("El saldo de #{@jugador.nombre} se ha quedado en #{@jugador.saldo}")
                    @vista.mostrar("El importe ha sido de #{saldo - @jugador.saldo}")
                  end
                end
              
              elsif (@casilla.tipo == TipoCasilla::SORPRESA)
                no_tiene_propietario = @juego.aplicar_sorpresa
              
                #Actualizar la posicion actual de ser necesario
                if (@casilla != @jugador.casilla_actual)
                  actualizar_casilla
                  @vista.mostrar("#{@jugador.nombre} se desplaza hasta la casilla numero #{@casilla.numeroCasilla}")
                  @vista.mostrar("Información de la casilla:\n#{@casilla.to_s}")
                end
              

                if (!bancarrota)
                  if (!@jugador.encarcelado)
                    if (@casilla.tipo == TipoCasilla::CALLE)
                      if (!no_tiene_propietario)
                        @vista.mostrar("La casilla actul se puede adquirir")
                        quiero_comprar = @vista.elegir_quiero_comprar
                        saldo = @jugador.saldo
                        @vista.mostrar("El saldo actual de #{@jugador.nombre} es #{saldo}")
                        
                        if (quiero_comprar)
                          comprado = @juego.comprar_titulo_propiedad(@casilla)
                          @vista.mostrar("Gracias por tu compra.")
                          @vista.mostrar("El saldo de #{@jugador.nombre} se ha quedado en #{@jugador.saldo}")
                          @vista.mostrar("El importe ha sido de #{saldo - @jugador.saldo}")
                        end
                      end
                    end
                  end
                end
              end
              

              if (!@jugador.encarcelado && !bancarrota && @jugador.tengo_propiedades)
                
              end
            end            
          end
        end
        
        if (!bancarrota)
          cambio_turno
        end
        
        if (bancarrota)
          fin_juego = true
        end
      end
      
      @vista.mostrar("Fin de la partida!")
      @vista.mostrar("A continuación puedes consultar los marcadores")
      ranking = @juego.obtener_ranking
      @vista.mostrar(ranking)
      @vista.esperar
      @vista.mostrar("Muchas gracias por jugar. Esperamos que os hayáis divertido y volváis a jugar pronto! :)")
    end
    
    def cambio_turno
      @juego.siguiente_jugador
      @jugador = @juego.jugadorActual
      actualizar_casilla
    end
    
    def bancarrota
      return @jugador.saldo <= 0
    end
    
    def actualizar_casilla
      @casilla = @jugador.casilla_actual
    end
    
    def self.main
      controlador = ControladorQytetet.new
      controlador.inicializacion_juego
      controlador.desarrollo_juego
    end
    
    private :bancarrota, :cambio_turno, :actualizar_casilla
  end
  
    ControladorQytetet.main

end
