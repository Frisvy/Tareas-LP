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
    int y = juego->t->H - 1;
    int x = coordenada_spawn(juego);
    if(x == -1){
        return;
    }
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
        if(juego->pool.drone > 0){
            juego->pool.drone--;
        }
        
    }
    else if (tipo_alien == 2){
        if(juego->pool.skater > 0){
            juego->pool.skater--;
        } 
    }
    else if(tipo_alien == 3){
        if(juego->pool.especial > 0){
            juego->pool.especial--;
        } 
    }
}


void spawn_inicio(struct Juego *juego){
    int aliens_iniciales = 0;
    if(juego->dificultad == 1){ // facil
        juego->pool.vivos_tope = 6;
        juego->pool.drone = 6;
        juego->pool.skater = 4;
        juego->pool.especial = 0;
        juego->pool.restantes = 15; //aliens a derrotar para ganar el juego
        juego->pool.aliens_randoms = 5; //aliens de cualquier tipo
        juego->armas.ammo_especial = 3;
        juego->armas.ammo_perforador = 7;
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
        juego->pool.especial = 0;
        juego->pool.aliens_randoms = 6;
        juego->armas.ammo_especial = 1; //solo un especial en dificil, para q sea.... mas dificilxd
        juego->armas.ammo_perforador = 7;
        while (aliens_iniciales < 3){
            int alienigena = alien_a_spawnear(juego);
            meter_alien(juego, alienigena);
            aliens_iniciales++;
        }
    }
}
void spawn_turno(Juego *juego){ //meter en el main un rand() para que si sale 30 y hay espacio se llame denuevo a spawn turno
    if(juego->vivos < juego->pool.vivos_tope){
        if(juego->vivos == juego->pool.restantes){
            return;
        }
        if(juego->pool.drone > 0  || juego->pool.skater > 0 || juego->pool.especial > 0 ){
            int tipo_alien_spawn = alien_a_spawnear(juego);
            int coordenada = coordenada_spawn(juego);
            if(coordenada != -1){
                meter_alien(juego, tipo_alien_spawn);
            }
        }
        else if(juego->pool.aliens_randoms > 0){
            int tipo_alien_spawn = alien_a_spawnear(juego);
            int coordenada = coordenada_spawn(juego);
            if(coordenada != -1){
                meter_alien(juego, tipo_alien_spawn);
            }
        }
    }
}
// CONFIGURAR EL SPAWN DE LOS ALIENS RANDOMS
// CONFIGURAR MECANICA ALIEN ESPECIAL