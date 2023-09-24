package ar.edu.unlu.poo.tp2.ejercicio6;

import java.util.ArrayList;

public class EstacionDeServicio {
    private ArrayList<Venta> ventas = new ArrayList();
    private  ArrayList<Empleado> empleados = new ArrayList<>();

    private Empleado buscarEmpleado(int id){
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId()==id){
                return empleados.get(i);
            }
        }
        return new Empleado();
    }
    public void nuevaVenta(int id, String patente, byte combustible, double cantidad, int surtidor){

        Empleado empleado = buscarEmpleado(id);
        String scombustible="";
        if (combustible==1){
            scombustible="nafta";
        }
        if (combustible==2){
            scombustible="nafta";
        }
        if (combustible==3){
            scombustible="nafta";
        }
        Venta nuevaVenta = new Venta(empleado, patente, scombustible, cantidad, surtidor);
        ventas.add(nuevaVenta);
    }
    //informes------------------------------------------------------
    public void informeCantCombustible(){
        ArrayList<Integer> combustibles= new ArrayList<>();
        combustibles.add(0);//nafta
        combustibles.add(0);//gasoil
        combustibles.add(0);//kerosene
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getCombus() instanceof Nafta){
                combustibles.add(0,combustibles.get(0)+1);
            }
            if (ventas.get(i).getCombus() instanceof Gasoil){
                combustibles.add(1,combustibles.get(1)+1);
            }
            if (ventas.get(i).getCombus() instanceof Kerosene){
                combustibles.add(2,combustibles.get(2)+1);
            }
        }
        System.out.println("nafta:"+combustibles.get(0));
        System.out.println("gasoil:"+combustibles.get(1));
        System.out.println("kerosene:"+combustibles.get(2));
    }
    public void informeVentaSurtidor(){
        ArrayList<Integer> surtidores = new ArrayList<>();
        //recolectar
        for (int i = 0; i < ventas.size(); i++) {
            surtidores.add(ventas.get(i).getSurtidor(), surtidores.get(ventas.get(i).getSurtidor())+1);
        }
        //buscar mayor
        int mayor=0;
        while (mayor>-1){
            mayor=-1;
            int pos=-1;
            for (int i = 0; i < surtidores.size(); i++) {
                if (surtidores.get(i)>=mayor){
                    mayor=surtidores.get(i);
                    pos=i;
                }
            }
            if (mayor>-1){
                System.out.println("surtidor " + pos + " con " + mayor + " cantidad de ventas");
                surtidores.add(pos,-1);
            }
        }
    }
    public void informeLitroSurtidor(){
        ArrayList<Double> surtidores = new ArrayList<>();
        //recolectar
        for (int i = 0; i < ventas.size(); i++) {
            surtidores.add(ventas.get(i).getSurtidor(), surtidores.get(ventas.get(i).getSurtidor())+ventas.get(i).getCombus().getCantidad());
        }                  //numero surtidor            //(litros acumulados)+                      litros de la venta siendo analizada
        //buscar mayor
        Double mayor=0.0;
        while (mayor>-1){
            mayor=-1.0;
            int pos=-1;
            for (int i = 0; i < surtidores.size(); i++) {
                if (surtidores.get(i)>=mayor){
                    mayor=surtidores.get(i);
                    pos=i;
                }
            }
            if (mayor>-1){
                System.out.println("surtidor " + pos + " con " + mayor + " cantidad de ventas");
                surtidores.add(pos,-1.0);
            }
        }
    }
    public void informeVentaEmpleado(){
        ArrayList<Integer> empleados = new ArrayList<>();
        //recolectar
        for (int i = 0; i < ventas.size(); i++) {
            empleados.add(ventas.get(i).getEmpleado().getId(), empleados.get(ventas.get(i).getEmpleado().getId())+1);
        }
        //buscar mayor
        int mayor=0;
        while (mayor>-1){
            mayor=-1;
            int pos=-1;
            for (int i = 0; i < empleados.size(); i++) {
                if (empleados.get(i)>=mayor){
                    mayor=empleados.get(i);
                    pos=i;
                }
            }
            if (mayor>-1){
                System.out.println("empleado " + pos + " con " + mayor + " cantidad de ventas");
                empleados.add(pos,-1);
            }
        }
    }
    public void top10clientes(){
        ArrayList<String> clientes = new ArrayList<>();
        ArrayList<Integer> cantidad = new ArrayList<>();
        //recolectar
        for (int i = 0; i < ventas.size(); i++) {
            boolean existe=false;
            for (int j = 0; j < clientes.size(); j++) {
                if (clientes.get(j)==ventas.get(i).getPatente()){
                    cantidad.add(j,cantidad.get(j)+1);
                    existe=true;
                }
            }
            if(!existe){
                clientes.add(ventas.get(i).getPatente());
                cantidad.add(1);
            }
        }
        //buscar mayor
        int mayor=0;
        int cant = 10;
        while ((mayor>-1)&&(cant>0)){
            mayor=-1;
            int pos=-1;
            for (int i = 0; i < cantidad.size(); i++) {
                if (cantidad.get(i)>=mayor){
                    mayor=cantidad.get(i);
                    pos=i;
                }
            }
            if (mayor>-1){
                System.out.println("cliente " + clientes.get(pos) + " con " + mayor + " cantidad de ventas");
                cantidad.add(pos,-1);
            }
            cant--;
        }
    }
}
