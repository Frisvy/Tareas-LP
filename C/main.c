#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>
#include "main.h"
/*
* Nombre: pedir_accion
* Parámetros: no recibe
* Retorno: un char
* Descripción: pide un char al jugador por consola y lo retorna
*/
char pedir_accion(){
    char accion;
    scanf(" %c", &accion);
    return accion;
}
/*
* Nombre: validar_accion
* Parámetros: struct juego y un char que corresponde al input del jugador
* Retorno: un booleano (true o false)
* Descripción: determina si la accion del jugador es valida o invalida, y en caso de ser la h imprime un menu de ayuda, se utiliza principalmente en el main para saber si la accion debera consumir turno o no
*/
bool validar_accion(struct Juego *juego, char accion){
    bool validez = true;
    if(accion == 'a'){
        if((juego->jugador_x - 1)< 0){
            printf("Accion invalida\n");
            validez = false;
        }
    }
    else if(accion == 'd'){
        if(juego->dificultad == 1){
            if((juego->jugador_x + 1) > 4){
                printf("Accion invalida\n");
                validez = false;
            }
        }
        else if(juego->dificultad == 2){
            if((juego->jugador_x + 1) > 6){
                printf("Accion invalida\n");
                validez = false;
            }
        }
    }
    else if(accion == 'h'){
        printf("Mata a todos los aliens antes de que lleguen a la ultima casilla para ganar, recuerda los controles.\n");
        printf("Controles: a/d mover | 1=NORMAL 2=PERFORADOR 3=ESPECIAL | q salir | help ayuda\n");
        printf("¡Buena Suerte!\n");
        validez = false;
    }
    else if(accion == '1' || accion == '2' || accion == '3'){
        validez = true;
    }
    else{
        printf("Accion invalida\n");
        validez = false;
    }
    return validez;    
}
/*
* Nombre: ejecutar_accion
* Parámetros: struct juego y un char
* Retorno: no retorna
* Descripción: ejecuta la accion que selecciono el jugador, en caso de ser una accion de disparo llama a la funcion de disparar armas para consumir el turno en caso de que se pudiera disparar
*/
void ejecutar_accion(struct Juego *juego, char accion){
    if(accion == 'a'){
            juego->jugador_x = juego->jugador_x - 1;
            juego->turno = juego->turno + 1;
        }
    else if(accion == 'd'){
        juego->jugador_x = juego->jugador_x + 1;
        juego->turno = juego->turno + 1;
    }
    else if(accion == 'h'){
    }
    else if(accion == '1' || accion ==  '2' || accion == '3'){
        int id = accion - '1';
        bool disparo = disparar_armas(juego, id);
        if(disparo){ // si no se pudo disparar el arma por q falto municion no se suma turno
            juego->turno = juego->turno + 1; 
        }
    }
}

char accion; // variable global que corresponde al input del jugador, creo que no es necesario que sea global, pero ya que la deje aqui hasta el final ahi se queda

int main(){
    srand(time(NULL)); // genera la seed para los numeros aleatorios
    Juego* juego = malloc(sizeof (Juego));
    juego->turno = 1;
    juego->continuar = true;
    int probabilidad;
    bool validez_accion;
    juego->vivos = 0;
    do{ //ciclo del menu de inicio del juego, no se avanza hasta que se seleccione una dificultad 
        menu_inicio(juego);
        if(juego->dificultad == 1){ //dificultad facil
            juego->t = tablero_crear(5,16); //16 para que el jugador este en y=0, y hayan 15 casillas para mover aliens
            juego->pool.restantes = 15;
        }
        else if (juego->dificultad == 2){ // dificultad dificil
            juego->t = tablero_crear(7,16);
            juego->pool.restantes = 20;
        }
        else {
            limpiar_consola();
            printf("Selecciona una opcion valida papu :3\n");
        }
    } while ((juego->dificultad != 1) && (juego->dificultad != 2));
    juego->armas.fn[0] = arma_normal; 
    juego->armas.fn[1] = arma_perforador;
    juego->armas.fn[2] = arma_especial;

    spawn_inicio(juego); //spawn de los primeros enemigos
    do{ //ciclo principal del juego donde se piden las acciones al jugador y se activan los efectos, y se imprimen los mensajes en pantalla
        probabilidad = rand() % 100;
        tablero_imprimir(juego);   
        accion = pedir_accion();
        validez_accion = validar_accion(juego, accion);
        if(!validez_accion){
            continue;
        }
        int turno_anterior = juego->turno; //para ver si cambio el turno o no
        ejecutar_accion(juego, accion);
        if(juego->turno == turno_anterior){ 
            continue;
        }
        resolver_danos(juego);
        if(juego->pool.restantes == 0){
            printf("HAS MATADO A TODOS LOS ALIENS\n");
            printf("FELICIDADES GANASTE\n");
            break;
        }
        mover_aliens(juego);
        spawn_turno(juego);
        if(probabilidad <=30){
            spawn_turno(juego);
        }
        if(!juego->continuar){
            break;
        }
    } while (accion != 'q');
    tablero_cerrar(juego->t);
    free(juego);
    return 0;
}
