#encoding :UTF-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require "singleton"


module ModeloQytetet
  class Qytetet
    include Singleton
    attr_reader :cartaActual, :jugadorActual, :jugadores
    @@MAX_JUGADORES = 4
    @@MAX_CARTAS = 10
    @@MAX_CASILLAS = 20
    @@PRECIO_LIBERTAD = 200
    @@SALDO_SALIDA = 1000
    
    def initialize
      @dado = Dado.instance
      inicializar_tablero
      inicializar_cartas_sorpresa
      inicializar_jugadores(["pepe", "pablo", "mario"])
      salida_jugadores
      @cartaActual = nil
    end
    
    def aplicar_sorpresa
      
    end
    
    def cancelar_hipoteca(casilla)
      
    end
    
    def comprar_titulo_propiedad(casilla)
      puedo_comprar = @jugadorActual.comprar_titulo
      return puedo_comprar
    end
    
    def edificar_casa(casilla)
      puedo_edificar = false
      
      if (casilla.soy_edificable)
        se_puede_edificar = casilla.se_puede_edificar_casa
        
        if (se_puede_edificar)
          puedo_edificar = @jugadorActual.puedo_edificar_casa(casilla)
          
          if (puedo_edificar)
            coste_edificar_casa = casilla.edificar_casa
            @jugadorActual.modificar_saldo(-coste_edificar_casa)
          end
        end
      end
      
      return puedo_edificar
    end
    
    def edificar_hotel(casilla)
      
    end
    
    def hipoteca_propiedad(casilla)
      puedo_hipotecar = false
      if (casilla.soy_edificable)
        se_puede_hipotecar = !casilla.esta_hipotecada
        
        if (se_puede_hipotecar)
          puedo_hipotecar = @jugadorActual.puedo_hipotecar(casilla)
          
          if (puedo_hipotecar)
            cantidad_recibida = casilla.hipotecar
            @jugadorActual.modificar_saldo(cantidad_recibida)
          end
        end
      end
      
      return puedo_hipotecar
    end
    
    def inicializar_juego(nombres)
      inicializar_jugadores(nombres)
      inicializar_cartas_sorpresa
      inicializar_tablero
      salida_jugadores
    end
    
    def intentar_salir_carcel(metodo)
      
    end
    
    def jugar
      
    end
    
    def obtener_ranking
      ranking = {}
      
      jugadores = @jugadores.sort { |j1,j2| j1.obtener_capital < j2.obtener_capital }
      
      jugadores.each do |jugador|
        capital = jugador.obtener_capital
        ranking[jugador.nombre] = capital
      end
      
      return ranking      
    end
    
    def propiedades_hipotecadas_jugador(hipotecadas)
      casillas = Array.new
      propiedades = @jugadorActual.obtener_propiedades_hipotecadas(hipotecadas)
      
      for item in @tablero.casillas
        for aux in propiedades
          if (aux == item.titulo)
            casillas << item
          end
        end
      end
      
      return casillas
    end
    
    def siguiente_jugador
      pos = 0
      long = @jugadores.length
      
      for i in 0..long
        if (@jugadorActual == @jugadores.at(i))
          pos = i
        end
      end
      
      @jugadorActual = @jugadores.at((pos + 1) % long)
      
      return @jugadorActual
    end
    
    def vender_propiedad(casilla)
      
    end
    
    def encarcelar_jugador
      
    end
    
    def inicializar_cartas_sorpresa
      @mazo = Array.new
      
      @mazo << Sorpresa.new("Te han pillado evadiendo impuestos. Te vas derechito a la cárcel.", @tablero.carcel.numeroCasilla, TipoSorpresa::IRACASILLA)
      @mazo << Sorpresa.new("Un diplomático anónimo ha pagado tu salida de la cárcel. Eres libre de irte." , 0, TipoSorpresa::SALIRCARCEL)
      @mazo << Sorpresa.new("Te has dejado a tu perro en una tienda de la Avenida de Móstoles.", 7, TipoSorpresa::IRACASILLA)
      @mazo << Sorpresa.new("Decides ir a visitar los jardines de la Calle de los Nardos.", 19, TipoSorpresa::IRACASILLA)
      @mazo << Sorpresa.new("Has recibido una jugosa transacción de una gran empresa.", 450, TipoSorpresa::PAGARCOBRAR)
      @mazo << Sorpresa.new("No has hecho bien la declaración de la renta y tienes que pagar los impuestos.", -300, TipoSorpresa::PAGARCOBRAR)
      @mazo << Sorpresa.new("Es tu cumpleaños. Entre todos hte han regalado una suma de dinero para que la gastes en lo que quieras.", 125, TipoSorpresa::PORJUGADOR)
      @mazo << Sorpresa.new("Has roto la estatua que te prestaron tus amigos para tu fiesta. Debes pagarles a cada uno lo que le pertoque.", -100, TipoSorpresa::PORJUGADOR)
      @mazo << Sorpresa.new("Tienes que devolver el préstamo que pediste para poder cada una de tus propiedaes edificar." , -20, TipoSorpresa::PORCASAHOTEL)
      @mazo << Sorpresa.new("Tus propiedades han sido muy bien valoradas y están llegando muchos inquilinos nuevos. Obtienes ganancias por cada propiedad.", 15, TipoSorpresa::PORCASAHOTEL)
    end
    
    def inicializar_jugadores(nombres)
      if (nombres.length < 2 || nombres.length > @@MAX_JUGADORES)
        raise ArgumentError, "Número incorrecto de jugadores."
      end
      
      @jugadores = Array.new
      
      for jugador in nombres
        @jugadores << Jugador.new(jugador)
      end
    end
    
    def inicializar_tablero
      @tablero = Tablero.new
    end
    
    def salida_jugadores
      for jugador in @jugadores
        jugador.casilla_actual = @tablero.obtener_casilla_numero(0)
      end
      
      @jugadorActual = @jugadores.at(rand(@jugadores.length))
    end
    
    def to_s
      "QYTETET
      \n->cartaActual #{@cartaActual} \n\n->mazo #{@mazo.to_s} \n\n->jugadores #{@jugadores}
      \n\n->jugadorActual #{@jugadorActual.to_s} \n\n->tablero #{@tablero.to_s} \n\n->dado #{@dado.to_s}"
    end
    
    private :inicializar_cartas_sorpresa, :inicializar_jugadores, :inicializar_tablero
  end
end
