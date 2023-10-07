package ar.edu.unlu.poo.tp2.ej14;

public class ProveedorTransporte extends Proveedor {

    public ProveedorTransporte(String nombre, double precioBase) {
        super(nombre, precioBase);
    }

    @Override
    double calcularExtra(int cantMaxClientes) {
        return cantMaxClientes * 0.003 * precioBase;
    }
    public String toString() {
        return "Proveedor de transporte: <" + nombre + "> | Precio de servicio base: <" + precioBase + ">";
    }
}
