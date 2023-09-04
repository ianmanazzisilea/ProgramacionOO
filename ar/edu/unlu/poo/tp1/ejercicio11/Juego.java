package ar.edu.unlu.poo.tp1.ejercicio11;

import java.util.ArrayList;

public class Juego {
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private ArrayList<Jugador> ganadores = new ArrayList<>();
    public void crearJugador(String nombre){
        Jugador jugador=new Jugador();
        jugador.setNombre(nombre);
        jugadores.add(jugador);
    }
    private int calcularPuntos(String palabra){
        int puntos = palabra.length();
        for (int i=0;i< palabra.length();i++){
            //k, z, x, y, w, q
            if (palabra.charAt(i) == "k".charAt(0)){
                puntos++;
            }
            if (palabra.charAt(i) == "z".charAt(0)){
                puntos++;
            }
            if (palabra.charAt(i) == "x".charAt(0)){
                puntos++;
            }
            if (palabra.charAt(i) == "y".charAt(0)){
                puntos++;
            }
            if (palabra.charAt(i) == "w".charAt(0)){
                puntos++;
            }
            if (palabra.charAt(i) == "q".charAt(0)){
                puntos++;
            }
        }
        return puntos;
    }
    private int buscarjugador(String nombre){
        for (int i=0;i< jugadores.size();i++){
            if (jugadores.get(i).getNombre()==nombre){
                return i;
            }
        }
        return -1;
    }
    public void asignarpalabra(String jugador, String palabra){
        int indicejugador = buscarjugador(jugador);
        if (indicejugador>=0){
            jugadores.get(indicejugador).setPalabra(palabra);
            jugadores.get(indicejugador).setPuntos(jugadores.get(indicejugador).getPuntos()+calcularPuntos(palabra));
        }

    }
    private void conseguirganador(){
        if (jugadores.size()>0){
            ganadores.add(jugadores.get(0));
            for (int i=1;i<jugadores.size();i++){
                if (ganadores.get(0).getPuntos()<jugadores.get(i).getPuntos()){
                    ganadores.clear();
                    ganadores.add(jugadores.get(i));
                }
                else
                    if (ganadores.get(0).getPuntos()==jugadores.get(i).getPuntos()){
                        ganadores.add(jugadores.get(i));
                    }
            }
        }
    }
    public void ganador(){
        conseguirganador();
        if (ganadores.size()>0){
            for (int i=0;i<ganadores.size();i++){
                System.out.println(ganadores.get(i).getNombre()+": "+ganadores.get(i).getPuntos());
                System.out.println("Lista de Palabras:");
                for (int j=0;j<ganadores.get(i).getPalabras().size();j++){
                    System.out.println(ganadores.get(i).getPalabras().get(j));
                }
            }
        }

    }
}
