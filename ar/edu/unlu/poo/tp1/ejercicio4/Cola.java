package ar.edu.unlu.poo.tp1.ejercicio4;

public class Cola {
    private Nodo nodocero;
    private Nodo endnodo;
    private Cola colaaux;
    public boolean esVacia(){
        if (nodocero == null){
            return true;
        }
        else return false;
    }
    public void encolar(Object dato){
        Nodo nuevonodo = new Nodo();
        nuevonodo.setDato(dato);
        if (nodocero == null){
            nodocero = nuevonodo;
        }
        else
            if (nodocero.getSiguiente()==null){
                endnodo = nuevonodo;
                nodocero.setSiguiente(endnodo);
            }
            else {
                endnodo.setSiguiente(nuevonodo);
                endnodo = nuevonodo;
            }
    }
    public Object desencolar(){
        Nodo nodo = new Nodo();
        if (nodocero == null){
            return null;
        }
        else{
            nodo=nodocero;
            nodocero=nodocero.getSiguiente();
            return nodo.getDato();
        }
    }
    public String longitud(){
        Cola colaaux =new Cola();
        int i=0;
        if (nodocero!=null){
            while (!esVacia()){
                colaaux.encolar(desencolar());
                i++;
            }
            while (!colaaux.esVacia()){
                encolar(colaaux.desencolar());
            }
        }
        return String.valueOf(i);
    }

    public void mostrartope(){
        System.out.println(nodocero.getDato());
    }

    public void mostrar(){
        Nodo nodoaux =new Nodo();
        Cola colaaux =new Cola();
        while (!esVacia()){
            mostrartope();
            nodoaux.setDato(desencolar());
            colaaux.encolar(nodoaux.getDato());
        }
        while (!colaaux.esVacia()){
            nodoaux.setDato(colaaux.desencolar());
            encolar(nodoaux.getDato());
        }
    }
}


