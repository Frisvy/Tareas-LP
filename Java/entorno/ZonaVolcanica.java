package entorno;

import objetos.ItemTipo;
import player.Jugador;
import java.util.Random;


public class ZonaVolcanica extends Zona{
//---------------Atributos---------------------------
    private boolean planoEncontrado;

//---------------Constructores-----------------------
    public ZonaVolcanica(){
        super("Zona Volcanica", 1000, 1500, 3, 8, ItemTipo.titanio, ItemTipo.sulfuro, ItemTipo.uranio, ItemTipo.PLANO_NAVE);
        this.planoEncontrado = false;      
    }

//---------------Setters y Getters-------------------
    public boolean getPlanoEncontrado(){return planoEncontrado;}

//---------------Otros-------------------------------
    
//Escribiendo los comentarios de las funciones me di cuenta que la funcion de encontrar plano estaba seteada en false, por lo cual hay una funcion en Jugador.Java que hace lo mismo que esta funcion de abajo, pero como es muy tarde como para borrar la variable y no quiero arreglar mas erroes dejare las 2 funciones ahi, porque si algo no esta roto no hay para que arreglarloXD
    
    /*
    * Nombre: encontrarPlano
    * Descripción: setea la variable de planoEncontrado en true
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void encontrarPlano(){
        this.planoEncontrado = true;
    }

    /*
    * Nombre: Explorar
    * Descripción: calcula la cantidad de oxigeno consumido por la accion de explorar, y determina la probabilidad de aparicion de items segun las formulas estipuladas en el pdf de la tarea, tambien imprimer mensajes de alerta con los objetos y las cantidades obtenidas
    * @param Parámetro(s): variable del jugador
    * @return: no retoran, es void
    */
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
            jugador.encontrarPlanos();
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
    /*
    * Nombre: recolectar
    * Descripción: consume el oxigeno correspondiente a la accion de recolectar en la profundidad actual del jugador, y ademas determina la cantidad de recurso que se debe recolectar segun la profundidad a la que se esta
    * @param Parámetro(s): variable del jugador, y variable con el tipo de recurso a recolectar
    * @return: no retoran, es void
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