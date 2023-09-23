package ar.edu.unlu.poo.tp2.ejercicio4;

public class Cliente {
    private CuentaNormal cnormal;
    private CuentaCredito ccredito;
    public void Cliente(CuentaNormal cuenta){
        cnormal = cuenta;
    }
    public void agregarCuentaCredito(CuentaCredito cuenta){
        ccredito = cuenta;
    }
    public double getSaldo(){
        if (cnormal.getSaldo()>0){
            return cnormal.getSaldo();
        }
        return 0;
    }
    public double getGiro(){
        if (cnormal.getSaldo()>0){
            return cnormal.getLimiteGiro();
        }
        return cnormal.getLimiteGiro()-cnormal.getSaldo();
    }
    public double getInversion(){
        return cnormal.getSaldoInvertido();
    }
    public double getCreditoDisponible(){
        return ccredito.getQueda();
    }
    public double getDeudaCredito(){
        return ccredito.getDebe();
    }
    public boolean gastarNormal(double dinero){
        if (dinero<=cnormal.getSaldo()){
            cnormal.retirar(dinero);
            return true;//no gira
        }
        else
            if (cnormal.retirar(dinero)){
                return false;//gira
            }
            else {
                System.out.println("no tiene Saldo suficiente");
                return false;
            }


    }
}
