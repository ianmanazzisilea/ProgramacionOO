package ar.edu.unlu.poo.tp1.ejercicio10;


import java.time.LocalDate;

public class Lista {
    private Tarea tareacero;
    private void insertar(Tarea tarea, int posicion){
        int i=1;
        Tarea tarearecorrer = new Tarea();
        if (tareacero != null){
            if (posicion==1){
                tarea.setSiguiente(tareacero);
                tareacero = tarea;
            }
            else{
                tarearecorrer = tareacero;
                while ((tarearecorrer.getSiguiente() != null) && (i != (posicion-1))){
                        tarearecorrer = tarearecorrer.getSiguiente();
                        i++;
                }
                if (i == (posicion-1)){
                    tarea.setSiguiente(tarearecorrer.getSiguiente());
                    tarearecorrer.setSiguiente(tarea);
                }
            }
        }
        else
        if (posicion == 1){
            tareacero = tarea;
        }
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
    private void eliminar(int prioridad){
        if (tareacero != null){
            if (longitud()==1){
                tareacero=null;
            }
            else
                if (prioridad==1){
                    tareacero=tareacero.getSiguiente();
                }
                else
                    if (prioridad>1){
                        Tarea tarea1 = tareacero;
                        int i=1;
                        if (longitud()>=prioridad){
                            while ((tarea1.getSiguiente()!=null) && (i!=prioridad-1)) {
                                tarea1.getSiguiente();
                                i++;
                            }
                            if ((i==prioridad-1)&&((tarea1.getSiguiente()!=null))){
                                tarea1.setSiguiente(tarea1.getSiguiente().getSiguiente());
                            }
                        }
                    }
        }
       /* int i=1;
        if (tareacero != null){
            if (tareacero.getSiguiente() != null){
                if (prioridad != 1){
                    Tarea tarea1 = tareacero;
                    while ((tarea1.getSiguiente() != null) && (i != prioridad-1)){
                        tarea1 = tarea1.getSiguiente();
                        i++;
                    }
                    if ((i == prioridad-1)&&(tarea1.getSiguiente() != null)){
                        Tarea tareaaux = tarea1.getSiguiente().getSiguiente();
                        tarea1.getSiguiente().setSiguiente(null);
                        tarea1.setSiguiente(tareaaux);
                        return true;
                    }
                    if ((i == prioridad-1)&&(tarea1.getSiguiente() == null)){
                        tarea1.setSiguiente(null);
                        return true;
                    }
                    return false;
                }
                else {
                    tareacero = tareacero.getSiguiente();
                    return true;
                }

            }
            else
                if (prioridad == 1){
                    tareacero = null;
                    return true;
                }
                else return false;
        }
        else return false;*/
    }
    //-------------------------------------------------------------------------------------------------------------------
    public void agregartarea (Object descripcion, int prioridad, String estado,
                              int dialimite, int meslimite, int añolimite,
                              int diarecordatorio, int mesrecordatorio, int añorecordatorio){
        if (estado == "completa"){
            Tarea tarea1 = new Tarea();
            LocalDate fechalimite = LocalDate.of(añolimite, meslimite, dialimite);
            tarea1.setFechalimite(fechalimite);
            tarea1.setDescripcion(descripcion);
            tarea1.setEstado(true);
            LocalDate fecharecordatorio = LocalDate.of
                    (añorecordatorio,mesrecordatorio,diarecordatorio);
            tarea1.setFecharecordatorio(fecharecordatorio) ;
            insertar(tarea1,prioridad);
        }
        else
        if (estado == "incompleta"){
            Tarea tarea1 = new Tarea();
            LocalDate fechalimite = LocalDate.of(añolimite, meslimite, dialimite);
            tarea1.setFechalimite(fechalimite);
            tarea1.setDescripcion(descripcion);
            LocalDate fecharecordatorio = LocalDate.of
                    (añorecordatorio,mesrecordatorio,diarecordatorio);
            tarea1.setFecharecordatorio(fecharecordatorio) ;
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
        tareaaux.setSiguiente(null);
        insertar(tareaaux, prioridadmodi);
    }
    private void ordenarporvencer(){
        if (tareacero!=null){
            Tarea tarea = tareacero;
            for (int i=1; i<=longitud();i++){
                LocalDate fechaactual = LocalDate.now();
                if (tarea.getFecharecordatorio().isBefore(fechaactual)){
                    modificarprioridad(i,1);
                }
                tarea=tarea.getSiguiente();
            }
        }
    }
    public void mostrar(){
        String sestado;
        ordenarporvencer();
        if (tareacero != null){
            Tarea tarea = tareacero;
            LocalDate fechaactual = LocalDate.now();
            while (tarea != null) {
                if (tarea.getEstado()){
                    sestado = "completa";
                }
                else
                    if (tarea.getFechalimite().isBefore(fechaactual)){
                        sestado = "vencida";
                    }
                    else
                        if (tarea.getFecharecordatorio().isBefore(fechaactual)){
                            sestado ="por vencer";
                        }
                        else sestado = "incompleta";
                System.out.println(tarea.getDescripcion().toString() + " " + tarea.getFechalimite().toString() +
                        " " + sestado);
                tarea = tarea.getSiguiente();
            }
        }
    }
    public void modificarestado(int prioridad){
        int i;
        Tarea tarea1 = tareacero;
        for (i = 1; i<= longitud()  ;i++){
            if (i == prioridad){
                if (tarea1.getEstado()){
                    tarea1.setEstado(false);
                }else tarea1.setEstado(true);
            }
            tarea1 = tarea1.getSiguiente();
        }
    }
    public void modificarfecha(int prioridad, int dialimite, int meslimite, int añolimite){
        int i;
        Tarea tarea1 = tareacero;
        for (i = 1; i<= longitud()  ;i++){
            if (i == prioridad){
                LocalDate fechalimite = LocalDate.of(añolimite, meslimite, dialimite);
                tarea1.setFechalimite(fechalimite);
            }
            tarea1 = tarea1.getSiguiente();
        }
    }
}
