package ar.edu.unlu.poo.tpfinal;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IJuego extends IObservableRemoto {


    //----------------------------------GETTERS-------------------------------
    boolean getTurno(int indice) throws RemoteException;
    ArrayList<String> getCartas()throws RemoteException;

    int getIndice(IControladorRemoto anObserver) throws RemoteException;

    ArrayList<String> getMano(int indice) throws RemoteException;

    String getNombre(int indice) throws RemoteException;

    String getGanador() throws RemoteException;

    Integer ingresarJugador() throws RemoteException;

    int getBonus() throws RemoteException;

    boolean getBBonus() throws RemoteException;

    void hayganador() throws RemoteException;

    void descartarbonus(int indicemano) throws RemoteException;

    //inicio del juego------------------------------------
    void empezar() throws RemoteException;

    default void actualizarvista() throws RemoteException {
        notificarObservadores(Evento.MOSTRAR_MESA);
    }

    void robar() throws RemoteException;

    void jugada(ArrayList<Integer> indicemano, int indicemesa) throws RemoteException;

    //turno----------------------------------------------------
    void turno() throws RemoteException;

    void recuperarturno()throws RemoteException;

    Object[] getscore()throws RemoteException;
    void guardarscore(String nombrenuevo)throws RemoteException;
}
