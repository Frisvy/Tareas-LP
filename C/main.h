#ifndef MAIN_H
#define MAIN_H
#include "tablero.h"
#include "armas.h"
#include "spawn.h"
#include "entidades.h"



typedef struct Juego {
    Tablero *t; /* encapsula W/H y la politica de memoria/render */
    Armas armas;
    PoolAliens pool;
    int dificultad; // 1 = facil , 2 = dificil
    int turno, vivos, jugador_x; // n_turnos, n_vivos, posicionx jugador
    char accion; // accion del jugador por turno 
    bool continuar; // en caso de que el jugador muera se canbia a false para terminar el juego
} Juego;

char pedir_accion();//funcion que pide un input al jugador y lo retorna como variable
bool validar_accion(struct Juego *juego, char accion);
void ejecutar_accion(struct Juego *juego, char accion);
#endif
