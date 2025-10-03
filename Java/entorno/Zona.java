package entorno;

import java.util.EnumSet;
import player.Jugador;
import objetos.ItemTipo;

public abstract class Zona{
    public String nombre;
    private int profundidadMin;
    private int profundidadMax;
    private EnumSet <ItemTipo> recursos;

    public void entrar(Jugador jugador){}
    public void explorar(Jugador jugador){}
}