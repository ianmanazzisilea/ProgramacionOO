package ar.edu.unlu.poo.tp2.ej12;

import java.util.Calendar;
import java.util.Date;

public class Inversion {
    /*Responsabilidades
        Tiene el monto total invertido (Se le agrega un %40)
        Tiene fecha de inicio
        Tiene cant días de duración de inversión
     */
    private double monto = 0;
    private Date fechaInicio;
    private int cantDias;
    private boolean estado;
    Inversion(double monto, Date fechaInicio, int cantDias){
        if(estado){
            System.out.println("Ya hay una inversión actualmente");
        } else {
            Calendar calendar = Calendar.getInstance();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(fechaInicio);
            calendar1.add(Calendar.DAY_OF_YEAR, 30);
            calendar.setTime(fechaInicio);
            calendar.add(Calendar.DAY_OF_YEAR, cantDias);
            Date fecha = new Date();
            if(calendar.getTime().before(fecha)){//Controla que hayan pasado la cant de días establecidos
                this.monto = monto;
            } else if(calendar1.getTime().before(fecha)){//5% al menos 30 días
                this.monto = monto - ((monto * 35) / 100);
            } else {
                System.out.println("La inversión no tuvo interés.");
                this.monto = monto - ((monto*40)/100);
            }
            this.fechaInicio = fechaInicio;
            this.cantDias = cantDias;
            estado = true;
        }
    }
    public double invertido(){
        if(estado){
            return monto;
        } else {
            return -1;
        }
    }
    public double finalizarInversion(){
        if(estado){
            monto = 0;
            estado = false;
            return monto;
        } else {
            return -1;
        }
    }
}
