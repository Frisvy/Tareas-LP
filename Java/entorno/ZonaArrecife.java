package entorno;

import objetos.ItemTipo;
import player.Jugador;
import java.util.Random;

public class ZonaArrecife extends Zona{
//---------------Atributos---------------------------
    private int piezasTanque;

//---------------Constructores-----------------------
    public ZonaArrecife(){ 
        super("Zona Arrecife", 0, 199,1, 3,ItemTipo.cuarzo, ItemTipo.silicio, ItemTipo.cobre, ItemTipo.PIEZA_TANQUE);
        this.piezasTanque = 3; // 30% probabilidad de encontrar pieza al explorar
    }

//---------------Setters y Getters-------------------     
    public int getPiezasTanque(){return piezasTanque;}

//---------------Otros-------------------------------    
    /*
    * Nombre: obtenerPiezaTanque
    * Descripción: cada vez que se llama resta una pieza de tanque en la pool de objetos claves de la zona
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void obtenerPiezaTanque(){
        piezasTanque -= 1; 
    }

    /*
    * Nombre: explorar
    * Descripción: realiza la accion de explorar, gastando el oxigeno correspondiente a la zona y la profundidad de la zona, ademas de determinar la cantidad y el tipo de objetos que se obtendran tras no obtener el objeto unico de la zona
    * @param Parámetro(s): variable del jugador
    * @return: no retoran, es void
    */
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

    /*
    * Nombre: recolectar 
    * Descripción: realiza la accion de recolectar, lo que implica consumir el oxigeno correspondiente segun las formulas del pdf, y permite obtener la cantidad del recurso elegido por el jugador correspondiente a la profundidad en la que se encuentra este ultimo
    * @param Parámetro(s): variable del jugador y variable de tipo ItemTipo con el recurso que desea recolectar el jugador
    * @return: no retorna, es void
    */
    public void recolectar(Jugador jugador, ItemTipo recurso){
        double profundidadNormalizada = profundidadNormalizada(jugador);
        double calculoOxigeno = Math.ceil(10 + 6*profundidadNormalizada); //no sumamos presion y redondeamos hacia arriba
        jugador.getTanqueOxigeno().consumirO2((int)calculoOxigeno);

        int cantidadRecoleccion = produccionPorRecolectar(jugador);
        jugador.agregarAlInventario(recurso, cantidadRecoleccion);
        System.out.println("Se a obtenido " + cantidadRecoleccion + " de " + recurso);
    }
}