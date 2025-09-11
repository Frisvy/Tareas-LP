Nombre: Gabriel Ordenes
Rol: 202473521-7

--------------------------------COMENTARIOS--------------------------------------
1.- En la terminal se espera recibir un input de largo 1 unicamente, colocar algo como dd, aa o ddddd, entre otras combinaciones de mas de una letra provoca comportamientos inesperados , un ejemplo seria  el hacer que el jugador avance mas de una casilla en caso de poner mas de una d, en cuyo caso si acto seguido se coloca una unica 'd' hace q el personaje no se mueva, etc (intentaria arreglarlo pero la verdad no se que a se deben esos bugs en especifico y me parece razonable esperar inputs validos, el colocar una letra cualquiera que no salga en los controles del juego no provoca ningun error hasta donde pude probar, es solo la concatenacion de mas de una caracter el problema)

2.- Para el movimiento en zigzag del alien skater se implemento de tal manera de que se movera en diagonal hacia la derecha, a no ser de que choque con un muro, en cuyo caso se movera hasta la izquierda en diagonal hasta chocar con el siguiente muro, y asi sucesivamente. Ahora un detalle importante es que segun lo que se dijo en el foro de la tarea respecto a la resolucion de colisiones de 2 aliens "puedes hacer que en caso de colisión, se mueva a la izquierda en vez de la derecha o descienda solo verticalmente, incluso que salte esa celda para ponerse al otro lado del alien", tomando ese comentario en cuenta opte por hacer que si el alien skater se topa con otro alien, este va a dejar de rebotar y se va a mover en linea recta hacia abajo.

void mover_aliens(struct Juego *juego){ //si los aliens bajan cada 2 turnos, esto implica que en el final del turno 2 movemos al alien. ejecutamos la accion del jugador y despues movemos el alien para evitar conflictos en caso de que llegue a y = 0
    if((juego->turno % 2) != 0){ //si es impar hay q mover el alien, ya que por como implemente la accion del jugador el contador de turnos sube inmediatamente tras el input del jugador
        int nueva_posicion_y;
        int nueva_posicion_x;
        for(int y = juego->t->H - 1; y >= 0; y--){
            for(int x = 0; x < juego->t->W; x++){
                Celda *celda = juego->t->celdas[y][x];
                if(celda != NULL && celda->alien != NULL){
                    Alien *alien = celda->alien;
                    if (alien->marca){
                        continue;
                    }
                    nueva_posicion_y = alien->y - 1 ;
                    if(nueva_posicion_y == 0){ // hacer algo pa matar al jugador, o cerrar el juego, piensapiensa
                        juego->continuar = false;
                        return;
                    }
                    if((celda->alien->tipo == 1) || (celda->alien->tipo == 3 )){ // movimiento dron y especial
                        nueva_posicion_x = x;
                        if(juego->t->celdas[nueva_posicion_y][nueva_posicion_x] == NULL){
                            juego->t->celdas[nueva_posicion_y][nueva_posicion_x] = celda;
                            juego->t->celdas[y][x] = NULL;
                            alien->y = nueva_posicion_y;
                            alien->marca = true;
                        }
                    }
                    else if(celda->alien->tipo == 2){ //movimiento skater
                        nueva_posicion_x = x + alien->dx;
                        if(juego->t->celdas[y][x + 1] != NULL){
                            juego->t->celdas[nueva_posicion_y][x] = celda;
                            juego->t->celdas[y][x] = NULL;
                            alien->y = nueva_posicion_y;
                            alien->marca = true;
                        }
                        else if(nueva_posicion_x < 0 || nueva_posicion_x >= juego->t->W){ //rebote en caso de chocar los bordes 
                            alien->dx = -alien->dx;
                            nueva_posicion_x = x + alien->dx;
                        }
                        else if(nueva_posicion_x >= 0 && nueva_posicion_x < juego->t->W){ 
                            if (juego->t->celdas[nueva_posicion_y][nueva_posicion_x] == NULL){
                                juego->t->celdas[nueva_posicion_y][nueva_posicion_x] = celda;
                                juego->t->celdas[y][x] = NULL;
                                alien->x = nueva_posicion_x;
                                alien->y = nueva_posicion_y;
                                alien->marca = true;
                                if (alien->x == 0 || alien->x == juego->t->W - 1){ //si el alien esta en cualquier borde hay que invertir la direccion del zigzag 
                                    alien->dx = -alien->dx;
                                }
                            }
                            if(juego->t->celdas[nueva_posicion_y][x] == NULL){ // caso de que el skater choque con otro alien en vez de un muro
                                juego->t->celdas[nueva_posicion_y][x] = celda;
                                juego->t->celdas[y][x] = NULL;
                                alien->y = nueva_posicion_y;
                                alien->marca = true;
                            }
                        }
                    }
                }
            }
        }
        for(int y = juego->t->H - 1; y >= 0; y--){ // restablecemos las marcas para cuando volvamos a activar la funcion
            for(int x = 0; x < juego->t->W; x++){
                Celda *celda = juego->t->celdas[y][x];
                if (celda != NULL && celda->alien != NULL){
                    celda->alien->marca = false;
                }
            }
        }        
    }    
}