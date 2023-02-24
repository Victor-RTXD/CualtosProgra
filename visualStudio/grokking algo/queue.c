#include <stdio.h>
#include <stdlib.h>

#define MAX_QUEUE_SIZE 3

int queue[MAX_QUEUE_SIZE];
int front = 0; // puntero al inicio de la cola
int rear = -1; // puntero al final de la cola
int count = 0; // número de elementos en la cola

void enqueue(int value) {
    // Comprobar si la cola está llena
    if (count >= MAX_QUEUE_SIZE) {
        printf("La cola está llena.\n");
    } else {
        // Actualizar posición del puntero rear
        rear = (rear + 1) % MAX_QUEUE_SIZE;
        // Añadir elemento al final de la cola
        queue[rear] = value;
        // Incrementar contador de elementos en la cola
        count++;
    }
}

int dequeue() {
    if (count <= 0) {
        printf("La cola está vacía.\n");
        return -1;
    } else {
        int value = queue[front];
        front = (front + 1) % MAX_QUEUE_SIZE;
        count--;
        printf("se borrara: ");
        return value;
    }
}

int main() {
    enqueue(1);
    enqueue(2);
    enqueue(3);

    printf("%d\n", dequeue()); // imprime 1
    printf("%d\n", dequeue()); // imprime 2
    printf("%d\n", dequeue()); // imprime 3

    enqueue(7);
    
    for (int i = 0; i < MAX_QUEUE_SIZE; i++)
    {
        printf("%d", queue[i]);
    }
    

    return 0;
}