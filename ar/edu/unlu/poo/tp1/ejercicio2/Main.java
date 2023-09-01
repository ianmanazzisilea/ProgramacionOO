package ar.edu.unlu.poo.tp1.ejercicio2;

public class Main {
    public static void main(String[] args) {
        Lista lista1 = new Lista();
        System.out.println(String.valueOf(lista1.esVacia()));

        lista1.agregar("hola");
        lista1.agregar("mundo");
        lista1.mostrar();
        lista1.eliminar(1);
        lista1.mostrar();
        lista1.insertar("adios",1);
        lista1.mostrar();
        System.out.println(lista1.longitud());
        System.out.println(lista1.recuperar(1));
    }
}
