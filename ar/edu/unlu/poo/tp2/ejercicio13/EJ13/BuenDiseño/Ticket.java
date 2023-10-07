package EJ13.BuenDiseño;

public class Ticket {
    private final String nro;
    private final Vuelo vuelo;

    public Ticket(String nro, Vuelo vuelo){
        this.nro=nro;
        this.vuelo=vuelo;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }
}
