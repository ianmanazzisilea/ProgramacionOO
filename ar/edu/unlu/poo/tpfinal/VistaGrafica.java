package ar.edu.unlu.poo.tpfinal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class VistaGrafica implements IVista, Serializable {
    private JTabbedPane tabbedPane1;
    private JFrame frame;
    private JPanel contentPane;
    private JTextArea txtSalida;
    private JComboBox entrada;
    private JTextArea textArea2;
    private JButton button1;
    private ArrayList<String> mano = new ArrayList<>();
    private Controlador controlador;
    private ArrayList<String> cartasMesa = new ArrayList<>();
    private fase faseactual = fase.salaespera;
    private boolean canto = true;
    private int indice;
    private ArrayList<String> jugada = new ArrayList<>();
    private int indicebonus;
    @Override
    public void mesaactualizada(){
        txtSalida.setText("");
        mano = controlador.getMano();
        cartasMesa = controlador.getMesa();
        txtSalida.append("mano:" + "\n");
        for (int i = 0; i < mano.size(); i++) {
            txtSalida.append("carta " + (i + 1) + " " + mano.get(i) + "\n");
        }
        txtSalida.append("mesa:" + "\n");
        for (int i = 0; i < cartasMesa.size(); i++) {
            txtSalida.append("carta " + (i + 1) + " " + cartasMesa.get(i) + "\n");
        }
        mostrarMenu();
    }
    private enum fase{
        draw,
        mainfase,
        bonus,
        opponent,
        salaespera
    }
    //-------------------------comportamiento---------------------------------------------------
    public VistaGrafica(Controlador controlador){
        this.controlador = controlador;
        //hago esto por orden de ejecucion, yo lo hubiera hecho en el main
        controlador.setVista((IVista) this);
        this.frame = new JFrame("VistaConsola");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane1.setTitleAt(0,"juego");
        tabbedPane1.setTitleAt(0,"opciones");
        frame = new JFrame(controlador.getnombre());
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        button1.setLabel("empezar");
        //textArea2.setText("");
        txtSalida.append("aprete empezar en opciones cuando desee empezar" + "\n");
        frame.setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSalida.append(entrada.getSelectedItem() + "\n");
                procesarEntrada((String) entrada.getSelectedItem());
            }
        });
    }
    private void procesarEntrada(String input){
        if (mano.size()==0){
            if(faseactual != fase.salaespera)
                controlador.hayganador();
        }
            /*if (mano.size() == 2){
                canto = false;
                if (input.toLowerCase() == "dos"){
                    canto = true;
                }
                else
                    switch (faseactual){
                        case draw : draw(input);
                            break;
                        case mainfase : mainfase(input);
                            break;
                        case bonus : bonus(input);
                            break;
                        case salaespera : salaespera(input);
                            break;
                    }
            }
            else {*/
                switch (faseactual){
                    case draw : draw(input);
                        break;
                    case mainfase : mainfase(input);
                        break;
                    case bonus : bonus(input);
                        break;
                    case salaespera : salaespera(input);
                        break;
                }
            //}
            if (Objects.requireNonNull(faseactual) == fase.bonus) {
                mostrarMenu();
            }
    }

    private void salaespera(String input){
            faseactual = fase.opponent;
            controlador.empezar();
    }
    private void draw(String input){
        if (Objects.equals(input, "si")){
            faseactual = fase.mainfase;
            controlador.robar();
        }
        if (Objects.equals(input, "no")){
            faseactual = fase.mainfase;
            mostrarMenu();
        }
    }

    private void mainfase(String in){
        String input="-1";
        for (int i = 0; i < mano.size(); i++) {
            if (mano.get(i)==in){
                input = String.valueOf(i);
                mano.set(i,"");
                break;
            }
        }
        if (Integer.valueOf(input) > -1){
            jugada.add(String.valueOf(Integer.valueOf(input)));
        }
        if (Integer.valueOf(input) == -1){
            if (indice == 1){
                if (jugada.size() > 0){
                    controlador.jugada(jugada,indice-1);
                    txtSalida.append("intentó emparejar: "+"\n");
                    for (int i = 0; i < jugada.size(); i++) {
                        txtSalida.append("carta " + (Integer.valueOf(jugada.get(i)) + 1) + "\n");
                    }
                    txtSalida.append(" con la carta " + indice + " de la mesa" + "\n");

                }
                faseactual = fase.bonus;
                indice--;
            }
            else {
                if (jugada.size() > 0){
                    controlador.jugada(jugada,indice-1);
                    txtSalida.append("intentó emparejar: " + "\n");
                    for (int i = 0; i < jugada.size(); i++) {
                        txtSalida.append("carta " + (Integer.valueOf(jugada.get(i))+1) + "\n");
                    }
                    txtSalida.append(" con la carta " + indice + " de la mesa" + "\n");
                }
                indice--;
                txtSalida.append("que carta de la mano desea emparejar con la carta " + indice + "\n");
            }
            jugada = new ArrayList<>();
        }
    }
    private void bonus(String input){
        if (indicebonus == 0){
            indicebonus = controlador.bonus();
            if(indicebonus == 0){
                faseactual = fase.opponent;
                controlador.pasoturno();
            }
        }
        if (indicebonus > 1){
            if (Integer.valueOf(input)>=1){
                if (Integer.valueOf(input)<= mano.size()){
                    indicebonus--;
                    controlador.descartarbonus(Integer.valueOf(input)-1);
                }
            }

        }
        if (indicebonus == 1){
            if (Integer.valueOf(input)>=1){
                if (Integer.valueOf(input)<= mano.size()){
                    indicebonus--;
                    controlador.descartarbonus(Integer.valueOf(input)-1);
                    //pasa turno
                    faseactual = fase.opponent;
                    controlador.pasoturno();
                }
            }

        }
        if (!canto){
            controlador.nocanto();
        }
    }
        //-------------------visual-----------------------------------------------
    private void mostraropponent(){
        //mesaactualizada();
    }
    private void mostrardraw(){
        txtSalida.append("desea agarrar una carta?" + "\n");
    }
    private void mostrarmain(){
        if (indice == 0){
            indice = cartasMesa.size();
        }
        txtSalida.append("que carta de la mano desea emparejar con la carta " + indice + "\n");
    }
    private void mostrarbonus(){
        //mesaactualizada();
        if (controlador.booleanbonus()){
            txtSalida.append("ingrese carta que desee descartar por bonus de color" + "\n");
        }
    }
    private void opcionesespera(){
        entrada.removeAllItems();
        entrada.addItem("si");
        entrada.addItem("no");

    }
    private void opcionesdraw(){
        entrada.removeAllItems();
        entrada.addItem("si");
        entrada.addItem("no");
        button1.setLabel("robar?");
    }
    private void opcionesmain(){
        entrada.removeAllItems();
        for (int i = 0; i < mano.size(); i++) {
            entrada.addItem(mano.get(i));
        }
        entrada.addItem("terminar de combinar");
        button1.setLabel("combinar");
    }
    private void opcionesbonus(){
        entrada.removeAllItems();
        if (controlador.booleanbonus()){
            for (int i = 0; i < mano.size(); i++) {
                entrada.addItem(mano.get(i));
            }
        }
        else txtSalida.append("aprete en terminar");
        button1.setLabel("terminar");
    }
    private void setearopciones(){
        switch (faseactual){
            case salaespera:opcionesespera();
            break;
            case draw:opcionesdraw();
            break;
            case mainfase:opcionesmain();
            break;
            case bonus:opcionesbonus();
            break;
        }
    }
    private void mostrarMenu(){
        setearopciones();
        switch(faseactual){
            case draw: mostrardraw();
            break;
            case mainfase : mostrarmain();
            break;
            case bonus : mostrarbonus();
            break;
            case opponent : mostraropponent();
            break;
        }
    }
    @Override
    public void inicioturno() {
        if (controlador.getTurno()){
            faseactual = fase.draw;
            opcionesdraw();
        }
        else faseactual = fase.opponent;
    }

    @Override
    public void finpartida() {
        txtSalida.append("ganó el jugador: " + controlador.ganador());
        faseactual = fase.opponent;
    }
}
