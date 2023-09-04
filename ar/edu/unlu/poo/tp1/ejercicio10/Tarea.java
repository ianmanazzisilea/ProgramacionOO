package ar.edu.unlu.poo.tp1.ejercicio10;

import java.time.LocalDate;

public class Tarea {
    private Object descripcion;
    private boolean estado;
    private LocalDate fechalimite;
    private LocalDate fecharecordatorio;
    private Tarea siguiente;

    public void setDescripcion(Object descripcion){
        this.descripcion = descripcion;
    }
    public Object getDescripcion(){
        return descripcion;
    }

    public void setEstado(boolean estado){
        this.estado = estado;
    }

    public boolean getEstado(){
        return estado;
    }

    public LocalDate getFechalimite() {
        return fechalimite;
    }

    public void setFechalimite(LocalDate fechalimite) {
        this.fechalimite = fechalimite;
    }

    public void setSiguiente(Tarea siguiente) {
        this.siguiente = siguiente;
    }
    public Tarea getSiguiente() {
        return siguiente;
    }

    public void setFecharecordatorio(LocalDate fecharecordatorio) {
        this.fecharecordatorio = fecharecordatorio;
    }

    public LocalDate getFecharecordatorio() {
        return fecharecordatorio;
    }
}
