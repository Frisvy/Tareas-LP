package objetos;

public class Item{
//---------------Atributos-----------------------
    private ItemTipo tipo;
    private int cantidad;
    
//---------------Constructores-------------------
    public Item(ItemTipo tipo, int cantidad){
        this.tipo = tipo;
        this.cantidad = cantidad;
    }
//---------------Setters y Getters---------------
    public int getCantidad(){return cantidad;}
    public ItemTipo getTipo(){return tipo;} 
}