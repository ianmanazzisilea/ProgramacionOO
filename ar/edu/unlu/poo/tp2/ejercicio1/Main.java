package ar.edu.unlu.poo.tp2.ejercicio1;

public class Main {
    public static void main(String[] args) {
        Club club=new Club();
        club.agregarsocio("Pedro","a@hotmail.com","siempreviva"
                ,"basica",5,9,2023);
        club.agregarsocio("Maria","b@hotmail.com","callefalsa"
                ,"intermedia",5,9,2023);
        club.agregaractividad("entrada gratis", "intermedia");
        club.devolversocios("intermedia");
        club.devolverActividades();
    }
}
