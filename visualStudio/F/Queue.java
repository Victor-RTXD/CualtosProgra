package F;
import java.util.Scanner;

public class Queue {
    int front = -1;
    int rear = -1;
    Scanner sc = new Scanner(System.in);

    public class Persona {
        String id;
        String contraseña;
    }
    Persona queue[] = new Persona[5];

    //metodos
    void enQueue() {
        if ((rear + 1) % queue.length == front)
            System.out.println("se pasa ");
        else {
            if (front == -1)
                front = 0;
            rear = (rear + 1) % queue.length;

           queue[rear] = new Persona();
           System.out.println("dame tu id ");
           queue[rear].id = sc.nextLine();
           System.out.println("dame tu contraseña ");

           queue[rear].contraseña = sc.nextLine();
        }
    }

    void deQueue() {
        if (front == -1)
        System.out.println("sobrecarga");
        else {
            System.out.println("se eliminara: " + queue[front].id);
            queue[front].contraseña = "";
            queue[front].id = "";
            if (front == rear)
                front = rear = -1;
            else
                front = (front + 1) % queue.length;
        }
    }

    void print(short contador) {
        for (int i = 0; i <= contador; i++) {
            System.out.println("contraseña: " + queue[i].contraseña + " id:  " +  queue[i].id);
        }
    }
}