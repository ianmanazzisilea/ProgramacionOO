package ar.edu.unlu.poo.tp2.ejercicio1;

import java.time.LocalDate;

public class Socio {
    private String id;
    private String nombre;
    private String email;
    private String dirreccion;
    private byte estado;
    private LocalDate fechainscripcion;

    public Socio(String nombre, String id, String email, String dirreccion, String estado, LocalDate fechainscripcion){
        if (estado == "basica"){
            this.estado = 1;
        }
        else
            if (estado == "intermedia"){
                this.estado = 2;
            }
            else
                if (estado == "destacada"){
                    this.estado = 3;
                }
                else this.estado = 0; //sin pagar
        this.email = email;
        this.id = id;
        this.dirreccion = dirreccion;
        this.fechainscripcion=fechainscripcion;
        this.nombre=nombre;
    }

    public String getDirreccion() {
        return dirreccion;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getEstado() {
        if (estado == 1){
            return "basica";
        }
        else
        if (estado == 2){
            return "intermedia";
        }
        else
        if (estado == 3){
            return "destacada";
        }
        else return "no es socio"; //sin pagar
    }

    public LocalDate getFechainscripcion() {
        return fechainscripcion;
    }

    public String getNombre() {
        return nombre;
    }
}
