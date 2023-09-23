package ar.edu.unlu.poo.tp2.ejercicio4;

import java.time.LocalDate;

public class CuentaNormal {
    private double Saldo;
    private double SaldoInvertido;
    private LocalDate diaInversion;
    private double LimiteGiro;
    private int N;//dias para inversion

    public double getSaldo() {
        if (diaInversion.plusDays(N).isBefore(LocalDate.now())){
            return Saldo;
        }
        else
        {
            Saldo= Saldo+(SaldoInvertido*0.4);
            diaInversion=LocalDate.now();
            return Saldo;
        }
    }

    public double getLimiteGiro() {
        return LimiteGiro;
    }

    public double getSaldoInvertido() {
        return SaldoInvertido;
    }
    public void retirarInversion(){
        Saldo = Saldo + SaldoInvertido;
        SaldoInvertido=0;
    }
    public boolean agregarInversion(Double dinero){
        if (Saldo+LimiteGiro>=dinero){
            Saldo = Saldo-dinero;
            SaldoInvertido = SaldoInvertido+dinero;
            diaInversion = LocalDate.now();
            return true;
        }
        return false;
    }
    public void depositar(Double dinero){
        Saldo= Saldo+dinero;
    }
    public boolean retirar(Double dinero){
        if (Saldo+LimiteGiro>=dinero){
            Saldo = Saldo-dinero;
            return true;
        }
        return false;
    }
}
