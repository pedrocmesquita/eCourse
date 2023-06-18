CC = gcc
CFLAGS = -g -Wall
LIBS = -pthread

all: prog

prog: main.o
	$(CC) $(CFLAGS) main.o -o prog $(LIBS)

main.o: main.c
	$(CC) $(CFLAGS) -c main.c -o main.o

clean:
	rm -f *.o prog

run: prog
	./prog
