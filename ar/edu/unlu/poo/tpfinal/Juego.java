package ar.edu.unlu.poo.tpfinal;

import java.util.ArrayList;

public class Juego implements Subject{
    private ArrayList<Observer> observers = new ArrayList<>() ;
    static private ArrayList<Jugador> jugadores = new ArrayList();
    private Mazo mazo = new Mazo();
    static private ArrayList<Carta> cartas = new ArrayList();//cartas en mesa
    private int turno=0;
    private int bonus = 0;
    private ArrayList<Carta> cartasbonus = new ArrayList<>();
    private Jugador jugadorganador;
    //----------------------------------GETTERS-------------------------------
    public boolean getTurno(Jugador jugador){
        if (jugador==jugadores.get(turno)){
            return true;
        }
        return false;
    }
    public String getGanador() {
        return jugadorganador.getNombre();
    }

    public void ingresarJugador(Jugador jugador){
        jugadores.add(jugador);
    }
    public int getBonus() {
        return bonus;
    }
    public boolean getBBonus() {
        if (bonus > 0){
            return true;
        }
        else return false;
    }
    public void hayganador(Jugador jugador){
        jugadorganador = jugador;
        notifyMessage(Evento.FIN_PARTIDA);
    }
    public void descartarbonus(int indicemano,Jugador jugador){
        Carta carta = jugador.getMano().get(indicemano);
        cartasbonus.add(carta);
        jugador.desacartar(carta);
    }

    //inicio del juego------------------------------------
    public void empezar(){
        //repartir cartas
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < jugadores.size(); j++) {
                jugadores.get(j).roba(mazo.getCartaSuperior());
            }
        }
        cartas.add(mazo.getCartaSuperior());
        cartas.add(mazo.getCartaSuperior());
        notifyMessage(Evento.INICIO_TURNO);
        actualizarvista();
    }

    public static ArrayList<String> getCartas() {
        ArrayList<String> mesa=new ArrayList<>();
        for (int j = 0; j < cartas.size(); j++) {
            mesa.add(cartas.get(j).getColor() + " " + cartas.get(j).getNumero());
        }
        return mesa;
    }

    private void actualizarvista(){
            notifyMessage(Evento.MOSTRAR_MESA);
    }
    public void robar(Jugador jugador){
        jugador.roba(mazo.getCartaSuperior());
        actualizarvista();
    }
    public void jugada(ArrayList<Integer> indicemano, int indicemesa,Jugador jugador){
        ArrayList<Carta> jugadamano = new ArrayList<>();
        int comodinnumero = 0;
        int acumulador = 0;
        Carta cartamesa = cartas.get(indicemesa);
        for (int i = 0; i < indicemano.size(); i++) {
            Carta cartajugador = jugador.getMano().get(indicemano.get(i));
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
                    jugador.desacartar(jugadamano.get(i));
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
                        jugador.desacartar(jugadamano.get(i));
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
                    jugador.desacartar(jugadamano.get(i));
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
        cartasbonus.clear();
        bonus=0;
        //pasar turno
        if (turno == (jugadores.size()-1)){
            turno=0;
        }
        else turno++;
        actualizarvista();
        notifyMessage(Evento.INICIO_TURNO);
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
