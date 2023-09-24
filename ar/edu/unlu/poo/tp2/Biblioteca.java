package ar.edu.unlu.poo.tp2.ejercicio8;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    static private final ArrayList<Publicacion> publicaciones = new ArrayList<>();

    public void agregarlibro(String titulo, String editor, String isbn, String año, String telefono){
        Scanner myObj = new Scanner(System.in);
        String autor = myObj.nextLine();
        if (autor != ""){
            ArrayList<String> autores = new ArrayList<>();
            autores.add(autor);
            while (autor != ""){
                autor = myObj.nextLine();
                autores.add(autor);
            }
            Libro libro1 = new Libro(autores,isbn,año,titulo,telefono,editor);
            publicaciones.add(libro1);
        }
    }
    public void agregarrevista(String titulo,int numero, String editor, String issn, String año, String telefono){
        Revista revista1 = new Revista(issn,numero,año,titulo,telefono,editor);
        publicaciones.add(revista1);
    }
    public void agregartesis(String titulo,String autor, int mes, int año, String editor, String telefono){
        Tesis tesis1 = new Tesis(autor,String.valueOf(mes),String.valueOf(año),titulo,telefono,editor);
        publicaciones.add(tesis1);
    }
    public void agregarldiario(String titulo, String editor, int dia, int mes, int año, String telefono){
        Diario diario1 = new Diario(dia,mes,año,titulo,telefono,editor);
        publicaciones.add(diario1);
    }
    private ArrayList<Publicacion> buscarautor(String autor){
        ArrayList<Publicacion> publicacion2 = new ArrayList<>();
        for (int i = 0; i < publicaciones.size(); i++) {
            Publicacion publicacion1 = publicaciones.get(i);
            if (publicacion1 instanceof Libro){
                for (int j = 0; j < ((Libro) publicacion1).getAutor().size(); j++) {
                    if (((Libro) publicacion1).getAutor().get(j)==autor){
                        publicacion2.add(publicacion1);
                    }
                }
            }
            if (publicacion1 instanceof Tesis){
                if (((Tesis) publicacion1).getAutor()==autor){
                    publicacion2.add(publicacion1);
                }
            }
        }
        return publicacion2;
    }

    private ArrayList<Publicacion> buscartitulo(String titulo){
        ArrayList<Publicacion> publicacion2 = new ArrayList<>();
        for (int i = 0; i < publicaciones.size(); i++) {
            Publicacion publicacion1 = publicaciones.get(i);
            if (publicacion1.getNombre()==titulo){
                publicacion2.add(publicacion1);
            }
        }
        return publicacion2;
    }

    private ArrayList<Publicacion> buscarisbn(String isbn){
        ArrayList<Publicacion> publicacion2 = new ArrayList<>();
        for (int i = 0; i < publicaciones.size(); i++) {
            Publicacion publicacion1 = publicaciones.get(i);
            if (publicacion1 instanceof Libro){
                if (((Libro) publicacion1).getIsbn()==isbn){
                    publicacion2.add(publicacion1);
                }
            }
        }
        return publicacion2;
    }
    private ArrayList<Publicacion> buscarissn(String issn){
        ArrayList<Publicacion> publicacion2 = new ArrayList<>();
        for (int i = 0; i < publicaciones.size(); i++) {
            Publicacion publicacion1 = publicaciones.get(i);
            if (publicacion1 instanceof Revista){
                if (((Revista) publicacion1).getIssn()==issn){
                    publicacion2.add(publicacion1);
                }
            }
        }
        return publicacion2;
    }

    public boolean prestar(Publicacion publicacion,int id){
        if (publicacion.prestar(id)){
            return true;
        }
        else return false;
    }

    public String cantidadprestamos(Publicacion publicacion){
        return ("Cantidad de prestamos: " + publicacion.getCantprestado());
    }

    public boolean devolver(Publicacion publicacion,int id){
        if (publicacion.devolver(id)){
            return true;
        }
        else return false;
    }

    public void agregarejemplar(Publicacion publicacion,int id){
        publicacion.crearejemplar(id);
    }
    private ArrayList<Publicacion> buscar(String identificador){
        ArrayList<Publicacion> publicacion2 = new ArrayList<>();
        ArrayList<Publicacion> aux = new ArrayList<>();
        aux = buscarautor(identificador);
        for (int i = 0; i < aux.size(); i++) {
            publicacion2.add(aux.get(i));
        }
        aux = buscartitulo(identificador);
        for (int i = 0; i < aux.size(); i++) {
            publicacion2.add(aux.get(i));
        }
        aux = buscarisbn(identificador);
        for (int i = 0; i < aux.size(); i++) {
            publicacion2.add(aux.get(i));
        }
        aux = buscarissn(identificador);
        for (int i = 0; i < aux.size(); i++) {
            publicacion2.add(aux.get(i));
        }
        return publicacion2;
    }

    //Mascara
    public String verPublicaciones(String identificador){
        ArrayList<Publicacion> publicacion2 = new ArrayList<>();
        publicacion2=buscar(identificador);
        String descripciongeneral="";
        for (int i = 0; i < publicacion2.size(); i++) {
            Publicacion publicacion = publicacion2.get(i);
            descripciongeneral = descripciongeneral + (i+1) + ": " +
                    publicacion.getNombre() +"\n";
        }
        return descripciongeneral;
    }
    public Publicacion seleccionarpublicacion(String identificador,int seleccionador){
        ArrayList<Publicacion> publicacion2 = new ArrayList<>();
        publicacion2=buscar(identificador);
        if ((seleccionador<=publicacion2.size()) &&(seleccionador>0)){
            return publicacion2.get(seleccionador-1);
        }
        else throw new RuntimeException("seleccione una opcion posible!!");
    }
}
