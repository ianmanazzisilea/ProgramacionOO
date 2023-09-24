package ar.edu.unlu.poo.tp2.ejercicio5;

import java.time.LocalDate;

public class CuentaNormal {
    private double Saldo;
    private double SaldoInvertido;
    private LocalDate diaInversion;
    private double LimiteGiro;
    private int N;//dias para inversion
    private boolean precancelar;
    private boolean pasoTiempo(){
        if (diaInversion.plusDays(N).isBefore(LocalDate.now())){
            return false;
        }
        else
            return true;
    }
    public double getSaldo() {
        if (pasoTiempo()){
            return Saldo;
        }
        else
        {
            Saldo= Saldo+(SaldoInvertido*0.4);
            diaInversion=LocalDate.now();
            return Saldo;
        }
    }

    public void setPrecancelar(boolean precancelar) {
        this.precancelar = precancelar;
    }

    public double getLimiteGiro() {
        return LimiteGiro;
    }

    public double getSaldoInvertido() {
        return SaldoInvertido;
    }
    public void retirarInversion(){
        if (diaInversion.plusDays(30).isBefore(LocalDate.now())){
            Saldo = Saldo + SaldoInvertido;
            SaldoInvertido=0;
        }
        else {
            if (pasoTiempo()){
                Saldo = Saldo + SaldoInvertido*1.4;
            }
            else Saldo = Saldo + SaldoInvertido*1.05;
            SaldoInvertido=0;
        }

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
        if (Saldo<dinero){
            if(precancelar){
                retirarInversion();
            }
            if (Saldo+LimiteGiro>=dinero){
                Saldo = Saldo-dinero;
                return true;
            }
            else {
                return false;
            }
        }
        else {
            Saldo = Saldo-dinero;
            return true;
        }
    }
}
