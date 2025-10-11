package entorno;

import objetos.ItemTipo;
import player.Jugador;
import java.util.Random;

public class ZonaArrecife extends Zona{
//---------------Atributos---------------------------
    private int piezasTanque;

//---------------Constructores-----------------------
    public ZonaArrecife(){ 
        super("Zona Arrecife", 0, 199,1, 3);
        this.piezasTanque = 3; // 30% probabilidad de encontrar pieza al explorar
        this.getRecursos().add(ItemTipo.cuarzo);
        this.getRecursos().add(ItemTipo.silicio);
        this.getRecursos().add(ItemTipo.cobre);
        this.getRecursos().add(ItemTipo.PIEZA_TANQUE);
    }

//---------------Setters y Getters-------------------     
    public int getPiezasTanque(){return piezasTanque;}

    public void obtenerPiezaTanque(){
        piezasTanque -= 1; 
    }

//---------------Otros-------------------------------    
    public void explorar(Jugador jugador){
        //la primera parte se encarga del consumo del oxigeno
        double profundidadNormalizada = profundidadNormalizada(jugador);
        double calculoOxigeno = Math.ceil(12 + 10*profundidadNormalizada); //no hay presion asi que no sumamos nada mas
        jugador.getTanqueOxigeno().consumirO2((int)calculoOxigeno); // restamos el oxigeno del jugador

        double probabilidad = Math.random(); // rango [0,1]
        Random rng = new Random();
        int recursoRandom = rng.nextInt(3); // numero entre [0,2]
        if(probabilidad < 0.3 && this.piezasTanque > 0 ){
            this.obtenerPiezaTanque();
            jugador.agregarAlInventario(ItemTipo.PIEZA_TANQUE, 1);
            System.out.println("Se a obtenido una Pieza de tanque");
        }
        else{ // calcular la cantidad de recursos que se obtienen al explorar y no encontrar nada
            if(recursoRandom == 0){ // cuarzo
                int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
                jugador.agregarAlInventario(ItemTipo.cuarzo, cantidadItem);
                System.out.println("Se a obtenido " + cantidadItem + " de cuarzo");
            }
            else if(recursoRandom == 1){//silicio
                int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
                jugador.agregarAlInventario(ItemTipo.silicio, cantidadItem);
                System.out.println("Se a obtenido " + cantidadItem + " de silicio");

            }
            else if(recursoRandom == 2){ //cobre
                int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
                jugador.agregarAlInventario(ItemTipo.cobre, cantidadItem);
                System.out.println("Se a obtenido " + cantidadItem + " de cobre");
            }
        }        

    }
}