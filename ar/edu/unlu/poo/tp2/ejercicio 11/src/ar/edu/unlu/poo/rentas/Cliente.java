package ar.edu.unlu.poo.rentas;

public class Cliente {
    private String nombre;
    private int dni;
    private int MontoTotal;

    public Cliente (String nombre,int dni){
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public int getMontoTotal() {
        return MontoTotal;
    }

    public void Actualizar_monto(int monto){
        this.MontoTotal = this.MontoTotal + monto;
    }

}
