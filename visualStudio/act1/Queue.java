package act1;

public class Queue {
    int front = -1;
    int rear = -1;
    int item;
    int size = 2;
    Integer queue[] = new Integer[size];

    //metodos
    void enQueue(int value) {
        if ((rear + 1) % queue.length == front)
            System.out.println("se pasa ");
        else {
            if (front == -1)
                front = 0;
            rear = (rear + 1) % queue.length;
            queue[rear] = value;
        }
    }

    void deQueue() {
        if (front == -1)
        System.out.println("sobrecarga");
        else {
            System.out.println("se eliminara: " + queue[front]);
            queue[front] = null;
            if (front == rear)
                front = rear = -1;
            else
                front = (front + 1) % queue.length;
        }
    }

    void print() {
        for (int i = 0; i < queue.length; i++) {
            System.out.println(queue[i]);
        }
    }

}