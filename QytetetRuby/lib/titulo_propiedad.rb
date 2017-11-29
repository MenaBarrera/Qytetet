#encoding :UTF-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class TituloPropiedad
    attr_reader :nombre, :alquilerBase, :factorRevalorizacion, :hipotecaBase, :precioEdificar
    attr_accessor :hipotecada, :casilla, :propietario 
    def initialize(nom, alqBase, factReval, hipotBase, precEdificar)
      @nombre = nom
      @hipotecada = false
      @alquilerBase = alqBase
      @factorRevalorizacion = factReval
      @hipotecaBase = hipotBase
      @precioEdificar = precEdificar
      @propietario = nil
      @casilla = nil
    end
    
    def cobrar_alquiler(coste)
      @propietario.modificar_saldo(-coste)
    end
    
    def propietario()
      
    end
    
    def propietario_encarcelado
      return @propietario.encarcelado
    end
    
    def tengo_propietario()
      ret = false
      
      if(@propietario !=nil)
        ret = true
      end
      return ret
    end
    
    
    def to_s
      "Nombre: #{@nombre}\n Hipotecada: #{@hipotecada}\n Alquiler Base: #{@alquilerBase}\n Factor Revalorización: #{@factorRevalorizacion}\n Hipoteca Base: #{@hipotecaBase}\n Precio Edificar: #{@precioEdificar}\n Propietario: " + (@propietario == nil ? "" : @propietario.nombre)    
    end    
  end
end
