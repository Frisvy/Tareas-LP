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
    tablero->celdas = (void ***)malloc(alto * sizeof(void**));
    
    for(int y = 0; y < alto; y++){
        tablero->celdas[y] = (void **)malloc(ancho * sizeof(void*));
        
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
void limpiar_consola(){
    printf("\033[2J\033[H");
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
    printf("Turno: %d | Vivos: %d | Restantes inv.: %d | Municion (1)NORMAL: ∞ (2)PERFORANTE: %d (3)ESPECIAL: %d | Jugador x = %d\n", juego->turno, juego->vivos, juego->pool.restantes, juego->armas.ammo_perforador, juego->armas.ammo_especial, juego->jugador_x);
    printf("Accion (a/d/1/2/3/q/h): ");
    scanf("%c", &juego->accion);
}
void rellenar_tablero(Juego  *juego){
    
}

void menu_inicio(Juego* juego){
    printf("=== Space Defender ===\n");
    printf("Controles: a/d mover | 1=NORMAL 2=PERFORADOR 3=ESPECIAL | q salir | help ayuda\n");
    printf("Seleccione  una dificultad (1 = Facil, 2 = Dificil): ");
    scanf("%d", &juego->dificultad);
    
}

