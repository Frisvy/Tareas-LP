#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "main.h"
#include "armas.h"

/*
* Nombre: arma_normal
* Parámetros: struct juego
* Retorno: booleano, variable con true o false
* Descripción: dispara el arma, y marca daño en caso de impactar a un alien, si impacta imprime mensaje de impacto, y si no indica que fallo
*/
bool arma_normal (struct Juego *juego){
    int x = juego->jugador_x;
    bool impacto = false;
    for(int y = 1; y < juego->t->H; y++){ //partimos de y = 1 pq y = 0 es el jugador jeje
        Celda *celda_alien = juego->t->celdas[y][x];
        if(celda_alien != NULL && celda_alien->alien != NULL){
            celda_alien->dano_pend = celda_alien->dano_pend + 1;
            impacto = true;
            break;

        }
    }
    if(impacto){
        printf("Impacto\n");
        return true;
    }
    else{
        printf("Fallo\n");
        return true;
    }
    return impacto;
}
/*
* Nombre: arma_perforador
* Parámetros: struct juego
* Retorno: booleano
* Descripción: revisa si hay municion. En caso de que no haya retorna false, en caso de que si haya retorna true, y si impacta a un enemigo le marca el dano para que la funcion de dano lo procese
*/
bool arma_perforador(struct Juego *juego){
    int x = juego->jugador_x;
    bool impacto = false;
    if(juego->armas.ammo_perforador > 0){
        juego->armas.ammo_perforador = juego->armas.ammo_perforador - 1; 
        impacto = true;
        for(int y = 1; y < juego->t->H ; y++){
            Celda *celda_alien = juego->t->celdas[y][x];
            if( celda_alien != NULL && celda_alien->alien != NULL ){
                celda_alien->dano_pend = celda_alien->dano_pend + 1;
            }
        }
    }
    return impacto;
}
/*
* Nombre: arma_especial
* Parámetros: struct juego
* Retorno: booleano
* Descripción: revisa si hay municion, en caso de que no, retorna false, en caso de que si retorna true, y hace el descuento de la municion correspondiente, le marca el daño a los enemigos para q despues se procese
*/
bool arma_especial(struct Juego *juego){
    int x = juego->jugador_x;
    bool impacto = false;
    int y_impacto = -1;
    if (juego->armas.ammo_especial > 0){
        juego->armas.ammo_especial = juego->armas.ammo_especial - 1; 
        impacto = true;
        for(int y = 1; y < juego->t->H; y++){
            Celda *celda_alien = juego->t->celdas[y][x];
            if(celda_alien != NULL && celda_alien->alien != NULL){
                y_impacto = y;
                break;
            }
        }
        if(y_impacto == -1){
            return impacto;
        }
        for(int cuadrado_y = -1; cuadrado_y <= 1; cuadrado_y++){
            for(int cuadrado_x = -1; cuadrado_x <= 1; cuadrado_x++){
                int coordenada_y = y_impacto + cuadrado_y;
                int coordenada_x = x + cuadrado_x;
                if (coordenada_y < 1 || coordenada_y >= juego->t->H){ //si la coordenada esta en y = 0, o arriba del todo del tablero, nos la saltamos
                    continue;
                }
                if( coordenada_x < 0 || coordenada_x >= juego->t->W){
                    continue;;
                }
                Celda *celda_alien = juego->t->celdas[coordenada_y][coordenada_x];
                if(celda_alien && celda_alien->alien){
                    celda_alien->dano_pend = celda_alien->dano_pend + 2; // se marca el daño del alien
                }
            }
        }
    }
    return impacto;
}
/*
* Nombre: disparar_armas
* Parámetros: struct juego y int de id
* Retorno: booleano
* Descripción: revisa que el id del arma sea valido para despues asignarselo a FuncArmas, en caso de q no sea valido retorna false, de lo contrario true
*/
bool disparar_armas(struct Juego *juego, int arma_id){ //transformar el char a int en el main
    if(arma_id < 0 || arma_id > 2){
        return false;
    }
    FuncArmas arma = juego->armas.fn[arma_id];
    if(!arma){
        return false;
    }
    return arma(juego);
}
