CFLAGS = -Wall -g 
SRC = main.c tablero.c spawn.c entidades.c armas.c
HEADERS = main.h tablero.h spawn.h entidades.h armas.h
OBJ = $(SRC:.c=.o)
all: juego

juego: $(OBJ)
	gcc $(CFLAGS) -o $@ $^

%.o: %.c $(HEADERS)
	gcc $(CFLAGS) -c $<

clean:
	rm -f $(OBJ) juego

run:
	make all && ./juego
