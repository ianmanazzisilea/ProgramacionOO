package ar.edu.unlu.poo.tpfinal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class VistaConsola implements IVista, Serializable {
    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JTextField txtEntrada;
    private JPanel contentPane;
    private JTextArea txtSalida;
    private JTextPane textPane1;
    private JFrame frame;
    private fase faseactual = fase.salaespera;
    private ArrayList<String> mano = new ArrayList<>();
    private Controlador controlador;
    private ArrayList<String> cartasMesa = new ArrayList<>();
    private int indice;
    private ArrayList<String> jugada = new ArrayList<>();
    private int indicebonus;
    private JPanel Juego;
    private JPanel Reglas;
    private JTextArea textArea1;
    private boolean canto = true;
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
    public VistaConsola(Controlador controlador) {
        this.controlador = controlador;
        //hago esto por orden de ejecucion, yo lo hubiera hecho en el main
        controlador.setVista((IVista) this);
        this.frame = new JFrame("VistaConsola");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane1.setTitleAt(0,"juego");
        tabbedPane1.setTitleAt(0,"reglas");
        frame = new JFrame(controlador.getnombre());
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        txtSalida.append("aprete si cuando desee empezar" + "\n");
        recuperartop();
        frame.setVisible(true);
        txtEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //txtSalida.setText("");
                String entrada = txtEntrada.getText();
                txtSalida.append(entrada + "\n");
                txtEntrada.setText("");
                procesarEntrada(entrada);

            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSalida.append(txtEntrada.getText() + "\n");
                procesarEntrada(txtEntrada.getText());
                txtEntrada.setText("");
            }
        });
    }

    private void procesarEntrada(String input) {
        input = input.trim();
        if (input.isEmpty())
            return;
        if (mano.size()==0){
            if(faseactual != fase.salaespera)
                controlador.hayganador();
        }
        if (mano.size() == 2){
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
        else {
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
        }
    }

    private void mainfase(String input){
        if (Integer.valueOf(input) > 0){
            if (Integer.valueOf(input) > mano.size()){
                txtSalida.append("ingrese la posicion de una carta que tenga o 0 para pasar de carta en mesa" + "\n");
            }
            else {
                jugada.add(String.valueOf(Integer.valueOf(input) - 1));
            }
        }
        if (Integer.valueOf(input) == 0){
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
        if (Integer.valueOf(input) < 0){
            txtSalida.append("ingrese la posicion de una carta que tenga o 0 para pasar de turno" + "\n");
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
        else txtSalida.append("ingrese algo en el input" + "\n");

    }
    private void mostrarMenu(){
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
    public void finpartida(){
        txtSalida.append("ganó el jugador: " + controlador.ganador());
        faseactual = fase.opponent;
    }

    @Override
    public String getnombre() {
        String ganador = (String) JOptionPane.showInputDialog(
                null,
                "Ha ganado", "Elija su nombre",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null
        );
        return ganador;
    }
    private void recuperartop(){
        Object[] lista = controlador.getscore();//5
        for (int i = 0; i < 5; i++) {
            //Object[] jugador = (Object[]) lista[i];
            //textArea1.append(jugador[0] + " " + jugador[1]);
        }
    }
    public void inicioturno(){
        if (controlador.getTurno()){
            faseactual = fase.draw;
            mostrarMenu();
        }
        else faseactual = fase.opponent;
    }
}
