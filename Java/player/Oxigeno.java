package player;



public class Oxigeno{
    
//---------------Atributos-----------------------    
    private int oxigenoRestante;
    private int capacidadMaxima;

//---------------Constructores-------------------
    public Oxigeno(){
        this.oxigenoRestante = 60;
        this.capacidadMaxima = 60;
    }

//---------------Setters y Getters---------------     
    public int getOxigenoRestante(){
        return oxigenoRestante;
    }

//---------------Otros---------------------------  
    public void consumirO2(int unidades){
        this.oxigenoRestante = oxigenoRestante - unidades;
    }
    
    public void recargarCompleto(){
        this.oxigenoRestante = capacidadMaxima;
    }
}