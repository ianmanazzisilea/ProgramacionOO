package ar.edu.unlu.poo.tp1.ejercicio1;


public class Lista {
    private Nodo nodocero;
    public boolean esVacia(){
        if (nodocero == null){
            return true;
        }
        else return false;
    }
    public void agregar(Object dato){
        Nodo nodo = new Nodo();
        nodo=nodocero;
        Nodo nuevonodo = new Nodo();
        nuevonodo.setDato(dato);
        if (nodocero == null){
            nodocero = nuevonodo;
        }
        else{
            while (nodo.getSiguiente() != null){
                nodo = nodo.getSiguiente();
            }
            nodo.setSiguiente(nuevonodo);
        }
    }
    public String longitud(){
        Nodo nodo = nodocero;
        int i=0;
        if (nodo!=null){
            i++;
            while (nodo.getSiguiente() != null){
                nodo = nodo.getSiguiente();
                i++;
            }
        }
        return String.valueOf(i);
    }
    public boolean eliminar(int elemento){
        Nodo nodo = nodocero;
        Nodo nodosiguiente = new Nodo();
        int i=1;
        if (nodo != null){
            if (nodo.getSiguiente() != null){
                while (nodo.getSiguiente() != null){
                    if (i< (elemento )){
                        nodo = nodo.getSiguiente();
                        i++;
                    }
                    else
                    if (i == (elemento  )){
                        nodosiguiente = nodo.getSiguiente();
                        nodo.setSiguiente(nodosiguiente.getSiguiente());
                        nodo.setDato(nodosiguiente.getDato());
                        nodosiguiente = null;
                        return true;
                    }
                }
                if (i == (elemento )){
                    nodo = null;
                    return true;
                }
                else return false;
            }
            if (elemento == 1){
                nodocero = null;
                return true;
            }
            else return false;
        }
        else return false;
    }
    public Object recuperar(int elemento){
        Nodo nodo = nodocero;
        int i=1;
        if (nodo != null){
            if (nodo.getSiguiente() != null){
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
                return null;
            }
            else
                if (elemento == 1){
                    return nodocero.getDato();
                }
                else return null;
        }
        else return nodocero.getDato();

    }
    public boolean insertar(Object elemento, int posicion){
        Nodo nodo = nodocero;
        int i=0;
        Nodo nuevonodo = new Nodo();
        nuevonodo.setDato(elemento);
        if (nodo != null){
            if (nodo.getSiguiente() != null){
                while (nodo.getSiguiente() != null){
                    if (i< (posicion - 1)){
                        nodo = nodo.getSiguiente();
                        i++;
                    }
                    else
                    if (i == (posicion - 1)){
                        nuevonodo.setSiguiente(nodo.getSiguiente());
                        nodo.setSiguiente(nuevonodo);
                        return true;
                    }
                }
            }
            else
                if (posicion == 2){
                    nodo.setSiguiente(nuevonodo);
                }
                else
                    if (posicion == 1){
                        nuevonodo.setSiguiente(nodo);
                        nodocero = nuevonodo;
                    }
        }
        else
            if (posicion == 1){
                nodo.setDato(elemento);
            }

        return false;
    }
    public void mostrar(){
        Nodo nodo = nodocero;
        if (nodo != null){
            while (nodo.getSiguiente() != null) {
                System.out.println(nodo.getDato());
                nodo = nodo.getSiguiente();
            }
            System.out.println(nodo.getDato());
        }
    }
}
