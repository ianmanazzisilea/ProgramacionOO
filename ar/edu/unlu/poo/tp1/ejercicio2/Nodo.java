package ar.edu.unlu.poo.tp1.ejercicio2;

public class Nodo {
    private Object dato=null;
    private Nodo siguiente=null;
    private Nodo anterior=null;

    public void setDato(Object dato) {
        this.dato = dato;
    }
    public Object getDato() {
        return this.dato;
    }
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    public Nodo getSiguiente() {
        return siguiente;
    }
    public void setAnterior(Nodo anterior){this.anterior = anterior;}
    public Nodo getAnterior() {return anterior;}
}