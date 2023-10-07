package ar.edu.unlu.poo.tp2.ej14;

public class ProveedorHospedaje extends Proveedor {
    int cantMinimaClientes;

    public ProveedorHospedaje(String nombre, double precioBase, int cantMinimaClientes) {
        super(nombre, precioBase);
        this.cantMinimaClientes = cantMinimaClientes;
    }

    @Override
    double calcularExtra(int cantMaxClientes) {
        double precioProveedor = precioBase;
        if (cantMinimaClientes > cantMaxClientes) {
            int clientesExtras = cantMinimaClientes - cantMaxClientes;
            double descuento = clientesExtras * 0.005 * precioBase;
            precioProveedor -= descuento;
        }
        return precioProveedor;
    }

    public String toString() {
        return "Proveedor de hospedaje: <" + nombre + "> | Precio de servicio base: <" + precioBase + ">";
    }
}
