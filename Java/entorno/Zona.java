public abstract class Zona{
    private String nombre;
    private int profundidadMin;
    private int profundidadMax;
    private enum ItemTipo{
        cuarzo, silicio, cobre, //zona arrecife
        plata, oro, acero, diamante, magnetita, // zona profunda 
        titanio, sulfuro, uranio, // zona volcanica
        PIEZA_TANQUE, PLANO_NAVE, MODULO_PROFUNDIDAD
    };
}