package ar.edu.unlu.poo.tp2.ejercicio5;

public class CuentaCredito {
    private double montoLimite;//=n;
    private double montoGastado;

    public double getDebe() {
        return montoGastado*1.05;
    }

    public double getQueda() {
        return montoLimite-montoGastado;
    }

    public void pagar(double ingreso){
        montoGastado= ((montoGastado*1.05)- ingreso)/1.05;
    }
    public boolean comprar(double dinero){
        if (montoGastado+dinero<=montoLimite){
            montoGastado=montoGastado+dinero;
            return true;
        }
        return false;
    }
}
