package ar.edu.unlu.poo.tp1.ejercicio4;

public class Main {
    public static void main(String[] args) {
        Cola cola1 = new Cola();
        System.out.println(String.valueOf(cola1.esVacia()));

        cola1.encolar("hola");
        cola1.encolar("mundo");
        cola1.mostrar();
        cola1.encolar("cruel");
        cola1.desencolar();
        cola1.mostrar();
        System.out.println(cola1.longitud());
    }
}
