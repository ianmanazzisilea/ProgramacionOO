package ar.edu.unlu.poo.tp2.ejercicio10;

import java.time.LocalDate;

public class EmpleadoComision extends Empleado{
    private int cantVentas;
    private double gananciaVentas;
    private double porcentajecomicion=0.5;//50%

    public EmpleadoComision(String nombre, String apellido, String telefono, String cuit, int añoNacimiento, int mesNacimiento, int diaNacimiento) {
        super(nombre, apellido, telefono, cuit, añoNacimiento, mesNacimiento, diaNacimiento);
    }

    @Override
    public double calcularSueldo() {
        if (super.getCumpleaños().getMonth() == LocalDate.now().getMonth()){
            return (gananciaVentas*porcentajecomicion+gananciaVentas*0.005)+2000;
        }
        return gananciaVentas*porcentajecomicion;
    }
}
