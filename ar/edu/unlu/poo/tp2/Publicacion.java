package ar.edu.unlu.poo.tp2.ejercicio8;

import java.util.ArrayList;

public class Publicacion {
    private String nombre;
    private String editor;
    private String telefono;
    private ArrayList<Ejemplar> ejemplares =new ArrayList();

    public String getNombre() {
        return nombre;
    }

    public String getEditor() {
        return editor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void crearejemplar(int id){
        Ejemplar ejemplar= new Ejemplar(id);
        ejemplares.add(ejemplar);
    }
    public boolean prestar(int id){
        for (int i = 0; i < ejemplares.size(); i++) {
            if (ejemplares.get(i).getId()==id) {
                ejemplares.get(i).prestar();
                return true;
            }
        }
        return false;
    }

    public boolean devolver(int id){
        for (int i = 0; i < ejemplares.size(); i++) {
            if (ejemplares.get(i).getId()==id) {
                ejemplares.get(i).devolver();
                return true;
            }
        }
        return false;
    }
    public int getCantejemplar() {
        int cont=0;
        for (int i = 0; i < ejemplares.size(); i++) {
            if (!ejemplares.get(i).getPrestado()) cont++;
        }
        return cont;
    }
    public int getCantprestado() {
        int cont=0;
        for (int i = 0; i < ejemplares.size(); i++) {
            if (ejemplares.get(i).getPrestado()) cont++;
        }
        return cont;
    }
}
