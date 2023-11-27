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
    public void robar(){
        modelo.robar();
    }
    public void jugada(ArrayList<String> indicemano,int indicemesa){
        ArrayList<Integer> manojugada = new ArrayList<>();
        for (int i = 0; i < indicemano.size(); i++) {
            int intindicemano = Integer.valueOf(indicemano.get(i));
            manojugada.add(intindicemano);
        }
        modelo.jugada(manojugada,indicemesa);
    }
    public void nocanto(){
        modelo.robar();
        modelo.robar();
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
    public boolean booleanbonus(){
        return modelo.getBBonus();
    }
    public int bonus(){
        return modelo.getBonus();
    }
    public void descartarbonus(int indicemano){
        modelo.descartarbonus(indicemano);
    }

    @Override
    public void update() {

    }
}
