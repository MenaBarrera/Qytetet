# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module ModeloQytetet
class Calle < Casilla
  attr_accessor :numHoteles, :numCasas, :titulo
  
  def initialize(numCas, cost, tituloP)
    super(numCas,TipoCasilla::CALLE ,cost)
    
    @titulo = tituloP
    @numHoteles = 0
    @numCasas = 0
    
    if @titulo != nil
      @titulo.casilla = self
    end
  end
  
    
  def calcular_valor_hipoteca
      hipoteca_base = @titulo.hipotecaBase
      
      cantidad_recibida = hipoteca_base + (@numCasas * 0.5 * hipoteca_base + @numHoteles * hipoteca_base)
      
      return cantidad_recibida
    end
    
    def cancelar_hipoteca
      @titulo.hipotecada = false
      cantidad_pagar = calcular_valor_hipoteca * 1.1
      
      return cantidad_pagar
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
      nuevo_num = @numHoteles + 1
      @numHoteles = nuevo_num
      @numCasas = 0
      coste_edificar_hotel = @titulo.precioEdificar
      
      return coste_edificar_hotel
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
    
    def se_puede_edificar_casa(factor)
      return @numCasas < 4 * factor
    end
    
    def se_puede_edificar_hotel(factor)
      return @numHoteles < (4 * factor) && @numCasas == 4 * factor
    end
    
     def tengo_propietario
      tengo_propietario = @titulo.tengo_propietario
      
      return tengo_propietario
    end
    
    def vender_titulo
      precio_compra = @coste + (@numCasas + @numHoteles) * @titulo.precioEdificar
      precio_venta = precio_compra + @titulo.factorRevalorizacion * precio_compra
      
      @titulo.propietario = nil
      @numHoteles = 0
      @numCasas = 0
      
      return precio_venta
    end
    
    def asignar_propietario(jugador)
      @titulo.propietario = jugador
      
      return @titulo
    end
    
    def asignar_titulo_propiedad
      
    end
    
    # llamar al super 
    def to_s
      super + " texto-- "
    end
end
end
