package ar.edu.unlu.poo.tp2.ejercicio7;

public abstract class Combustible {
    private double precio;
    private double cantidad;

    public abstract void setCantidad(double cantidad);

    public abstract double getCantidad();

    public abstract double getPrecio();
}
