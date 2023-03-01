package F;

import java.util.Scanner;

public class CircularQueue {
    private int front; // índice del primer elemento
    private int rear; // índice del último elemento
    private int size; // tamaño actual de la cola
    private int capacity; // capacidad máxima de la cola
    private int[] data; // array para almacenar los elementos de la cola

    public CircularQueue(int capacity) {
        this.front = 0;
        this.rear = -1;
        this.size = 0;
        this.capacity = capacity;
        this.data = new int[capacity];
    }

    public void enqueue(int element) {
        if (isFull()) {
            System.out.println("La cola está llena.");
            return;
        }
        rear = (rear + 1) % capacity;
        data[rear] = element;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return -1;
        }
        int element = data[front];
        front = (front + 1) % capacity;
        size--;
        return element;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return;
        }
        int i = front;
        while (i != rear) {
            System.out.print(data[i] + " ");
            i = (i + 1) % capacity;
        }
        System.out.println(data[rear]);
    }

    public boolean isFull() {
        return size == capacity;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        CircularQueue cola = new CircularQueue(5);
        short i = 0;
        int n = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\n1: insertar elementos max 5\n2: eliminar elemento\n3: mostrar elementos\n 0: salir");
            i = sc.nextShort();
            if (i == 1) {
                System.out.println("ingresa el id: ");
                n = sc.nextInt();
                cola.enqueue(n);
            } else if (i == 2) {
                cola.dequeue();
            } else if (i == 3) {
                System.out.println("**************");
                cola.print();
            }
        } while (i != 0);
    }
}