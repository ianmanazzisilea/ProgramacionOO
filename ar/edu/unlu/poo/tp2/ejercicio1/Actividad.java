package ar.edu.unlu.poo.tp2.ejercicio1;

public class Actividad {
    private String descripcion;
    private byte nivelrequerido;
    public Actividad(String descripcion, String nivel){
        this.descripcion = descripcion;
        if (nivel == "basica"){
            nivelrequerido = 1;
        }
        else
        if (nivel == "intermedia"){
            nivelrequerido = 2;
        }
        else
        if (nivel == "destacada"){
            nivelrequerido = 3;
        }
        else nivelrequerido = 0; //sin pagar
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNivelrequerido() {
        if (nivelrequerido == 1){
            return "basica";
        }
        else
            if (nivelrequerido == 2){
                return "intermedia";
            }
            else
                if (nivelrequerido == 3){
                    return "destacada";
                }
                else return "no es socio"; //sin pagar
    }
}
