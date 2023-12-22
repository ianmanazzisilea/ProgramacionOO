package ar.edu.unlu.poo.tpfinal;

import java.io.Serializable;
import java.util.ArrayList;

public class Mesa implements Serializable {
    private ArrayList<Carta> cartas = new ArrayList<>();

    public Carta getCarta(int indice) {
        return cartas.get(indice);
    }
    public void add(Carta carta){
        cartas.add(carta);
    }
    public void remove(int indice){
        cartas.remove(indice);
    }
    public int size(){
        return cartas.size();
    }
}
