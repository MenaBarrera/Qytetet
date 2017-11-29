#encoding :UTF-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module ModeloQytetet

  class Jugador
    attr_accessor :casilla_actual, :encarcelado
    attr_writer :carta_libertad
    attr_reader :nombre, :saldo, :propiedades

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
      if(casilla.numeroCasilla < @casilla_actual.numeroCasilla)
        modificar_saldo(Qytetet.SALDO_SALIDA);
      end
      
      @casilla_actual = casilla;
      
      if(casilla.soy_edificable)
        tengo_propietario = casilla.tengo_propietario
        
        if(tengo_propietario)
          encarcelado = casilla.propietario_encarcelado
          
          if(!encarcelado)
            if (!casilla.esta_hipotecada)
              coste_alquiler = casilla.cobrar_alquiler()
              modificar_saldo(-coste_alquiler)
            end
            
          end
        end
        
      elsif (casilla.tipo == TipoCasilla::IMPUESTO)
        coste = casilla.coste
        modificar_saldo(-coste)
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
            @propiedades << titulo                              # no hay que hacer new de titulo?
            modificar_saldo(-coste_compra)
            puedo_comprar = true
          end
        end
      end
      return puedo_comprar
    end
    
    def devolver_carta_libertad()
       cartaAux = Sorpresa.new(@carta_libertad.texto, @carta_libertad.valor, @carta_libertad.tipo)
       @carta_libertad = nil
       
      return cartaAux   
    end
    
    def ir_a_carcel(casilla)
      @casilla_actual = casilla
      @encarcelado = true
      
    end
    
    def modificar_saldo(cantidad)
      @saldo += cantidad
      
    end
    
    def obtener_capital()
      capital = @saldo;
        
        for i in @propiedades
          capital = capital + (i.casilla.numCasas + i.casilla.numHoteles) * i.precioEdificar + i.casilla.coste
        end
      return capital
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
      numero_total = cuantas_casas_y_hoteles_tengo
      modificar_saldo(numero_total*cantidad)  
    end
    
    def pagar_libertad(cantidad)
      tengo_saldo = tengo_saldo(cantidad)
      
      if(tengo_saldo)
        modificar_saldo(-cantidad)
      end
      return tengo_saldo
    end
    
    def puedo_edificar_casa(casilla)
      es_mia = es_de_mi_propiedad(casilla)
      tengo_saldo = false
      
      if(es_mia)
        coste_edificar_casa = casilla.titulo.precioEdificar
        tengo_saldo = tengo_saldo(coste_edificar_casa)
      end
      
      return es_mia && tengo_saldo
    end
    
    def puedo_edificar_hotel(casilla)
      tengo_saldo = false
      es_mia = es_de_mi_propiedad(casilla)
      
      if(es_mia)
        coste_edificar = casilla.titulo.precioEdificar
        tengo_saldo = tengo_saldo(coste_edificar)
      end
      
      return es_mia && tengo_saldo
    end
    
    def puedo_hipotecar(casilla)
      puedo_hipotecar = false
      
      if(es_de_mi_propiedad(casilla))
        puedo_hipotecar = true
      end
      return puedo_hipotecar
    end
    
    def puedo_pagar_hipoteca(casilla)
      hipoteca = casilla.calcular_valor_hipoteca * 1.1
      puedo_pagar = false
      if(hipoteca < @saldo)
        puedo_pagar = true
      end
      return puedo_pagar
    end
    
    def puedo_vender_propiedad(casilla)
      es_mia = es_de_mi_propiedad(casilla)
      hipotecada = casilla.esta_hipotecada
      
      return es_mia && !hipotecada
    end
    
    def tengo_carta_libertad()
      return @carta_libertad != nil
    end
    
    def vender_propiedad(casilla)
      precio_venta = casilla.vender_titulo
      modificar_saldo(precio_venta)
      eliminar_de_mis_propiedades(casilla)  
    end
    
    def cuantas_casas_y_hoteles_tengo()
        numCasas = 0;
        numHoteles = 0;
        for i in @propiedades
          numCasas += @propiedades.index(i).casilla.numCasas
          numHoteles += @propiedades.index(i).casilla.numHoteles
        end
        return numCasas + numHoteles;
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
      " Encarcelado #{@encarcelado} \n Nombre: #{@nombre} \n Saldo: #{@saldo}\n Carta Liberdad: #{@carta_libertad}  \n Casilla actual: #{@casilla_actual}\n Propiedades #{@propiedades} "
    end
    
    private :cuantas_casas_y_hoteles_tengo, :eliminar_de_mis_propiedades, :es_de_mi_propiedad, :tengo_saldo
    
  end #clase
end #module

