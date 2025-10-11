package objetos;

public class NaveExploradora extends Vehiculo{
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

}