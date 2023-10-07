package EJ13.BuenDiseño;

import java.util.ArrayList;

public class Tripulante implements Rol{
    private final String cargo;
    private final ArrayList<ReciboSueldo> recibos=new ArrayList<>();
    private final Vuelo vuelo;
    public Tripulante(String cargo,Vuelo vuelo) {
        this.cargo=cargo; this.vuelo=vuelo;
    }

    @Override
    public Boolean parcipoEn(Vuelo vuelo) {
        if(vuelo==this.vuelo){
            System.out.print("Tripulante");
            return true;
        }
        return false;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }
}
