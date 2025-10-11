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
    
    public void setZonaActual(Zona zona){ // revisar correcta implementacion junto a la interfaz
        if(this.puedeAcceder(zona.getProfundidadMin())){
            this.zonaActual = zona;
        }
        else{
            System.out.println("No se pudo acceder a la Zona, faltan mejoras clave");
        }
    }

//---------------Otros-------------------------
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
  
    public void verEstadoJugador(){
        System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): (" + this.getNave().getProfundidadAnclaje() + " ," + this.profundidadActual + ") m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante());
        System.out.println("1) Subir o descender en profundidad (a nado)");
        System.out.println("2) Explorar");
        System.out.println("3) Recoger recursos");
        System.out.println("4) Volver a la nave");
        System.out.println("5) Ver profundidad actual");
        System.out.println("6) Ver inventario");
        System.out.println("0) Salir");
    }

    public void verMenuNave(){
        System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): (" + this.getNave().getProfundidadAnclaje() + " ," + this.profundidadActual + ") m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante());
        System.out.println("1) Ajustar anclaje de nave");
        System.out.println("2) Guardar TODOS los objetos del jugador en la nave");
        System.out.println("3) Crear objetos");
        System.out.println("4) Moverse a otra Zona");
        System.out.println("5) Ver inventario de la nave");
        System.out.println("0) Salir de la nave");
    }
    public void verMenuZonas(){
        System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): (" + this.getNave().getProfundidadAnclaje() + " ," + this.profundidadActual + ") m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante());
        System.out.println("1) Zona Arrecife");
        System.out.println("2) Zona Profunda");
        System.out.println("3) Zona Volcanica");
        System.out.println("4) Nave Estrellada");
    }

    public int oxigenoMoverJugador(int nuevaProfundidad){
        double profundidadNormalizada = this.zonaActual.profundidadNormalizada(this);
        double deltaZ = Math.abs(this.profundidadActual - nuevaProfundidad);
        double calculoOxigeno = Math.ceil((3+(3*profundidadNormalizada))*(deltaZ/50));

        return (int)calculoOxigeno;
    }

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


    public boolean puedeAcceder(int requerido){ //profundidad minima de la nueva zona
        if(requerido >= 1000){ //para entrar a la zona volcanica
            return mejoraTanque && trajeTermico; // si no hay traje y tanque retorna false, si estan va a ser true
        }
        return true;
    }


}