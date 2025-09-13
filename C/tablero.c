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
            Celda *celda = tablero->celdas[y][x];
            if(celda != NULL){
                if(celda->alien != NULL){
                    free(celda->alien);
                    celda->alien = NULL;
                }
                free(celda);
                tablero->celdas[y][x] = NULL;
            }
        }
        free(tablero->celdas[y]);
        tablero->celdas[y] = NULL;    
    }
    free(tablero->celdas);
    tablero->celdas = NULL;
    free(tablero);
}


void limpiar_consola(){
    printf("\033[2J\033[H");
}

void tablero_imprimir(Juego *juego){

    printf("=====================\n");
    for(int y = juego->t->H - 1; y >= 0; y--){ //se imprime al reves ya que de esa manera el spawn de aliens corresponde a y= H-1
        for(int x = 0; x < juego->t->W; x++){
            Celda *celda = (Celda*)juego->t->celdas[y][x];
            
            if(celda == NULL){
                printf("[.]");
            }
            else{
                Alien *alien = (Alien*)celda->alien;
                if(alien->tipo == 1){
                    printf("[D]");
                }
                else if(alien->tipo == 2){
                    printf("[S]");
                }
                else if(alien->tipo == 3){
                    printf("[E]");
                }
            }
        }       
        printf("\n");
    }
    if(juego->dificultad == 1){
        for(int i = 0; i < 5;i++ ){
            if(juego->jugador_x != i){
                printf("   ");
            }
            else{
                printf(" ^ ");
            }
        }
        printf("\n");
    }
    else if(juego->dificultad == 2){
        for(int i = 0; i < 7;i++ ){
            if(juego->jugador_x != i){
                printf("   ");
            }
            else{
                printf(" ^ ");
            }
        }
        printf("\n");
    }
    printf("=====================\n");
    printf("Turno: %d | Vivos: %d | Restantes inv.: %d | Municion (1)NORMAL: ∞ (2)PERFORANTE: %d (3)ESPECIAL: %d | Jugador x = %d\n", juego->turno, juego->vivos, juego->pool.restantes, juego->armas.ammo_perforador, juego->armas.ammo_especial, juego->jugador_x);
    printf("Accion (a/d/1/2/3/q/h): ");
    scanf("%c", &juego->accion);
}



void menu_inicio(Juego* juego){
    char eleccion_dif;
    printf("=== Space Defender ===\n");
    printf("Controles: a/d mover | 1=NORMAL 2=PERFORADOR 3=ESPECIAL | q salir | help ayuda\n");
    printf("Seleccione  una dificultad (1 = Facil, 2 = Dificil): ");
    scanf("%c", &eleccion_dif); //no uso juego->dificultad directamente ya que si alguien pone en la terminal algo distinto de un numero se bugea feo el programa y me sicoseo TT
    if(eleccion_dif == '1'){
       juego->dificultad = 1;
       juego->jugador_x = 2; // se asigna la posicion incial del jugador en el centro del tablero (el centro cambia dependiendo de la diff)
    }
    else if(eleccion_dif == '2'){
        juego->dificultad = 2;
        juego->jugador_x = 3;
    }
}


