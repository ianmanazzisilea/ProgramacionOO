package ar.edu.unlu.poo.tp1.ejercicio9;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

public class GeneradorDeFechas {
    private LocalDate generarFecha(){
        //DateTimeFormatter formato = DateTimeFormatter.ofPattern ("dd/MM/yyyy");
        LocalDate d1=LocalDate.of(0000,01,01);
        //d1.format(formato);
        int cantdias =(int) (Math.random() * 1000000) + 0;
        d1= d1.plusDays(cantdias);
        System.out.println(d1);
        return d1;
    }
    private String formatear(String fecha){
        String dia="";
        String mes="";
        String año="";
        año = año + fecha.charAt(0);
        año = año + fecha.charAt(1);
        año = año + fecha.charAt(2);
        año = año + fecha.charAt(3);
        mes = mes + fecha.charAt(5);
        mes = mes + fecha.charAt(6);
        dia = dia + fecha.charAt(8);
        dia = dia + fecha.charAt(9);
        return (dia+"-"+mes+"-"+año);
    }
    public void probarentreFechas(int cantPruebas){
        CalculadorDeFechas calcular = new CalculadorDeFechas();
        calcular.setFormatoingles(false);
        for (int i=0; i<cantPruebas;i++){
            String fecha1 = formatear(String.valueOf(generarFecha()));
            String fecha2 = formatear(String.valueOf(generarFecha()));
            String fecha3 = formatear(String.valueOf(generarFecha()));

            System.out.println(calcular.entreFechas(fecha1,fecha2,fecha3));
            System.out.println("-------------------");
        }
    }
    public void probarfechaMayor(int cantPruebas){
        CalculadorDeFechas calcular = new CalculadorDeFechas();

        for (int i=0; i<cantPruebas;i++){
            String fecha1 = formatear(String.valueOf(generarFecha()));
            String fecha2 = formatear(String.valueOf(generarFecha()));
            System.out.println(calcular.fechaMayor(fecha1,fecha2));
            System.out.println("-------------------");
        }
    }
    public void probarfechaMenor(int cantPruebas){
        CalculadorDeFechas calcular = new CalculadorDeFechas();

        for (int i=0; i<cantPruebas;i++){
            String fecha1 = formatear(String.valueOf(generarFecha()));
            String fecha2 = formatear(String.valueOf(generarFecha()));
            System.out.println(calcular.fechaMenor(fecha1,fecha2));
            System.out.println("-------------------");
        }
    }
}

