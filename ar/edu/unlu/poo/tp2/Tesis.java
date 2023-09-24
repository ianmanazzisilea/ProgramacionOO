package ar.edu.unlu.poo.tp2.ejercicio8;

import java.util.ArrayList;

public class Tesis extends Publicacion{
    private String autor;
    private String mes;
    private String año;
    public Tesis(String autor, String mes, String año, String titulo,
                 String telefono, String editor){
        this.autor=autor;
        this.año=año;
        this.mes=mes;
        super.setEditor(editor);
        super.setNombre(titulo);
        super.setTelefono(telefono);
    }



    public Object getTitulo(){
        return super.getNombre();
    }

    public Object getAutor(){
        return autor;
    }

    public String getAño() {
        return año;
    }
}
