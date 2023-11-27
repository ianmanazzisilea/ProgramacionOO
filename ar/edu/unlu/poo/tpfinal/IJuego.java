package ar.edu.unlu.poo.tpfinal;

import java.util.ArrayList;

public interface IJuego {

    void robar();

    void jugada(ArrayList<Integer> intindicemano, int indicemesa);

    void turno();
    void empezar();
}
