import objetos.*;
import player.*;
import entorno.*;



public class Main{
    public static void main(String[] args) {
        System.out.println("==== Subnautica ====");
        ZonaArrecife arrecife = new ZonaArrecife();
        ZonaProfunda profunda = new ZonaProfunda();
        ZonaVolcanica volcanica = new ZonaVolcanica();
        NaveEstrellada estrellada = new NaveEstrellada();
        Jugador jugador = new Jugador(arrecife);
        jugador.verEstadoJugador();

    }

}