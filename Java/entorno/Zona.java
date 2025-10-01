package entorno;

import java.util.EnumSet;
import player.Jugador;

public abstract class Zona{
    public String nombre;
    private int profundidadMin;
    private int profundidadMax;
    private EnumSet <ItemTipo> recursos;
    
    public enum ItemTipo{
        cuarzo, silicio, cobre, //zona arrecife
        plata, oro, acero, diamante, magnetita, // zona profunda 
        titanio, sulfuro, uranio, // zona volcanica
        PIEZA_TANQUE, PLANO_NAVE, MODULO_PROFUNDIDAD
    }

    public void entrar(Jugador jugador){}
    public void explorar(Jugador jugador){}
}