package entorno;

import objetos.ItemTipo;
import player.Jugador;
import java.util.Random;

public class ZonaArrecife extends Zona{
    private int piezasTanque;

    public ZonaArrecife(){ 
        super("Zona Arrecife", 0, 199,1, 3);
        this.piezasTanque = 3; // 30% probabilidad de encontrar pieza al explorar
        this.getRecursos().add(ItemTipo.cuarzo);
        this.getRecursos().add(ItemTipo.silicio);
        this.getRecursos().add(ItemTipo.cobre);
        this.getRecursos().add(ItemTipo.PIEZA_TANQUE);
    }

    public int getPiezasTanque(){return piezasTanque;}

    public void obtenerPiezaTanque(){
        piezasTanque -= 1; 
    }

    public void explorar(Jugador jugador){
        //la primera parte se encarga del consumo del oxigeno
        double profundidadNormalizada = profundidadNormalizada(jugador);
        double calculoOxigeno = Math.ceil(12 + 10*profundidadNormalizada); //no hay presion asi que no sumamos nada mas
        jugador.getTanqueOxigeno().consumirO2((int)calculoOxigeno); // restamos el oxigeno del jugador

        double probabilidad = Math.random(); // rango [0,1]
        Random rng = new Random();
        int recursoRandom = rng.nextInt(3);
        if(probabilidad < 0.3 && this.piezasTanque > 0 ){
            this.obtenerPiezaTanque();
        }
        else{ // calcular la cantidad de recursos que se obtienen al explorar y no encontrar nada, añadir en el inventario del jugador la cantidad, no en la zona XD
            if(recursoRandom == 0){ // cuarzo
                jugador.getInventario().getCantidad //ayuda
            }
            else if(recursoRandom == 1){

            }
            else if(recursoRandom == 2){

            }
        }        

    }
}