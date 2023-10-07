package ar.edu.unlu.poo.rentas;

public class Alquiler{
    private Cliente cliente;
    private Presupuesto presupuesto;
    private String fecha_inicio;
    private String fecha_final;
    private int saldo;

    public Alquiler(Presupuesto presupuesto, Cliente cliente, String fecha_inicio, String fecha_final){
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.presupuesto = presupuesto;
        this.cliente = cliente;
        if (presupuesto.getAuto() instanceof AutoPasajero){
            this.saldo = CalcularPrecioAutoPasajero (presupuesto,((AutoPasajero) presupuesto.getAuto()).es_vip());
        } else if (presupuesto.getAuto() instanceof Camion){
            this.saldo = CalcularPrecioCamion(presupuesto);
        } else if (presupuesto.getAuto() instanceof Combi){
            this.saldo = 4500 * presupuesto.getDias_alquiler();
        } else {
            this.saldo = presupuesto.getAuto().getPrecio();
        }
    }

    private int CalcularPrecioAutoPasajero (Presupuesto presupuesto,Boolean vip){
        if (vip){
            return (presupuesto.getAuto().getPrecio() + (500 * ((AutoPasajero) presupuesto.getAuto()).GetCant_asientos())) * (presupuesto.getDias_alquiler());
        } else {
            return (presupuesto.getAuto().getPrecio() + (300 * ((AutoPasajero) presupuesto.getAuto()).GetCant_asientos())) * (presupuesto.getDias_alquiler());
        }
    }

    private int CalcularPrecioCamion (Presupuesto presupuesto){
        if (presupuesto.getDias_alquiler() < 30){
            return (presupuesto.getAuto().getPrecio() * presupuesto.getDias_alquiler());
        } else {
            return (75000 * presupuesto.getDias_alquiler());
        }
    }

    public int getSaldo(){
        return saldo;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
