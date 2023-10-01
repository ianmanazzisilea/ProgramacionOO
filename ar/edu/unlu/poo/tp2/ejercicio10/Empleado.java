package ar.edu.unlu.poo.tp2.ejercicio10;

import java.time.LocalDate;

public abstract class Empleado {
    //nombre, apellido, teléfono y CUIT.
    private String nombre;
    private String apellido;
    private String telefono;
    private String cuit;
    private LocalDate cumpleaños;

    public LocalDate getCumpleaños() {
        return cumpleaños;
    }

    public Empleado(String nombre, String apellido, String telefono, String cuit, int añoNacimiento, int mesNacimiento, int diaNacimiento){
        this.apellido=apellido;
        this.nombre=nombre;
        this.telefono=telefono;
        this.cuit=cuit;
        this.cumpleaños=LocalDate.of(añoNacimiento,mesNacimiento,diaNacimiento);
    }
    public abstract double calcularSueldo();

}
