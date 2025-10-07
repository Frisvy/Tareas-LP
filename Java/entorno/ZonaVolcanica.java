package entorno;


public class ZonaVolcanica extends Zona{
    private boolean planoEncontrado;

    public ZonaVolcanica(){
        super("Zona Volcanica", 1000, 1500, 3, 8 );
        this.planoEncontrado = false;
    }
}