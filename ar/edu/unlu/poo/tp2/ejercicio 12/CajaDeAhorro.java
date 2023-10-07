package ar.edu.unlu.poo.tp2.ej12;

public class CajaDeAhorro extends CuentaInversion{
    public boolean realizarCompra(double monto){
        if(saldo < monto ){
            return false;
        } else {
            if(monto > saldo && monto < saldo + invertido.invertido()){ //Si el pago supera al saldo disponible
                //Si el saldo mas lo invertido cubre el monto
                double inversion = invertido.finalizarInversion();
                double resto = Math.abs(inversion - monto);
                saldo -= resto;
            } else { //si es pago es igual o menor al saldo disponible
                saldo -= monto;
            }
        }
        return true;
    }
}
