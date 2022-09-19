package practica3;

import java.util.Scanner;

/*Crear clase para manejar una cuenta bancaria */
public class CuentaBancaria {
    private int saldo;
    private String nombre;
    private int nip;
    private int numeroCuenta;

    public Scanner sc = new Scanner(System.in);

    //inicio getters and setters
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

    /**
     * Descricion: esta funcion imprime todos los atributos del metodo constructor
     * (saldo, nombre, nip, numero de cuenta)
     */
    public void printData() {
        System.out.println("saldo: " + this.saldo + " nombre: " + this.nombre + " nip: " + this.nip + " numero de cuenta: " + this.numeroCuenta);
    }

    /**
     * Descricpion: funcion para ingresarle datos a atributos ya existentes 
     * @param nombre numeroCuenta nip saldo
     */
    public void crearCuenta() {
        System.out.println("ingrese nombre: ");
        this.nombre = sc.next();
        System.out.println("ingrese num de cuenta: ");
        this.numeroCuenta = sc.nextInt();
        System.out.println("ingrese nip: ");
        this.nip = sc.nextInt();
        System.out.println("ingrese saldo: ");
        this.saldo = sc.nextInt();
    }

    /**
     * Descripción: es una funcion booleana que compara el nip del usuario con el que ingresa
     * @return true or false
     */
    public boolean autentificacion() {
        int cuentaComparador, nipComparador;
        System.out.println("teclea numero de cuenta: ");
        cuentaComparador = sc.nextInt();
        System.out.println("teclea nip: ");
        nipComparador = sc.nextInt();

        if(cuentaComparador == this.numeroCuenta && nipComparador == this.nip) {
            return true;
        } else {
            System.out.println("Contraseña o numero de cuenta incorrecto");
            return false;
        }
    }

    public void depsoitar() {

    }
    
    /**
     * Descripcion: ingresar autentificacion, validar datos, ingresar catnidad, validar si existe saldo, descontar saldo 
     * @param null
     */
    public void retirar() {
        int cantidad;

        if(autentificacion() == true) {
            System.out.println("ingresa cantidad a retirar: ");
            cantidad = sc.nextInt();

            if(this.saldo >= 500 && cantidad <= 8000) {
                this.saldo = this.saldo - cantidad;
                System.out.println("ahora tu saldo es: " + this.saldo);
            } else {
                System.out.println("No se puede retirar");
            }
        } 
    }

}
/*
 * curso de maestra
 * POO
 * POO2022
 */