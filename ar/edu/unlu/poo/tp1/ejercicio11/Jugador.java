package ar.edu.unlu.poo.tp1.ejercicio11;

import java.util.ArrayList;

public class Jugador {
    private int puntos = 0;
    private String nombre;
    private ArrayList<String> palabras = new ArrayList<>();

    public void setPalabra(String palabra) {
        this.palabras.add(palabra);
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }
}
