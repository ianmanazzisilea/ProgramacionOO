package ar.edu.unlu.poo.tp1.ejercicio7;

public class Calculador2doGrado {
    public void calcularx(int a,int b,int c){
        if ((b*b-4*a*c)>=0){
            if (a !=0){
                double mas = (-b + Math.sqrt(b*b-4*a*c))/(2*a);
                double menos =(-b - Math.sqrt(b*b-4*a*c))/(2*a);
                if (mas!=menos){
                    System.out.println(mas +" y "+  menos + " son posibles x");
                }
                else System.out.println(mas + " es la unica x posible");
            }
            else System.out.println("no es una ecuacion cuadratica");

        }
        else System.out.println("no hay x posible");
    }
    public void calculary(int a,int b,int c,int x){
        System.out.println(a*x*x + b*x + c);
    }
}
