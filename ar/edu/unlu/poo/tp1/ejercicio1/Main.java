package ar.edu.unlu.poo.tp1.ejercicio1;

public class Main {
    public static void main(String[] args) {
        Lista lista1 = new Lista();
        lista1.agregar("hola");
        lista1.agregar("mundo");
        System.out.println(String.valueOf(lista1.esVacia()));
        //lista1.recuperar(1);
        //lista1.mostrar();
    }
}
