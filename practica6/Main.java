package practica6;

public class Main {
    public static void main(String[] args) {
        FiguraGeometrica figura;

        figura = new Circulo(1);
        System.out.println("el radio del circulo es: " + figura.getClass() + figura.getValor1());
        System.out.println("el area es: " + figura.getArea());
        System.out.println("el perimetro es: " + figura.getPerimetro());

        figura = new Cuadrado(5);
        System.out.println("el valor del lado es: " + figura.getClass() + figura.getValor1());
        System.out.println("el area es: " + figura.getArea());
        System.out.println("el perimetro es: " + figura.getPerimetro());

        figura = new Triangulo(5,3);
        System.out.println("el valor del lado es: " + figura.getClass() + figura.getValor1());
        System.out.println("el area es: " + figura.getArea());
        System.out.println("el perimetro es: " + figura.getPerimetro());

        figura = new Rectangulo(5,3);
        System.out.println("el valor del lado es: " + figura.getClass() + figura.getValor1());
        System.out.println("el area es: " + figura.getArea());
        System.out.println("el perimetro es: " + figura.getPerimetro());
    }
}
/*
 * Polimorfismo: ser muy util a la hora de jerarquizar y querer dar un patrón de comportamiento común a una serie de objetos que heredan de la misma clase.
 * Mi opinion: el polimorfismo es una forma de hacer que los metodos de la super clase al ser heredados por las clases hijas, puedan
 * funcionar de diferente forma pero con mismo proposito, por ejemplo, cuando avanza un coche rueda, cuando avanza un avión vuela, lo hacen
 * de forma diferente pero al final de cuentas avanzan
 */