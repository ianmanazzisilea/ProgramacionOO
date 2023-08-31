package ar.edu.unlu.poo.tp1.ejercicio6;

public class Main {
    public static void main(String[] args) {

        Biblioteca biblioteca1 = new Biblioteca();
        //creo un par de libros
        biblioteca1.agregarlibro("20000leguas","julio","509","32","5", 4);
        biblioteca1.agregarlibro("20050leguas","julio","509","32","5", 4);
        //veo la lista con elementos llamados julio
        // para saber que numero ingresar en seleccionar libro
        System.out.println(biblioteca1.verLibros("julio"));
        //agrego ejemplar en libro seleccionado
        biblioteca1.agregarejemplar(biblioteca1.seleccionarlibro("julio",1));
        System.out.println(biblioteca1.verLibro(biblioteca1.seleccionarlibro("julio",2)));
        //presto ejemplar de libro seleccionado si es posible
        biblioteca1.prestar(biblioteca1.seleccionarlibro("julio",2));
        //muestro la cantidad de prestamos que tiene el libro seleccionado
        System.out.println(biblioteca1.cantidadprestamos(biblioteca1.seleccionarlibro("julio",2)));
        System.out.println(biblioteca1.verLibro(biblioteca1.seleccionarlibro("julio",2)));
        //se devuelve ejempllar de libro seleccionado si es posible
        biblioteca1.devolver(biblioteca1.seleccionarlibro("julio",2));
        System.out.println(biblioteca1.verLibro(biblioteca1.seleccionarlibro("julio",2)));
        System.out.println(biblioteca1.verLibros("julio"));

    }
}