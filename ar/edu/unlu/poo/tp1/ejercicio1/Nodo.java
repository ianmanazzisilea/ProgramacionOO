package ar.edu.unlu.poo.tp1.ejercicio1;

public class Nodo {
    private Object dato=null;
    private Nodo siguiente=null;

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Object getDato() {
        return dato;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }
}
