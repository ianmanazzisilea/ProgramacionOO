package ar.edu.unlu.poo.rentas;

public class AutoPasajero extends Auto {
    private int cant_asientos;
    private boolean vip;

    public AutoPasajero (String marca, String patente, boolean vip, int cant_asientos){
        super(marca, patente);
        this.vip = vip;
        this.cant_asientos = cant_asientos;
    }

    public boolean es_vip(){
        return vip;
    }

    public int GetCant_asientos(){
        return cant_asientos;
    }

}
