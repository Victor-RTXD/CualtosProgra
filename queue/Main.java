import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue cola = new Queue();
        short i;
        
        while (true) {
            System.out.println("1, 2, 3");
            i = sc.nextShort();
            if (i == 1) {
                System.out.println("Ingresa elemento: ");
                i = sc.nextShort();
                cola.enQueue(i);
            } else if (i == 2) {
                cola.deQueue();
            } else {
                cola.print();
            }
        }
    }
}