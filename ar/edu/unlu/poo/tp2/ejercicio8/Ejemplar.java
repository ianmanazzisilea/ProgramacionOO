package ar.edu.unlu.poo.tp2.ejercicio8;

public class Ejemplar {
    private boolean prestado=false;
    private int id;
    private String condicionFisica="";
    public Ejemplar(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void prestar(){
        prestado=true;
    }
    public void devolver(){
        prestado=false;
    }
    public boolean getPrestado(){
        return prestado;
    }
}
