package ar.edu.unlu.poo.tp1.ejercicio11;

public class Main {
    public static void main(String[] args) {
        Juego crossword = new Juego();
        crossword.crearJugador("Pepe");
        crossword.crearJugador("Maria");
        crossword.asignarpalabra("Pepe","adjuntx");
        crossword.asignarpalabra("Maria","adjuntar");
        crossword.ganador();
    }
}
