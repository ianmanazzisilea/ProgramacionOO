package ar.edu.unlu.poo.tp2.ejercicio10;

import java.time.LocalDate;
import java.util.Scanner;

public class EmpleadoHora extends Empleado{
    private int sueldoPHora;

    public EmpleadoHora(String nombre, String apellido, String telefono, String cuit, int añoNacimiento, int mesNacimiento, int diaNacimiento) {
        super(nombre, apellido, telefono, cuit, añoNacimiento, mesNacimiento, diaNacimiento);
    }

    @Override
    public double calcularSueldo() {
        System.out.println("¿Cuantas horas trabajará?");
        Scanner myObj = new Scanner(System.in);
        int cantHoras = Integer.valueOf(myObj.nextLine());
        if (cantHoras>40){
            if (super.getCumpleaños().getMonth() == LocalDate.now().getMonth()){
                return (cantHoras*sueldoPHora)*1.2+2000;
            }
            else return (cantHoras*sueldoPHora)*1.2;
        }
        else
            if (super.getCumpleaños().getMonth() == LocalDate.now().getMonth()){
                return (cantHoras*sueldoPHora)+2000;
            }
            else return(cantHoras*sueldoPHora);
    }
}
