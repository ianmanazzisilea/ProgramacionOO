package ar.edu.unlu.poo.tp2.ej14;

public abstract class Proveedor {
    String nombre;
    double precioBase;

    public Proveedor(String nombre, double precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
    }

    public String getNombre() {
        return nombre;
    }
    public double getPrecioBase() {
        return precioBase;
    }

    abstract double calcularExtra(int cantMaxClientes);
}
