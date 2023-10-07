package ar.edu.unlu.poo.rentas;

public class Presupuesto {
    private Auto auto;
    private int dias_alquiler;

    public Presupuesto(Auto auto,int dias_alquiler){
        this.auto = auto;
        this.dias_alquiler = dias_alquiler;
    }

    public Auto getAuto() {
        return auto;
    }

    public int getDias_alquiler() {
        return dias_alquiler;
    }
}
