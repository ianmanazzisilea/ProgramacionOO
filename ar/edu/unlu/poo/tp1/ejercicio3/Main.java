package ar.edu.unlu.poo.tp1.ejercicio3;

public class Main {
    public static void main(String[] args) {
        Pila pila1 = new Pila();
        System.out.println(String.valueOf(pila1.esVacia()));

        pila1.apilar("hola");
        pila1.apilar("mundo");
        pila1.mostrar();
        System.out.println(pila1.longitud());
    }
}
