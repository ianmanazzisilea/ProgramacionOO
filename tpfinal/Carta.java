package ar.edu.unlu.poo.tpfinal;

import java.io.Serializable;

public class Carta implements Serializable {
    private String color;
    private String numero;
    public Carta(String color,String numero){
        this.color=color;
        this.numero=numero;
    }

    public String getColor() {
        return color;
    }

    public String getNumero() {
        return numero;
    }
}
