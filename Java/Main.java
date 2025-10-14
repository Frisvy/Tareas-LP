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
            if(jugador.getTanqueOxigeno().getOxigenoRestante() == 0){ //si el jugador llega a 0 de O2 queda inconciente y pierde el oxigeno
                jugador.derrotado();
            }
            jugador.verEstadoJugador();
            String eleccion = scanner.nextLine();
            boolean menuNave = true;
            boolean menuRecolectar = true;
            switch(eleccion){
                
                case "0" ->{
                    continuar = false;
                }
                case "1" ->{ //subir o bajar en profundidad
                    System.out.println("Seleccione la profundidad de destino (Rango Zona = [" + jugador.getZonaActual().getProfundidadMin() + " - " + jugador.getZonaActual().getProfundidadMax() + "]): " );
                    String profundidadSeleccionada = scanner.nextLine();
                    int profundidadDestino = Integer.parseInt(profundidadSeleccionada);
                    jugador.subirDescender(profundidadDestino);
                }
                case "2" ->{ //explorar
                    jugador.getZonaActual().explorar(jugador);
                }
                case "3" ->{ //recolectar
                    while(menuRecolectar){
                        jugador.verMenuRecolectar(jugador);
                        String eleccionRecolectar = scanner.nextLine();
                        switch(eleccionRecolectar){
                            case "1" ->{
                                if(jugador.getZonaActual().getNombre() == "Zona Arrecife"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.cuarzo);
                                }
                                else if(jugador.getZonaActual().getNombre() == "Zona Profunda"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.plata);
                                }
                                else if(jugador.getZonaActual().getNombre() == "Zona Volcanica"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.titanio);
                                }
                                else if(jugador.getZonaActual().getNombre() == "Nave Estrellada"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.cables);
                                }
                                menuRecolectar = false;
                            }
                            case "2" ->{
                                if(jugador.getZonaActual().getNombre() == "Zona Arrecife"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.silicio);
                                }
                                else if(jugador.getZonaActual().getNombre() == "Zona Profunda"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.oro);
                                }
                                else if(jugador.getZonaActual().getNombre() == "Zona Volcanica"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.sulfuro);
                                }
                                else if(jugador.getZonaActual().getNombre() == "Nave Estrellada"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.piezas_de_metal);
                                }
                                menuRecolectar = false;
                            }
                            case "3" ->{
                                if(jugador.getZonaActual().getNombre() == "Zona Arrecife"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.cobre);
                                }
                                else if(jugador.getZonaActual().getNombre() == "Zona Profunda"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.acero);
                                }
                                else if(jugador.getZonaActual().getNombre() == "Zona Volcanica"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.uranio);
                                }
                                menuRecolectar = false;
                            }
                            case "4" ->{ 
                                if(jugador.getZonaActual().getNombre() == "Zona Profunda"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.diamante);
                                }
                                menuRecolectar = false;
                            }
                            case "5" ->{
                                if(jugador.getZonaActual().getNombre() == "Zona Profunda"){
                                    jugador.getZonaActual().recolectar(jugador,ItemTipo.magnetita);
                                }
                                menuRecolectar = false;
                            }
                        }

                    }

                } 
                case "4" ->{//volver a la nave
                    //validar si ta muelto o no
                    System.out.println("Has entrado en la nave");
                    jugador.getTanqueOxigeno().recargarCompleto();
                    while(menuNave){
                        jugador.verMenuNave();
                        String eleeccionMenuNave = scanner.nextLine();

                        switch(eleeccionMenuNave){
                            case "0" ->{
                                menuNave = false;
                            }
                            case "1" ->{
                                System.out.println("Seleccione la profundidad de destino (Rango Zona = [" + jugador.getZonaActual().getProfundidadMin() + " - " + jugador.getZonaActual().getProfundidadMax() + "]): " );
                                String profundidadSeleccionadaAnclaje = scanner.nextLine();
                                int profundidadDestinoAnclaje = Integer.parseInt(profundidadSeleccionadaAnclaje);
                                jugador.getNave().fijarAnclaje(profundidadDestinoAnclaje, jugador);
                            }
                            case "2" ->{
                                jugador.getNave().transferirObjetos(jugador);
                            }
                            case "3" ->{//creacion
                                jugador.verMenuCreacion();
                                String eleccionMenuCreacion = scanner.nextLine();
                                switch(eleccionMenuCreacion){
                                    case "1" ->{
                                        jugador.mejorarTanque();
                                        System.out.println("tanquedemierda" + jugador.getMejoraTanque());
                                    }
                                    case "2" ->{
                                        jugador.mejorarOxigeno();
                                    }
                                    case "3" ->{
                                        jugador.crearTrajeTermico();
                                    }
                                    case "4" ->{
                                        jugador.instalarModuloProfundidad();
                                    }
                                }
                            }
                            case "4" -> { //cambio de zona 
                                jugador.verMenuZonas();
                                String eleccionZonaNueva = scanner.nextLine();
                                switch(eleccionZonaNueva){
                                    case "1" ->{
                                        arrecife.entrar(jugador);
                                        jugador.getNave().setProfundidadAnclaje(0);
                                    }
                                    case "2" ->{
                                        profunda.entrar(jugador);
                                        jugador.getNave().setProfundidadAnclaje(200);
                                    }
                                    case "3" ->{
                                        volcanica.entrar(jugador);
                                        if(jugador.getZonaActual().getNombre() == "Zona Volcanica"){ // si se pudo acceder seteamos el anclaje, de lo contrario lo dejamos igual
                                            jugador.getNave().setProfundidadAnclaje(1000);
                                        }
                                    }
                                    case "4" ->{
                                        estrellada.entrar(jugador);
                                        jugador.getNave().setProfundidadAnclaje(0);
                                    }
                                }
                            }
                            case "5" ->{
                                jugador.getNave().verInventarioNave();
                            }
                        }
                    }
                }
                case "5" ->{
                    jugador.verInventarioJugador();
                }
            }
        }
    }
}