package ar.edu.unlu.poo.tp2.ej9;

public class Esfera extends Figura3d{
    private final double radio;
    public Esfera(double radio){
        this.radio = radio;
    }
    @Override
    double getVolumen() {
        return (4.0/3.0) * Math.PI * Math.pow(radio,3);
    }

    @Override
    public double getArea() {
        return 4 * Math.PI * Math.pow(radio,2);
    }
}
