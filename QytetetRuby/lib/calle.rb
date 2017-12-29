# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class Calle < Casilla
  
  def initialize(ncas, coste, tituloP)
    super(ncas,coste)
    
    @titulo = tituloP
    @numHoteles = 0
    @numCasas = 0
  end
end
