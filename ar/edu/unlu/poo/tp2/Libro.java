package ar.edu.unlu.poo.tp2.ejercicio8;

import java.util.ArrayList;

public class Libro extends Publicacion{

    private ArrayList<String>autores;
    private String isbn;
    //private String paginas;
    private String año;
    private ArrayList<Ejemplar> ejemplares =new ArrayList();

    public Libro(ArrayList<String> autores, String isbn, String año, String titulo,
                 String telefono,String editor){
        this.autores=autores;
        this.año=año;
        this.isbn=isbn;
        super.setEditor(editor);
        super.setNombre(titulo);
        super.setTelefono(telefono);
    }

    public String verdescripcion(){
        String sautores="";
        for (int i = 0; i < autores.size(); i++) {
            sautores = sautores + autores.get(i) + "  ";
        }
        return ("titulo: " + super.getNombre() + "| autor/es: " + sautores + "| isbn: "
                + isbn + "| telefono: " + super.getTelefono() + "| editor: " +
                super.getEditor() + "| año: " + año);
        //"| cantidad de paginas: " + paginas + "| año: " + año + "| cantidad de ejemplares sin prestar: " + cantejemplar + "| cantidad de ejemplares prestados: " + cantprestado);
    }

    public Object getTitulo(){
        return super.getNombre();
    }

    public ArrayList<String> getAutor(){
        return autores;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAño() {
        return año;
    }
 }