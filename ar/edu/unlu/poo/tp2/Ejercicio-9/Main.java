package ar.edu.unlu.poo.tp2.ej9;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Figura2d> formas2d = new ArrayList<>();
        formas2d.add(new Circulo(3.0));
        formas2d.add(new Rectangulo(2.0, 7.0));
        formas2d.add(new Cuadrado(4.0));
        formas2d.add(new Triangulo(2.0, 5.0));

        List<Figura3d> formas3d = new ArrayList<>();
        formas3d.add(new Cubo(1.0));
        formas3d.add(new Esfera(2.0));
        formas3d.add(new Paralelepipedo(3.0, 4.0, 8.0));
        formas3d.add(new Tetraedro(4.0));

        for (Figura2d figura : formas2d) {
            System.out.printf("Área: " + figura.getArea() + "\tPerimetro: " + figura.getPerimetro() + "\n");
        }
        for (Figura3d figura : formas3d) {
            System.out.printf("Área: " + figura.getArea() + "\tPerimetro: " + figura.getVolumen() + "\n");
        }
    }
}