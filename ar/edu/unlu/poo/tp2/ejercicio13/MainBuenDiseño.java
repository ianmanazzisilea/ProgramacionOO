import EJ13.BuenDiseño.*;

public class MainBuenDiseño {

    public static void main(String[] args) {
        Persona romi = new Persona("Romina", "asdasd", "asdada");
        System.out.println("Romina es el azafata de un vuelo");
        Vuelo vuelo1=new Vuelo();

        Tripulante rol = new Tripulante("azafata", vuelo1);
        romi.establecerRol(rol);
        vuelo1.add(romi);
        System.out.println("Ahora Romina se quiere ir de vacaciones entonces compra un ticket");
        Vuelo vuelo2=new Vuelo();
        Ticket ticket = new Ticket("45345", vuelo2);
        Pasajero pasajero = new Pasajero("123123", ticket);
        romi.establecerRol(pasajero);
        //se añade a romi a la lista de miembros de ese vuelo
        vuelo2.add(romi);
        System.out.println("Ya no se necesitan 2 versiones para asignar un tripulante/pasajero");

    }
}
