#ifndef SPAWN_H
#define SPAWN_H
#include "main.h"

typedef struct {
    int especial; int skater; int drone;
    int vivos_tope; /* tope de aliens simultaneos en pantalla */
    int restantes; //alien restantes para ganar
    int aliens_randoms;
} PoolAliens;

int coordenada_spawn(struct Juego *juego);
int alien_a_spawnear(struct Juego *juego);
void meter_alien(struct Juego *juego, int tipo_alien);
void spawn_inicio(struct Juego *juego);
void spawn_turno(struct Juego *juego);

#endif