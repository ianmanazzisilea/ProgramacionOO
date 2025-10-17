package ar.edu.unlu.poo.tpfinal;

import ar.edu.unlu.poo.tpfinal.services.Serializador;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import java.io.*;
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
    public ArrayList<Carta> getMano(int indice)throws RemoteException{
        ArrayList<Carta> mano = new ArrayList<>();
        for (int i = 0; i < jugadores.get(indice).getMano().size(); i++) {
            mano.add(jugadores.get(turno).getMano().get(i));
            //mano.add(jugadores.get(indice).getMano().get(i).getColor() + " " +
            //        jugadores.get(indice).getMano().get(i).getNumero());
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
    public ArrayList<Carta> getCartas() throws RemoteException {
        ArrayList<Carta> mesaString = new ArrayList<>();
        for (int j = 0; j < mesa.size(); j++) {
            mesaString.add(mesa.getCarta(j));
            //mesaString.add(Juego.mesa.getCarta(j).getColor() + " " + Juego.mesa.getCarta(j).getNumero());
        }
        return mesaString;
    }
    @Override
    public void hayganador()throws RemoteException{
        jugadorganador = jugadores.get(turno);
        notificarObservadores(Evento.FIN_PARTIDA);
    }
    @Override
    public void descartarbonus(ArrayList<Carta> jugada)throws RemoteException{
        if (jugada.size()<=bonus){
            for (int i = 0; i < jugada.size(); i++) {
                jugadores.get(turno).desacartar(jugada.get(i));
                mazo.descartar(jugada.get(i));
            }
        }
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

    private boolean pruebajugada(Carta cartamesa,Integer contador, ArrayList<Carta> mano){
        if (contador == jugadores.get(turno).getMano().size()){
            if (logicajuego.emparejar(mano, cartamesa)[0]==-1)
                return false;
            else
                return true;
        }
        else {
            boolean no = pruebajugada(cartamesa,contador++,mano);
            mano.add(jugadores.get(turno).getMano().get(contador));
            boolean si = pruebajugada(cartamesa,contador++,mano);
            return (si || no);
        }
    }
    @Override
    public void robar()throws RemoteException{
        boolean hayjugada = false;
        for (int i = 0; i < mesa.size(); i++) {
            Carta mesai = mesa.getCarta(i);
            ArrayList<Carta> mano = new ArrayList<>();
            hayjugada = pruebajugada(mesai,0,mano);
            if (hayjugada) break;
        }
        if (!hayjugada){
            jugadores.get(turno).roba(mazo.getCartaSuperior());
        }
        actualizarvista();
    }
    @Override
    public void jugada(ArrayList<Carta> mano, Carta mesacarta)throws RemoteException{
        Integer[] retorno = logicajuego.emparejar(mano,mesacarta);
        if (retorno[1] == 1){
            for (int i = 0; i < mano.size(); i++) {
                jugadores.get(turno).desacartar(mano.get(i));//anda mal el descarte?
            }
            for (int i = 0; i < mano.size(); i++) {
                mazo.descartar(mano.get(i));
            }
            mesa.remove(mesacarta);
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
        else {
            turno++;
            turnototal++;
        };

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
    public void guardarscore(String nombrenuevo) throws IOException {
            File archivoPuntajes = new File("scoreboard.dat");

            if (!archivoPuntajes.exists()) {
                archivoPuntajes.createNewFile();
            }

            Serializador scoreboard = new Serializador("scoreboard.dat");
            Object[] puntajes = scoreboard.readObjects();

            ArrayList<Object[]> listaPuntajes = new ArrayList<>();
            boolean existe = false;

            // Actualizar puntaje o añadir un nuevo jugador
            if (puntajes != null) {
                for (Object o : puntajes) {
                    Object[] puntaje = (Object[]) o;

                    // Verificar si el jugador ya existe
                    if (puntaje[0].equals(nombrenuevo)) {
                        if(turnototal < (int) puntaje[1]){
                            puntaje[1] =  turnototal; // Mejora puntaje
                        }
                        existe = true;
                    }
                    listaPuntajes.add(puntaje);
                }
            }

            // Si el jugador no existía, se añade uno nuevo
            if (!existe) {
                Object[] nuevoPuntaje = {nombrenuevo, 1};
                listaPuntajes.add(nuevoPuntaje);
            }

            // Guardar todos los puntajes actualizados en el archivo
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoPuntajes))) {
                for (Object[] puntaje : listaPuntajes) {
                    oos.writeObject(puntaje);
                }
            }
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
