package ar.edu.unlu.poo.tp2.ej14;

public class Venta {
    private PaqueteTuristico paquete;
    private int cantidadClientes = 0;

    public Venta(PaqueteTuristico paquete) {
        this.paquete = paquete;
    }

    public void setCantidadClientes(int cantidadClientes) {
        this.cantidadClientes += cantidadClientes;
    }

    public PaqueteTuristico getPaquete() {
        return paquete;
    }
    public int getCantidadClientes() {
        return cantidadClientes;
    }
    public String toString() {
        return "Paquete con destino a <" + paquete.getDestino() + "> contratado por " + cantidadClientes
                + " personas";
    }
    public String soloDestino() {
        return "Paquete con destino a <" + paquete.getDestino() + ">";
    }
}
