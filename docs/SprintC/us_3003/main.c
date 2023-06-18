#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/types.h>
#include <semaphore.h>

#define MAX_NUMBER_ROWS 20
#define MAX_NUMBER_COLUMMS 10

/*
	As Project Manager, I want the team to "explore" the synchronization problems related
	to the shyncronization of shared boards and design a conceptual solution based on
	practical evidence.
*/

typedef struct
{
	int columms, rows, cells, id;
	char title[50];
	board oldboard;
} board;

int edit_board(board *board)
{
	sem_wait(semw);	//wait until able to write on board
	board.oldboard = board;	//save old board contents
	sem_post(semw);	//let board be edited again
}

int main()
{
	//shared memory
	char shm[32] = "/shmem";

	int fd = shm_open(shm, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);

	if (fd == -1)
	{
		printf("Error creating shared memory.\n");
		exit(1);
	}

	if (ftruncate(fd, sizeof(board)) == -1)
	{
		printf("Error creating shared memory.\n");
		exit(1);
	}

	board *data = mmap(NULL, sizeof(board) * CHILD, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

	//semaphores
	sem_unlink("semr");
	sem_t *semr = sem_open("semr", O_CREAT | O_EXCL, 0644, 1);    //1 if board is ready to be read
    if (semr == SEM_FAILED)
    {
        perror("Error in sem_open().");
        exit(1);
    }

    sem_unlink("semw");
    sem_t *semw = sem_open("semw", O_CREAT | O_EXCL, 0644, 1);    //1 if board is ready to be edited
    if (semw == SEM_FAILED)
    {
        perror("Error in sem_open().");
        exit(1);
    }

	board *emptyBoard;	//create empty board

	//fork to simulate 2 processes
	pid_t pid = fork();
	if (pid < 0)
	{
		printf("Fork failed.\n");
		exit(1);
	}

	//child
	if (pid == 0)
	{
		edit_board(emptyBoard);
		exit(0);
	}

	//parent
	edit_board(emptyBoard);

	//clear semaphores
	sem_unlink("semr");
	sem_unlink("semw");

	//clear shared memory
	int r = munmap(emptyBoard, sizeof(board));

	if (r < 0)
	{
		printf("Error clearing shared memory.\n");
		exit(1);
	}

	r = close(fd);
	if (r < 0)
	{
		printf("Error clearing shared memory.\n");
		exit(1);
	}

	r = shm_unlink(shm);
	if (r < 0)
	{
		printf("Error clearing shared memory.\n");
		exit(1);
	}

	return 0;
}
