package ar.edu.unlu.poo.tp2.ej9;

public class Cuadrado extends Figura2d{
    private final double lado;

    public Cuadrado(double lado){
        this.lado = lado;
    }
    @Override
    double getPerimetro() {
        return lado*4;
    }

    @Override
    public double getArea() {
        return lado*lado;
    }
}
