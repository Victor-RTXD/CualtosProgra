package practica3;

public class Main {
    public static void main(String[] args) {
        CuentaBancaria BBVA = new CuentaBancaria(0, "", 0, 0);
        BBVA.printData();

        CuentaBancaria santander = new CuentaBancaria(90, "ee", 0, 0);
        santander.printData();
    }
}
