package ar.edu.unlu.poo.tp2.ej14;

import java.util.ArrayList;

public class PaqueteTuristico {
    private String destino;
    private ArrayList<Proveedor> proveedores;

    public PaqueteTuristico(String destino) {
        this.destino = destino;
        this.proveedores = new ArrayList<>();
    }

    public void agregarProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }

    public String getDestino() {
        return destino;
    }

    public StringBuilder mostrarPaquete() {
        StringBuilder paquete = new StringBuilder();
        for (Proveedor proveedor : proveedores) {
            paquete.append(proveedor.toString()).append("\n");
        }
        paquete.append("Destino: <").append(destino).append(">");
        return paquete;
    }
}
