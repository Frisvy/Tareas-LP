package objetos;

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
    public boolean puedeAcceder(int requerido){
        if(requerido > this.profundidadSoportada){
            System.out.println("No se puede acceder la nave no soporta la profundidad");
            return false;
        }
        return true;
    }
//---------------Sub Clase---------------------------
    private class ModuloProfundidad{
        private int profundidadExtra;

        public void aumentarProfundidad(){}
    }

}