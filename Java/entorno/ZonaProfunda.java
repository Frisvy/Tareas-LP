package entorno;

import objetos.ItemTipo;
import player.Jugador;
import java.util.Random;

public class ZonaProfunda extends Zona{
//---------------Atributos-----------------------    
    private int presion;

//---------------Constructores-------------------
    public ZonaProfunda(){
        super("Zona Profunda", 200, 999, 2,6);
        this.presion = 10;
        this.getRecursos().add(ItemTipo.plata);
        this.getRecursos().add(ItemTipo.oro);
        this.getRecursos().add(ItemTipo.acero);
        this.getRecursos().add(ItemTipo.diamante);
        this.getRecursos().add(ItemTipo.magnetita);
    }

//---------------Setters y Getters---------------
    public int getPresion(){return presion;}

//---------------Otros---------------------------
    public double calculoPresion(Jugador jugador){ //solo si mejoraTanque = false, explorar
        double presionFormula = this.presion + 6*profundidadNormalizada(jugador); //this.presion es la presion que decia la tarea que utilicemos, en vez de poner 10 
        return presionFormula;
    }

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
}