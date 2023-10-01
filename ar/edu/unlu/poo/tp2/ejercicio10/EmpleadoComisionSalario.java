package ar.edu.unlu.poo.tp2.ejercicio10;

import java.time.LocalDate;

public class EmpleadoComisionSalario extends Empleado{
    private int cantVentas;
    private int gananciaVentas;
    private double porcentajecomicion=0.5;//50%
    private int sueldo;

    public EmpleadoComisionSalario(String nombre, String apellido, String telefono, String cuit, int añoNacimiento, int mesNacimiento, int diaNacimiento) {
        super(nombre, apellido, telefono, cuit, añoNacimiento, mesNacimiento, diaNacimiento);
    }

    @Override
    public double calcularSueldo() {
        if (super.getCumpleaños().getMonth() == LocalDate.now().getMonth()){
            return gananciaVentas*porcentajecomicion+gananciaVentas*0.005+3000;
        }
        return sueldo+(gananciaVentas*porcentajecomicion);
    }
}
