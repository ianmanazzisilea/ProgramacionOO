package ar.edu.unlu.poo.tpfinal;

import java.util.ArrayList;

public class Jugador {
    static private ArrayList<Carta> mano = new ArrayList();

    public int getCantidadCarta(){
        return mano.size();
    }
    public void roba(Carta carta){
        mano.add(carta);
    }

    public ArrayList<Carta> getMano() {
        return mano;
    }
    public void desacartar(int indice){
        mano.remove(indice);
    }
}
