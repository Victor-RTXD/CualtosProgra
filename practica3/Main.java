package practica3;

public class Main {
    public static void main(String[] args) {
        CuentaBancaria BBVA = new CuentaBancaria(0, null, 0, 0);
        BBVA.crearCuenta();
        BBVA.printData();
        BBVA.retirar();
    }
}
