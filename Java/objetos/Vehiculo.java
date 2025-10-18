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
    /*
    * Nombre: agregarItemsBodega
    * Descripción: recibe un item y una cantidad para agregarla a la bodega de la nave
    * @param Parámetro(s): variable ItemTipo con el tipo del item y int con la cantidad a agregar
    * @return: no retoran, es void
    */
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

    /*
    * Nombre: trasnferirObjetos
    * Descripción: transfiere los objetos del inventario del jugador a la bodega de la nave. Una vez transferidos todos los objetos se procede a vaciar el inventario del jugador
    * @param Parámetro(s): variable de tipo Jugador (osea el jugador jeje)
    * @return: no retoran, es void
    */
    public void transferirObjetos(Jugador jugador){ //se transfieren los elementos del jugador a la bodega
        for(Item elemento : jugador.getInventario()){
            agregarItemsBodega(elemento.getTipo(), elemento.getCantidad());
        }
        jugador.vaciarInventario();

    } //Se permite que los estudiantes decidan qué argumentos debe contener el método ’void transferirObjetos(...) con libertad.
}