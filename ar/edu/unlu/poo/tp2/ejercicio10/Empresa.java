package ar.edu.unlu.poo.tp2.ejercicio10;

import java.util.ArrayList;
import java.util.Scanner;

public class Empresa {
    private ArrayList<Empleado> empleados = new ArrayList();
    public void crearEmpleado(String tipoEmpleado){
        System.out.println("nombre");
        Scanner myObj = new Scanner(System.in);
        String nombre = myObj.nextLine();
        System.out.println("apellido");
        myObj = new Scanner(System.in);
        String apellido = myObj.nextLine();
        System.out.println("telefono");
        myObj = new Scanner(System.in);
        String telefono = myObj.nextLine();
        System.out.println("cuit");
        myObj = new Scanner(System.in);
        String cuit = myObj.nextLine();
        System.out.println("dia de nacimiento");
        myObj = new Scanner(System.in);
        int dia = Integer.valueOf(myObj.nextLine());
        System.out.println("mes de nacimiento");
        myObj = new Scanner(System.in);
        int mes = Integer.valueOf(myObj.nextLine());
        System.out.println("año de nacimiento");
        myObj = new Scanner(System.in);
        int año = Integer.valueOf(myObj.nextLine());
        if (tipoEmpleado == "pasante"){
            Pasante empleadoNuevo = new Pasante(nombre,apellido,telefono,cuit,año,mes,dia);
            empleados.add(empleadoNuevo);
        }
        if (tipoEmpleado == "empleadohora"){
            EmpleadoHora empleadoNuevo = new EmpleadoHora(nombre,apellido,telefono,cuit,año,mes,dia);
            empleados.add(empleadoNuevo);
        }
        if (tipoEmpleado == "empleadosalario"){
            EmpleadoAsalariado empleadoNuevo = new EmpleadoAsalariado(nombre,apellido,telefono,cuit,año,mes,dia);
            empleados.add(empleadoNuevo);
        }
        if (tipoEmpleado == "empleadocomision"){
            EmpleadoComision empleadoNuevo = new EmpleadoComision(nombre,apellido,telefono,cuit,año,mes,dia);
            empleados.add(empleadoNuevo);
        }
        if (tipoEmpleado == "empleadocomisionsalario"){
            EmpleadoComisionSalario empleadoNuevo = new EmpleadoComisionSalario(nombre,apellido,telefono,cuit,año,mes,dia);
            empleados.add(empleadoNuevo);
        }
    }
    public double getSalario(int idEmpleado){
        if (idEmpleado<=empleados.size()){
            return empleados.get(idEmpleado-1).calcularSueldo();
        }
        return -1;
    }
}
