package ar.edu.unlu.poo.tp2.ejercicio3;

import java.util.ArrayList;

public class Academia {
    private ArrayList<Profesor> profesores = new ArrayList<>();
    private int legajo = 1;
    public void agregarprofesor(String nombre,String disciplina){
        Profesor profesor= new Profesor(nombre,disciplina);
    }
    public void agregardiagrama(String nombreprofesor,String nivel,String dia, String hora, String minuto){
        for (int i = 0; i < profesores.size(); i++) {
            if (profesores.get(i).getNombre()==nombreprofesor){
                profesores.get(i).agregardiagrama(dia,hora,minuto,nivel);
            }
        }
    }
    public void agregaralumno(String nombre, String diciplina, String nivel){
        for (int i = 0; i < profesores.size(); i++) {
            for (int j = 0; j < profesores.get(j).getDiagramas().size(); j++) {
                if (profesores.get(i).getDiagramas().get(j).getDisciplina()==diciplina){
                    if (profesores.get(i).getDiagramas().get(j).getNivel()==nivel){
                        profesores.get(i).agregaralumno(String.valueOf(legajo),nombre);
                        legajo++;
                    }
                }
            }
        }
    }
    public String diciplinamasasistida(){
        ArrayList<String> diciplinas = new ArrayList<>();
        ArrayList<Integer> cantidad = new ArrayList<>();
        for (int i = 0; i < profesores.size(); i++) {
            int j = 0;
            Boolean agregado= false;
            while (j < diciplinas.size()) {
                if (diciplinas.get(j)==profesores.get(i).getDisciplina()){
                    cantidad.add(j,cantidad.get(j)+1);
                    agregado = true;
                    j=diciplinas.size();
                }
                j++;
            }
            if (!agregado){
                diciplinas.add(profesores.get(i).getDisciplina());
                cantidad.add(1);
            }
        }
        String mejord = "";
        int cant = -1;
        for (int i = 0; i < diciplinas.size(); i++) {
            if (cantidad.get(i)>=cant){
                cant=cantidad.get(i);
                mejord=diciplinas.get(i);
            }
        }
        if (cant>-1){
            return mejord;
        }
        else return "no hay diciplinas ingresadas";
    }
}
