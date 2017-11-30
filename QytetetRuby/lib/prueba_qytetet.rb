#encoding :UTF-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
=begin
require_relative "tipo_sorpresa"        # no hace falta poner .rb
require_relative "sorpresa"
require_relative "casilla"
require_relative "tablero"
require_relative "titulo_propiedad"
require_relative "tipo_casilla"
require_relative "qytetet"
require_relative "metodo_salir_carcel"
require_relative "jugador"
require_relative "dado"

module ModeloQytetet
  
  class PruebaQytetet
    @@mazo = Array.new        # @@mazo = [] es lo mismo
    @@tablero = Tablero.new
    @@qytetet = Qytetet.instance
    
    def self.inicializar_sorpresas
      @@mazo << Sorpresa.new("a", 2, TipoSorpresa::IRACASILLA)
      @@mazo << Sorpresa.new("b", 0, TipoSorpresa::IRACASILLA)
      @@mazo << Sorpresa.new("c", @@tablero.carcel.numeroCasilla, TipoSorpresa::SALIRCARCEL)
    end
    
    def self.sorpresas_mayores_cero
      array_mayores = Array.new
      for i in @@mazo
        if i.valor > 0
          array_mayores << i
        end
      end
      return array_mayores
    end
    
    def self.sorpresas_ir_casilla
      array_ir_casilla = Array.new
      for i in @@mazo
        if i.tipo == TipoSorpresa::IRACASILLA
          array_ir_casilla << i
        end
      end
      return array_ir_casilla
    end
    
    def self.sorpresas_tipo_casilla(tipo_sorpresa)
      array_tipo_casilla = Array.new
      for i in @@mazo
        if i.tipo == tipo_sorpresa
          array_tipo_casilla << i
        end
      end
      return array_tipo_casilla
    end
    
    def self.main
      inicializar_sorpresas
      mayores = sorpresas_mayores_cero
      ir_casilla = sorpresas_ir_casilla
      tipos = sorpresas_tipo_casilla(TipoSorpresa::SALIRCARCEL)
      puts "Mazo de PruebaQytetet"
      puts @@mazo
      puts mayores          #to_s o no to_s?
      puts ir_casilla
      puts tipos
      puts @@tablero
      puts @@qytetet.to_s
    end
  end
  
  # esto es una prueba de lanzamiento de excepciones en ruby
  begin
      PruebaQytetet.main
        
      rescue ArgumentError => a
        puts "ERROR. 2 <= NUM_JUGADORES <= 4"
        
    end
  
end

=end