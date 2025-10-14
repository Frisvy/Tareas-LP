package objetos;

import player.Jugador;

public class NaveExploradora extends Vehiculo implements AccesoProfundidad{
//---------------Atributos---------------------------    
    private int profundidadSoportada;
    private int profundidadAnclaje;
    private ModuloProfundidad modulo;
    private boolean moduloInstalado;

//---------------Constructores-----------------------
    public NaveExploradora(){
        super();
        this.profundidadSoportada = 500;
        this.profundidadAnclaje = 0;
        this.modulo = new ModuloProfundidad();
        this.moduloInstalado = false;
    }

//---------------Setters y Getters-------------------
    public int getProfundidadAnclaje(){return profundidadAnclaje;}
    public boolean getModuloInstalado(){return moduloInstalado;}
    public void setProfundidadAnclaje(int nuevoAnclaje){
        this.profundidadAnclaje = nuevoAnclaje;
    }
    public void setProfundidadSoportada(int profundidadSoportada){
        this.profundidadSoportada = profundidadSoportada;
    }
//---------------Otros-------------------------------
    public boolean puedeAcceder(int requerido){ //revisar
        if(requerido > this.profundidadSoportada){
            System.out.println("No se puede acceder la nave no soporta la profundidad");
            return false;
        }
        return true;
    }

    public void verInventarioNave(){
        System.out.println("----Inventario Nave----");
        if(this.getBodega().isEmpty()){
            System.out.println("No tienes nada en la bodega");
        }
        for(Item objeto : this.getBodega()){
            if(objeto.getCantidad() > 0){
                System.out.println(objeto.getCantidad() +" de " + objeto.getTipo());
            }
        }
    }

    public void fijarAnclaje(int nuevoAnclaje, Jugador jugador){
        if(puedeAcceder(nuevoAnclaje)){
            if(jugador.getZonaActual().getProfundidadMin() > nuevoAnclaje){ //en caso que quiera poner un anclaje mas arriba de la zona actual
                this.profundidadAnclaje = jugador.getZonaActual().getProfundidadMin();
                jugador.setProfundidadActual(profundidadAnclaje);
                System.out.println("Para salir de la zona regresa a la nave");
                return;
            }
            this.profundidadAnclaje = nuevoAnclaje;
            jugador.setProfundidadActual(profundidadAnclaje); 
        }
    }

    public void instalarModuloProfundidad(){
        this.modulo.aumentarProfundidad();
        this.profundidadSoportada = profundidadSoportada + this.modulo.getProfundidadExtra();
        this.moduloInstalado = true;
    }

//---------------Sub Clase---------------------------
    private class ModuloProfundidad{
    //---------atributos-------------
        private int profundidadExtra;
    
    //---------setters y getters-----
        private int getProfundidadExtra(){return profundidadExtra;}
    //---------constuctores----------
    public ModuloProfundidad(){
        this.profundidadExtra = 0;
    }
    //---------metododos-------------
        public void aumentarProfundidad(){
            this.profundidadExtra = 1000;
        }
    }
}