package entorno;

import java.util.EnumSet;
import player.Jugador;
import objetos.ItemTipo;
import static java.lang.Math.max;

public abstract class Zona{
//---------------Atributos-----------------------        
    public String nombre;
    private int profundidadMin;
    private int profundidadMax;
    private EnumSet <ItemTipo> recursos;
//---------------Constructores-------------------
    public Zona(String nombre, int profundidadMin, int profundidadMax){
        this.nombre = nombre;
        this.profundidadMin = profundidadMin;
        this.profundidadMax = profundidadMax;
    }
//---------------Setters y Getters---------------  
    public int getProfundidadMin(){return profundidadMin;}
    public int getProfundidadMax(){return profundidadMax;}
    public String getNombre(){return nombre;}
    
//---------------Otros--------------------------- 
    public double profundidadNormalizada(Jugador jugador){ //utilice math.max para que la formula se vea igual que la de la tarea, y no usar condicionales por casos 
        double profundidadNormalizada = (jugador.getProfundidadActual() - profundidadMin)/Math.max(1.0,(double)(profundidadMax - profundidadMin));
        return profundidadNormalizada; // = d en la tarea
    }

    public void entrar(Jugador jugador){
        
    }
    
    public void explorar(Jugador jugador){
        //la primera parte se encarga del consumo del oxigeno
        double profundidadNormalizada = profundidadNormalizada(jugador);
        int oxigenoConsumido = (int)Math.ceil(profundidadNormalizada);
        jugador.getTanqueOxigeno().consumirO2(oxigenoConsumido);

        double probabilidad = Math.random(); // rango [0,1]
        if(probabilidad < 0.3){
            
        }        

    }
    
}