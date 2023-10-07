package ar.edu.unlu.poo.tp2.ej12;

public class Cliente {
    /*Responsabilidades
        Tener sus datos
        Conocer su billetera virtual?
     */
    private String nombre, apellido, dni, email;
    Cliente(String nombre, String apellido, String dni, String email){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
    }
    public String toString(){
        return "Apellido y Nombre: "+apellido + " " + nombre + "\nDni: " + dni + "\nE-mail: "+ email + "\n";
    }
}
