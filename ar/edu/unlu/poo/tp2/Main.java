package ar.edu.unlu.poo.tp2.ejercicio8;

public class Main {
    public static void main(String[] args) {

        Biblioteca biblioteca1 = new Biblioteca();
        //creo un par de libros
        biblioteca1.agregarlibro("20000leguas","del fondo","509","2022","2323123456");
        biblioteca1.agregarlibro("80dias","del medio","509","2022","2323123456");
        biblioteca1.agregarlibro("viajecentro","del tope","509","2022","2323123456");
        //veo la lista con elementos llamados julio
        // para saber que numero ingresar en seleccionar libro
        System.out.println(biblioteca1.verPublicaciones("julio"));
        //agrego ejemplar en libro seleccionado
        biblioteca1.agregarejemplar(biblioteca1.seleccionarpublicacion("julio",1),1);
        //presto ejemplar de libro seleccionado si es posible
        biblioteca1.prestar(biblioteca1.seleccionarpublicacion("julio",2),1);
        //muestro la cantidad de prestamos que tiene el libro seleccionado
        System.out.println(biblioteca1.cantidadprestamos(biblioteca1.seleccionarpublicacion("julio",2)));
        //se devuelve ejemplar de libro seleccionado si es posible
        biblioteca1.devolver(biblioteca1.seleccionarpublicacion("julio",2),1);
        System.out.println(biblioteca1.verPublicaciones("julio"));
    }
}
