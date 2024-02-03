package ar.edu.unlu.poo.tpfinal;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import javax.swing.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Objects;

public class Controlador implements IControladorRemoto, Serializable {
    private IVista vista;
    private IJuego modelo;
    private int indice;
    public <T extends IObservableRemoto>Controlador(T modelo) {
        try{
            this.setModeloRemoto(modelo);
        } catch(RemoteException e){
            e.printStackTrace();
        }
    }
    public Controlador(int contadorindice){
        //this.indice = contadorindice;
    }

    public void setJugador() {
        try {
            try{
                indice = modelo.ingresarJugador();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }

    public void setVista(IVista vista) {
        this.vista = vista;
    }

    public void hayganador(){
        try {
            modelo.hayganador();
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }
    public void robar(){
        try {
            modelo.robar();
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }
    public void jugada(ArrayList<String> indicemano,int indicemesa){
        ArrayList<Integer> manojugada = new ArrayList<>();
        for (int i = 0; i < indicemano.size(); i++) {
            int intindicemano = Integer.valueOf(indicemano.get(i));
            manojugada.add(intindicemano);
        }
        try {
            modelo.jugada(manojugada,indicemesa);
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }
    public void nocanto(){
        try {
            modelo.robar();
            modelo.robar();
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }
    public void pasoturno(){
        try {
            modelo.turno();
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }
    public void empezar(){
        try {
            //setModeloRemoto((IObservableRemoto) this);
            modelo.empezar();
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }
    //temporal

    public boolean getTurno(){
        try {
            return modelo.getTurno(indice);
        }catch (RemoteException e){
            e.printStackTrace();
            return false;
        }

    }
    public ArrayList<String> getMano() {
        try {
            ArrayList<String> mano = modelo.getMano(indice);
            return mano;
        }catch (RemoteException e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
    public ArrayList<String> getMesa(){
        try {
            return modelo.getCartas();
        }catch (RemoteException e){
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
    public String getnombre(){
        try {
            return modelo.getNombre(indice);
        }catch (RemoteException e){
            e.printStackTrace();
            return "";
        }

    }
    public String ganador(){
        try {
            String turno = modelo.getGanador();
            if (Objects.equals(turno, String.valueOf(indice))){
                String ganador = (String) JOptionPane.showInputDialog(null,
                        "Elija un nombre","Ah ganado!",1);
                //JOptionPane.QUESTION_MESSAGE, "Elija su nombre"
                //);
                String nombre = ganador;
                modelo.guardarscore(nombre);
            }
            return modelo.getGanador();
        }catch (RemoteException e){
            e.printStackTrace();
            return "";
        }

    }
    public boolean booleanbonus(){
        try {
            return modelo.getBBonus();
        }catch (RemoteException e){
            e.printStackTrace();
            return false;
        }

    }
    public int bonus(){
        try {
            return modelo.getBonus();
        }catch (RemoteException e){
            e.printStackTrace();
            return 0;
        }

    }
    public void descartarbonus(int indicemano){
        try {
            modelo.descartarbonus(indicemano);
        }catch (RemoteException e){
            e.printStackTrace();
        }

    }
    public Object[] getscore(){
        try {
            return modelo.getscore();
        }catch (RemoteException e){
            e.printStackTrace();
            Object[] vacio = new Object[0];
            vacio[0] = "";
            return vacio;
        }
    }
    public void cargarpartida(){
        try {
            modelo.recuperarturno();
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(IObservableRemoto modelo, Object o) throws RemoteException {
        if (o instanceof Evento){
            switch ((Evento) o){
                case INICIO_TURNO:vista.inicioturno();
                    break;
                case MOSTRAR_MESA:vista.mesaactualizada();
                    break;
                case FIN_PARTIDA:vista.finpartida();
                    break;
            }
        }
    }
    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modelo=(IJuego) modeloRemoto;
    }
}
