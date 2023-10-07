package ar.edu.unlu.poo.tp2.ej12;

public abstract class Cuenta {
    double saldo = 0;
    abstract boolean realizarCompra(double monto);
    public void agregarSaldo(double saldo){
        this.saldo += saldo;
    }
    public double saldo(){
        return saldo;
    }
}
