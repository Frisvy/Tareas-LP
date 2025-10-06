package player;



public class Oxigeno{
    private int oxigenoRestante;
    private int capacidadMaxima;

    public Oxigeno(){
        this.oxigenoRestante = 60;
        this.capacidadMaxima = 60;
    }
    
    public int getOxigenoRestante(){
        return oxigenoRestante;
    }

    public void consumirO2(int unidades){
        
    }
    
    public void recargarCompleto(){
        this.oxigenoRestante = capacidadMaxima;
    }
}