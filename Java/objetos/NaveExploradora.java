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
    /*
    * Nombre: puedeAcceder
    * Descripción: recibe la profunidad a la que se quiere mover la nave y determina si la nave es capaz de soportar la profundidad deseada, en caso de soportarla retorna true, de lo contrario false (niega el acceso a la profundidad deseada)
    * @param Parámetro(s): int requerido, que representa la profundidad a la que se quiere ir
    * @return: retorna valor booleano
    */
    public boolean puedeAcceder(int requerido){ 
        if(requerido > this.profundidadSoportada){
            System.out.println("No se puede acceder la nave no soporta la profundidad");
            return false;
        }
        return true;
    }

    /*
    * Nombre: verInventarioNave
    * Descripción: Imprime el inventario de la nave con los items que hay en la bodega junto con sus respectivas cantidades
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
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

    /*
    * Nombre: fijarAnclaje
    * Descripción: fija el anclaje de la nave en el deseado por el jugador, en caso de que la profundidad de anclaje se salga del margen de la zona se seteara el anclaje en el margen y se pedira al jugador que vuelva a la nave para cambiar de zona
    * @param Parámetro(s): int con el nuevo anclaje y variable del jugador
    * @return: no retoran, es void
    */
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

    /*
    * Nombre: instalarModuloProfundidad
    * Descripción: instala el modulo de profundidad en la nave aumentando la profundidad de esta ultima
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
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
        /*
        * Nombre: aumentarProfundidad 
        * Descripción: setea la profundidad extra del ModuloProfundidad de la nave en 1000 
        * @param Parámetro(s): no recibe
        * @return: no retoran, es void
        */
        public void aumentarProfundidad(){
            this.profundidadExtra = 1000;
        }
    }
}