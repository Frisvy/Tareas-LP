package entorno;

import objetos.ItemTipo;
import player.Jugador;
import java.util.Random;

//SEGUN EL PDF DE LA TAREA LAS ACCIONES NO CONSUMEN O2 EN ESTA ZONA

public class NaveEstrellada extends Zona{
//---------------Atributos---------------------------
    private boolean moduloEncontrado;

//---------------Constructores-----------------------
    public NaveEstrellada(){
        super("Nave Estrellada", 0, 0, 1, 4,ItemTipo.cables, ItemTipo.piezas_de_metal, ItemTipo.MODULO_PROFUNDIDAD);
        this.moduloEncontrado = false;
    }

//---------------Setters y Getters-------------------  
    public boolean getModuloEncontrado(){return moduloEncontrado;}    

//---------------Otros------------------------------- 
    /*
    * Nombre: encontrarModulo 
    * Descripción: sete la variable de moduloEncontrado a true
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void encontrarModulo(){
        this.moduloEncontrado = true;
    }

    /*
    * Nombre: explorar
    * Descripción: determina el rng de los diversos items de la zona, ademas de consumir el oxigeno y la cantidad de los recursos correspondiente a la accion segun las formulas del pdf,
    * @param Parámetro(s): variable del jugador
    * @return: no retoran, es void
    */
    public void explorar(Jugador jugador){
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

    /*
    * Nombre: recolectar
    * Descripción: realiza la accion de recolectar, la cual añade cierta cantidad de recursos al inventario del jugador
    * @param Parámetro(s): variable del jugador y variable ItemTipo con el tipo de recurso a recolectar
    * @return: no retoran, es void
    */
    public void recolectar(Jugador jugador, ItemTipo recurso){
        int cantidadRecoleccion = produccionPorRecolectar(jugador);
        jugador.agregarAlInventario(recurso, cantidadRecoleccion);
        System.out.println("Se a obtenido " + cantidadRecoleccion + " de " + recurso);
    } 

}