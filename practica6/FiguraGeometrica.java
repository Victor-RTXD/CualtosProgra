package practica6;

public abstract class FiguraGeometrica {
    protected double valor1;
    
    public FiguraGeometrica(double valor1) {
        super();
        this.valor1 = valor1;
    }

    public double getValor1() {
        return valor1;
    }

    public void setValor1(double valor1) {
        this.valor1 = valor1;
    }

    public abstract double getArea();
    public abstract double getPerimetro();
}
/*
 * ¿Que es la sobre escritura de metodos?
 * Simplemente es generear un metodo de una super clase que se hereda a las
 * sub clases y se comporta de forma diferente, como el polimorfismo,
 * ya que la podemos sobre escribir
 * 
 * ¿Que es una clase abstracta?
 * Simplemente es una clase que tiene atributos y metodos muy generalistas
 * para que los pudera heredar sus clases hijas, y de esta clase no se 
 * puede instanciar ningun objeto
 * 
 * ¿Que es un metodo abstracto?
 * Un metodo que en su interior no tiene nada, se puede veren en el ejemplo,
 * puede ayudar a generar una sobre escritura de metodos
 */