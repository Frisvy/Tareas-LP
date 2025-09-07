#include <stdio.h>
#include <stdlib.h>
#include "main.h"
// width: ancho
// height: altura

/*typedef struct {
    int W, H;
    void ***celdas;    // celdas[y][x] -> (void*) que apunta a Celda*  
} Tablero; */

Tablero* tablero_crear(int ancho, int alto){
    Tablero* tablero = malloc(sizeof(Tablero));
    tablero->H = alto; // eje Y (filas)
    tablero->W = ancho; // eje X (columnas)
    tablero->celdas = malloc(alto * sizeof(void**));
    
    for(int y = 0; y < alto; y++){
        tablero->celdas[y] = malloc(ancho * sizeof(void*));
        
        for(int x = 0; x < ancho; x++){
            tablero->celdas[y][x] = NULL; 
        }
    }
    return tablero;
}

void tablero_cerrar(Tablero *tablero){
    for(int y = 0; y < tablero->H; y++){
        for(int x = 0; x < tablero->W; x++){
            if(tablero->celdas[y][x] != NULL){
                free(tablero->celdas[y][x]);
            }
        }
        if(tablero->celdas[y] != NULL){
            free(tablero->celdas[y]);
        }
    }
    free(tablero->celdas);
    free(tablero);
}

void tablero_imprimir(Juego *juego){
    printf("=====================\n");
    for(int y = 0; y < juego->t->H; y++){
        for(int x = 0; x < juego->t->W; x++){
            if(juego->t->celdas[y][x] == NULL){
                printf("[.]");
            }
            else{
                printf("[%p]",juego->t->celdas[y][x]);
            }
            

        }        
        printf("\n");
    }
    printf("=====================\n");
}


int main(){
    Juego* juego;
    Tablero* tableroo = tablero_crear(5,15);
    juego->t = tableroo;
    tablero_imprimir(juego);
    tablero_cerrar(juego->t);
    return(0);
}