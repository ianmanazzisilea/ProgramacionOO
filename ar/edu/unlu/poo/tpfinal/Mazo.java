package ar.edu.unlu.poo.tpfinal;

import java.io.Serializable;
import java.util.ArrayList;

public class Mazo implements Serializable {
    static private ArrayList<Carta> cartas = new ArrayList();
    static private ArrayList<Carta> descarte = new ArrayList();

    public Mazo(){
        //azules
        for (int i = 1; i <= 10 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("azul",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 1; i <= 10 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("azul",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 1; i <= 5 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("azul",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 0; i < 2; i++) {
            Carta carta= new Carta("azul","#");
            cartas.add(carta);
        }
        //verde
        for (int i = 1; i <= 10 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("verde",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 1; i <= 10 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("verde",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 1; i <= 5 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("verde",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 0; i < 2; i++) {
            Carta carta= new Carta("verde","#");
            cartas.add(carta);
        }
        //amarillo
        for (int i = 1; i <= 10 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("amarillo",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 1; i <= 10 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("amarillo",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 1; i <= 5 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("amarillo",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 0; i < 2; i++) {
            Carta carta= new Carta("amarillo","#");
            cartas.add(carta);
        }
        //rojo
        for (int i = 1; i <= 10 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("rojo",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 1; i <= 10 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("rojo",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 1; i <= 5 ; i++) {
            if (i==2){
                Carta carta= new Carta("multicolor",String.valueOf(i));
                cartas.add(carta);
            }
            else {
                Carta carta= new Carta("rojo",String.valueOf(i));
                cartas.add(carta);
            }
        }
        for (int i = 0; i < 2; i++) {
            Carta carta= new Carta("rojo","#");
            cartas.add(carta);
        }
    }
    //------------------------------------------------------------------------
    private void mezclar(){
        //juntar mazos
        for (int i = 0; i < descarte.size(); i++) {
            Carta carta= descarte.get(i);
            cartas.add(carta);
            descarte.clear();
        }
    }
    public Carta getCartaSuperior(){
        if (cartas.size()==0){
            mezclar();
        }
        int random = (int) (Math.random() * cartas.size());
        Carta carta = cartas.get(random);
        cartas.remove(random);
        return carta;
    }
    public void descartar(Carta carta){
        descarte.add(carta);
    }
}
