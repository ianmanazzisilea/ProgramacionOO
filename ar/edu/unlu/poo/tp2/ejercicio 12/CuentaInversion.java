package ar.edu.unlu.poo.tp2.ej12;

import java.util.Date;

public abstract class CuentaInversion extends Cuenta{
    Inversion invertido;
    public double montoInvertido(){
        return invertido.invertido();
    }
    public void invertir(double monto, Date fechaInicio, int cantDias){
        if(monto <= saldo){//Si me alcanza el saldo para invertir
            invertido = new Inversion(monto, fechaInicio, cantDias);
            saldo -= monto;
            System.out.println("Inversión realizada.");
        } else {
            System.out.print("No le alcanza el saldo para realizar la inversión.");
        }
    }
    public void finalizarInversion(){
        if(invertido.finalizarInversion() != -1){
            agregarSaldo(invertido.finalizarInversion());
        }
    }
}
