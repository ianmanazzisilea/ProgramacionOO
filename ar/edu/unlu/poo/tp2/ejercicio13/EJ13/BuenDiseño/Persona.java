package EJ13.BuenDiseño;

import java.util.ArrayList;

public class Persona {
    private final String nombre;
    private final String numeroDeTelefono;
    private final String direccion;
    private final ArrayList<Rol> roles=new ArrayList<>();

    public Persona(String nombre,String numeroDeTelefono,String direccion){
        this.nombre=nombre;
        this.numeroDeTelefono=numeroDeTelefono;
        this.direccion=direccion;
    }
    public void rolVuelo(Vuelo vuelo){
        boolean valor=false;
        for (Rol rol:roles){
            valor= rol.parcipoEn(vuelo);
            if (valor){
                System.out.print("\nEsta persona participo en el vuelo como un: ");
                break;
            }
        }

    }
    public void establecerRol(Rol rol){
        roles.add(rol);
    }
}

