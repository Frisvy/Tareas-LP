#ifndef TABLERO_H
#define TABLERO_H


typedef struct {
    int W, H;
    void ***celdas; /* celdas[y][x] -> (void*) que apunta a Celda* */
} Tablero;

typedef struct Juego Juego; //como uso el #include "tablero.h" en el main.h, necesito hacer este forward declaration para que funcionen bien las funciones 

Tablero* tablero_crear(int ancho, int alto);
void tablero_imprimir(struct Juego *juego);
void tablero_cerrar(Tablero *tablero);
void menu_inicio(struct Juego* juego); //crea un menu de inicio y setea la dificultad del juego
void limpiar_consola(); //limpia la consola para que se vea mas bonito
#endif