#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "main.h"
#include "entidades.h"


int coordenada_spawn(struct Juego *juego){ //determina una coordenada aleatoria donde spawneara el siguiente alien, en caso de que no haya espacio suficiente para que spawnee el alien retorna -1
    int y = juego->t->H - 1;
    int coordenadas_libres[juego->t->W];
    int n = 0;
    int coordenada_x;
    for(int x = 0; x < juego->t->W; x++){
        if(juego->t->celdas[y][x] == NULL){
            coordenadas_libres[n] = x;
            n++;
        }
    }
    if(n == 0){
        return -1;
    }
    else{
        coordenada_x = coordenadas_libres[rand() % n];
    }
    return coordenada_x;
}

int alien_a_spawnear(struct Juego *juego){ //determina aleatoriamente el tipo de alien que spawneara
    int probabilidad = rand() % 100;
    int tipo_alien;
    if(probabilidad < 40){
        tipo_alien = 1; // dron
    }
    else if(probabilidad < 70){
        tipo_alien = 2; //skater
    }
    else{
        tipo_alien = 3;
    }
    return tipo_alien;
}
void meter_alien(struct Juego *juego, int tipo_alien){ //crea el alien y lo inserta en la celda correspondiente
    /*if(juego->vivos >= juego->pool.vivos_tope){
        break;
    }*/ //colocar eso despues en el main
    int y = juego->t->H - 1;
    int x = coordenada_spawn(juego);
    Alien *alienigena = malloc(sizeof (Alien));
    alienigena->tipo = tipo_alien;
    if (alienigena->tipo == 1){ // dron
        alienigena->hp = 2;
        alienigena->x = x;
        alienigena->y = y;
        alienigena->dx = 0;
    } 
    else if (alienigena->tipo == 2){ // skater
        alienigena->hp = 1;
        alienigena->x = x;
        alienigena->y = y;
        alienigena->dx = 1;
    }
    else{ //especial
        alienigena->hp = 1;
        alienigena->x = x;
        alienigena->y = y;
        alienigena->dx = 0;
    }
    Celda *celda = malloc(sizeof (Celda));
    celda->alien = alienigena;
    celda->dano_pend = 0;
    juego->t->celdas[y][x] = (void*)celda;
    juego->vivos++;
    //juego->pool.restantes--;
    if (tipo_alien == 1){
        juego->pool.drone--;
    }
    else if (tipo_alien == 2){
        juego->pool.skater--;
    }
    else if(tipo_alien == 3){
        juego->pool.especial--;
    }
}


void spawn_inicio(struct Juego *juego){
    int aliens_iniciales = 0;
    if(juego->dificultad == 1){ // facil
        juego->pool.vivos_tope = 6;
        juego->pool.drone = 6;
        juego->pool.skater = 4;
        juego->pool.restantes = 15; //aliens a derrotar para ganar el juego
        juego->pool.aliens_randoms = 5; //aliens de cualquier tipo
        while (aliens_iniciales < 2){
            int alienigena = alien_a_spawnear(juego);
            meter_alien(juego, alienigena);
            aliens_iniciales++;
        }
    }
    else if (juego->dificultad == 2){ // dificil
        juego->pool.vivos_tope = 8;
        juego->pool.drone = 8;
        juego->pool.skater = 6;
        juego->pool.restantes = 20;
        juego->pool.aliens_randoms = 6;
        while (aliens_iniciales < 3){
            int alienigena = alien_a_spawnear(juego);
            meter_alien(juego, alienigena);
            aliens_iniciales++;
        }
    }
}
void spawn_turno(Juego *juego){ //meter en el main un rand() para que si sale 30 y hay espacio se llame denuevo a spawn turno
    if(juego->vivos < juego->pool.vivos_tope){
        if(juego->pool.drone > 0  || juego->pool.skater > 0 || juego->pool.especial > 0 ){
            int tipo_alien_spawn = alien_a_spawnear(juego);
            int coordenada = coordenada_spawn(juego);
            if(coordenada != -1){
                meter_alien(juego, tipo_alien_spawn);
                if (tipo_alien_spawn == 1){
                    juego->pool.drone - 1;
                }
                else if(tipo_alien_spawn == 2){
                    juego->pool.skater - 1;
                }
                else if(tipo_alien_spawn == 3){
                    juego->pool.especial - 1;
                }
            }
        }
    }
}
