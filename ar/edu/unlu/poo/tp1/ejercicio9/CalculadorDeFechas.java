package ar.edu.unlu.poo.tp1.ejercicio9;

import java.time.LocalDate;

public class CalculadorDeFechas {
    private boolean formatoingles = false;

    public void setFormatoingles(boolean formatoingles) {
        this.formatoingles = formatoingles;
    }

    private LocalDate transformar(String fecha){
        String año="";
        String mes="";
        String dia="";
        if (formatoingles){
            mes = mes + fecha.charAt(0);
            mes = mes + fecha.charAt(1);
            dia = dia + fecha.charAt(3);
            dia = dia + fecha.charAt(4);
        }
        else{
            dia = dia + fecha.charAt(0);
            dia = dia + fecha.charAt(1);
            mes = mes + fecha.charAt(3);
            mes = mes + fecha.charAt(4);

        }
        año = año + fecha.charAt(6);
        año = año + fecha.charAt(7);
        año = año + fecha.charAt(8);
        año = año + fecha.charAt(9);
        return LocalDate.of(Integer.valueOf(año),
                Integer.valueOf(mes), Integer.valueOf(dia));
    }

    public boolean entreFechas(String sfanalizar, String sf1, String sf2){
        LocalDate fanalizar = transformar(sfanalizar);
        LocalDate f1 = transformar(sf1);
        LocalDate f2 = transformar(sf2);
        if (f1.isAfter(f2)){
            if (f1.isAfter(fanalizar) && f2.isBefore(fanalizar)){
                return true;
            }
            else return false;
        }
        else
            if (f2.isAfter(fanalizar) && f1.isBefore(fanalizar)){
                return true;
            }
            else return false;
    }
    public boolean fechaMayor(String sfanalizar, String sfechacomparar){
        LocalDate fanalizar = transformar(sfanalizar);
        LocalDate fechacomparar = transformar(sfechacomparar);
        if (fanalizar.isAfter(fechacomparar)){
            return true;
        }
        else return false;
    }
    public boolean fechaMenor(String sfanalizar, String sfechacomparar){
        LocalDate fanalizar = transformar(sfanalizar);
        LocalDate fechacomparar = transformar(sfechacomparar);
        if (fanalizar.isBefore(fechacomparar)){
            return true;
        }
        else return false;
    }
}
