#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "main.h"




int main(){
    srand(time(NULL));
    Juego* juego = malloc(sizeof (Juego));
    do
    {
        menu_inicio(juego);
        if(juego->dificultad == 1){ //dificultad facil
            juego->t = tablero_crear(5,15);
            juego->pool.restantes = 15;
        }
        
        else if (juego->dificultad == 2){ // dificultad dificil
            juego->t = tablero_crear(7,15);
            juego->pool.restantes = 20;
        }
        else {
            limpiar_consola();
            printf("Selecciona una opcion valida papu :3\n");
        
        }
    
    } while ((juego->dificultad != 1) && (juego->dificultad != 2));
    
    spawn_inicio(juego);
    limpiar_consola();
    tablero_imprimir(juego);
    
    
    return(0);
}