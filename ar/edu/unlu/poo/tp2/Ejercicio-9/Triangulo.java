package ar.edu.unlu.poo.tp2.ej9;

public class Triangulo extends Figura2d{
    private double base;
    private double altura;
    public Triangulo(double base, double altura){
        this.base = base;
        this.altura = altura;
    }
    @Override
    double getPerimetro() {
        return Math.pow(base,2) + Math.pow(altura,2);
    }

    @Override
    public double getArea() {
        return (base * altura) / 2;
    }
}
