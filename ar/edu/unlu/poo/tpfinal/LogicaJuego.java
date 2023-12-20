package ar.edu.unlu.poo.tpfinal;

import java.util.ArrayList;

public class LogicaJuego {
    public Integer[] emparejar(ArrayList<Carta> jugadamano, Carta cartamesa){
        int comodinnumero = 0;
        int acumulador = 0;
        Integer[] bonusmasesigual = new Integer[2];//0 para bonus 1 para booleano
        bonusmasesigual[0] = 0;
        bonusmasesigual[1] = 0;
        for (int i = 0; i < jugadamano.size(); i++) {
            Carta cartajugador = jugadamano.get(i);
            if (cartajugador.getNumero() == "#"){
                comodinnumero++;
            }
            else
                acumulador = Integer.valueOf(cartajugador.getNumero()) + acumulador;
        }
        if (cartamesa.getNumero() == "#"){
            if (acumulador <= 10){
                boolean bbonus = compararcolores(jugadamano,cartamesa);
                if (bbonus){
                    bonusmasesigual[0]++;
                }
                bonusmasesigual[1] = 1;
            }
        }
        else
        if (comodinnumero > 0){
            if ((acumulador + comodinnumero) <= Integer.valueOf(cartamesa.getNumero())){
                boolean bbonus = compararcolores(jugadamano,cartamesa);
                if (bbonus){
                    bonusmasesigual[0]++;
                }
                bonusmasesigual[1] = 1;
            }
            else {
                //seleciono cartas no validas
            }
        } else if (acumulador == Integer.valueOf(cartamesa.getNumero())) {
            boolean bbonus = compararcolores(jugadamano,cartamesa);
            if (bbonus){
                bonusmasesigual[0]++;
            }
            bonusmasesigual[1] = 1;
        }
        return bonusmasesigual;
    }
    private boolean compararcolores(ArrayList<Carta> indicemano, Carta indicemesa){
        boolean mismo = true;
        for (int i = 0; i < indicemano.size(); i++) {
            if (indicemano.get(i).getNumero() != "2"){
                if (indicemesa.getNumero() != "2"){
                    if (indicemano.get(i).getColor() != indicemesa.getColor()){
                        mismo = false;
                    }
                }

            }
        }
        return mismo;
    }
}
