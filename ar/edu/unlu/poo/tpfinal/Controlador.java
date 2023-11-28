package ar.edu.unlu.poo.tpfinal;

import java.util.ArrayList;

public class Controlador{
    private IVista vista;
    private Juego modelo;
    private ArrayList<String> jugadores = new ArrayList<>();
    private Jugador jugador;
    public Controlador(Juego modelo, Jugador jugador) {
        //this.vista = vista;
        this.modelo = modelo;
        this.modelo.ingresarJugador(jugador);
        this.jugador = jugador;
    }

    public void setVista(IVista vista) {
        this.vista = vista;
    }

    public void hayganador(){
        modelo.hayganador(jugador);
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

    public Juego getModelo() {
        return modelo;
    }
    public void setobserver(Observer observer){
        modelo.attach(observer);
    }
    public void empezar(){
        modelo.empezar();
    }
    //temporal

    public boolean getTurno(){
        return modelo.getTurno(jugador);
    }
    public ArrayList<String> getMano() {
        ArrayList<String> mano=new ArrayList<>();
        for (int j = 0; j < jugador.getMano().size(); j++) {
            mano.add(jugador.getMano().get(j).getColor() + " " + jugador.getMano().get(j).getNumero());
        }
        return mano;
    }
    public ArrayList<String> getMesa(){
        return modelo.getCartas();
    }
    public String ganador(){
        return modelo.getGanador();
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
}
