package objetos;

import java.util.ArrayList;
import java.util.List;

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
    public void transferirObjetos(){} //Se permite que los estudiantes decidan qué argumentos debe contener el método ’void transferirObjetos(...) con libertad.
    public void mostrarObjetos(){}
}