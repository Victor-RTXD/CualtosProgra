package practica3;

/*Crear clase para manejar una cuenta bancaria */
public class CuentaBancaria {
    private int saldo;
    private String nombre;
    private int nip;
    private int numeroCuenta;

    /*public cuentaBancaria() {
        this.saldo = 0;
        this.nombre = "";
        this.nip = 0;
        this.numeroCuenta = 0;
    } */
    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    //fin getters y setters

    public CuentaBancaria(int saldo, String nombre, int nip, int numeroCuenta) {
        this.saldo = saldo;
        this.nombre = nombre;
        this.nip = nip;
        this.numeroCuenta = numeroCuenta;
    }

    public void depositar(int valor) {

    }

    public void retirar(int valor) {

    }

    public void printData() {
        System.out.println("saldo: " + this.saldo + "nombre: " + this.nombre + "nip: " + this.nip + "numero de cuenta: " + this.numeroCuenta);
    }
}