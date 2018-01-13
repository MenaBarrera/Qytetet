#encoding :UTF-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class Casilla
    attr_reader :coste, :tipo
    attr_accessor :numHoteles, :numCasas, :titulo, :numeroCasilla
    
=begin

    def initialize(numCas, tipoCas, cost, propiedad)
      @numeroCasilla = numCas
      @coste = cost
      @numHoteles = 0
      @numCasas = 0
      @tipo = tipoCas
      @titulo = propiedad
      
      # Se puede hacer esto?
      if @titulo != nil
        @titulo.casilla = self
      end
    end
    
=end
    
    def initialize(numCas,tipoCas,cost)
      @numeroCasilla = numCas
      @coste = cost
      @tipo = tipoCas
      
      
    end
    
    def self.iniciar_casilla(numCas, tipoCas)
      if (tipoCas == TipoCasilla::IMPUESTO)
        new(numCas, tipoCas, 200)
      else
        new(numCas, tipoCas, 0)
      end
    end
    
    
    
    def soy_edificable
      if (@tipo == TipoCasilla::CALLE)
        return true
      else
        return false
      end
    end
    
   
    
    def to_s
      if (@tipo == TipoCasilla::CALLE)
        "Número casilla: #{@numeroCasilla}\n Coste: #{@coste}\n Número Hoteles: #{@numHoteles}\n Número Casas: #{@numCasas}\n Tipo: #{@tipo}\n Título propiedad: #{@titulo}"
      else
        "Número casilla: #{@numeroCasilla}\n Tipo: #{@tipo}" + (@tipo == TipoCasilla::IMPUESTO ? "\n Coste: #{@coste}" : "")

      end
    end
    
    
  end
end
