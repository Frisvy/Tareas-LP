typedef struct Juego {
    Tablero *t; /* encapsula W/H y la politica de memoria/render */
    Armas armas;
    PoolAliens pool;
    int dificultad;
    int turno, vivos, jugador_x;
} Juego;
