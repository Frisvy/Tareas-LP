Nombre: Gabriel Ordenes
Rol: 202473521-7

--------------------------------COMENTARIOS--------------------------------------
1.- En la terminal se espera recibir un input de largo 1 unicamente, colocar algo como dd, aa o ddddd, entre otras combinaciones de mas de una letra provoca comportamientos inesperados , un ejemplo seria  el hacer que el jugador avance mas de una casilla en caso de poner mas de una d, en cuyo caso si acto seguido se coloca una unica 'd' hace q el personaje no se mueva, etc (intentaria arreglarlo pero la verdad no se que a se deben esos bugs en especifico y me parece razonable esperar inputs validos, el colocar una letra cualquiera que no salga en los controles del juego no provoca ningun error hasta donde pude probar, es solo la concatenacion de mas de una caracter el problema)

2.- Para el movimiento en zigzag del alien skater se implemento de tal manera de que se movera en diagonal hacia la derecha, a no ser de que choque con un muro, en cuyo caso se movera hasta la izquierda en diagonal hasta chocar con el siguiente muro, y asi sucesivamente. Ahora un detalle importante es que segun lo que se dijo en el foro de la tarea respecto a la resolucion de colisiones de 2 aliens "puedes hacer que en caso de colisión, se mueva a la izquierda en vez de la derecha o descienda solo verticalmente, incluso que salte esa celda para ponerse al otro lado del alien", tomando ese comentario en cuenta opte por hacer que si el alien skater se topa con otro alien, este va a dejar de rebotar y se va a mover en linea recta hacia abajo.

3.- Respecto al arma especial. Esta funciona de tal manera que si impacta a un enemigo se expandira y golpeara todas las casillas adyacentes al alien (es decir un cuadrado de 3,3 si el alien esta en el centro)



