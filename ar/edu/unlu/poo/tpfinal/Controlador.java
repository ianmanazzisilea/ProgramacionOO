package ar.edu.unlu.poo.tpfinal;

import java.util.ArrayList;

public class Controlador implements Observer{
    private IVista vista;
    private Juego modelo;
    private ArrayList<String> jugadores = new ArrayList<>();

    public void setfase(){
        vista.setFaseactual();
    }
    public Controlador(IVista vista, Juego modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    public void robar(String jugador){
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i) == jugador){
                modelo.robar(i);
            }
        }
    }
    public void jugada(String jugador,String indicemano,int indicemesa){
        int intindicemano = Integer.valueOf(indicemano);
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i) == jugador){
                modelo.jugada(i,intindicemano,indicemesa);
            }
        }
    }
    public void nocanto(String jugador){
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i) == jugador){
                modelo.robar(i);
                modelo.robar(i);
            }
        }
    }
    public void pasoturno(){
        modelo.turno();
    }

    public void empezar(){
        modelo.empezar();
    }
    //temporal
    public void mostrar(ArrayList<String> listamesa,ArrayList<String> listamano){
        vista.mesaactualizada(listamesa, listamano);
    }
    @Override
    public void update() {

    }
}
