#encoding :UTF-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module ModeloQytetet

  class Jugador
    attr_accessor :casilla_actual, :encarcelado
    attr_writer :carta_libertad
    attr_reader :nombre, :saldo

    def initialize(nom)
      @encarcelado = false
      @nombre = nom
      @saldo = 7500
      @carta_libertad = nil
      @propiedades = Array.new
      @casilla_actual = Casilla.iniciar_casilla(0, TipoCasilla::SALIDA)
    end

    def tengo_propiedades()
      ret = false
      
      if(@propiedades.size() > 0)
        ret = true
      end
      return ret
    end

    def actualizar_posicion(casilla)
      tengo_propietario = false
      if(casilla.numeroCasilla < @casillaActual.numeroCasilla)
        modificar_saldo(Qytetet.SALDO_SALIDA);
      end
      
      @casilla_actual = casilla;
      
      if(casilla.soy_edificable)
        tengo_propietario = casilla.tengo_propietario
        
        if(tengo_propietario)
          @encarcelado = casilla.propietario_encarcelado
          
          if(!encarcelado)
            coste_alquiler = casilla.cobrar_alquiler()
            modificar_saldo(-coste_alquiler)
            
          end
        end
        
        else if casilla.tipo == TipoCasilla::IMPUESTO
        coste = casilla.coste
        modificar_saldo(-coste)
        end
      end
      return tengo_propietario
    end
    

    
    def comprar_titulo()
      puedo_comprar = false
      
      if(@casilla_actual.soy_edificable)
        tengo_propietario = @casilla_actual.tengo_propietario
        
        if(!tengo_propietario)
          coste_compra = @casilla_actual.coste
          
          if(coste_compra <= @saldo)
            titulo = @casilla_actual.asignar_propietario(self) #NO ESTOY SEGURO DE QUE SEA SELF
            @casilla_actual.titulo.propietario = self
            @propiedades << titulo                              # no hay que hacer new de titulo?
            modificar_saldo(-coste_compra)
            puedo_comprar = true
          end
        end
      end
      return puedo_comprar
    end
    
    def devolver_carta_libertad()
       @cartaAux = Sorpresa.new(cartaLibertad.getTexto(), cartaLibertad.getValor(), cartaLibertad.getTipo())
       @cartaLibertad = nil
       
      return @cartaAux   
    end
    
    def ir_a_carcel(casilla)
      
    end
    
    def modificar_saldo(cantidad)
      @saldo += cantidad
      
    end
    
    def obtener_capital()
      
    end
    
    def obtener_propiedades_hipotecadas(hipotecada)
      hipotecada_parametro = Array.new
      
      for i in @propiedades
        if(i.hipotecada == hipotecada)
          hipotecada_parametro << i
        end
      end
      return hipotecada_parametro
    end
    
    def pagar_cobrar_por_casa_y_hotel(cantidad)
      
    end
    
    def pagar_libertad(cantidad)
      
    end
    
    def puedo_edificar_casa(casilla)
      es_mia = es_de_mi_propiedad(casilla)
      
      if(es_mia)
        coste_edificar_casa = casilla.precioEdificar
        tengo_saldo = tengo_saldo(coste_edificar_casa)
      end
      
    end
    
    def puedo_edificar_hotel(casilla)
      
    end
    
    def puedo_hipotecar(casilla)
      
    end
    
    def puedo_pagar_hipoteca(casilla)
      
    end
    
    def puedo_vender_propiedad(casilla)
      return es_de_mi_propiedad && !casilla.hipotecada
    end
    
    def tengo_carta_libertad()
      return @carta_libertad != nil
    end
    
    def vender_propiedad(casilla)
      es_mia = es_de_mi_propiedad
      hipotecada = casilla.esta_hipotecada
      precio_venta = casilla.vender_propiedad(casilla)
      modificar_saldo(precio_venta)
      eliminar_de_mis_propiedades(casilla)  
    end
    
    def cuantas_casas_y_hoteles_tengo()
      
    end
    
    def eliminar_de_mis_propiedades(casilla)
      for i in @propiedades
        if(i.casilla == casilla)
          @propiedades.delete(i)
        end
      end
    end
    
    def es_de_mi_propiedad(casilla)
      poseido = false;
      for i in @propiedades
        if(i.casilla == casilla)
          poseido = true
        end
      end
      return poseido
    end

    def tengo_saldo(cantidad)
      ret = false
      
      if(@saldo >= cantidad)
        ret = true
      end
      return ret
    end
    
    def to_s
      "Encarcelado #{@encarcelado} \n Nombre: #{@nombre} \n  Saldo: #{@saldo}
        \n Carta Liberdad: #{@cartaLibertad}  \n  
       Casilla actual: #{@casilla_actual}\n Propiedades #{@propiedades} "
    end
    
    private :cuantas_casas_y_hoteles_tengo, :eliminar_de_mis_propiedades, :es_de_mi_propiedad, :tengo_saldo
    
  end #clase
end #module

