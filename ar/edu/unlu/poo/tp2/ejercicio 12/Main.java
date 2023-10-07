package ar.edu.unlu.poo.tp2.ej12;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private final Scanner leer = new Scanner(System.in);
    private final ArrayList<BilleteraVirtual> billeteras = new ArrayList<>();
    public static void main(String[] args) throws ParseException {
        Main sistema = new Main();
        sistema.menu();
    }
    public void menu() throws ParseException {
        boolean control = true;
        while(control) {
            int op;
            System.out.println("\tBienvenid@ al menú.");
            System.out.println("1-.Crear cliente.");
            System.out.println("2-.Habilitar cuenta de Credito a cliente");
            System.out.println("3-.Agregar saldo a cuenta normal, caja de ahorro o cuenta crédito.");
            System.out.println("4-.Pagar con cuenta normal, crédito o caja de ahorro.");
            System.out.println("5-.Invertir dinero.");
            System.out.println("6-.Mostrar información completa de un cliente");
            System.out.println("7-Finalizar inversión.");
            System.out.println("0-.Salir.");
            System.out.print("Ingrese una opción: ");
            op = leer.nextInt();
            switch(op){
                case 1 -> crearCliente();
                case 2 -> crearCuentaCredito();
                case 3 -> agregarSaldo();
                case 4 -> pagarSaldo();
                case 5 -> invertir();
                case 6 -> mostrarCliente();
                case 7 -> finalizarInversion();
                case 0 -> control=false;
                default ->
                        System.out.println("Ingrese una opcion valida.");
            }
        }
    }
    public void finalizarInversion(){
        int num, op;
        System.out.print("Ingrese numero de cliente: ");
        num = leer.nextInt();
        System.out.print("Ingrese con qué cuenta invertir: 1-Cuenta normal. 2-Caja de Ahorro. : ");
        op = leer.nextInt();
        BilleteraVirtual billetera = billeteras.get(num-1);
        if(op ==1){
            billetera.finalizarInversion(billetera.cuentaNormal());
        } else {
            billetera.finalizarInversion(billetera.cajaDeAhorro());
        }
    }
    public void mostrarCliente(){
        int num;
        System.out.print("Ingrese numero de cliente: ");
        num = leer.nextInt();
        BilleteraVirtual billetera = billeteras.get(num-1);
        billetera.mostrarBilletera();
    }
    public void crearCliente(){
        String nombre, apellido, dni, email;
        //Aca se crea la billetera después de crear el cliente
        System.out.print("Ingrese el nombre: ");
        nombre = leer.nextLine();
        System.out.print("Ingrese apellido: ");
        apellido = leer.nextLine();
        System.out.print("Ingrese dni: ");
        dni = leer.nextLine();
        System.out.print("Ingrese email: ");
        email = leer.nextLine();
        Cliente nuevoCliente = new Cliente(nombre, apellido, dni, email);
        BilleteraVirtual billetera = new BilleteraVirtual(nuevoCliente);
        billeteras.add(billetera);
        System.out.printf("El número de indentificación del cliente generado es: " + billeteras.size());
    }
    public void crearCuentaCredito(){
        int num;
        System.out.print("Ingrese numero de cliente: ");
        num = leer.nextInt();
        BilleteraVirtual billetera = billeteras.get(num-1);
        billetera.crearCuentaCredito();
    }
    public void agregarSaldo() {
        int num, op;
        double monto;
        //Se paga la deuda de la cuenta credito
        System.out.print("Ingrese numero de cliente: ");
        num = leer.nextInt();
        System.out.print("Ingrese con qué cuenta invertir: 1-Cuenta normal. 2-Caja de Ahorro. 3-Cuenta Crédito. : ");
        op = leer.nextInt();
        BilleteraVirtual billetera = billeteras.get(num - 1);
        System.out.print("Ingrese monto a agregar: ");
        monto = leer.nextInt();
        if (op == 1) {
            billetera.cargarSaldo(billetera.cuentaNormal(), monto);
        } else if (op == 2) {
            billetera.cargarSaldo(billetera.cajaDeAhorro(), monto);
        } else {
            billetera.cargarSaldo(billetera.cuentaCredito(), monto);
        }
    }
    public void invertir() throws ParseException {
        Date fechaInicio;
        String fecha;
        int num, op;
        double monto;
        System.out.print("Ingrese numero de cliente: ");
        num = leer.nextInt();
        BilleteraVirtual billetera = billeteras.get(num-1);
        System.out.print("Ingrese con qué cuenta invertir: 1-Cuenta normal. 2-Caja de Ahorro. : ");
        op = leer.nextInt();
        System.out.print("Ingrese monto a invertir: ");
        monto = leer.nextInt();
        System.out.print("Ingrese fecha de inicio (format:dd/MM/YYYY) : ");
        fecha = leer.nextLine();
        fechaInicio = formato.parse(fecha);
        System.out.print("Ingrese cantidad de días que durará la inversión: ");
        num = leer.nextInt();
        if(op ==1){
            billetera.invertir(billetera.cuentaNormal(), monto, fechaInicio, num);
        } else {
            billetera.invertir(billetera.cajaDeAhorro(), monto, fechaInicio, num);
        }
    }
    public void pagarSaldo(){
        int num, op;
        double monto;
        System.out.print("Ingrese numero de cliente: ");
        num = leer.nextInt();
        System.out.print("Ingrese con qué cuenta invertir: 1-Cuenta normal. 2-Caja de Ahorro. 3-Crédito.: ");
        op = leer.nextInt();
        BilleteraVirtual billetera = billeteras.get(num-1);
        System.out.print("Ingrese monto a pagar: ");
        monto = leer.nextInt();
        if(op == 1){
            billetera.pagar(billetera.cuentaNormal(), monto);
        } else if(op == 2){
            billetera.pagar(billetera.cajaDeAhorro(), monto);
        } else {
            billetera.pagar(billetera.cuentaCredito(), monto);
        }
    }
    }
