package ar.edu.unlu.poo.tp1.ejercicio3;

public class Pila {
    private Nodo nodocero;
    private Pila pilaaux;
    public boolean esVacia(){
        if (nodocero == null){
            return true;
        }
        else return false;
    }
    public void apilar(Object dato){
        Nodo nodo = new Nodo();
        nodo=nodocero;
        Nodo nuevonodo = new Nodo();
        nuevonodo.setDato(dato);
        if (nodocero == null){
            nodocero = nuevonodo;
        }
        else{
            nuevonodo.setSiguiente(nodocero);
            nodocero = nuevonodo;
        }
    }
    public Object desapilar(){
        Nodo nodo = new Nodo();
        if (nodocero == null){
            return null;
        }
        else{
            nodo=nodocero;
            nodocero = nodocero.getSiguiente();
            return nodo.getDato();
        }
    }
    public String longitud(){
        int i=0;
        Pila pilaaux = new Pila();
        if (nodocero!=null){
            while (!esVacia()){
                pilaaux.apilar(desapilar());
                i++;
            }
            while (!pilaaux.esVacia()){
                apilar(pilaaux.desapilar());
            }
        }
        return String.valueOf(i);
    }

    public void mostrartope(){
        System.out.println(nodocero.getDato());
    }

    public void mostrar(){
        Pila pilaaux = new Pila();
            while (!esVacia()){
                mostrartope();
                pilaaux.apilar(desapilar());
            }
            while (!pilaaux.esVacia()){
                apilar(pilaaux.desapilar());
            }
    }
}

