package ar.edu.unlu.poo.tp1.ejercicio7;

import java.util.Random;

public class Randomizadordecalculos {
    public void provarcon(int cantveces){
        Calculador2doGrado calculador = new Calculador2doGrado();
        for (int i= 1; i<= cantveces; i++){
            int a = (int) (Math.random() * 3) - 1;
            int b = (int) (Math.random() * 10) + 1;
            int c = (int) (Math.random() * 3) + 1;
            calculador.calcularx(a,b,c);
            int x = (int) (Math.random() * 10) + 1;
            calculador.calculary(a,b,c,x);
            System.out.println("a: " + a + " | b: " + b + " | c: " + c + " | x: " + x);
            System.out.println("-----------------------------------------");
        }
    }
}
