package entorno;

import java.util.EnumSet;
import player.Jugador;
import objetos.ItemTipo;


public abstract class Zona{
//---------------Atributos-----------------------    
    public String nombre;
    private int profundidadMin;
    private int profundidadMax;
    private EnumSet <ItemTipo> recursos;
    private int nMinRecolectar;
    private int nMaxRecolectar;
    
//---------------Constructores-------------------
    public Zona(String nombre, int profundidadMin, int profundidadMax, int nMinRecolectar, int nMaxRecolectar){
        this.nombre = nombre;
        this.profundidadMin = profundidadMin;
        this.profundidadMax = profundidadMax;
        this.recursos = EnumSet.noneOf(ItemTipo.class);
        this.nMinRecolectar = nMinRecolectar;
        this.nMaxRecolectar = nMaxRecolectar;
    }

    public Zona(String nombre, int profundidadMin, int profundidadMax, int nMinRecolectar, int nMaxRecolectar,ItemTipo... recursosZona){
        this(nombre, profundidadMin, profundidadMax, nMinRecolectar, nMaxRecolectar);
        for (ItemTipo recurso : recursosZona){
            this.recursos.add(recurso);
        }
    }

//---------------Setters y Getters---------------  
    public int getProfundidadMin(){return profundidadMin;}
    public int getProfundidadMax(){return profundidadMax;}
    public String getNombre(){return nombre;}
    public EnumSet <ItemTipo> getRecursos(){return recursos;}
    
//---------------Otros--------------------------- 
    /*
    * Nombre: profundidadNormalizada
    * Descripción: calcula la profundidad normalizada del jugador, profundidad la cual se utiliza en distintas formulas para calcular el oxigeno consumido o los recursos recolectados
    * @param Parámetro(s): variable del jugador
    * @return: double con la profundidad normalizada del jugador
    */
    public double profundidadNormalizada(Jugador jugador){ //utilice math.max para que la formula se vea igual que la de la tarea, y no usar condicionales por casos 
        double profundidadNormalizada = (jugador.getProfundidadActual() - profundidadMin)/Math.max(1.0,(double)(profundidadMax - profundidadMin));
        return profundidadNormalizada; // = d en la tarea
    }
    
    /*
    * Nombre: cantiadRecoleccionExplorar
    * Descripción: retorna la cantidad de recursos que recibira el jugador tras realizar la accion de explorar en una zona determinada
    * @param Parámetro(s): variable del jugador
    * @return: int con la cantidad de objetos a recolectar por el jugador
    */
    public int cantidadRecoleccionExplorar(Jugador jugador){
        double d = this.profundidadNormalizada(jugador);
        double nMin = (double)this.nMinRecolectar;
        double cantidadMaxima = Math.max(1.0, Math.floor(nMin * d));
        
        return (int)cantidadMaxima;
    }

    /*
    * Nombre: produccionPorRecolectar
    * Descripción: determina la cantidad de recursos que se generaran tras realizar la accion de recolectar en una zona determina
    * @param Parámetro(s): variable el jugador
    * @return: retorna un int con  la cantidad de recursos que se generara tras realizar la accion de recolectar
    */
    public int produccionPorRecolectar(Jugador jugador){
        double d = this.profundidadNormalizada(jugador);
        double nMin = (double)this.nMinRecolectar;
        double nMax = (double)this.nMaxRecolectar;
        double cantidadMaxima = Math.max(1.0,Math.floor(nMin + ((nMax - nMin) * d)));

        return (int)cantidadMaxima;
    }

    /*
    * Nombre: entrar 
    * Descripción: realiza el cambio de zona del jugador, para esto hace uso del metodo setProfundidad actual, que a su vez hace uso de la Interfaz AccesoProfundidad para cumplir los requisitos de la tarea, en caso de que el jugador no cumpla con los requisitos de entrada para una zona determinada, los metodos que llama la funcion se encargan de imprimir mensajes de alerta 
    * @param Parámetro(s): variable del jugador
    * @return: no retoran, es void
    */
    public void entrar(Jugador jugador){
        Zona zonaAnterior = jugador.getZonaActual();
        jugador.setZonaActual(this);
        if(jugador.getZonaActual() != zonaAnterior){
            jugador.setProfundidadActual(this.getProfundidadMin());
        }
    }

//metodos abstractos definidos en las distintas zonas
    public abstract void explorar(Jugador jugador);
    public abstract void recolectar(Jugador jugador, ItemTipo recurso);
    
}