package ar.edu.unlu.poo.tp2.ej14;

public class ProveedorGuia extends Proveedor {
    public ProveedorGuia(String nombre, double precioBase) {
        super(nombre, precioBase);
    }

    @Override
    double calcularExtra(int cantMaxClientes) {
        if (cantMaxClientes > 200) {
            return precioBase + 90;
        }
        return precioBase;
    }
    public String toString() {
        return "Guia de Turismo: <" + nombre + "> | Precio de servicio base: <" + precioBase + ">";
    }
}
