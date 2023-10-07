package ar.edu.unlu.poo.tp2.ej14;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private ArrayList<Venta> compras;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.compras = new ArrayList<>();
    }

    public void realizarCompra(Venta venta) {
        compras.add(venta);
    }

    public ArrayList<Venta> getCompras() {
        return compras;
    }

    public String getNombre() {
        return nombre;
    }
}
