package entorno;

import objetos.ItemTipo;
import player.Jugador;
import java.util.Random;

public class NaveEstrellada extends Zona{
//---------------Atributos---------------------------
    private boolean moduloEncontrado;

//---------------Constructores-----------------------
    public NaveEstrellada(){
        super("Nave Estrellada", 0, 0, 1, 4);
        this.moduloEncontrado = false;
        this.getRecursos().add(ItemTipo.cables);
        this.getRecursos().add(ItemTipo.piezas_de_metal);
        this.getRecursos().add(ItemTipo.MODULO_PROFUNDIDAD);
    }

//---------------Setters y Getters-------------------  
    public boolean getModuloEncontrado(){return moduloEncontrado;}    

//---------------Otros------------------------------- 
    public void encontrarModulo(){
        this.moduloEncontrado = true;
    }


    public void explorar(Jugador jugador){
        //consumo de oxigeno
        double profundidadNormalizada = profundidadNormalizada(jugador);
        double calculoOxigeno = Math.ceil(12 + 10*profundidadNormalizada); //no hay presion
        jugador.getTanqueOxigeno().consumirO2((int)calculoOxigeno);

        //calculo rng items 
        double probabilidad = Math.random(); // rango [0,1]
        Random rng = new Random();
        int recursoRandom = rng.nextInt(2); // numero entre [0,1]
        if(probabilidad < 0.25 && !this.moduloEncontrado){
            this.encontrarModulo();
            jugador.agregarAlInventario(ItemTipo.MODULO_PROFUNDIDAD, 1);
            System.out.println("Has encontrado el modulo profundidad");
        }
        else{
            if(recursoRandom == 0){//cables
                int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
                jugador.agregarAlInventario(ItemTipo.cables, cantidadItem);
                System.out.println("Se han obtenido " + cantidadItem + " cables");
            }
            else if(recursoRandom == 1){//piezas de metal
                int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
                jugador.agregarAlInventario(ItemTipo.piezas_de_metal, cantidadItem);
                System.out.println("Se han obtenido " + cantidadItem + " piezas de metal");
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