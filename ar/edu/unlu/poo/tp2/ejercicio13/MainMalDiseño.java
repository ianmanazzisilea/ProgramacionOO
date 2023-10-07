import EJ13.MalDiseño.Pasajero;
import EJ13.MalDiseño.Ticket;
import EJ13.MalDiseño.Tripulante;
import EJ13.MalDiseño.Vuelo;

public class MainMalDiseño {
    public static void main(String[] args) {
        System.out.println("Romina es el azafata de un vuelo");
        Vuelo vuelo1=new Vuelo();
        Tripulante romiTripulante = new Tripulante("Romina", "asdasd", "asdada", "Piloto");
        vuelo1.add(romiTripulante);
        System.out.println("Ahora Romina se quiere ir de vacaciones entonces compra un ticket");
        //El ticket solo esta relacionado con pasajero por lo que pepe necesita registrarse
        //nuevamente, pero esta vez como pasajero
        Vuelo vuelo2=new Vuelo();
        Ticket ticket = new Ticket(10,vuelo2);
        Pasajero romiPasajera = new Pasajero("Romina", "asdasd", "asdada", 987, ticket);
        vuelo2.add(romiPasajera);
        System.out.println("Hay 2 versiones de la persona Romina, una como pasajero y otra como tripulante");

    }
}
