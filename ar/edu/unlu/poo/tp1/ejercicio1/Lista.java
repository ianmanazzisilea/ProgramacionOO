package ar.edu.unlu.poo.tp1.ejercicio1;


import static java.util.Objects.isNull;

public class Lista {
    private Nodo nodocero;
    public boolean esVacia(){
        if (nodocero.getSiguiente() == null){
            return true;
        }
        else return false;
    }
    public void agregar(Object dato){
        Nodo nodosiguiente = new Nodo();
        nodosiguiente=nodocero;
        Nodo nuevonodo = new Nodo();
        nuevonodo.setDato(dato);
        while (nodosiguiente.getSiguiente() != null){
            nodosiguiente = nodosiguiente.getSiguiente();
        }
        nodosiguiente.setSiguiente(nuevonodo);

    }
    public String longitud(){
        Nodo nodosiguiente = nodocero;
        int i=0;
        while (!isNull(nodosiguiente.getSiguiente())){
            nodosiguiente = nodosiguiente.getSiguiente();
            i++;
        }
        return String.valueOf(i);
    }
    public boolean eliminar(int elemento){
        Nodo nodo = nodocero;
        int i=0;
        while (!isNull(nodo.getSiguiente())){
            if (i< (elemento - 1)){
                nodo = nodo.getSiguiente();
                i++;
            }
            else
                if (i == (elemento - 1)){
                    nodo.setSiguiente(nodo.getSiguiente().getSiguiente());
                    return true;
                }
        }
        return false;
    }
    public Object recuperar(int elemento){
        Nodo nodo = nodocero;
        int i=0;
        while (nodo.getSiguiente() != null){
            if (i< (elemento)){
                nodo = nodo.getSiguiente();
                i++;
            }
            else
            if (i == (elemento)){
                return nodo.getDato();
            }
        }
        return false;
    }
    public boolean inertar(Object elemento, int posicion){
        Nodo nodo = nodocero;
        int i=0;
        while (!isNull(nodo.getSiguiente())){
            if (i< (posicion - 1)){
                nodo = nodo.getSiguiente();
                i++;
            }
            else
            if (i == (posicion - 1)){
                Nodo nuevonodo = new Nodo();
                nuevonodo.setDato(elemento);
                nuevonodo.setSiguiente(nodo.getSiguiente());
                nodo.setSiguiente(nuevonodo);
                return true;
            }
        }
        return false;
    }
    public void mostrar(){
        Nodo nodo = nodocero;
        int i=0;
        while (nodo.getSiguiente() != null) {
            System.out.println(nodo.getDato());
        }
    }
}
