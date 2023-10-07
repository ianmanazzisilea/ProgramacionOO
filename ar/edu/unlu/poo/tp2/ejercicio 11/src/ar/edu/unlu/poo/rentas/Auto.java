package ar.edu.unlu.poo.rentas;

public class Auto {
    private String marca;
    private String patente;
    private int km = 0;
    protected int precio = 3000;

    public Auto (String marca, String patente){
        this.marca = marca;
        this.patente = patente;
    }

    public int getPrecio() {
        return precio;
    }

    public String getPatente(){
        return patente;
    }
}
