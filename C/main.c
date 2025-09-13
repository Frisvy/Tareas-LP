#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>
#include "main.h"

char pedir_accion(){
    char accion;
    scanf(" %c", &accion);
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
        else if(juego->dificultad == 2){
            if((juego->jugador_x + 1) > 6){
                printf("Accion invalida\n");
                validez = false;
            }
        }
    }
    else if(accion == 'h'){
        printf("Mata a todos los aliens antes de que lleguen a la ultima casilla para ganar, recuerda los controles.\n");
        printf("Controles: a/d mover | 1=NORMAL 2=PERFORADOR 3=ESPECIAL | q salir | help ayuda\n");
        printf("¡Buena Suerte!\n");
        validez = false;
    }
    else if(accion == '1' || accion == '2' || accion == '3'){
        validez = true;
    }
    else{
        printf("Accion invalida\n");
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
    }
    else if(accion == '1' || accion ==  '2' || accion == '3'){
        int id = accion - '1';
        bool disparo = disparar_armas(juego, id);
        if(disparo){ // si no se pudo disparar el arma por q falto municion no se suma turno
            juego->turno = juego->turno + 1; 
        }
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
    juego->vivos = 0;
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
    juego->armas.fn[0] = arma_normal;
    juego->armas.fn[1] = arma_perforador;
    juego->armas.fn[2] = arma_especial;

    spawn_inicio(juego); //spawn de los primeros enemigos
    do{
        probabilidad = rand() % 100;
        tablero_imprimir(juego);   
        accion = pedir_accion();
        validez_accion = validar_accion(juego, accion);
        if(!validez_accion){
            continue;
        }
        int turno_anterior = juego->turno; //para ver si cambio el turno o no
        ejecutar_accion(juego, accion);
        if(juego->turno == turno_anterior){ 
            continue;
        }
        resolver_danos(juego);
        if(juego->pool.restantes == 0){
            printf("HAS MATADO A TODOS LOS ALIENS\n");
            printf("FELICIDADES GANASTE\n");
            break;
        }
        mover_aliens(juego);
        spawn_turno(juego);
        if(probabilidad <=30){
            spawn_turno(juego);
        }
        if(!juego->continuar){
            break;
        }
    } while (accion != 'q');
    tablero_cerrar(juego->t);
    free(juego);
    return 0;
}
//HACER Q LAS ACCIONES INVALIDAS NO CONSUMAN 
//sdadasd