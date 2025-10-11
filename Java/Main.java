import objetos.*;
import player.*;
import entorno.*;
import java.util.Scanner;


public class Main{
    public static void main(String[] args) {    
//----------------instanciacion de zonas y jugador -----------------------------
        ZonaArrecife arrecife = new ZonaArrecife();
        ZonaProfunda profunda = new ZonaProfunda();
        ZonaVolcanica volcanica = new ZonaVolcanica();
        NaveEstrellada estrellada = new NaveEstrellada();
        Jugador jugador = new Jugador(arrecife);
//------------------------------------------------------------------------------
        Scanner scanner = new Scanner(System.in); //pedir accion al jugador
        boolean continuar = true; // para mantener el bucle  principal
        while(continuar){
            System.out.println("==== Subnautica ====");
            jugador.verEstadoJugador();
            String eleccion = scanner.nextLine();

            switch(eleccion){
                case "0" ->{
                    continuar = false;
                }
                case "1" ->{
                    //System.out.print("\033[H\033[2J"); //para limpiar la pantalla tras cada elecccion
                    System.out.println("Seleccione la profundidad de destino (Rango Zona = [" + jugador.getZonaActual().getProfundidadMin() + " - " + jugador.getZonaActual().getProfundidadMax() + "]): " );
                    String profundidadSeleccionada = scanner.nextLine();
                    int profundidadDestino = Integer.parseInt(profundidadSeleccionada);
                    jugador.subirDescender(profundidadDestino);
                }
                case "2" ->{
                    jugador.getZonaActual().explorar(jugador);
                }
                case "4" ->{
                    //validar si ta muelto o no
                    jugador.getTanqueOxigeno().recargarCompleto();
                }
            }
        }

    }

}