package ar.edu.unlu.poo.tp2.ejercicio1;

import java.time.LocalDate;
import java.util.ArrayList;

public class Club {
    private int id = 1;
    private ArrayList<Socio> socios = new ArrayList<>();
    private ArrayList<Actividad> actividades = new ArrayList<>();
    public void agregarsocio(String nombre, String email, String direccion, String estado, int dia,int mes,int año){
        Socio socio1 = new Socio(nombre,String.valueOf(id),email,direccion,estado,LocalDate.of(año, mes, dia));
        id++;
        socios.add(socio1);
    }
    public void agregaractividad(String descripcion, String nivel){
        Actividad actividad1 = new Actividad(descripcion,nivel);
        actividades.add(actividad1);
    }
    public void devolverNuevosSocios(){
        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getFechainscripcion().getDayOfMonth()==LocalDate.now().getDayOfMonth()){
                System.out.println(socios.get(i).getId()+" - "+socios.get(i).getNombre()
                        +" - "+socios.get(i).getEstado());
            }
        }
    }
    public void devolverActividades(String nivel){
        for (int i = 0; i < actividades.size(); i++) {
            if (actividades.get(i).getNivelrequerido()==nivel){
                System.out.println(actividades.get(i).getDescripcion());
            }
        }
    }
    public void devolverActividades(){
        for (int i = 0; i < actividades.size(); i++) {
                System.out.println(actividades.get(i).getDescripcion()+" - "+
                        actividades.get(i).getNivelrequerido());
        }
    }
    public void devolversocios(String nivel){
        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getEstado()==nivel){
                System.out.println(socios.get(i).getId()+" - "+socios.get(i).getNombre());
            }
        }
    }
    public void devolversocios(){
        System.out.println("Basica:");
        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getEstado()=="basica"){
                System.out.println(socios.get(i).getId()+" - "+socios.get(i).getNombre());
            }
        }
        System.out.println("Intermedia:");
        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getEstado()=="intermedia"){
                System.out.println(socios.get(i).getId()+" - "+socios.get(i).getNombre());
            }
        }
        System.out.println("Destacada:");
        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getEstado()=="destacada"){
                System.out.println(socios.get(i).getId()+" - "+socios.get(i).getNombre());
            }
        }
        System.out.println("Sin Pagar:");
        for (int i = 0; i < socios.size(); i++) {
            if (socios.get(i).getEstado()=="no es socio"){
                System.out.println(socios.get(i).getId()+" - "+socios.get(i).getNombre());
            }
        }
    }
}
