package ar.edu.unlu.poo.tpfinal;

import java.util.ArrayList;

public class Juego implements IJuego,Subject{
    static private ArrayList<Jugador> jugadores = new ArrayList();
    private Mazo mazo = new Mazo();
    static private ArrayList<Carta> cartas = new ArrayList();//cartas en mesa
    private int turno=0;
    private int bonus = 0;
    private ArrayList<Observer> observers = new ArrayList<>();
    private String subjectState;
    private Controlador controlador;//temporal
    //tambien temporal

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    //inicio del juego------------------------------------
    public void empezar(){
        /*for (int i = 0; i < observers.size(); i++) {
            jugadores.add(new Jugador());
        }*/
        //repartir cartas
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < jugadores.size(); j++) {
                jugadores.get(i).roba(mazo.getCartaSuperior());
            }
        }
        cartas.add(mazo.getCartaSuperior());
        cartas.add(mazo.getCartaSuperior());
            controlador.setfase();
        actualizarvista();
        //notifyMessage();
    }

    private static ArrayList<String> getCartas() {
        ArrayList<String> mesa=new ArrayList<>();
        for (int j = 0; j < cartas.size(); j++) {
            mesa.add(cartas.get(j).getColor() + " " + cartas.get(j).getNumero());
        }
        return mesa;
    }

    public static ArrayList<String> getMano(int i) {
        ArrayList<String> mano=new ArrayList<>();
        for (int j = 0; j < jugadores.get(i).getMano().size(); j++) {
            mano.add(jugadores.get(i).getMano().get(j).getColor() + " " + jugadores.get(i).getMano().get(j).getNumero());
        }
        return mano;
    }

    private void actualizarvista(){
        for (int i = 0; i < jugadores.size(); i++) {
            controlador.mostrar(getCartas(),getMano(i));
        }
    }
    public void robar(int indicejugador){
        jugadores.get(indicejugador).roba(mazo.getCartaSuperior());
    }
    public void jugada(int indicejugador,int indicemano, int indicemesa){
        Carta cartajugador = jugadores.get(indicejugador).getMano().get(indicemano);
        Carta cartamesa = cartas.get(indicemesa);
        if (cartajugador.getNumero()==cartamesa.getNumero()){
            if (cartajugador.getColor()==cartamesa.getColor()){
                bonus++;
            }
            jugadores.get(indicejugador).desacartar(indicemano);
            cartas.remove(indicemesa);
            mazo.descartar(cartajugador);
            mazo.descartar(cartamesa);
        }
    }

    //turno----------------------------------------------------
    public void turno(){
        //bonus
        for (int i = 0; i < cartas.size() - 2; i++) {
            cartas.add(mazo.getCartaSuperior());
        }
        for (int i = 0; i < bonus; i++) {
            cartas.add(mazo.getCartaSuperior());
        }
        bonus = 0;
        //pasar turno
        if (turno == (jugadores.size()-1)){
            turno=0;
        }
        else turno++;
        controlador.setfase();//temporal
    }
//----------------------------subject-----------------------------------------------
    @Override
    public void attach(Observer anObserver) {
        observers.add(anObserver);
    }

    @Override
    public void detach(Observer anObserver) {
        observers.remove(anObserver);
    }

    @Override
    public void notifyMessage() {
        for (Observer observer: observers) {
            observer.update();
        }
    }

    public void newMessage(String message) {
        subjectState = message;
        this.notifyMessage();
    }

    public String getState() {
        return subjectState;
    };
}
