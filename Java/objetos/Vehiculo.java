package objetos;

import java.util.ArrayList;
import java.util.List;
import player.Jugador;


public abstract class Vehiculo{
//---------------Atributos-----------------------
    private List<Item> bodega;

//---------------Constructores-----------------------
    public Vehiculo(){
        this.bodega = new ArrayList<>();
    }

//---------------Setters y Getters-------------------
    public List<Item> getBodega(){return bodega;}

//---------------Otros-------------------------------
    public void agregarItemsBodega(ItemTipo tipo, int cantidad){
        if(cantidad > 0){
            for(Item elemento : bodega){
                if(elemento.getTipo() == tipo){
                    elemento.setCantidad(cantidad + elemento.getCantidad());
                    return;
                }
            }
            bodega.add(new Item(tipo, cantidad));
        }
    }


public void transferirObjetos(Jugador jugador){ //se transfieren los elementos del jugador a la bodega
        for(Item elemento : jugador.getInventario()){
            agregarItemsBodega(elemento.getTipo(), elemento.getCantidad());
        }
        jugador.vaciarInventario();

    } //Se permite que los estudiantes decidan qué argumentos debe contener el método ’void transferirObjetos(...) con libertad.
}