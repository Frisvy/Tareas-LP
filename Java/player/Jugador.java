package player;

import java.util.ArrayList;
import java.util.List;
import entorno.Zona;
import objetos.NaveExploradora;
import objetos.Item;
import objetos.AccesoProfundidad;
import objetos.ItemTipo;

public class Jugador implements AccesoProfundidad {
//---------------Atributos-----------------------
    private Oxigeno tanqueOxigeno;
    private List<Item> inventario;
    private Zona zonaActual; // solo se puede estar en una zona a la vez
    private int profundidadActual;
    private boolean tienePlanos;
    private NaveExploradora nave;
    private boolean trajeTermico;
    private boolean mejoraTanque; 
    private boolean victoria;
//---------------Constructores-----------------------
    public Jugador(Zona zonaInicial){
        this.tanqueOxigeno = new Oxigeno();
        this.inventario = new ArrayList<Item>();
        this.zonaActual = zonaInicial;
        this.profundidadActual = 0;
        this.tienePlanos = false;
        this.nave = new NaveExploradora();
        this.trajeTermico = false;
        this.mejoraTanque = false;
        this.victoria = false;
        
    }
    public Jugador(Oxigeno tanqueOxigeno, List<Item> inventario, Zona zonaActual, int profundidadActual, boolean tienePlanos, NaveExploradora nave, boolean trajeTermico, boolean mejorarTanque){
        this.tanqueOxigeno = tanqueOxigeno;
        this.inventario = inventario;
        this.zonaActual = zonaActual;
        this.profundidadActual = profundidadActual;
        this.tienePlanos = tienePlanos;
        this.nave = nave;
        this.trajeTermico = trajeTermico;
        this.mejoraTanque = mejorarTanque;
        this.victoria = false;
    }
    
//---------------Setters y Getters-----------------------    
    public int getProfundidadActual(){return profundidadActual;}
    public Oxigeno getTanqueOxigeno(){return tanqueOxigeno;}
    public Zona getZonaActual(){return zonaActual;}
    public List<Item> getInventario(){return inventario;}
    public NaveExploradora getNave(){return nave;}
    public boolean getTienePlanos(){return tienePlanos;}
    public boolean getTrajeTermico(){return trajeTermico;}
    public boolean getMejoraTanque(){return mejoraTanque;}
    public boolean getVictoria(){return victoria;}
    public void setProfundidadActual(int nuevaProfundidad){this.profundidadActual = nuevaProfundidad;}
    
    /*
    * Nombre: setZonaActual
    * Descripción: Recibe una zona y determina si el jugador puede acceder o no a ella segun los criterios establecidos en la interfaz puedeAcceder, en caso de que el jugador pueda acceder, setea el jugador en la nueva zona
    * @param Parámetro(s): variable de tipo Zona con la zona a la cual se quiere acceder
    * @return: no retorna, es void
    */
    public void setZonaActual(Zona zona){ 
        if(this.puedeAcceder(zona.getProfundidadMin())){
            this.zonaActual = zona;
        }
        else{
            System.out.println("No se pudo acceder a la Zona, faltan mejoras clave");
        }
    }

//---------------Otros-------------------------
    
    /*
    * Nombre: puedeAcceder
    * Descripción: Dada la profundidad minima de una zona retorna true en caso de que el jugador cumpla con las mejoras necesarias que requiera la zona a la hora de querer entrar, en caso contrario retorna false
    * @param Parámetro(s): int requerido, representa la profundidad minima de la nueva zona 
    * @return: retorna un valor booleano
    */
    public boolean puedeAcceder(int requerido){ //profundidad minima de la nueva zona
        if(requerido >= 1000){ //para entrar a la zona volcanica
            return mejoraTanque && trajeTermico && nave.getModuloInstalado(); // si no hay traje, tanque y modulo profundidad retorna false, si estan va a ser true
        }
        return true;
    }  

    /*
    * Nombre: encontrarPlanos
    * Descripción: cambia el estado de la variable tienePlanos de false a true, se llama una vez se encuentran los planos
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void encontrarPlanos(){
        this.tienePlanos = true;
    }

    
    /*
    * Nombre: agregraAlInventario
    * Descripción: recibe un item junto con la cantidad que se quiere añadir al inventario del jugador, en caso de que el objeto no se encuentre con anterioridad en el inventario, se crea una nueva instancia en la lista del iventario
    * @param Parámetro(s): variable ItemTipo con e tipo del item a añadir, y int cantidad con la cantidad a añadir
    * @return: no retoran, es void
    */
    public void agregarAlInventario(ItemTipo tipo, int cantidad){
        if(cantidad > 0){
            for(Item elemento : inventario){
                if(elemento.getTipo() == tipo){
                    elemento.setCantidad(cantidad + elemento.getCantidad());
                    return; //para que en caso de que si exista el elemento nos saltemos la linea de abajo y no tengamos repetidos en la lista
                }
            }
            inventario.add(new Item(tipo, cantidad)); //en caso de que no tengamos ninguna unidad del itme en el inventario
        }
    }

    /*
    * Nombre: vaciarInventario
    * Descripción: Vacia el inventario del jugador en caso de que este muera, pero no elimina los objetos clave como Modulo profundidad para no arruinar la progresion del juego
    * @param Parámetro(s): no recive
    * @return: no retoran, es void
    */
    public void vaciarInventario(){
        int cantidadModuloProfundidad = 0;
        int cantidadPiezaTanque = 0;
        int cantidadPlanoNave = 0;
        //en caso de que hayan objetos clave guardamos la cantidad para que no se borren si el jugador se desmaya y asi no arruinar la progresion del juego
        for(Item elemento : inventario){
            if(elemento.getTipo() == ItemTipo.MODULO_PROFUNDIDAD){
                cantidadModuloProfundidad = elemento.getCantidad();
            }
            else if(elemento.getTipo() == ItemTipo.PIEZA_TANQUE){
                cantidadPiezaTanque = elemento.getCantidad();
            }
            else if(elemento.getTipo() == ItemTipo.PLANO_NAVE){
                cantidadPlanoNave = elemento.getCantidad();
            }
        }
        if(inventario != null){
            inventario.clear();
            System.out.println("Se a vaciado el inventario del jugador");
        }
        this.agregarAlInventario(ItemTipo.MODULO_PROFUNDIDAD, cantidadModuloProfundidad);
        this.agregarAlInventario(ItemTipo.PIEZA_TANQUE, cantidadPiezaTanque);
        this.agregarAlInventario(ItemTipo.PLANO_NAVE, cantidadPlanoNave);
    }

    /*
    * Nombre: consumirItem
    * Descripción: consume el item que se entregue junto con la cantidad deseada a consumir en caso de que hayan suficientes unidades de dicho objeto, en caso de que no hayan suficientes unidades imprime un mensaje de error (los objetos se eliminan de la bodega, no del inventario del jugador)
    * @param Parámetro(s): ItemTipo con el tipo del objeto y int cantidad con la cantidad a eliminar
    * @return: no retoran, es void
    */
    public void consumirItem(ItemTipo tipo, int cantidad){
        for(Item elemento : this.getNave().getBodega()){
            if(elemento.getTipo() == tipo){
                if(elemento.getCantidad() >= cantidad){
                    elemento.setCantidad(elemento.getCantidad() - cantidad);
                    return; //se desconto el elemento
                }
            }
        }
        System.out.println("No tienes suficiente " + tipo);
    }

    /*
    * Nombre: mejorarTanque
    * Descripción: aplica las mejoras del tanque en caso de que se cuenten con los materiales necesarios para mejorarlo, en caso de que no se encuentren los recursos necesarios para mejorar el tanque, la  funcion de consumirItem se encarga de imprimir el mensaje de error
    * @param Parámetro(s): no recive
    * @return: no retoran, es void
    */
    public void mejorarTanque(){
        for(Item elemento : this.getNave().getBodega()){
            if(elemento.getTipo() == ItemTipo.PIEZA_TANQUE){
                if(elemento.getCantidad() == 3){
                    this.mejoraTanque = true; //la presion ya no debe afectar
                    this.consumirItem(ItemTipo.PIEZA_TANQUE, 3);
                    System.out.println("Tanque mejorado");
                    this.getTanqueOxigeno().aplicarMejoraTanque(); //aumento de capacidad
                    this.getTanqueOxigeno().recargarCompleto();//recarga del tanque a 120
                    return;
                }
            }
        }
        this.consumirItem(ItemTipo.PIEZA_TANQUE, 3);
    }

    /*
    * Nombre: mejorarOxigeno
    * Descripción: aplica la mejora de oxigeno en caso de que se cuente con los recursos necesarios en la bodega de la nave
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void mejorarOxigeno(){
        int cantidadPlata = 0;
        int cantidadCuarzo = 0;
        for(Item elemento : this.getNave().getBodega()){
            if(elemento.getTipo() == ItemTipo.plata){
                cantidadPlata = elemento.getCantidad();
            }
            if(elemento.getTipo() == ItemTipo.cuarzo){
                cantidadCuarzo = elemento.getCantidad();
            }
        }
        if(cantidadPlata >= 10 && cantidadCuarzo >= 15){
            this.consumirItem(ItemTipo.plata, 10);
            this.consumirItem(ItemTipo.cuarzo, 15);
            this.getTanqueOxigeno().aplicarMejoraOxigeno();
            System.out.println("Se mejoro en 30 el oxigeno");
            return;
        }
        System.out.println("No tienes suficientes recursos para aplicar la mejora");
    }

    /*
    * Nombre: crearTrajeTermico
    * Descripción: crea el traje termico en caso de que l jugador cuente con los recursos necesarios para su fabricacion. Tambien setea la variable de Traje termico a true
    * @param Parámetro(s):no recibe 
    * @return: no retoran, es void
    */
    public void crearTrajeTermico(){
        int cantidadSilicio = 0;
        int cantidadOro = 0;
        int cantidadCuarzo = 0;
        for(Item elemento : this.getNave().getBodega()){
            if(elemento.getTipo() == ItemTipo.silicio){
                cantidadSilicio = elemento.getCantidad();
            }
            if(elemento.getTipo() == ItemTipo.oro){
                cantidadOro = elemento.getCantidad();
            }
            if(elemento.getTipo() == ItemTipo.cuarzo){
                cantidadCuarzo = elemento.getCantidad();
            }
        }
        if(cantidadSilicio >= 10 && cantidadCuarzo >= 5 && cantidadOro >= 3){
            this.consumirItem(ItemTipo.silicio, 10);
            this.consumirItem(ItemTipo.cuarzo, 5);
            this.consumirItem(ItemTipo.oro, 3);
            this.trajeTermico = true;
            System.out.println("Se creo el traje termico");
            return;
        }
        System.out.println("Faltan recursos para crear el traje termico");
    }   

    /*
    * Nombre: instalarModuloProfundidad
    * Descripción: instala el modulo profundidad a la nave, y consume los objetos claves para eso, ademas de eso llama al metodo de instalarModuloProfundidad desde la naveExploradora para aumentar la profundidad maxima de la nave en 1000 unidades adiconales
    * @param Parámetro(s): no tiene
    * @return: no retoran, es void
    */
    public void instalarModuloProfundidad(){
        int cantidadModuloProfundidad = 0;
        for(Item elemento : this.getNave().getBodega()){
            if(elemento.getTipo() == ItemTipo.MODULO_PROFUNDIDAD){
                cantidadModuloProfundidad = elemento.getCantidad();
            }
        }
        if(cantidadModuloProfundidad >= 1){
            this.consumirItem(ItemTipo.MODULO_PROFUNDIDAD, 1);
            this.getNave().instalarModuloProfundidad();
            System.out.println("Se instalo el modulo profundidad");
            return;
        }
        System.out.println("Necesitas encontrar el modulo profundidad y depositarlo en la bodega para poder instalarlo");
    }

    /*
    * Nombre: repararNaveEstrellada
    * Descripción: en caso de que se cuenten con los recursos necesarios se "repara la nave" seteando la variable de victoria en true, lo cual corta todos los bucles del main y imprime el mensaje de victoria, en caso de que no se cumpla alguno de los requisitos establecidos para reparar la nave se imprime un mensaje indicando el error
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void repararNaveEstrellada(){
        if(this.getZonaActual().getNombre() != "Nave Estrellada"){
            System.out.println("Necesitas estar en la Nave estrellada para repararla");
            return;
        }
        if(!this.getTienePlanos()){
            System.out.println("Necesitas los planos de la nave para repararla");
            return;
        }
        int cantidadTitanio = 0;
        int cantidadAcero = 0;
        int cantidadUranio = 0;
        int cantidadSulfuro = 0;
        for(Item elemento : this.getNave().getBodega()){
            if(elemento.getTipo() == ItemTipo.titanio){
                cantidadTitanio = elemento.getCantidad();
            }
            if(elemento.getTipo() == ItemTipo.acero){
                cantidadAcero = elemento.getCantidad();
            }
            if(elemento.getTipo() == ItemTipo.uranio){
                cantidadUranio = elemento.getCantidad();
            }
            if(elemento.getTipo() == ItemTipo.sulfuro){
                cantidadSulfuro = elemento.getCantidad();
            }
        }
        if(cantidadTitanio >= 50 && cantidadAcero >= 30 && cantidadUranio >= 15 && cantidadSulfuro >= 20){
            this.consumirItem(ItemTipo.titanio, 50);
            this.consumirItem(ItemTipo.acero, 30);
            this.consumirItem(ItemTipo.uranio, 15);
            this.consumirItem(ItemTipo.sulfuro, 20);
            System.out.println("Se reparo la naveeee shiiiiiiiiiii");
            this.victoria = true; // se gana papus
            return;
        }
        System.out.println("Faltan recursos para reparar la nave");

    }

    /*
    * Nombre: verEstadoJugador
    * Descripción: imprime el menu de seleccion de acciones, junto con el estado del jugador, da detalles del oxigeno, zona actual, profundidad, mejoras , entre otros
    * @param Parámetro(s): no recibe 
    * @return: no retoran, es void
    */
    public void verEstadoJugador(){
        System.out.println("==== Subnautica ====");
        System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): (" + this.getNave().getProfundidadAnclaje() + " ," + this.profundidadActual + ") m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante() + " | Traje Termico: " + getTrajeTermico() + " | Mejora Tanque: " + getMejoraTanque() + " | Modulo Profundidad: " + this.getNave().getModuloInstalado());
        System.out.println("1) Subir o descender en profundidad (a nado)");
        System.out.println("2) Explorar");
        System.out.println("3) Recoger recursos");
        System.out.println("4) Volver a la nave");
        System.out.println("5) Ver inventario");
        System.out.println("0) Salir");
    }

    /*
    * Nombre: verMenuNave
    * Descripción: imprime el menu de seleccion de accion de la nave junto con ciertos detalles del estado del jugador
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void verMenuNave(){
        this.setProfundidadActual(this.getNave().getProfundidadAnclaje());
        System.out.println("==== Subnautica ====");
        System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): (" + this.getNave().getProfundidadAnclaje() + " ," + this.profundidadActual + ") m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante());
        System.out.println("1) Ajustar anclaje de nave");
        System.out.println("2) Guardar TODOS los objetos del jugador en la nave");
        System.out.println("3) Crear objetos");
        System.out.println("4) Moverse a otra Zona");
        System.out.println("5) Ver inventario de la nave");
        System.out.println("0) Salir de la nave");
    }

    /*
    * Nombre: verMenuCreacion
    * Descripción: imprime el menu de creacion de objetos
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void verMenuCreacion(){
        System.out.println("==== Subnautica ====");
        System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): (" + this.getNave().getProfundidadAnclaje() + " ," + this.profundidadActual + ") m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante());
        System.out.println("1) Mejora de tanque");
        System.out.println("2) Mejora de oxigeno");
        System.out.println("3) Traje termico");
        System.out.println("4) Instalar Modulo de profundidad");
        System.out.println("5) Crear robot");
        System.out.println("6) Mejorar capacidad de carga (Robot)");
        System.out.println("7) Reparar robot");
        System.out.println("8) Reparar Nave estrellada"); //se debe estar en nave estrellada
    }

    /*
    * Nombre: verMenuZonas
    * Descripción: imprime el menu de eleccion de zonas
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void verMenuZonas(){
        System.out.println("==== Subnautica ====");
        System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): (" + this.getNave().getProfundidadAnclaje() + " ," + this.profundidadActual + ") m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante());
        System.out.println("1) Zona Arrecife");
        System.out.println("2) Zona Profunda");
        System.out.println("3) Zona Volcanica");
        System.out.println("4) Nave Estrellada");
    }

    /*
    * Nombre: verMenuRecolectar
    * Descripción: imprime el menu de recursos que se pueden recolectar segun la zona en la que se encuentra actualmente el jugador
    * @param Parámetro(s): recibe la variable del Jugador
    * @return: no retoran, es void
    */
    public void verMenuRecolectar(Jugador jugador){
        System.out.println("==== Subnautica ====");
        if(jugador.getZonaActual().getNombre() == "Zona Arrecife"){
            System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): (" + this.getNave().getProfundidadAnclaje() + " ," + this.profundidadActual + ") m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante());
            System.out.println("1) Cuarzo");
            System.out.println("2) Silicio");
            System.out.println("3) Cobre");
        }
        else if(jugador.getZonaActual().getNombre() == "Zona Profunda"){
            System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): (" + this.getNave().getProfundidadAnclaje() + " ," + this.profundidadActual + ") m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante());
            System.out.println("1) Plata");
            System.out.println("2) Oro");
            System.out.println("3) Acero");
            System.out.println("4) Diamante");
            System.out.println("5) Magnetita");
        }
        else if(jugador.getZonaActual().getNombre() == "Zona Volcanica"){
            System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): (" + this.getNave().getProfundidadAnclaje() + " ," + this.profundidadActual + ") m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante());
            System.out.println("1) Titanio");
            System.out.println("2) Sulfuro");
            System.out.println("3) Uranio");
        }
        else if(jugador.getZonaActual().getNombre() == "Nave Estrellada"){
            System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): (" + this.getNave().getProfundidadAnclaje() + " ," + this.profundidadActual + ") m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante());
            System.out.println("1) Cables");
            System.out.println("2) Piezas de metal");
        }
    }

    /*
    * Nombre: verInventarioJugador
    * Descripción: Imprime el inventario del jugador en la consola señalando los items con su respectiva cantidad almacenada
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void verInventarioJugador(){
        System.out.println("----Inventario Jugador----");
        if(inventario.isEmpty()){
            System.out.println("No tienes nada en el inventario");
        }
        for(Item objeto : inventario){
            if(objeto.getCantidad() > 0){
                System.out.println(objeto.getCantidad() +" de " + objeto.getTipo());
            }
        }
    }

    /*
    * Nombre: oxigenoMoverJugador
    * Descripción: calcula la cantidad de oxigeno que consume mover al jugador desde su profundidad actual, hasta una profundidad determinada
    * @param Parámetro(s): un int con la nueva profundidad a la que se quiere mover al jugador
    * @return: retorna las unidades de oxigeno que se deben consumir tras mover al jugador
    */
    public int oxigenoMoverJugador(int nuevaProfundidad){
        double profundidadNormalizada = this.zonaActual.profundidadNormalizada(this);
        double deltaZ = Math.abs(this.profundidadActual - nuevaProfundidad);
        double calculoOxigeno = Math.ceil((3+(3*profundidadNormalizada))*(deltaZ/50));

        return (int)calculoOxigeno;
    }

    /*
    * Nombre: subirDescender
    * Descripción: recibe la nueva profundidad a la que se quiere mover el jugador, y llama a oxigenoMoverJugador para calcular la cantidad de oxigeno a consumir y lo consume, ademas de esto impide el cambio de zona a nado y suguiere volver a la nave para cambiar de zona
    * @param Parámetro(s): un int con la nueva profundidad a la que se quiere mover el jugador
    * @return: no retoran, es void
    */
    public void subirDescender(int nuevaProfundidad){  //actualiza profundidad actual y consume oxigeno
        if(nuevaProfundidad > this.getZonaActual().getProfundidadMax()){//si intenta cambiar de zona a nado lo dejamos en la profundidad maxima permitida pa q se vaya a la nave el jugador
            System.out.println("Para cambiar de zona vuelve a la nave");
            int costeOxigeno = this.oxigenoMoverJugador(this.getZonaActual().getProfundidadMax());
            this.getTanqueOxigeno().consumirO2(costeOxigeno);
            this.profundidadActual = this.getZonaActual().getProfundidadMax();
            return;
        }
        else if(nuevaProfundidad < this.getZonaActual().getProfundidadMin()){
            System.out.println("Para cambiar de zona vuelve a la nave");
            int costeOxigeno = this.oxigenoMoverJugador(this.getZonaActual().getProfundidadMin());
            this.getTanqueOxigeno().consumirO2(costeOxigeno);
            this.profundidadActual = this.getZonaActual().getProfundidadMin();
            return;
        }
        int costeOxigeno = this.oxigenoMoverJugador(nuevaProfundidad);
        this.getTanqueOxigeno().consumirO2(costeOxigeno);
        this.profundidadActual = nuevaProfundidad;
    }

    /*
    * Nombre: derrotado
    * Descripción: en caso de que el jugador sufra la condicion de derrota se vacia su inventario con el metodo vaciarInventario y se setea su nueva profundidad en la del anclaje de la nave(es decir vuelve a la nave) y imprime un mensaje explicando que el jugador quedo inconciente y volvio a la nave
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void derrotado(){
        this.vaciarInventario();
        this.profundidadActual = this.getNave().getProfundidadAnclaje();
        System.out.println("Has quedado inconsciente, volviste a la nave y perdiste todo tu inventario");
        this.getTanqueOxigeno().recargarCompleto();
    }

}