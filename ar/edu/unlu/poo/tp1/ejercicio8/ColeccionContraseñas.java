package ar.edu.unlu.poo.tp1.ejercicio8;

import java.util.ArrayList;

public class ColeccionContraseñas {
    private final ArrayList<AdminContra> contraseñas = new ArrayList<>();
    public void generarContraseñas(int cantidad,int longitud){
        for (int i=0;i<cantidad;i++){
            AdminContra contraseñanueva= new AdminContra();
            contraseñanueva.sugerirContraseña(longitud);
            contraseñas.add(contraseñanueva);
        }
    }
    public void generarContraseñas(int cantidad){
        for (int i=0;i<cantidad;i++){
            AdminContra contraseñanueva= new AdminContra();
            contraseñanueva.sugerirContraseña(8);
            contraseñas.add(contraseñanueva);
        }
    }
    public void mostrar(){
        for (int i=0; i<contraseñas.size();i++){
            if (contraseñas.get(i).getFuerte()){
                System.out.println("<" + contraseñas.get(i).getContraseña() + "> - Fuerte");
            }
            else System.out.println("<" + contraseñas.get(i).getContraseña() + "> - Débil");
        }
    }
    public void modificarcontra(int indice, String nuevacontra){
        contraseñas.get(indice).setContraseña(nuevacontra);
    }
    public void generarContraseñaFuerte(int indice, int longitud){
        contraseñas.get(indice).sugerirContraseña(longitud);
        if ((!contraseñas.get(indice).getFuerte())&&(longitud>=8)){
            generarContraseñaFuerte(indice,longitud);
        }
    }
    public void generarContraseñaFuerte(int indice){
        contraseñas.get(indice).sugerirContraseña(8);
        if (!contraseñas.get(indice).getFuerte()){
            generarContraseñaFuerte(indice,8);
        }
    }
}
