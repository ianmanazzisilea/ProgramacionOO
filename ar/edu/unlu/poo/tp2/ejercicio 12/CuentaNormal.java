package ar.edu.unlu.poo.tp2.ej12;

public class CuentaNormal extends CuentaInversion{
    /*Responsabilidades
        límite de giro en descubierto
            Si el limite de giro es >0 entonces si carga saldo se descontará
            de allí para volver a 0 hasta que sea 0 otra vez
        saldo total
        Inversión actual (1 a la vez)
     */
    private double giroEnDescubierto = 0;
    private double saldoDeuda = 0;
    public double limitedeGiro(){
        return giroEnDescubierto;
    }
    @Override
    public void agregarSaldo(double saldo){
        if(giroEnDescubierto == 0){
            giroEnDescubierto = saldo + ((saldo*35)/100);
        }
        if(saldoDeuda <= saldo && saldoDeuda > 0){ //Si tengo deuda y es más chica que el saldo cargado
            double resto = Math.abs(saldoDeuda - saldo);
            saldoDeuda = 0;
            if(resto > 0){ //Si se cubre la deuda y queda dinero, se agrega al saldo
                this.saldo += resto;
            }
        } else if(saldoDeuda > saldo){//Si la deuda que tengo es mayor al saldo cargado
            saldoDeuda -= saldo;
        } else { //Si no hay deuda se suma al saldo
            this.saldo += saldo;
        }
    }
    @Override
    public boolean realizarCompra(double monto){
        if(saldo == 0 && saldoDeuda == giroEnDescubierto){
            return false;
        } else {
            if(monto > saldo){ //Si el pago supera al saldo disponible
                if(monto < saldo + invertido.invertido()){ //Si el saldo mas lo invertido cubre el monto
                    double inversion = invertido.finalizarInversion();
                    double resto = Math.abs(inversion - monto);
                    saldo -= resto;
                } else if(monto > saldo + (giroEnDescubierto - saldoDeuda) && monto < invertido.invertido() + saldo + (giroEnDescubierto - saldoDeuda)){//precancelar inversión
                    //Si el saldo necesita hacer un giro más el saldo más lo invertido
                    double inversion = invertido.finalizarInversion();
                    double resto = Math.abs(inversion - monto);
                    resto = Math.abs(saldo - resto);
                    saldo = 0;
                    System.out.printf("Se generó un giro en descubierto de :" + resto + "\n");
                    saldoDeuda += resto;
                } else {
                    double resto = Math.abs(saldo - monto);
                    saldo = 0;
                    saldoDeuda += resto;
                    System.out.print("Se generó un giro en descubierto de :" + (saldoDeuda-resto) + "\n");
                }
            } else { //si es pago es igual o menor al saldo disponible
                saldo -= monto;
            }
        }
        return true;
    }
}
