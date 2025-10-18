package player;



public class Oxigeno{
    
//---------------Atributos-----------------------    
    private int oxigenoRestante;
    private int capacidadMaxima;

//---------------Constructores-------------------
    public Oxigeno(){
        this.oxigenoRestante = 60;
        this.capacidadMaxima = 60;
    }

//---------------Setters y Getters---------------     
    public int getOxigenoRestante(){return oxigenoRestante;}

//---------------Otros---------------------------  
    /*
    * Nombre: ConsumirO2 
    * Descripción: Consume la cantidad de oxigeno que se indique, restandolo del atributo de oxigeno restante
    * @param Parámetro(s): int unidades, indica la cantidad de unidades de oxigeno a consumir
    * @return: no retoran, es void
    */
    public void consumirO2(int unidades){
        this.oxigenoRestante = oxigenoRestante - unidades;
        System.out.println("Se comsumio " + unidades + " de O2");
        if(this.oxigenoRestante <= 0){
            System.out.println("muelto");
            this.oxigenoRestante = 0;
        }
    }


    /*
    * Nombre: recargarCompleto
    * Descripción: Restablece la cantidad de oxigeno restante a la cantidad maxima del tanque
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void recargarCompleto(){
        this.oxigenoRestante = capacidadMaxima;
        System.out.println("Se a restablecido el O2");
    }

    /*
    * Nombre: aplicarMejoraTanque
    * Descripción: aplica la mejora del tanque, aumentando la capacidad a el doble, osea a 120 unidades ya que es la primera mejora que se aplica al tanque y la cantidad de partida es 60
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */
    public void aplicarMejoraTanque(){
        this.capacidadMaxima = capacidadMaxima*2;
    }

    /*
    * Nombre: aplicarMejoraOxigeno
    * Descripción: Aplica la mejora correspondiente de oxigeno, aumentando la capacidad del tanque en 30, esta funcion no se encarga del consumo de recursos de la mejora
    * @param Parámetro(s): no recibe
    * @return: no retoran, es void
    */

    public void aplicarMejoraOxigeno(){
        this.capacidadMaxima = capacidadMaxima + 30;
        this.recargarCompleto();
    }
}