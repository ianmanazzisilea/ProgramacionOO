package ar.edu.unlu.poo.tp2.ejercicio2;

import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private String dni;
    private Proveedor transporte;
    private Proveedor hospedaje;
    private ArrayList<Proveedor> excurciones = new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public Proveedor getTransporte() {
        return transporte;
    }

    public Proveedor getHospedaje() {
        return hospedaje;
    }

    public ArrayList<Proveedor> getExcurciones() {
        return excurciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setTransporte(Proveedor transporte) {
        this.transporte = transporte;
    }

    public void setHospedaje(Proveedor hospedaje) {
        this.hospedaje = hospedaje;
    }

    public void setExcurciones(ArrayList<Proveedor> excurciones) {
        this.excurciones = excurciones;
    }
}
