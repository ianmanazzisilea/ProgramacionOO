package ar.edu.unlu.poo.tp2.ejercicio3;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

public class Profesor {
    private String nombre;
    private String disciplina;
    private ArrayList<Alumno> alumnos = new ArrayList<>();
    private ArrayList<Diagramacion> diagramas = new ArrayList<>();
    public Profesor(String nombre, String disciplina){
        this.nombre=nombre;
        this.disciplina=disciplina;
    }
    public void agregaralumno(String legajo,String nombre){
        Alumno alumno1 = new Alumno(legajo, nombre);
    }
    public void agregardiagrama(String dia, String hora, String minuto, String nivel){
        LocalTime horario = LocalTime.of(Integer.valueOf(hora),Integer.valueOf(minuto));
        Diagramacion diagrama = new Diagramacion(DayOfWeek.valueOf(dia), horario ,nivel);
        diagramas.add(diagrama);
    }
    public void agregarasistencia(String legajo,Month mes){
        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).getLegajo()==legajo){
                alumnos.get(i).agregarasistencia(mes);
            }
        }
    }

    public ArrayList<Diagramacion> getDiagramas() {
        return diagramas;
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public String getNombre() {
        return nombre;
    }
    public void cobra(){
        int paga=0;
        for (int i = 0; i < alumnos.size(); i++) {
            for (int j = 0; j < alumnos.get(i).getAsistencias().size(); j++) {
                paga=paga+alumnos.get(i).getAsistencias().get(j);
            }
        }
        paga=paga*10;
    }

    public String getDisciplina() {
        return disciplina;
    }
}
