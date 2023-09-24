package ar.edu.unlu.poo.tp2.ejercicio7;

public class Empleado {
    private String nombre;
    private int id;
    private byte turno;//1 a 3

    public int getId() {
        return id;
    }
    public Empleado(int id, String nombre,String sturno){
        this.id = id;
        this.nombre=nombre;
        if (sturno=="mañana"){
            turno=1;
        }
        else
            if (sturno=="tarde"){
                turno=2;
            }
            else
                if(sturno=="noche"){
                    turno=3;
                }
                else turno=0;
    }
}
