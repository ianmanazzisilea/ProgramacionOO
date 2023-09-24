package ar.edu.unlu.poo.tp2.ejercicio8;

import java.time.LocalDate;
import java.util.ArrayList;

public class Diario extends Publicacion{
    private LocalDate fechaPublicacion;
    public Diario(int dia,int mes, int año, String titulo,
                 String telefono, String editor){
        LocalDate dateeof;
        fechaPublicacion= LocalDate.of(año,mes,dia);
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

    public LocalDate getfecha() {
        return fechaPublicacion;
    }
}
