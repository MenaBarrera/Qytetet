#encoding :UTF-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module ModeloQytetet
  class Tablero
    attr_reader :carcel
    def initialize
      inicializar
    end
    
    def es_casilla_carcel(numeroCasilla)
      if @casillas.at(numeroCasilla).tipo == TipoCasilla::CARCEL
        return true
      else
        return false
      end      
    end
    
    def obtener_casilla_numero(numeroCasilla)
      return @casilla.at(numeroCasilla)
    end
    
    def obtener_nueva_casilla(casilla, desplazamiento)
      numero_casilla = casilla.numeroCasilla
      numero_casilla = (numero_casilla + desplazamiento) % @casillas.length
      
      return @casillas.at(numero_casilla) 
    end
    
    def inicializar
      @casillas = Array.new
      
      i = 0
      titulos_calles = Array.new
      titulos_calles << TituloPropiedad.new("Calle Segovia", 350, 0.17, 80, 190)
      titulos_calles << TituloPropiedad.new("Calle Arosa", 350, -0.14, 90, 675)
      titulos_calles << TituloPropiedad.new("Calle Salamanca", 400, 0.13, 95, 340)
      titulos_calles << TituloPropiedad.new("Calle Cuenca", 250, 0.17, 65, 645)
      titulos_calles << TituloPropiedad.new("Avenida de Móstoles", 650, 0.2, 100, 1000)
      titulos_calles << TituloPropiedad.new("Calle del Metal", 550, -0.2, 75, 900)
      titulos_calles << TituloPropiedad.new("Calle Santander", 350, 0.15, 75, 625)
      titulos_calles << TituloPropiedad.new("Calle Guadalajara", 400, 0.1, 65, 970)
      titulos_calles << TituloPropiedad.new("Calle Burgos", 500, 0.19, 55, 310)
      titulos_calles << TituloPropiedad.new("Calle Ciudad Real", 450, -0.14, 75, 860)
      titulos_calles << TituloPropiedad.new("Calle del Trueno", 600, 0.1, 55, 650)
      titulos_calles << TituloPropiedad.new("Calle de los Nardos", 750, 0.2, 100, 1000)
      
      @carcel = Casilla.iniciar_casilla(5, TipoCasilla::CARCEL)
      @casillas << Casilla.iniciar_casilla(0, TipoCasilla::SALIDA)
      @casillas << Casilla.iniciar_calle(1, 400, titulos_calles[i])
      i += 1
      @casillas << Casilla.iniciar_calle(2, 360, titulos_calles[i])
      i+= 1
      @casillas << Casilla.iniciar_calle(3, 380, titulos_calles[i])
      i+= 1
      @casillas << Casilla.iniciar_calle(4, 300, titulos_calles[i])
      i+= 1
      @casillas << Casilla.iniciar_casilla(6, TipoCasilla::SORPRESA)
      @casillas << Casilla.iniciar_calle(7, 500, titulos_calles[i])
      i+= 1
      @casillas << Casilla.iniciar_calle(8, 490, titulos_calles[i])
      i+= 1
      @casillas << Casilla.iniciar_calle(9, 400, titulos_calles[i])
      i+= 1
      @casillas << Casilla.iniciar_casilla(10, TipoCasilla::IMPUESTO)
      @casillas << Casilla.iniciar_calle(11, 550, titulos_calles[i])
      i+= 1
      @casillas << Casilla.iniciar_casilla(12, TipoCasilla::SORPRESA)
      @casillas << Casilla.iniciar_calle(13, 600, titulos_calles[i])
      i+= 1
      @casillas << Casilla.iniciar_casilla(14, TipoCasilla::PARKING)
      @casillas << Casilla.iniciar_casilla(15, TipoCasilla::JUEZ)
      @casillas << Casilla.iniciar_calle(16, 550, titulos_calles[i])
      i+= 1
      @casillas << Casilla.iniciar_calle(17, 700, titulos_calles[i])
      i+= 1
      @casillas << Casilla.iniciar_casilla(18, TipoCasilla::SORPRESA)
      @casillas << Casilla.iniciar_calle(19, 790, titulos_calles[i])
    end
        
    def to_s
      "Tablero: \n Casillas: #{@casillas} \n Cárcel: #{@carcel}"
    end
        
    private :inicializar
  end
end
