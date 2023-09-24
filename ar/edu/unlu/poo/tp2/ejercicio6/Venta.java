package ar.edu.unlu.poo.tp2.ejercicio6;

public class Venta {
    private Empleado empleado;
    private String patente;
    private Combustible combus;
    private int surtidor;
    public Venta(Empleado empleado,String patente,String combustible, double cantidad, int surtidor){
        if(combustible== "nafta"){
            Nafta nafta = new Nafta();
            nafta.setCantidad(cantidad);
            combus=nafta;
            this.empleado = empleado;
            this.patente = patente;
            this.surtidor = surtidor;
        }
        if(combustible== "gasoil"){
            Gasoil gasoil = new Gasoil();
            gasoil.setCantidad(cantidad);
            combus=gasoil;
            this.empleado = empleado;
            this.patente = patente;
            this.surtidor = surtidor;
        }
        if(combustible== "kerosene"){
            Kerosene kerosene = new Kerosene();
            kerosene.setCantidad(cantidad);
            combus=kerosene;
            this.empleado = empleado;
            this.patente = patente;
            this.surtidor = surtidor;
        }
    }

    public int getSurtidor() {
        return surtidor;
    }

    public Combustible getCombus() {
        return combus;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public String getPatente() {
        return patente;
    }
}
