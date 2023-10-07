package ar.edu.unlu.poo.tp2.ej9;

public class Cubo extends Figura3d{
    private final double arista;
    public Cubo(double arista){
        this.arista = arista;
    }
    @Override
    double getVolumen() {
        return Math.pow(arista,3);
    }

    @Override
    public double getArea() {
        return 6 * Math.pow(arista,2);
    }
}
