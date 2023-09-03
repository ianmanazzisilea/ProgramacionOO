package ar.edu.unlu.poo.tp1.ejercicio8;

public class Main {
    public static void main(String[] args) {
        //AdminContra contraseñador = new AdminContra();
        //System.out.println(contraseñador.sugerirContraseña(8));
        ColeccionContraseñas listacontra = new ColeccionContraseñas();
        listacontra.generarContraseñas(5,16);
        listacontra.mostrar();
        listacontra.generarContraseñaFuerte(2);
        listacontra.modificarcontra(3,"QRFlr345");
        listacontra.mostrar();
    }
}
