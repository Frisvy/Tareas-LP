package player;

import java.util.ArrayList;
import java.util.List;
import entorno.Zona;
import objetos.NaveExploradora;
import objetos.Item;
import objetos.AccesoProfundidad;

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
        this.inventario = new ArrayList<>();
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
    public int getProfundidadActual(){
        return profundidadActual;
    }

    public void setZonaActual(Zona zona){
        if(this.puedeAcceder(profundidadActual)){
            this.zonaActual = zona;
        }
        else{
            System.out.println("No se pudo acceder a la Zona";)
        }
    }

//---------------Otros-------------------------

    public void verEstadoJugador(){
        System.out.println("Zona actual: " + this.zonaActual.getNombre() + " | Profundidad (Anclaje, Buzo): "  + this.profundidadActual + " m | Oxigeno: " + this.tanqueOxigeno.getOxigenoRestante());
        System.out.println("1) Subir o descender en profundidad (a nado)");
        System.out.println("2) Explorar");
        System.out.println("3) Recoger recursos");
        System.out.println("4) Volver a la nave");
        System.out.println("5) Ver profundidad actual");
        System.out.println("6) Ver inventario");
        System.out.println("0) Salir");
    }

    public boolean puedeAcceder(int zMax){ //revisar
        if(this.getProfundidadActual() >= zMax){
            return true;
        }
        return false;
    }


}