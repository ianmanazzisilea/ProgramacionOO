package ar.edu.unlu.poo.tp2.ejercicio10;

import java.time.LocalDate;

public class EmpleadoAsalariado extends Empleado {
    private int sueldo;

    public EmpleadoAsalariado(String nombre, String apellido, String telefono, String cuit, int añoNacimiento, int mesNacimiento, int diaNacimiento) {
        super(nombre, apellido, telefono, cuit, añoNacimiento, mesNacimiento, diaNacimiento);
    }

    @Override
    public double calcularSueldo() {
        if (super.getCumpleaños().getMonth() == LocalDate.now().getMonth()){
            return sueldo+3000;
        }
        return sueldo;
    }
}
