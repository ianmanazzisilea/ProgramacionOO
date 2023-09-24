package ar.edu.unlu.poo.tp2.ejercicio8;

import java.time.LocalDate;
import java.util.ArrayList;

public class Revista extends Publicacion{
    private String issn;
    private int numero;
    private String año;
    private ArrayList<Ejemplar> ejemplares =new ArrayList();
    public Revista(String issn,int numero, String año, String titulo,
                 String telefono, String editor){
        this.numero=numero;
        this.año=año;
        this.issn=issn;
        super.setEditor(editor);
        super.setNombre(titulo);
        super.setTelefono(telefono);
    }
    public Object getTitulo(){
        return super.getNombre();
    }

    public Object getAutor(){
        return super.getEditor();
    }

    public String getIssn() {
        return issn;
    }

    public String getAño() {
        return año;
    }

}
