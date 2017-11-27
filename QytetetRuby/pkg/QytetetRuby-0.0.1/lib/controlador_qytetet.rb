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
        libre = @jugador.encarcelado
        
        if (!libre)
          @vista.mostrar("#{@jugador.nombre}, estas encarcelado/a")
          metodo = @vista.menu_salir_carcel
          libre = @juego.intentar_salir_carcel(metodo)
          @vista.mostrar((libre ? "Has" : "No has") + " logrado salir de la carcel")
        end
        
        if (libre)
          no_tiene_propietario = @juego.jugar
          bancarrota = jugador_en_bancarrota
          
          if (!bancarrota && !@jugador.encarcelado)
            
          end
        end
      end
    end
    
    def cambio_turno
      @juego.siguiente_jugador
      @jugador = @juego.jugadorActual
      @casilla = @jugador.casilla_actual
    end
    
    def jugador_en_bancarrota
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
    
    private :jugador_en_bancarrota, :cambio_turno
  end
  
    ControladorQytetet.main

end
