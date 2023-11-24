package ar.edu.unlu.poo.tpfinal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Juego modelo = new Juego();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VistaConsola vista1 = new VistaConsola();
                Controlador controlador = new Controlador(vista1,modelo);
                modelo.setControlador(controlador);
                vista1.setControlador(controlador);
                modelo.empezar();
            }
        });
    }
    //
    //Controlador controlador = new Controlador() necesita ivista
}
