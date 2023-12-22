package ar.edu.unlu.poo.tpfinal;

import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable {
    private ArrayList<Carta> mano = new ArrayList();
    private String nombre;
    public Jugador(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void roba(Carta carta){
        mano.add(carta);
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }
    public void desacartar(Carta indice){
        mano.remove(indice);
    }
}
