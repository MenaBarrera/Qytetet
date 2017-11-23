#encoding :UTF-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class Casilla
    attr_reader :numeroCasilla, :coste, :tipo
    attr_accessor :numHoteles, :numCasas, :titulo
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
    
    def self.iniciar_casilla(numCas, tipoCas)
      new(numCas, tipoCas, 0, nil)
    end
    
    def self.iniciar_calle(numCas, cost, prop)
      new(numCas, TipoCasilla::CALLE, cost, prop)
    end
    
    def asignar_propiedario(jugador)
      
    end
    
    def calcular_valor_hipoteca
      hipoteca_base = @titulo.hipotecaBase
      
      cantidad_recibida = hipoteca_base + (@numCasas * 0.5 * hipoteca_base + @numHoteles * hipoteca_base)
      
      return cantidad_recibida
    end
    
    def cancelar_hipoteca
      
    end
    
    def cobrar_alquiler
      coste_alquiler_base = @titulo.alquilerBase
      coste_alquiler = (coste_alquiler_base + (@numCasas * 0.5 + @numHoteles * 2))
      @titulo.cobrar_alquiler(coste_alquiler)
      
      return coste_alquiler
    end
    
    def edificar_casa
      nuevo_num = @numCasas + 1
      @numCasas = nuevo_num
      coste_edificar_casas = @titulo.precioEdificar
      
      return coste_edificar_casas
    end
    
    def edificar_hotel
      
    end
    
    def esta_hipotecada
      return @titulo.hipotecada
    end
    
    # hacerlo así?
    def get_coste_hipoteca
      
    end
    
    # hacerlo así?
    def get_precio_edificar
      coste_edificar_casa = @titulo.precioEdificar
      
      return coste_edificar_casa
    end
    
    def hipotecar
      @titulo.hipotecada = true
      cantidad_recibida = calcular_valor_hipoteca
      
      return cantidad_recibida
    end
    
    def precio_total_comprar
      
    end
    
    def propietario_encarcelado
      encarcelado = @titulo.propietario_encarcelado
      
      return encarcelado
    end
    
    def se_puede_edificar_casa
      
    end
    
    def se_puede_edificar_hotel
      
    end
    
    def soy_edificable
      if (@tipo == TipoCasilla::CALLE)
        return true
      else
        return false
      end
    end
    
    def tengo_propietario
      tengo_propietario = @titulo.tengo_propietario
      
      return tengo_propietario
    end
    
    def vender_titulo
      precio_compra = @coste + (@numCasas + @numHoteles) * @titulo.precioEdificar
      precio_venta = 
    end
    
    def asignar_titulo_propiedad
      
    end
    
    def to_s
      if (@tipo == TipoCasilla::CALLE)
        "Número casilla: #{@numeroCasilla} \n Coste: #{@coste} \n Número Hoteles: #{@numHoteles} \n Número Casas: #{@numCasas} \n Tipo: #{@tipo} \n Título propiedad: #{@tituloPropiedad}"
      else
        "Número casilla: #{@numeroCasilla}  \n Tipo: #{@tipo}"

      end
    end
    
    private :titulo= , :asignar_titulo_propiedad
  end
end
