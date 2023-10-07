import ar.edu.unlu.poo.rentas.*;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();
        gestor.setAutoPasajero("Ford","123ABC",false, 4);
        gestor.setAutoPasajero("Torino","123ABC",true,2);
        gestor.setAutoCamion("chevrolet","246A32");
        gestor.setAutoCamioneta("Dodge","J2K6A2",100);
        gestor.setAutoCombi("Ford","15ABF32");

        Cliente cliente1 = new Cliente("Manuel Belgrano",123456789);
        Cliente cliente2 = new Cliente("Riquelme",156453134);

        Presupuesto presupuesto1 = new Presupuesto(gestor.getAuto("123ABC"),3);
        Presupuesto presupuesto2 = new Presupuesto(gestor.getAuto("246A32"),10);
        Presupuesto presupuesto3 = new Presupuesto(gestor.getAuto("246A32"),34);
        Presupuesto presupuesto4 = new Presupuesto(gestor.getAuto("J2K6A2"),7);
        Presupuesto presupuesto5 = new Presupuesto(gestor.getAuto("15ABF32"),20);

        Alquiler alquiler1 = new Alquiler(presupuesto1, cliente1,"1/5/2023","4/5/2023");
        Alquiler alquiler2 = new Alquiler(presupuesto2,cliente1,"1/5/2023","11/5/2023");
        Alquiler alquiler3 = new Alquiler(presupuesto3,cliente2,"25/9/2023","29/10/2023");
        Alquiler alquiler4 = new Alquiler(presupuesto4,cliente1,"8/7/2023","14/7/2023");
        Alquiler alquiler5 = new Alquiler(presupuesto5,cliente2,"1/11/2023","21/11/2023");

        gestor.setCliente(cliente1);
        gestor.setCliente(cliente2);
        gestor.setAlquiler(alquiler1);
        gestor.setAlquiler(alquiler2);
        gestor.setAlquiler(alquiler3);
        gestor.setAlquiler(alquiler4);
        gestor.setAlquiler(alquiler5);

        System.out.println("\n");
        gestor.MostrarMontoCliente(123456789);
        System.out.println("\n");
        gestor.MostrarMontoCliente(156453134);
        System.out.println("\n");
        gestor.MostrarMontoTotalAlquileres();

    }
}