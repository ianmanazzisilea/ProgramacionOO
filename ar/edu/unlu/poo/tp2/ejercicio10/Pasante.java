package ar.edu.unlu.poo.tp2.ejercicio10;

public class Pasante extends Empleado{
    public Pasante(String nombre, String apellido, String telefono, String cuit, int añoNacimiento, int mesNacimiento, int diaNacimiento) {
        super(nombre, apellido, telefono, cuit, añoNacimiento, mesNacimiento, diaNacimiento);
    }

    @Override
    public double calcularSueldo() {
        return 0;
    }
}
