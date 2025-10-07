package entorno;

public class NaveEstrellada extends Zona{
    private boolean moduloEncontrado;

    public NaveEstrellada(){
        super("Nave Estrellada", 0, 0, 1, 4);
        this.moduloEncontrado = false;
    }
}