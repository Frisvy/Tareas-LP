package objetos;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehiculo{
    private List<Item> bodega;

    public Vehiculo(){
        this.bodega = new ArrayList<>();
    }
    public void transferirObjetos(){}
}