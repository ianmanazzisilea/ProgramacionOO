package ar.edu.unlu.poo.tp2.ej9;

public class Paralelepipedo extends Figura3d{
    private final double arista1;
    private final double arista2;
    private final double arista3;

    public Paralelepipedo(double arista1, double arista2, double arista3){
        this.arista1 = arista1;
        this.arista2 = arista2;
        this.arista3 = arista3;
    }

    @Override
    double getVolumen() {
        return arista1 * arista2 * arista3;
    }

    @Override
    public double getArea() {
        return 2 * (arista1 * arista2 + arista1 * arista3 + arista2 * arista3);
    }
}
