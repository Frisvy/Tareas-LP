package objetos;

import player.Jugador;

public class NaveExploradora extends Vehiculo implements AccesoProfundidad{
//---------------Atributos---------------------------    
    private int profundidadSoportada;
    private int profundidadAnclaje;

//---------------Constructores-----------------------
    public NaveExploradora(){
        super();
        this.profundidadSoportada = 500;
        this.profundidadAnclaje = 0;
    }

//---------------Setters y Getters-------------------
    public int getProfundidadAnclaje(){return profundidadAnclaje;}
    public void setProfundidadAnclaje(int nuevoAnclaje){
        this.profundidadAnclaje = nuevoAnclaje;
    }

//---------------Otros-------------------------------
    public boolean puedeAcceder(int requerido){ //revisar
        if(requerido > this.profundidadSoportada){
            System.out.println("No se puede acceder la nave no soporta la profundidad");
            return false;
        }
        return true;
    }
    public void verInventarioNave(){
        System.out.println("----Inventario Nave----");
        if(this.getBodega().isEmpty()){
            System.out.println("No tienes nada en la bodega");
        }
        for(Item objeto : this.getBodega()){
            if(objeto.getCantidad() > 0){
                System.out.println(objeto.getCantidad() +" de " + objeto.getTipo());
            }
        }
    }

    public void crearObjetos(Jugador jugador){}

    public void fijarAnclaje(int nuevoAnclaje){}


//---------------Sub Clase---------------------------
    private class ModuloProfundidad{
        private int profundidadExtra;

        public void aumentarProfundidad(){}
    }
}