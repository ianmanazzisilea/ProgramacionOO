package ar.edu.unlu.poo.tp2.ejercicio2;

import java.util.ArrayList;
import java.util.Scanner;

public class SunBeach {
    //1 destino, 1 transporte, 1 hospedaje y 1 o más excursiones.
    private ArrayList<Proveedor> proveedores= new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    public void agregarproveedor(String destino, String tipo, String nombre, String precio){
        Proveedor proveedor1= new Proveedor(destino, tipo, nombre, precio);
        proveedores.add(proveedor1);
    }
    private void soutbuscarmdt(String destino){
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getDestino() == destino){
                if (proveedores.get(i).getEstado() == "transporte"){
                    System.out.println(proveedores.get(i).getDescripcion() + " - $" + proveedores.get(i).getPrecio());
                }
            }
        }
    }
    private boolean bbuscarmdt(String destino, String transporte){
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getDestino() == destino){
                if (proveedores.get(i).getEstado() == "transporte"){
                    if (proveedores.get(i).getDescripcion() == transporte){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private Proveedor buscarmdt(String destino, String transporte){
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getDestino() == destino){
                if (proveedores.get(i).getEstado() == "transporte"){
                    if (proveedores.get(i).getDescripcion() == transporte){
                        return proveedores.get(i);
                    }
                }
            }
        }
        return new Proveedor("","","","");
    }
    private void soutbuscarh(String destino){
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getDestino() == destino){
                if (proveedores.get(i).getEstado() == "hospedaje"){
                    System.out.println(proveedores.get(i).getDescripcion() + " - $" + proveedores.get(i).getPrecio());
                }
            }
        }
    }
    private boolean bbuscarh(String destino, String hospedaje){
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getDestino() == destino){
                if (proveedores.get(i).getEstado() == "hospedaje"){
                    if (proveedores.get(i).getDescripcion() == hospedaje){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private Proveedor buscarh(String destino, String hospedaje){
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getDestino() == destino){
                if (proveedores.get(i).getEstado() == "hospedaje"){
                    if (proveedores.get(i).getDescripcion() == hospedaje){
                        return proveedores.get(i);
                    }
                }
            }
        }
        return new Proveedor("","","","");
    }
    private void soutbuscare(String destino){
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getDestino() == destino){
                if (proveedores.get(i).getEstado() == "excurcion"){
                    System.out.println(proveedores.get(i).getDescripcion() + " - $" + proveedores.get(i).getPrecio());
                }
            }
        }
    }
    private boolean bbuscare(String destino, String excurcion){
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getDestino() == destino){
                if (proveedores.get(i).getEstado() == "excurcion"){
                    if (proveedores.get(i).getDescripcion() == excurcion){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private Proveedor buscare(String destino, String excurcion){
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getDestino() == destino){
                if (proveedores.get(i).getEstado() == "excurcion"){
                    if (proveedores.get(i).getDescripcion() == excurcion){
                        return proveedores.get(i);
                    }
                }
            }
        }
        return new Proveedor("","","","");
    }
    private boolean bbuscard(String destino){
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getDestino() == destino){
                return true;
            }
        }
        return false;
    }

    public void posiblespaquetes(String destino){
        System.out.println("medios de transportes:");
        soutbuscarmdt(destino);
        System.out.println("hospedajes: ");
        soutbuscarh(destino);
        System.out.println("excurciones:");
        soutbuscare(destino);
    }
    public void comprar(String nombre, String dni){
        Cliente newCliente = new Cliente();
        newCliente.setNombre(nombre);
        newCliente.setDni(dni);
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Ingrese Destino");
        String destino = myObj.nextLine();  // Read user input
        if (bbuscard(destino)){
            System.out.println("Ingrese medio de transporte");
            String transporte = myObj.nextLine();  // Read user input
            if (bbuscarmdt(destino,transporte)){
                newCliente.setTransporte(buscarmdt(destino,transporte));
                System.out.println("Ingrese hospedaje");
                String hospedaje = myObj.nextLine();  // Read user input
                if (bbuscarh(destino,hospedaje)){
                    newCliente.setHospedaje(buscarh(destino,hospedaje));
                    String excurcion = new String();
                    ArrayList<Proveedor> excurciones = new ArrayList<>();
                    while (excurcion != ""){
                        System.out.println("Ingrese excurcion o aprete enter");
                        excurcion = myObj.nextLine();  // Read user input
                        if (bbuscare(destino, excurcion)){
                            excurciones.add(buscare(destino,excurcion));
                        }
                        else System.out.println("excurcion " + excurcion + " no es valida");
                    }
                    if (excurciones.size() >= 1){
                        newCliente.setExcurciones(excurciones);
                        clientes.add(newCliente);
                    }
                    else System.out.println("ingrese al menos una excurcion valida");
                }
                else System.out.println("hospedaje " + destino + " no es valido");

            }
            else System.out.println("transporte " + transporte + " no es valido");
        }
        else System.out.println("Destino " + destino + " no es invalido");  // Output user input
    }

    public void verClientes() {
        for (int i = 0; i < clientes.size(); i++) {
            int precio = 0;
            System.out.println(clientes.get(i).getNombre() + " - " + clientes.get(i).getDni());
            System.out.println("destino");
            System.out.println(clientes.get(i).getTransporte().getDestino());
            System.out.println("transorte");
            System.out.println(clientes.get(i).getTransporte().getDescripcion());
            precio=precio+Integer.valueOf(clientes.get(i).getTransporte().getPrecio());
            System.out.println("hospedaje");
            System.out.println(clientes.get(i).getHospedaje().getDescripcion());
            precio=precio+Integer.valueOf(clientes.get(i).getHospedaje().getPrecio());
            System.out.println("excurcion/es");
            for (int j = 0; j < clientes.get(i).getExcurciones().size(); j++) {
                System.out.println(clientes.get(i).getExcurciones().get(j).getDescripcion());
                precio=precio+Integer.valueOf(clientes.get(i).getExcurciones().get(j).getPrecio());
            }
            System.out.println("precio");
            System.out.println("$" + precio);
        };
    }
}
