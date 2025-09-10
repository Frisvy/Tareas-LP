Nombre: Gabriel Ordenes
Rol: 202473521-7

---------------comentarios--------------------------------------

si en la seleccion de dificultad se ingresa una opcion que no sea int, el programa se volvera loco debido a que se hacen operaciones con ese numero el cual se espera sea de tipo int.
if((juego->dificultad == 1) || (juego->dificultad == 2)){
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