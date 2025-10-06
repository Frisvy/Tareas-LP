package objetos;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehiculo{
    private List<Item> bodega;

    public Vehiculo(){
        this.bodega = new ArrayList<>();
    }
    public void transferirObjetos(){} //Se permite que los estudiantes decidan qué argumentos debe contener el método ’void transferirObjetos(...) con libertad.

}