package ar.edu.unlu.poo.tp2.ejercicio3;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Diagramacion {
    private String disciplina;
    private LocalTime horario;
    private DayOfWeek dia;
    private byte nivel;

    public Diagramacion(DayOfWeek dia,LocalTime horario, String nivel){
        if (nivel == "inicial"){
            this.nivel = 1;
        }
        else
        if (nivel == "intermedio"){
            this.nivel = 2;
        }
        else
        if (nivel == "avanzado"){
            this.nivel = 3;
        }
        else this.nivel = 0; //error
        this.horario=horario;
        this.dia=dia;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getNivel() {
        if (nivel == 1){
            return "inicial";
        }
        else
        if (nivel == 2){
            return "intermedio";
        }
        if (nivel == 3){
            return "avanzado";
        }
        else return "mal ingresado";
    }

    public DayOfWeek getDia() {
        return dia;
    }
}
