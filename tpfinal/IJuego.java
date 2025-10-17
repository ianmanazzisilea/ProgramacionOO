package ar.edu.unlu.poo.tpfinal;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IJuego extends IObservableRemoto {


    //----------------------------------GETTERS-------------------------------
    boolean getTurno(int indice) throws RemoteException;
    ArrayList<Carta> getCartas()throws RemoteException;

    int getIndice(IControladorRemoto anObserver) throws RemoteException;

    ArrayList<Carta> getMano(int indice) throws RemoteException;

    String getNombre(int indice) throws RemoteException;

    String getGanador() throws RemoteException;

    Integer ingresarJugador() throws RemoteException;

    int getBonus() throws RemoteException;

    boolean getBBonus() throws RemoteException;

    void hayganador() throws RemoteException;

    void descartarbonus(ArrayList<Carta> jugada) throws RemoteException;

    //inicio del juego------------------------------------
    void empezar() throws RemoteException;

    default void actualizarvista() throws RemoteException {
        notificarObservadores(Evento.MOSTRAR_MESA);
    }

    void robar() throws RemoteException;

    void jugada(ArrayList<Carta> mano, Carta mesacarta) throws RemoteException;

    //turno----------------------------------------------------
    void turno() throws RemoteException;

    void recuperarturno()throws RemoteException;

    Object[] getscore()throws RemoteException;
    void guardarscore(String nombrenuevo) throws IOException;
}
