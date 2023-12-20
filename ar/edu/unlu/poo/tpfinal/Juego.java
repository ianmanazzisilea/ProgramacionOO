package ar.edu.unlu.poo.tpfinal;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
//implements Subject
public class Juego extends ObservableRemoto implements IJuego, Serializable {
    static private ArrayList<Jugador> jugadores = new ArrayList();
    static private LogicaJuego logicajuego = new LogicaJuego();
    static private Mesa mesa = new Mesa();
    static private Mazo mazo = new Mazo();
    static private int turno=0;
    static private int bonus = 0;
    static private ArrayList<Carta> cartasbonus = new ArrayList<>();
    static private Jugador jugadorganador;
    static private int clientes = 0;

    //----------------------------------GETTERS-------------------------------
    @Override
    public boolean getTurno(int indice)throws RemoteException{
        if (jugadores.get(indice)==jugadores.get(turno)){
            return true;
        }
        return false;
    }
    @Override
    public int getIndice(IControladorRemoto anObserver)throws RemoteException{
        return clientes++;
    }
    @Override
    public ArrayList<String> getMano(int indice)throws RemoteException{
        ArrayList<String> nombre = new ArrayList<>();
        for (int i = 0; i < jugadores.get(indice).getMano().size(); i++) {
            nombre.add(jugadores.get(indice).getMano().get(i).getColor() + " " +
                    jugadores.get(indice).getMano().get(i).getNumero());
        }
        return nombre;
    }
    @Override
    public String getNombre(int indice)throws RemoteException{
        String nombre = jugadores.get(indice).getNombre();
        return nombre;
    }
    @Override
    public String getGanador()throws RemoteException {
        return jugadorganador.getNombre();
    }

    @Override
    public Integer ingresarJugador()throws RemoteException{
        jugadores.add(new Jugador("jugador" + String.valueOf(jugadores.size() + 1)));
        return jugadores.size();
    }
    @Override
    public int getBonus()throws RemoteException {
        return bonus;
    }
    @Override
    public boolean getBBonus()throws RemoteException {
        if (bonus > 0){
            return true;
        }
        else return false;
    }
    @Override
    public ArrayList<String> getCartas() throws RemoteException {
        ArrayList<String> mesaString = new ArrayList<>();
        for (int j = 0; j < Juego.mesa.size(); j++) {
            mesaString.add(Juego.mesa.getCarta(j).getColor() + " " + Juego.mesa.getCarta(j).getNumero());
        }
        return mesaString;
    }
    @Override
    public void hayganador()throws RemoteException{
        jugadorganador = jugadores.get(turno);
        notificarObservadores(Evento.FIN_PARTIDA);
    }
    @Override
    public void descartarbonus(int indicemano)throws RemoteException{
        Carta carta = jugadores.get(turno).getMano().get(indicemano);
        cartasbonus.add(carta);
        jugadores.get(turno).desacartar(carta);
    }

    //inicio del juego------------------------------------
    @Override
    public void empezar() throws RemoteException{
        //repartir cartas
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < jugadores.size(); j++) {
                jugadores.get(j).roba(mazo.getCartaSuperior());
            }
        }
        mesa.add(mazo.getCartaSuperior());
        mesa.add(mazo.getCartaSuperior());
        notificarObservadores(Evento.INICIO_TURNO);
        actualizarvista();
    }


    @Override
    public void robar()throws RemoteException{
        jugadores.get(turno).roba(mazo.getCartaSuperior());
        actualizarvista();
    }
    @Override
    public void jugada(ArrayList<Integer> indicemano, int indicemesa)throws RemoteException{
        ArrayList<Carta> mano = new ArrayList<>();
        for (int i = 0; i < indicemano.size(); i++) {
            mano.add(jugadores.get(turno).getMano().get(indicemano.get(i)));
        }
        Carta mesacarta = mesa.getCarta(indicemesa);
        Integer[] retorno = logicajuego.emparejar(mano,mesacarta);
        if (retorno[1] == 1){
            for (int i = 0; i < indicemano.size(); i++) {
                jugadores.get(turno).desacartar(mano.get(i));
            }
            for (int i = 0; i < mano.size(); i++) {
                mazo.descartar(mano.get(i));
            }
            mesa.remove(indicemesa);
            mazo.descartar(mesacarta);
            bonus = bonus + retorno[0];
        }
        actualizarvista();
    }


    //turno----------------------------------------------------
    @Override
    public void turno()throws RemoteException{
        //bonus
        while (mesa.size()<2){
            mesa.add(mazo.getCartaSuperior());
        }
        for (int i = 0; i < cartasbonus.size(); i++) {
            mesa.add(cartasbonus.get(i));
        }
        cartasbonus.clear();
        bonus=0;
        //pasar turno
        if (turno == (jugadores.size()-1)){
            turno=0;
        }
        else turno++;
        actualizarvista();
        notificarObservadores(Evento.INICIO_TURNO);
    }
//----------------------------subject-----------------------------------------------
    //@Override
    /*public void attach(Observer anObserver) {
        observers.add(anObserver);
    }*/

    //@Override
    /*public void detach(Observer anObserver) {
        observers.remove(anObserver);
    }*/

    /*@Override
    public void notifyMessage(Evento evento) {
        for (Observer observer: observers) {
            observer.update(this,evento);
        }
    }*/
}
