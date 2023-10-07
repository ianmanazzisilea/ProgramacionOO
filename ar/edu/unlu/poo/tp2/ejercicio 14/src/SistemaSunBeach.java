package ar.edu.unlu.poo.tp2.ej14;

import ar.edu.unlu.poo.tp2.ej9.Circulo;

import java.util.ArrayList;
import java.util.Comparator;

public class SistemaSunBeach {
    private ArrayList<Proveedor> proveedores = new ArrayList<>();
    private ArrayList<PaqueteTuristico> paquetes = new ArrayList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public SistemaSunBeach() {
        // registro de proveedores
        ProveedorGuia guia = new ProveedorGuia("Alejandro", 5000);
        ProveedorGuia otroGuia = new ProveedorGuia("Mia", 6000);
        ProveedorTransporte transporte = new ProveedorTransporte("Despegar.com", 10000);
        ProveedorHospedaje hospedaje = new ProveedorHospedaje("Hotel Tenonde", 2500, 10);
        registrarProveedor(guia);
        registrarProveedor(otroGuia);
        registrarProveedor(transporte);
        registrarProveedor(hospedaje);

        System.out.println("---Lista de proveedores---");
        mostrarProveedores();

        // registro de paquetes
        PaqueteTuristico paquete1 = new PaqueteTuristico("Paris");
        PaqueteTuristico paquete2 = new PaqueteTuristico("Bariloche");
        PaqueteTuristico paquete3 = new PaqueteTuristico("Berlin");
        registrarPaquete(paquete1);
        registrarPaquete(paquete2);
        registrarPaquete(paquete3);

        System.out.println("\n---Paquetes turísticos activos---");
        mostrarPaquetes();

        // registro de clientes
        Cliente unCliente = new Cliente("Romina");
        Cliente otroCliente = new Cliente("Mateo");
        registrarCliente(unCliente);
        registrarCliente(otroCliente);

        System.out.println("\n---Clientes registrados---");
        listarClientes();

        // compra de paquetes
        Venta venta1 = new Venta(paquete2);
        registrarVenta(venta1, unCliente, 2);
        Venta venta2 = new Venta(paquete3);
        registrarVenta(venta2, otroCliente, 5);
        Venta venta3 = new Venta(paquete1);
        registrarVenta(venta3, unCliente, 4);

        System.out.println("\n---Ventas realizadas---");
        // mostrar ventas
        listarVentas();

        // mostrar compras por cliente
        mostrarComprasCliente(unCliente.getNombre());
        mostrarComprasCliente(otroCliente.getNombre());

    }

    public void registrarVenta(Venta venta, Cliente cliente, int pasajeros) {
        ventas.add(venta);
        venta.setCantidadClientes(pasajeros);
        cliente.realizarCompra(venta);
    }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    public void registrarProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }
    public void registrarPaquete(PaqueteTuristico paqueteTuristico) {
        paquetes.add(paqueteTuristico);
    }
    public void mostrarProveedores() {
        for (Proveedor proveedor : proveedores) {
            System.out.println(proveedor.toString());
        }
    }
    public void mostrarPaquetes() {
        for (PaqueteTuristico paquete : paquetes) {
            System.out.println(paquete.mostrarPaquete());
        }

    }
    public void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente.getNombre());
        }
    }
    public Cliente buscarCliente(String cliente) {
        for(Cliente cli : clientes) {
            if (cli.getNombre().equalsIgnoreCase(cliente)) {
                return cli;
            }
        }
        return null;
    }
    public void mostrarComprasCliente(String nombre) {
        Cliente cliente = buscarCliente(nombre);
        System.out.println("\n---Compras realizadas por el cliente <" + cliente.getNombre() + ">\n");
        for (Venta compra : cliente.getCompras()) {
            System.out.println(compra.soloDestino());
        }
    }
    public void listarVentas() {
        for (Venta venta : ventas) {
            System.out.println(venta.toString());
        }
    }
    /*public double totalHistoricoVentas(){
        double totalHistorico = 0;
        for (Venta venta : ventas){
            totalHistorico += venta.getCantidadClientes();
        }
        return totalHistorico;
    }
    public void mostrarDestinoFavorito() {
        if (ventas.isEmpty()){
            return null;
        } else {
            ArrayList<Venta> topDestino = new ArrayList<>(ventas);
            topDestino.sort(Comparator.comparing(SistemaSunBeach::totalHistoricoVentas));
            return topDestino.subList(0,9);
        }
    }*/
}
