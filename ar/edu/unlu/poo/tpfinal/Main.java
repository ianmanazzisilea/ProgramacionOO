package ar.edu.unlu.poo.tpfinal;

public class Main {
    public static void main(String[] args) {
        Juego modelo = new Juego();
        Jugador jugador = new Jugador("Alonso");
        Jugador jugador1 = new Jugador("Eustaquio");
        Controlador controlador = new Controlador(modelo,jugador);
        Controlador controlador1 = new Controlador(modelo,jugador1);
        IVista vista = new VistaConsola(controlador);
        IVista vista1 = new VistaConsola(controlador1);
    }
}
