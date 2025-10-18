package entorno;

import objetos.ItemTipo;
import player.Jugador;
import java.util.Random;

public class ZonaProfunda extends Zona{
//---------------Atributos-----------------------    
    private int presion;

//---------------Constructores-------------------
    public ZonaProfunda(){
        super("Zona Profunda", 200, 999, 2,6, ItemTipo.plata, ItemTipo.oro, ItemTipo.acero, ItemTipo.diamante, ItemTipo.magnetita);
        this.presion = 10;
    }

//---------------Setters y Getters---------------
    public int getPresion(){return presion;}

//---------------Otros---------------------------
    
    /*
    * Nombre: calculoPresion
    * Descripción: calcula las unidades de oxigeno que consume la presion segun las formulas del pdf de la tarea
    * @param Parámetro(s): variable del jugador
    * @return: no retoran, es void
    */
    public double calculoPresion(Jugador jugador){ //solo si mejoraTanque = false, explorar
        double presionFormula = this.presion + 6*profundidadNormalizada(jugador); //this.presion es la presion que decia la tarea que utilicemos, en vez de poner 10 
        return presionFormula;
    }

    /*
    * Nombre: explorar
    * Descripción: calcula la cantidad de oxigeno que se consume dependiendo de si el jugador tiene o no activado el tanque de oxigeno, ademas define probabilidades aleatorias para para la generacion del recurso aleatorio segun las formulsa del pdf
    * @param Parámetro(s): variable del jugador
    * @return: no retoran, es void
    */
    public void explorar(Jugador jugador){
        //la primera parte se encarga del consumo del oxigeno
        double profundidadNormalizada = profundidadNormalizada(jugador);
        double calculoOxigeno;
        if(jugador.getMejoraTanque()){
            calculoOxigeno = Math.ceil(12 + 10*profundidadNormalizada); //si el tanque esta mejorado no sumamos la presion
        }
        else{
            calculoOxigeno = Math.ceil(12 + 10*profundidadNormalizada + calculoPresion(jugador)); //aqui hay presion asi que la sumamos al calculo
        }
        jugador.getTanqueOxigeno().consumirO2((int)calculoOxigeno);

        Random rng = new Random();
        int recursoRandom = rng.nextInt(5); // numero entre [0,4]
        if(recursoRandom == 0){ //plata
            int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
            jugador.agregarAlInventario(ItemTipo.plata, cantidadItem);
            System.out.println("Se a obtenido " + cantidadItem + " de plata");
        }
        else if(recursoRandom == 1){//oro
            int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
            jugador.agregarAlInventario(ItemTipo.oro, cantidadItem);
            System.out.println("Se a obtenido " + cantidadItem + " de oro");
        }
        else if(recursoRandom == 2){//acero
            int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
            jugador.agregarAlInventario(ItemTipo.acero, cantidadItem);
            System.out.println("Se a obtenido " + cantidadItem + " de acero");
        }
        else if(recursoRandom == 3){//diamante
            int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
            jugador.agregarAlInventario(ItemTipo.diamante, cantidadItem);
            System.out.println("Se a obtenido " + cantidadItem + " de diamante");
        }
        else if(recursoRandom == 4){//magnetita
            int cantidadItem = jugador.getZonaActual().cantidadRecoleccionExplorar(jugador);
            jugador.agregarAlInventario(ItemTipo.magnetita, cantidadItem);
            System.out.println("Se a obtenido " + cantidadItem + " de magnetita");
        }
    }
    
    /*
    * Nombre: recolectar
    * Descripción: realiza la accion de recolectar consumiendo el oxigeno correcpondiente a la accion, el cual varia segun las mejoras de el jugador, y utiliza el metodo de produccionPorRecolectar, el cual permite determinar cuanto del recurso a recolectar vamos a conseguir
    * @param Parámetro(s): variable del jugador, y variable del tipo ItemTipo con el tipo de recurso que se quiere recolectar
    * @return: no retoran, es void
    */
    public void recolectar(Jugador jugador, ItemTipo recurso){
        double profundidadNormalizada = profundidadNormalizada(jugador);
        double calculoOxigeno;
        if(jugador.getMejoraTanque()){
            calculoOxigeno = Math.ceil(12 + 6*profundidadNormalizada); //si el tanque esta mejorado no sumamos la presion
        }
        else{
            calculoOxigeno = Math.ceil(12 + 6*profundidadNormalizada + calculoPresion(jugador)); //aqui hay presion asi que la sumamos al calculo
        }        
        jugador.getTanqueOxigeno().consumirO2((int)calculoOxigeno);

        int cantidadRecoleccion = produccionPorRecolectar(jugador);
        jugador.agregarAlInventario(recurso, cantidadRecoleccion);
        System.out.println("Se a obtenido " + cantidadRecoleccion + " de " + recurso);
    }
}