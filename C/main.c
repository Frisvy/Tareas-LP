#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>
#include "main.h"

char pedir_accion(){
    char accion;
    scanf("%c", &accion);
    return accion;
}

bool validar_accion(struct Juego *juego, char accion){
    bool validez = true;
    if(accion == 'a'){
        if((juego->jugador_x - 1)< 0){
            printf("Accion invalida\n");
            validez = false;
        }
    }
    else if(accion == 'd'){
        if(juego->dificultad == 1){
            if((juego->jugador_x + 1) > 4){
                printf("Accion invalida\n");
                validez = false;
            }
        }
    }
    else if(juego->dificultad == 2){
        if((juego->jugador_x + 1) > 6){
            printf("Accion invalida\n");
            validez = false;
        }
    }
    else if(accion == 'h'){
        printf("Mata a todos los aliens antes de que lleguen a la ultima casilla para ganar, recuerda los controles.\n");
        printf("Controles: a/d mover | 1=NORMAL 2=PERFORADOR 3=ESPECIAL | q salir | help ayuda\n");
        printf("¡Buena Suerte!\n");
        validez = false;
    }
    else{
        validez = false;
    }
    return validez;    
}

void ejecutar_accion(struct Juego *juego, char accion){
    if(accion == 'a'){
            juego->jugador_x = juego->jugador_x - 1;
            juego->turno = juego->turno + 1;
        }
    else if(accion == 'd'){
        juego->jugador_x = juego->jugador_x + 1;
        juego->turno = juego->turno + 1;
    }
    else if(accion == 'h'){
        printf("Mata a todos los aliens antes de que lleguen a la ultima casilla para ganar, recuerda los controles.\n");
        printf("Controles: a/d mover | 1=NORMAL 2=PERFORADOR 3=ESPECIAL | q salir | help ayuda\n");
        printf("¡Buena Suerte!\n");
    } 
}

char accion; // variable global 

int main(){
    srand(time(NULL));
    Juego* juego = malloc(sizeof (Juego));
    juego->turno = 1;
    juego->continuar = true;
    int probabilidad;
    bool validez_accion;
    do{
        menu_inicio(juego);
        if(juego->dificultad == 1){ //dificultad facil
            juego->t = tablero_crear(5,16); //16 para que el jugador este en y=0, y hayan 15 casillas para mover aliens
            juego->pool.restantes = 15;
        }
        else if (juego->dificultad == 2){ // dificultad dificil
            juego->t = tablero_crear(7,16);
            juego->pool.restantes = 20;
        }
        else {
            limpiar_consola();
            printf("Selecciona una opcion valida papu :3\n");
        }
    } while ((juego->dificultad != 1) && (juego->dificultad != 2));
    
    spawn_inicio(juego); //spawn de los primeros enemigos
    do{
        probabilidad = rand() % 100;
        tablero_imprimir(juego);   
        accion = pedir_accion();
        validez_accion = validar_accion(juego, accion);
        if(validez_accion){
            ejecutar_accion(juego,accion);
            mover_aliens(juego);
            spawn_turno(juego);
            if(probabilidad < 30){
                spawn_turno(juego);
            }
        }
    } while (accion != 'q');
    
    return(0);
}
//HACER Q LAS ACCIONES INVALIDAS NO CONSUMAN