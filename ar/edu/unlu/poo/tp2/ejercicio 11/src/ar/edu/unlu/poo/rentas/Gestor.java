package ar.edu.unlu.poo.rentas;
import java.util.*;

public class Gestor {
    private int monto_total = 0;
    private ArrayList<Auto> autos = new ArrayList<>();
    private ArrayList<Alquiler> alquileres = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public void setAlquiler(Alquiler alquiler) {
        for (Auto auto_registrado : autos){
            if (auto_registrado.getPatente().equals(alquiler.getPresupuesto().getAuto().getPatente())){
                alquiler.getCliente().Actualizar_monto(alquiler.getSaldo());
                this.monto_total = monto_total + alquiler.getSaldo();
                alquileres.add(alquiler);
                return;
            }
        }
        System.out.println("El auto que quiere alquilar no se encuentra en el registro");
    }

    public void setAutoPasajero(String marca,String patente,boolean vip,int cant_asientos) {
        Auto auto = new AutoPasajero(marca,patente,vip, cant_asientos);
        for (Auto auto_registrado : autos){
            if (auto_registrado.getPatente().equals(auto.getPatente())){
                System.out.println("No se pudo registrar el auto por que ya existe un auto con la patente "+ patente);
                return;
            }
        }
        autos.add(auto);
    }

    public void setAutoCamion(String marca,String patente) {
        Auto auto = new Camion(marca,patente);
        for (Auto auto_registrado : autos){
            if (auto_registrado.getPatente().equals(auto.getPatente())){
                System.out.println("No se pudo registrar el auto por que ya existe un auto con la patente "+ patente);
                return;
            }
        }
        autos.add(auto);
    }

    public void setAutoCamioneta(String marca,String patente,int PAT) {
        Auto auto = new Camioneta(marca,patente,PAT);
        for (Auto auto_registrado : autos){
            if (auto_registrado.getPatente().equals(auto.getPatente())){
                System.out.println("No se pudo registrar el auto por que ya existe un auto con la patente "+ patente);;
                return;
            }
        }
        autos.add(auto);
    }

    public void setAutoCombi(String marca,String patente) {
        Auto auto = new Combi(marca,patente);
        for (Auto auto_registrado : autos){
            if (auto_registrado.getPatente().equals(auto.getPatente())){
                System.out.println("No se pudo registrar el auto por que ya existe un auto con la patente "+ patente);;
                return;
            }
        }
        autos.add(auto);
    }

    public void setCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente getClientes(int dni) {
        for (Cliente cliente : clientes){
            if (cliente.getDni() == dni){
                return cliente;
            }
        }
        return null;
    }

    public Auto getAuto(String patente) {
        for (Auto auto : autos){
            if (auto.getPatente().equals(patente)){
                return auto;
            }
        }
        System.out.println("Error, No se encontro el auto");
        return null;
    }

    public void MostrarMontoCliente(int dni){
        Cliente cliente = getClientes(dni);
        if (cliente != null){
            System.out.println("Nombre : "+ cliente.getNombre());
            System.out.println("DNI : "+ cliente.getDni());
            System.out.println("Monto total : "+ cliente.getMontoTotal());
        } else {
            System.out.println("El cliente no se encuentra en el registro Cliente");
        }
    }

    public void MostrarMontoTotalAlquileres(){
        System.out.println("El monto total de todos los alquileres del sistema es " + this.monto_total);
    }
}
