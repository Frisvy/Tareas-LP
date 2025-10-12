package entorno;

import objetos.ItemTipo;
import player.Jugador;
import java.util.Random;


public class ZonaVolcanica extends Zona{
//---------------Atributos---------------------------
    private boolean planoEncontrado;

//---------------Constructores-----------------------
    public ZonaVolcanica(){
        super("Zona Volcanica", 1000, 1500, 3, 8 );
        this.planoEncontrado = false;
        this.getRecursos().add(ItemTipo.titanio);
        this.getRecursos().add(ItemTipo.sulfuro);
        this.getRecursos().add(ItemTipo.uranio);
        this.getRecursos().add(ItemTipo.PLANO_NAVE);        
    }

//---------------Setters y Getters-------------------
    public boolean getPlanoEncontrado(){return planoEncontrado;}

//---------------Otros-------------------------------
    public void encontrarPlano(){
        this.planoEncontrado = false;
    }

    public void explorar(Jugador jugador){
        double profundidadNormalizada = profundidadNormalizada(jugador);
        double calculoOxigeno = Math.ceil(12 + 10*profundidadNormalizada); // no hay presion, asi que basta con negar la salida del jugador de la nave en caso de que se quiera bajar el bobo
        jugador.getTanqueOxigeno().consumirO2((int)calculoOxigeno); // restamos el oxigeno del jugador

        double probabilidad = Math.random(); //rango [0,1]
        Random rng = new Random();
        int recursoRandom = rng.nextInt(3); // numero entre [0,2]
        if(probabilidad < 0.15 && !this.planoEncontrado){
            this.encontrarPlano();
            jugador.agregarAlInventario(ItemTipo.PLANO_NAVE, 1);
            System.out.println("Se a encontrado un plano para una nave");
        }
        else{
            if(recursoRandom == 0){ //titanio
                int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
                jugador.agregarAlInventario(ItemTipo.titanio, cantidadItem);
                System.out.println("Se a obtenido " + cantidadItem + " de titanio");
            }
            else if(recursoRandom == 1){//sulfuro
                int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
                jugador.agregarAlInventario(ItemTipo.sulfuro, cantidadItem);
                System.out.println("Se a obtenido " + cantidadItem + " de sulfuro");

            }
            else if(recursoRandom == 2){ //uranio
                int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
                jugador.agregarAlInventario(ItemTipo.uranio, cantidadItem);
                System.out.println("Se a obtenido " + cantidadItem + " de uranio");
            }

        }
    }
    public void recolectar(Jugador jugador, ItemTipo recurso){
        double profundidadNormalizada = profundidadNormalizada(jugador);
        double calculoOxigeno = Math.ceil(10 + 6*profundidadNormalizada); //no sumamos presion y redondeamos hacia arriba
        jugador.getTanqueOxigeno().consumirO2((int)calculoOxigeno);

        int cantidadRecoleccion = produccionPorRecolectar(jugador);
        jugador.agregarAlInventario(recurso, cantidadRecoleccion);
        System.out.println("Se a obtenido " + cantidadRecoleccion + " de " + recurso);
    }
}