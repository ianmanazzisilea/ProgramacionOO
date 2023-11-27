package ar.edu.unlu.poo.tpfinal;

import java.util.ArrayList;

public class Juego implements Subject{
    private ArrayList<Observer> observers;
    static private ArrayList<Jugador> jugadores = new ArrayList();
    private Mazo mazo = new Mazo();
    static private ArrayList<Carta> cartas = new ArrayList();//cartas en mesa
    private int turno=0;
    private int bonus = 0;
    private ArrayList<Observer> observers = new ArrayList<>();
    private String subjectState;
    private Controlador controlador;//temporal
    private ArrayList<Carta> cartasbonus = new ArrayList<>();

    public int getBonus() {
        return bonus;
    }
    public boolean getBBonus() {
        if (bonus > 0){
            return true;
        }
        else return false;
    }
    public void descartarbonus(int indicemano){
        Carta carta = jugadores.get(0).getMano().get(indicemano);
        cartasbonus.add(carta);
        jugadores.get(0).desacartar(carta);
    }
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    //inicio del juego------------------------------------
    public void empezar(){
        /*for (int i = 0; i < observers.size(); i++) {
            jugadores.add(new Jugador());
        }*/
        jugadores.add(new Jugador());
        //repartir cartas
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < jugadores.size(); j++) {
                jugadores.get(j).roba(mazo.getCartaSuperior());
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
    public void robar(){
        //0 es temporal------------------------------------------
        jugadores.get(0).roba(mazo.getCartaSuperior());
        actualizarvista();
    }
    public void jugada(ArrayList<Integer> indicemano, int indicemesa){
        ArrayList<Carta> jugadamano = new ArrayList<>();
        int comodinnumero = 0;
        int acumulador = 0;
        Carta cartamesa = cartas.get(indicemesa);
        for (int i = 0; i < indicemano.size(); i++) {
            //el 0 es temporal----------------------------------------------
            Carta cartajugador = jugadores.get(0).getMano().get(indicemano.get(i));
            jugadamano.add(cartajugador);
            if (cartajugador.getNumero() == "#"){
                comodinnumero++;
            }
            else
                acumulador = Integer.valueOf(cartajugador.getNumero()) + acumulador;
        }
        if (cartas.get(indicemesa).getNumero() == "#"){
            if (acumulador <= 10){
                boolean bbonus = compararcolores(jugadamano,indicemesa);
                if (bbonus){
                    bonus++;
                }
                for (int i = 0; i < indicemano.size(); i++) {
                    jugadores.get(0).desacartar(jugadamano.get(i));
                }
                cartas.remove(indicemesa);
                for (int i = 0; i < jugadamano.size(); i++) {
                    mazo.descartar(jugadamano.get(i));
                }
                mazo.descartar(cartamesa);
            }
        }
        else
            if (comodinnumero > 0){
                if ((acumulador + comodinnumero) <= Integer.valueOf(cartamesa.getNumero())){
                    boolean bbonus = compararcolores(jugadamano,indicemesa);
                    if (bbonus){
                        bonus++;
                    }
                    for (int i = 0; i < indicemano.size(); i++) {
                        jugadores.get(0).desacartar(jugadamano.get(i));
                    }
                    cartas.remove(indicemesa);
                    for (int i = 0; i < jugadamano.size(); i++) {
                        mazo.descartar(jugadamano.get(i));
                    }
                    mazo.descartar(cartamesa);
                }
                else {
                    //seleciono cartas no validas
                }
            } else if (acumulador == Integer.valueOf(cartamesa.getNumero())) {
                boolean bbonus = compararcolores(jugadamano,indicemesa);
                if (bbonus){
                    bonus++;
                }
                for (int i = 0; i < indicemano.size(); i++) {
                    jugadores.get(0).desacartar(jugadamano.get(i));
                }
                cartas.remove(indicemesa);
                for (int i = 0; i < jugadamano.size(); i++) {
                    mazo.descartar(jugadamano.get(i));
                }
                mazo.descartar(cartamesa);
            }
        actualizarvista();
    }
    private boolean compararcolores(ArrayList<Carta> indicemano, int indicemesa){
        boolean mismo = true;
        for (int i = 0; i < indicemano.size(); i++) {
            if (indicemano.get(i).getNumero() != "2"){
                if (cartas.get(indicemesa).getNumero() != "2"){
                    if (indicemano.get(i).getColor() != cartas.get(indicemesa).getColor()){
                        mismo = false;
                    }
                }

            }
        }
        return mismo;
    }

    //turno----------------------------------------------------
    public void turno(){
        //bonus
        while (cartas.size()<2){
            cartas.add(mazo.getCartaSuperior());
        }
        for (int i = 0; i < cartasbonus.size(); i++) {
            cartas.add(cartasbonus.get(i));
        }
        //pasar turno
        if (turno == (jugadores.size()-1)){
            turno=0;
        }
        else turno++;
        controlador.setfase();//temporal
        actualizarvista();
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
    public void notifyMessage(Evento evento) {
        for (Observer observer: observers) {
            observer.update(this,evento);
        }
    }
}
