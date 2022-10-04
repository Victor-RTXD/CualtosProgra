package practica6;

public class Cuadrado extends FiguraGeometrica {
    public Cuadrado(double valor1) {
        super(valor1);
    }

    @Override
    public double getArea() {
        return Math.pow(valor1, 2);
    }

    @Override
    public double getPerimetro() {
        return this.valor1 * 4;
    }
}
