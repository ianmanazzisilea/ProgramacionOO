package ar.edu.unlu.poo.tp1.ejercicio5;

import java.time.LocalDate;

public class Lista {
    //static private final ArrayList<Tarea> tareas = new ArrayList<>();
    private Tarea tareacero;
    private boolean insertar(Tarea tarea, int posicion){
        int i=0;
        Tarea tarearecorrer = new Tarea();
        if (tareacero != null){
            if (tareacero.getSiguiente() != null){
                tarearecorrer = tareacero;
                while (tarearecorrer.getSiguiente() != null){
                    if (i< (posicion - 1)){
                        tarearecorrer = tarearecorrer.getSiguiente();
                        i++;
                    }
                    else
                    if (i == (posicion - 1)){
                        tarea.setSiguiente(tarearecorrer.getSiguiente());
                        tarearecorrer.setSiguiente(tarea);
                        return true;
                    }
                }
            }
            else
            if (posicion == 2){
                tareacero.setSiguiente(tarea);
            }
            else
            if (posicion == 1){
                tarea.setSiguiente(tareacero);
                tareacero = tarea;
            }
        }
        else
        if (posicion == 1){
            tareacero = tarea;
        }
        return false;
    }
    private int longitud(){
        int i=0;
        if (tareacero!=null){
            i++;
            Tarea tarea = tareacero;
            while (tarea.getSiguiente() != null){
                tarea = tarea.getSiguiente();
                i++;
            }
        }
        return i;
    }
    private Tarea recuperar(int prioridad){
        int i=1;
        if (tareacero != null){
            if (tareacero.getSiguiente() != null){
                Tarea tarea1 = tareacero;
                while (tarea1.getSiguiente() != null){
                    if (i< (prioridad)){
                        tarea1 = tarea1.getSiguiente();
                        i++;
                    }
                    else
                    if (i == (prioridad)){
                        return tarea1;
                    }
                }
                return null;
            }
            else
            if (prioridad == 1){
                return tareacero;
            }
            else return null;
        }
        else return null;

    }
    private boolean eliminar(int prioridad){

        int i=1;
        if (tareacero != null){
            if (tareacero.getSiguiente() != null){
                Tarea tarea1 = tareacero;
                while (tarea1.getSiguiente() != null){
                    if (i< (prioridad)){
                        tarea1 = tarea1.getSiguiente();
                        i++;
                    }
                    else
                    if (i == (prioridad)){
                        tarea1 = tarea1.getSiguiente();
                        //Tarea tareasiguiente = new Tarea();
                        //tareasiguiente = tarea1.getSiguiente();
                        //tarea1.setSiguiente(tareasiguiente.getSiguiente());
                        //tarea1.setDato(nodosiguiente.getDato());
                        //nodosiguiente = null;
                        return true;
                    }
                }
                if (i == (prioridad)){
                    tarea1 = null;
                    return true;
                }
                else return false;
            }
            if (prioridad == 1){
                tareacero = null;
                return true;
            }
            else return false;
        }
        else return false;
    }
    public void crear (Object descripcion, int prioridad, String estado, LocalDate fechalimite){
        if (estado == "completa"){
            Tarea tarea1 = new Tarea();
            tarea1.setFechalimite(fechalimite);
            tarea1.setDescripcion(descripcion);
            tarea1.setEstado(true);
            insertar(tarea1,prioridad);
        }
        else
        if (estado == "incompleta"){
            Tarea tarea1 = new Tarea();
            tarea1.setFechalimite(fechalimite);
            tarea1.setDescripcion(descripcion);
            tarea1.setEstado(false);
            insertar(tarea1,prioridad);
        }
    }
    public void modificardescripcion(int prioridad, Object descripcionnueva){
        int i;
        Tarea tarea1 = tareacero;
        for (i = 1; i<= longitud()  ;i++){
            if (i == prioridad){
                tarea1.setDescripcion(descripcionnueva);
            }
            tarea1 = tarea1.getSiguiente();
        }
    }
    public void modificarprioridad(int prioridadant, int prioridadmodi){
        Tarea tareaaux =recuperar(prioridadant);
        eliminar(prioridadant);
        insertar(tareaaux, prioridadmodi);
    }
    public void mostrar(){
        String sestado;
        if (tareacero != null){
            Tarea tarea = tareacero;
            LocalDate fechaactual = LocalDate.now();
            while (tarea != null) {
                if (tarea.getEstado()){
                    sestado = "completa";
                }
                else
                    if (tarea.getFechalimite().isAfter(fechaactual)){
                        sestado = "vencida";
                    }
                    else sestado = "incompleta";
                System.out.println(tarea.getDescripcion().toString() + " " + tarea.getFechalimite().toString() +
                        " " + sestado);
                tarea = tarea.getSiguiente();
            }
        }
    }
}
