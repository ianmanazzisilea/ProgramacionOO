package ar.edu.unlu.poo.tp2.ej12;

import java.util.Date;

public class BilleteraVirtual {
    /*Respondabilidades
        Hace movimientos de las cuentas (normal y credito) de un cliente
        Conocer al cliente?
     */
    private final Cliente cliente;
    private CuentaCredito cuentaCredito;
    private CuentaNormal cuentaNormal = new CuentaNormal();
    private CajaDeAhorro cajaDeAhorro = new CajaDeAhorro();
    BilleteraVirtual(Cliente cliente){
        this.cliente = cliente;
    }
    public void crearCuentaCredito(){
        cuentaCredito = new CuentaCredito();
    }
    public void cargarSaldo(Cuenta cuenta, double monto){
        cuenta.agregarSaldo(monto);
    }

    public void pagar(Cuenta cuenta, double monto){
        if(cuenta.realizarCompra(monto)){
            System.out.println("Se realizó la compra con éxito.");
        } else {
            System.out.println("No se pudo realizar la compra con el saldo disponible.");
        }
    }
    public CuentaNormal cuentaNormal(){
        return cuentaNormal;
    }
    public CajaDeAhorro cajaDeAhorro(){
        return cajaDeAhorro;
    }
    public CuentaCredito cuentaCredito(){ return cuentaCredito; }
    public void finalizarInversion(CuentaInversion cuenta){
        cuenta.finalizarInversion();
    }
    public void invertir(CuentaInversion cuenta, double monto, Date fechaInicio, int cantDias){
        cuenta.invertir(monto, fechaInicio, cantDias);
    }
    public void mostrarBilletera(){
        /*Saldo de la cuenta,
        límite de giro en descubierto,
        monto invertido (si existe inversión),
        monto disponible para compras a crédito y
        saldo deudor teniendo en cuenta todas las compras a crédito realizadas
         */
        System.out.println(cliente);
        System.out.println("\tInformación de la cuenta normal: ");
        System.out.print("Saldo de la cuenta normal: "+ cuentaNormal.saldo());
        System.out.printf("Limite de giro en descubierto de la cuenta: "+ cuentaNormal.limitedeGiro());
        if(cuentaNormal.montoInvertido() > 0) {
            System.out.printf("Monto invertido: "+ cuentaNormal.montoInvertido());
        }
        System.out.println("\tInformación de la Caja de Ahorro: ");
        System.out.printf("Saldo: " + cajaDeAhorro.saldo());
        if(cajaDeAhorro.montoInvertido()>0){
            System.out.printf("Monto invertido: "+ cajaDeAhorro.montoInvertido());
        }
        if(cuentaCredito().estado()){
            System.out.println("\tInformación de la cuenta de crédito: ");
            System.out.printf("Monto disponible para compras a crédito: "+ cuentaCredito.limiteDisponible());
            System.out.printf("Saldo deudor: "+cuentaCredito.saldo());
        }
    }
}