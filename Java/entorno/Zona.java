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
    private int nMinRecolectar;
    private int nMaxRecolectar;
//---------------Constructores-------------------
    public Zona(String nombre, int profundidadMin, int profundidadMax, int nMinRecolectar, int nMaxRecolectar){
        this.nombre = nombre;
        this.profundidadMin = profundidadMin;
        this.profundidadMax = profundidadMax;
        this.recursos = EnumSet.noneOf(ItemTipo.class);
        this.nMinRecolectar = nMinRecolectar;
        this.nMaxRecolectar = nMaxRecolectar;
    }
//---------------Setters y Getters---------------  
    public int getProfundidadMin(){return profundidadMin;}
    public int getProfundidadMax(){return profundidadMax;}
    public String getNombre(){return nombre;}
    public EnumSet <ItemTipo> getRecursos(){return recursos;}
    
//---------------Otros--------------------------- 
    public double profundidadNormalizada(Jugador jugador){ //utilice math.max para que la formula se vea igual que la de la tarea, y no usar condicionales por casos 
        double profundidadNormalizada = (jugador.getProfundidadActual() - profundidadMin)/Math.max(1.0,(double)(profundidadMax - profundidadMin));
        return profundidadNormalizada; // = d en la tarea
    }
    
    public int cantidadRecoleccionExplorar(Jugador jugador){
        double d = this.profundidadNormalizada(jugador);
        double nMin = (double)this.nMinRecolectar;
        double cantidadMaxima = Math.max(1.0, Math.floor(nMin * d));
        
        return (int)cantidadMaxima;
    }

    public void entrar(Jugador jugador){}
    
    public abstract void explorar(Jugador jugador);
    
}