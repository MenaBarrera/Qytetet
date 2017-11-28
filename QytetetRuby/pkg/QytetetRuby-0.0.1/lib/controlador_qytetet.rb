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
          @vista.esperar
        end
        
        if (libre)
          @vista.mostrar("#{@jugador.nombre} tira el dado")
          @vista.esperar
          no_tiene_propietario = @juego.jugar
          actualizar_casilla
          
          @vista.mostrar("#{@jugador.nombre} se desplaza hasta la casilla numero #{@casilla.numeroCasilla}")
          @vista.mostrar("Información de la casilla:\n#{@casilla.to_s}")
          @vista.esperar
          
          if (!bancarrota)
            if (!@jugador.encarcelado)
              if (@casilla.tipo == TipoCasilla::CALLE)
                if (!no_tiene_propietario)
                  @vista.mostrar("La casilla actual se puede adquirir")
                  @vista.esperar
                  quiero_comprar = @vista.elegir_quiero_comprar
                  saldo = @jugador.saldo
                  @vista.mostrar("El jugador " + (quiero_comprar ? "desea" : "no desea") + " adquirir la propiedad")
                  @vista.mostrar("El saldo actual de #{@jugador.nombre} es #{saldo}")
                  @vista.esperar

                  if (quiero_comprar)
                    comprado = @juego.comprar_titulo_propiedad(@casilla)
                    
                    if (comprado)
                      @vista.mostrar("Gracias por tu compra.")
                      @vista.mostrar("El saldo de #{@jugador.nombre} se ha quedado en #{@jugador.saldo}")
                      @vista.mostrar("El importe ha sido de #{saldo - @jugador.saldo}")
                      @vista.esperar
                      
                    else
                      @vista.mostrar("Que pena, no has podido comprar la propiedad!")
                      @vista.esperar
                    end
                  end
                end
              
              elsif (@casilla.tipo == TipoCasilla::SORPRESA)
                @vista.mostrar("Has caido en una casilla de tipo sorpresa!")
                @vista.mostrar("La carta que se va activar es la siguiente")
                @vista.mostrar(@juego.cartaActual)
                no_tiene_propietario = @juego.aplicar_sorpresa
              
                #Actualizar la posicion actual de ser necesario
                if (@casilla != @jugador.casilla_actual)
                  actualizar_casilla
                  @vista.mostrar("#{@jugador.nombre} se desplaza hasta la casilla numero #{@casilla.numeroCasilla}")
                  @vista.mostrar("Información de la casilla:\n#{@casilla.to_s}")
                  @vista.esperar
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
                          
                          if (comprado)
                            @vista.mostrar("Gracias por tu compra.")
                            @vista.mostrar("El saldo de #{@jugador.nombre} se ha quedado en #{@jugador.saldo}")
                            @vista.mostrar("El importe ha sido de #{saldo - @jugador.saldo}")
                            @vista.esperar                      
                          else
                            @vista.mostrar("Que pena, no has podido comprar la propiedad!")
                            @vista.esperar
                          end
                        end
                      end
                    end
                  end
                end
              end
              

              if (!@jugador.encarcelado && !bancarrota && @jugador.tengo_propiedades)
                opcion = @vista.menu_gestion_inmobiliaria
                
                while (opcion != 0)
                  casilla = elegir_propiedad(@jugador.propiedades)
                  saldo_actual = @jugador.saldo
                  
                  if (opcion == 1)
                    @vista.mostrar("El jugador #{@jugador.nombre} ha decidido edificar una casa. Actualmente posee #{casilla.casilla.numCasas} casas")
                    @vista.mostrar("Saldo actual: #{saldo_actual}")
                    @vista.esperar
                    edificado = @juego.edificar_casa(casilla.casilla)
                    
                    if (edificado)
                      @vista.mostrar("Gracias por la compra. El precio de la compra ha sido #{saldo_actual - @jugador.saldo}")
                      @vista.mostrar("El saldo actual es #{@jugador.saldo} y el numero de casas ha pasado a ser #{casilla.casilla.numCasas}")
                    else
                      @vista.mostrar("Desgraciadamente no se ha podido edificar una nueva casa. No dispones del suficiente dinero o ya se ha llegado al máximo de casas posible.")
                    end
                    @vista.esperar
                    
                  elsif (opcion == 2)
                    @juego.edificar_hotel(casilla.casilla)
                    
                  elsif (opcion == 3)
                    @juego.vender_propiedad(casilla.casilla)
                    
                  elsif (opcion == 4)
                    @juego.hipotecar_propiedad(casilla.casilla)
                    
                  elsif (opcion == 5)
                    @juego.cancelar_hipoteca(casilla.casilla)
                  end
                  
                  opcion = @vista.menu_gestion_inmobiliaria
                end
              end
            end            
          end
        end
        
        if (!bancarrota)
          @vista.mostrar("Cambio de turno. Le toca al siguiente jugador.")
          @vista.esperar
          cambio_turno
        end
        
        if (bancarrota)
          fin_juego = true
        end
      end
      
      puts "ey me he salido"
      
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
    
    def elegir_propiedad(propiedades) # lista de propiedades a elegir
      @vista.mostrar("\tCasilla\tTitulo");
       
      listaPropiedades= Array.new
      for prop in propiedades  # crea una lista de strings con numeros y nombres de propiedades
        propString= prop.casilla.numeroCasilla.to_s + ' ' +prop.nombre; 
        listaPropiedades<<propString
      end
      seleccion=@vista.menu_elegir_propiedad(listaPropiedades)  # elige de esa lista del menu
      propiedades.at(seleccion)
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
