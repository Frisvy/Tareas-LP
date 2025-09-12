#include <stdio.h>
#include <stdlib.h>
#include "main.h"
#include "entidades.h"

void mover_aliens(struct Juego *juego){ //si los aliens bajan cada 2 turnos, esto implica que en el final del turno 2 movemos al alien. ejecutamos la accion del jugador y despues movemos el alien para evitar conflictos en caso de que llegue a y = 0
    if((juego->turno % 2) != 0){ //si es impar hay q mover el alien, ya que por como implemente la accion del jugador el contador de turnos sube inmediatamente tras el input del jugador
        int nueva_posicion_y;
        int nueva_posicion_x;
        for(int y = 0; y <juego->t->H; y++){
            for(int x = 0; x < juego->t->W; x++){
                Celda *celda = juego->t->celdas[y][x];
                if(celda != NULL && celda->alien != NULL){
                    Alien *alien = celda->alien;
                    if (alien->marca){
                        continue;
                    }
                    nueva_posicion_y = alien->y - 1 ;
                    if(nueva_posicion_y == 0){ // hacer algo pa matar al jugador, o cerrar el juego, piensapiensa
                        juego->continuar = false;
                        return;
                    }
                    if((celda->alien->tipo == 1) || (celda->alien->tipo == 3 )){ // movimiento dron y especial
                        nueva_posicion_x = x;
                        if(juego->t->celdas[nueva_posicion_y][nueva_posicion_x] == NULL || juego->t->celdas[nueva_posicion_y][nueva_posicion_x] != NULL ){
                            juego->t->celdas[nueva_posicion_y][nueva_posicion_x] = celda;
                            juego->t->celdas[y][x] = NULL;
                            alien->y = nueva_posicion_y;
                            alien->marca = true;
                        }
                    }
                    else if(celda->alien->tipo == 2){ //movimiento skater
                        nueva_posicion_x = x + alien->dx;
                        if(nueva_posicion_x >= 0 && nueva_posicion_x < juego->t->W){ // desplazamiento dentro del rango del mapa
                            Celda *celda_derecha = juego->t->celdas[y][nueva_posicion_x]; //celda con el alien de la derecha
                            if(celda_derecha != NULL && celda_derecha->alien != NULL){ //si hay un alien 
                                nueva_posicion_x = x;
                                nueva_posicion_y = alien->y - 1;
                            }
                        }
                        if(nueva_posicion_x < 0 || nueva_posicion_x >= juego->t->W){//si nos salimos del borde del mapa hay que rebotar pal otro lado
                            alien->dx = -alien->dx;
                            nueva_posicion_x = x + alien->dx;
                        }
                        if (nueva_posicion_x >= 0 && nueva_posicion_x < juego->t->W){
                            if (juego->t->celdas[nueva_posicion_y][nueva_posicion_x] == NULL || juego->t->celdas[nueva_posicion_y][x] != NULL){
                                juego->t->celdas[nueva_posicion_y][nueva_posicion_x] = celda;
                                juego->t->celdas[y][x] = NULL; //borramos la celda anterior para que no se duplique el alien
                                alien->x = nueva_posicion_x;
                                alien->y = nueva_posicion_y;
                                alien->marca = true;
                                if (alien->x == 0 || alien->x == juego->t->W - 1) {
                                    alien->dx = -alien->dx;
                                }
                            }
                            else if (juego->t->celdas[nueva_posicion_y][x] == NULL || juego->t->celdas[nueva_posicion_y][x] != NULL)  {
                                juego->t->celdas[nueva_posicion_y][x] = celda;
                                juego->t->celdas[y][x] = NULL;
                                alien->y = nueva_posicion_y;
                                alien->marca = true;
                            }
                        }
                    }   
                }
            }
        }
        for(int y = juego->t->H - 1; y >= 0; y--){ // restablecemos las marcas para cuando volvamos a activar la funcion
            for(int x = 0; x < juego->t->W; x++){
                Celda *celda = juego->t->celdas[y][x];
                if (celda != NULL && celda->alien != NULL){
                    celda->alien->marca = false;
                }
            }
        }        
    }    
}