package entorno;

import player.Jugador;

public class ZonaArrecife extends Zona{
    private int piezasTanque;

    public ZonaArrecife(){
        super("Zona Arrecife", 0, 199);
        this.piezasTanque = 3; // 30% probabilidad de encontrar pieza al explorar
    }

    public void explorar(Jugador jugador){

    }
}