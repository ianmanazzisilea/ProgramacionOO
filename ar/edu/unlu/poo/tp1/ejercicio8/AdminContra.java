package ar.edu.unlu.poo.tp1.ejercicio8;

public class AdminContra {
    private String contraseña;
    private boolean fuerte;

    private void esFuerte(String contraseña){
        int mayusculasrestantes=3;
        int minusculasrestantes=2;
        int numerosrestantes=3;
        for (int i=0;i<contraseña.length();i++){
            int asc = contraseña.charAt(i);
            if (asc > 57){
                asc = asc + 7;
            }
            else numerosrestantes--;
            if (asc > 90){
                asc = asc + 6;
                minusculasrestantes--;
            }
            else
            if (asc >= 65){
                mayusculasrestantes--;
            }
        }
        if ((mayusculasrestantes <= 0) && (minusculasrestantes <= 0)
                &&(numerosrestantes <= 0)){
            fuerte = true;
        }
        else fuerte = false;
    }

    public String sugerirContraseña(int longitud){
        //48-57 = 0-9
        //65-90 = A-Z
        //97-122 = a-z
        contraseña="";
        for (int i=0; i<longitud; i++){
            int asc = (int) ((Math.random() * 62)+48);
            if (asc > 57){
                asc = asc + 7;
                if (asc > 90){
                    asc = asc + 6;
                }
            }
            contraseña= contraseña + (char)asc;
        }
        esFuerte(contraseña);
        return contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }
    public boolean getFuerte(){
        return fuerte;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
        esFuerte(contraseña);
    }
}
