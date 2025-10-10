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
        double calculoOxigeno = Math.ceil(12 + 10*profundidadNormalizada + calculoPresion(jugador)); //aqui hay presion asi que la sumamos al calculo
        jugador.getTanqueOxigeno().consumirO2((int)calculoOxigeno);

        Random rng = new Random();
        int recursoRandom = rng.nextInt(5); // numero entre [0,4]
        if(recursoRandom == 0){ //plata
            jugador.agregarAlInventario(ItemTipo.plata, jugador.getZonaActual().cantidadRecoleccionExplorar(jugador));
        }
        else if(recursoRandom == 1){//oro
            jugador.agregarAlInventario(ItemTipo.oro, jugador.getZonaActual().cantidadRecoleccionExplorar(jugador));
        }
        else if(recursoRandom == 2){//acero
            jugador.agregarAlInventario(ItemTipo.acero, jugador.getZonaActual().cantidadRecoleccionExplorar(jugador));
        }
        else if(recursoRandom == 3){//diamante
            jugador.agregarAlInventario(ItemTipo.diamante, jugador.getZonaActual().cantidadRecoleccionExplorar(jugador));
        }
        else if(recursoRandom == 4){//magnetita
            jugador.agregarAlInventario(ItemTipo.magnetita, jugador.getZonaActual().cantidadRecoleccionExplorar(jugador));
        }
    }    
}