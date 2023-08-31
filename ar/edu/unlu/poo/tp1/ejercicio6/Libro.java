package ar.edu.unlu.poo.tp1.ejercicio6;

public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private String paginas;
    private String año;
    private int cantejemplar;
    private int cantprestado;

    public String verdescripcion(){
        return ("titulo: " + titulo + "| autor: " + autor + "| isbn: " + isbn +
                "| cantidad de paginas: " + paginas + "| año: " + año +
                "| cantidad de ejemplares sin prestar: " + cantejemplar +
                "| cantidad de ejemplares prestados: " + cantprestado);
    }
    public boolean prestar(){
        if (cantejemplar > 0) {
            cantejemplar --;
            cantprestado ++;
            return true;
        }
        else return false;
    }

    public boolean devolver(){
        if (cantprestado > 0) {
            cantejemplar++;
            cantprestado--;
            return true;
        }
        else return false;
    }

    public void crearejemplar(){
        cantejemplar++;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public Object getTitulo(){
        return titulo;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }
    public Object getAutor(){
        return autor;
    }
    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getPaginas() {
        return paginas;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getAño() {
        return año;
    }
    public void setCantejemplar(int cantejemplar) {
        this.cantejemplar = cantejemplar;
    }

    public int getCantejemplar() {
        return cantejemplar;
    }

    public void setCantprestado(int cantprestado) {
        this.cantprestado = cantprestado;
    }

    public int getCantprestado() {
        return cantprestado;
    }
 }