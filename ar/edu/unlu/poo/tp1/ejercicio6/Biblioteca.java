package ar.edu.unlu.poo.tp1.ejercicio6;
import java.util.Iterator;
import java.util.ArrayList;
public class Biblioteca {
    static private final ArrayList<Libro> libros = new ArrayList<>();
    static private ArrayList<Libro> libros2 = new ArrayList<>();
    public String agregarlibro(String titulo, String autor, String paginas, String isbn, String año, int cantEjemplares){
        Libro libro1 = new Libro();
        libro1.setAutor(autor);
        libro1.setTitulo(titulo);
        libro1.setPaginas(paginas);
        libro1.setIsbn(isbn);
        libro1.setAño(año);
        libro1.setCantejemplar(cantEjemplares);
        libros.add(libro1);
        return libro1.verdescripcion();
    }
    public void buscarautor(String autor){
        Iterator<Libro> iteradorLibros = libros.iterator();
        for (int i = 0; i < libros.size(); i++) {
            Libro libro1 = libros.get(i);
            if (libro1.getAutor()==autor){
                libros2.add(libro1);
            }
        }
    }
    public void buscartitulo(String titulo){
        Iterator<Libro> iteradorLibros = libros.iterator();
        for (int i = 0; i < libros.size(); i++) {
            Libro libro1 = libros.get(i);
            if (libro1.getTitulo()==titulo){
                libros2.add(libro1);
            }
        }
    }

    public void buscarisbn(String isbn){
        Iterator<Libro> iteradorLibros = libros.iterator();
        for (int i = 0; i < libros.size(); i++) {
            Libro libro1 = libros.get(i);
            if (libro1.getIsbn()==isbn){
                libros2.add(libro1);
            }
        }
    }
    public boolean prestar(Libro libro){
        if (libro.prestar()){
            return true;
        }
        else return false;
    }

    public String cantidadprestamos(Libro libro){
        return ("Cantidad de prestamos: " + libro.getCantprestado());
    }

    public boolean devolver(Libro libro){
        return libro.devolver();
    }

    public String agregarejemplar(Libro libro){
        libro.crearejemplar();
        return libro.verdescripcion();
    }
    public String verLibro(Libro libro){
        return libro.verdescripcion();
    }

    //public Libro getLibro(int numerolibro){
    //    return libros.get(numerolibro);

    //Mascara
    public String verLibros(String identificador){
        libros2.clear();
        Iterator<Libro> iteradorLibros = libros2.iterator();
        buscarautor(identificador);
        buscartitulo(identificador);
        buscarisbn(identificador);
        String descripciongeneral="";
        for (int i = 0; i < libros2.size(); i++) {
            Libro libro2 = libros2.get(i);
            descripciongeneral = descripciongeneral + (i+1) + ": " +
                    verLibro(libro2) +"\n";
        }
        return descripciongeneral;
    }
    public Libro seleccionarlibro(String identificador,int seleccionador){
        Iterator<Libro> iteradorLibros = libros2.iterator();
        buscarautor(identificador);
        buscartitulo(identificador);
        buscarisbn(identificador);
        seleccionador--;
        for (int i = 0; i < libros2.size(); i++) {
            Libro libro2 = libros2.get(i);
            if (i==seleccionador){
                return libro2;
            }
        }
        throw new RuntimeException("seleccione una opcion posible!!");
    }

}
