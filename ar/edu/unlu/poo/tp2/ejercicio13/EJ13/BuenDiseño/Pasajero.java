package EJ13.BuenDiseño;


public class Pasajero implements Rol {
    private final String numeroPasajero;
    private final Ticket ticket;

    public Pasajero(String numeroPasajero,Ticket ticket) {
        this.numeroPasajero=numeroPasajero;
        this.ticket=ticket;
    }


    @Override
    public Boolean parcipoEn(Vuelo vuelo) {
        return ticket.getVuelo() == vuelo;
    }
}
