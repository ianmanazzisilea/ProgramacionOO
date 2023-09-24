package ar.edu.unlu.poo.tp2.ejercicio2;

public class Proveedor {
    private byte estado;
    private String descripcion;
    private String destino;
    private int precio;
    public Proveedor(String destino, String estado, String descripcion, String sprecio){
        if (estado == "transporte"){
            this.estado = 1;
        }
        else
            if (estado == "hospedaje"){
                this.estado = 2;
            }
            else
                if (estado == "excurcion"){
                    this.estado = 3;
                }
                else this.estado = 0; //error
        this.descripcion = descripcion;
        this.destino = destino;
        precio = Integer.valueOf(sprecio);
    }

    public String getDescripcion() {
        return descripcion;
    }
    public String getEstado() {
        if (estado == 1){
            return "transporte";
        }
        else
        if (estado == 2){
            return "hospedaje";
        }
        if (estado == 3){
            return "excurcion";
        }
        else return "mal ingresado";
    }

    public String getDestino() {
        return destino;
    }

    public String getPrecio() {
        return String.valueOf(precio);
    }
}
