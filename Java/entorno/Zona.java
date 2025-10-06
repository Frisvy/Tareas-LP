package entorno;

import java.util.EnumSet;
import player.Jugador;
import objetos.ItemTipo;

public abstract class Zona{
    public String nombre;
    private int profundidadMin;
    private int profundidadMax;
    private EnumSet <ItemTipo> recursos;

    public Zona(String nombre, int profundidadMin, int profundidadMax){
        this.nombre = nombre;
        this.profundidadMin = profundidadMin;
        this.profundidadMax = profundidadMax;
    }
   
    public int getProfundidadMin(){return profundidadMin;}
    public int getProfundidadMax(){return profundidadMax;}
    public String getNombre(){return nombre;}
    
    public void entrar(Jugador jugador){
        
    }
    
    public void explorar(Jugador jugador){}
    
}