package ar.edu.unlu.poo.tp2.ejercicio7;


public class Gasoil extends Combustible {
    private double precio;
    private double cantidad;
    @Override
    public void setCantidad(double cantidad) {
        this.cantidad=cantidad;
    }

    @Override
    public double getCantidad() {
        return cantidad;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
