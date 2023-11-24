package ar.edu.unlu.poo.tpfinal;

import java.util.ArrayList;

public interface IVista {
    void setFaseactual();
    void setControlador(Controlador controlador);//temporal?
    void mesaactualizada(ArrayList<String> cartasMano, ArrayList<String> cartasMesa);

}
