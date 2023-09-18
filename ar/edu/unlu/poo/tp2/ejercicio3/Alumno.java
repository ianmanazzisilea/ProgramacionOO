package ar.edu.unlu.poo.tp2.ejercicio3;

import java.time.Month;
import java.util.ArrayList;

public class Alumno {
    private String legajo;
    private String nombre;
    private ArrayList<Integer> asistencias = new ArrayList<>();

    public Alumno(String legajo, String nombre){
        this.legajo = legajo;
        this.nombre = nombre;
        for (int i = 0; i < 12; i++) {
            asistencias.add(0);
        }
    }

    public String getLegajo() {
        return legajo;
    }

    public void agregarasistencia(Month mes){
        asistencias.add(mes.getValue(),asistencias.get(mes.getValue())+1);
    }

    public ArrayList<Integer> getAsistencias() {
        return asistencias;
    }
}
