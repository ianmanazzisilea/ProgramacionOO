package ar.edu.unlu.poo.tp1.ejercicio10;

import ar.edu.unlu.poo.tp1.ejercicio10.Lista;

public class Main {
    public static void main(String[] args) {
        Lista listatareas = new Lista();
        listatareas.agregartarea("Ir al supermercado mañana",1,
                "incompleta",10,9,2023
                ,3,9,2023);
        listatareas.modificardescripcion(1,"Ir al supermercado");
        listatareas.agregartarea("Consultar repuesto del auto",2,
                "completa",1,9,2023,
                4,9,2023);
        listatareas.modificarprioridad(1,2);
        listatareas.modificarestado(1);
        listatareas.agregartarea("Ir al cine a ver la nueva película de Marvel",3,
                "incompleta",1,9,2023,
                2,9,2023);
        listatareas.mostrar();
    }
}

