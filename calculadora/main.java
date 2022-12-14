package calculadora;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        short i;
        System.out.println("Selecciona la opcion deseada");
        do {
            System.out.println("1: Carga Eléctrica ");
            System.out.println("2: Campo electrico");
            System.out.println("3: Circuitos en serie");
            System.out.println("4: Circuitos en paralelo");
            System.out.println("0: Salir");

            i = sc.nextShort();
            switch (i) {
                case 1:
                    CargaElectrica ce = new CargaElectrica();
                    ce.leyCoulomb();
                break;
                case 2:
                    CampoElectrico caele = new CampoElectrico();
                    caele.campoElectrico();
                break;
                case 3:
                    Circuitos circuitos = new Circuitos();
                    circuitos.leyOhm();
                break;
                case 4:
                    CircuitosParalelos paralelos = new CircuitosParalelos();
                    paralelos.circuitosParalelos();
                break;
                default:
                    System.out.println();
                break;
            }
        } while (i != 0);
    }
}