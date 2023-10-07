package EJ13.MalDiseño;

public class Pasajero extends Persona{
    private final Integer numeroPasajero;
    Ticket ticket;

    public Pasajero(String nombre, String numeroDeTelefono, String direccion,Integer numeroPasajero,Ticket ticket) {
        super(nombre, numeroDeTelefono, direccion);
        this.numeroPasajero=numeroPasajero;
        this.ticket=ticket;
    }
}
