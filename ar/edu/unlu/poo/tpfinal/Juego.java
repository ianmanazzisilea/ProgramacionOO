package ar.edu.unlu.poo.tpfinal;

import ar.edu.unlu.poo.tpfinal.services.Serializador;
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
    static private int turnototal = 0;
    static private int bonus = 0;
    static private ArrayList<Carta> cartasbonus = new ArrayList<>();
    static private Jugador jugadorganador;
    static private int clientes = 0;
    private static Serializador backup_jugadores = new Serializador("backupj.dat");
    private static Serializador backup_mesa = new Serializador("backupme.dat");
    private static Serializador backup_mazo = new Serializador("backupma.dat");
    private static Serializador backup_turno = new Serializador("backupt.dat");
    private static Serializador backup_clientes = new Serializador("backupc.dat");
    private static Serializador backup_turnototal = new Serializador("backuptt.dat");
    private static Serializador score = new Serializador("score.dat");
    //----------------------------------GETTERS-------------------------------
    public static ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public static int getTurno() {
        return turno;
    }

    public static int getClientes() {
        return clientes;
    }

    public static Mazo getMazo() {
        return mazo;
    }

    public static Mesa getMesa() {
        return mesa;
    }
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
        ArrayList<String> mano = new ArrayList<>();
        for (int i = 0; i < jugadores.get(indice).getMano().size(); i++) {
            mano.add(jugadores.get(indice).getMano().get(i).getColor() + " " +
                    jugadores.get(indice).getMano().get(i).getNumero());
        }
        return mano;
    }
    @Override
    public String getNombre(int indice)throws RemoteException{
        String nombre = jugadores.get(indice).getNombre();
        return nombre;
    }
    @Override
    public String getGanador()throws RemoteException {
        return String.valueOf(turno);
    }

    @Override
    public Integer ingresarJugador()throws RemoteException{
        jugadores.add(new Jugador("jugador" + (jugadores.size() + 1)));
        return jugadores.size()-1;
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
    public void actualizarvista() throws RemoteException {
        IJuego.super.actualizarvista();
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
        turnototal++;
        if (turno == (jugadores.size()-1)){
            turno=0;
        }
        else turno++;
        guardarturno();
        actualizarvista();
        notificarObservadores(Evento.INICIO_TURNO);
    }
//----------------------------serializable------------------------------------------
    private void guardarturno()throws RemoteException{
        backup_jugadores.writeOneObject(jugadores);
        backup_mesa.writeOneObject(mesa);
        backup_mazo.writeOneObject(mazo);
        backup_turno.writeOneObject(turno);
        backup_clientes.writeOneObject(clientes);
        backup_turnototal.writeOneObject(turnototal);
    }

    @Override
    public void recuperarturno()throws RemoteException{
        Object objetojugador = backup_jugadores.readObjects();
        Object objetomesa = backup_mesa.readObjects();
        Object objetomazo = backup_mazo.readObjects();
        Object objetoturno = backup_turno.readObjects();
        Object objetoclientes = backup_clientes.readObjects();
        Object objetott = backup_turnototal.readObjects();
        jugadores = (ArrayList<Jugador>) objetojugador;
        mesa = (Mesa) objetomesa;
        mazo = (Mazo) objetomazo;
        turno = (Integer) objetoturno;
        clientes = (Integer) objetoclientes;
        turnototal = (Integer) objetott;
        actualizarvista();
        notificarObservadores(Evento.INICIO_TURNO);
    }
    @Override
    public Object[] getscore()throws RemoteException{
        return score.readObjects();
    }
    @Override
    public void guardarscore(String nombrenuevo)throws RemoteException{
        Object[] mejores = score.readObjects();
        if (mejores.length > 0){
            int cont = 0;
            Object[] tur = new Object[2];
            tur[0] = String.valueOf(turnototal);
            tur[1] = nombrenuevo;
            while (cont < 5 && cont < mejores.length){
                Object[] mejor =(Object[]) mejores[cont];//0-4
                if (Integer.valueOf((String) tur[0]) > Integer.valueOf((String) mejor[0])){
                    Object[] aux =(Object[]) mejores[cont];
                    mejores[cont] = tur;
                    tur = aux;
                }
                cont++;
            }
        }else{
            Object[] tur = new Object[2];
            tur[0] = String.valueOf(turnototal);
            tur[1] = nombrenuevo;
            mejores[0] = tur;
        }
        score.addOneObject(mejores);
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
