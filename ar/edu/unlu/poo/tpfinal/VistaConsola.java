package ar.edu.unlu.poo.tpfinal;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VistaConsola implements IVista{
    private String nombre;
    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JTextField txtEntrada;
    private JPanel contentPane;
    private JPanel Juego;
    private JPanel Reglas;
    private JTextArea txtSalida;
    private JFrame frame;
    private fase faseactual = fase.opponent;
    private ArrayList<String> mano = new ArrayList<>();
    private Controlador controlador;
    private ArrayList<String> cartasMesa = new ArrayList<>();

    public void VistaConsola(){
        this.frame = new JFrame("VistaConsola");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
    @Override
    public void setFaseactual() {
        faseactual = fase.draw;
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void mesaactualizada(ArrayList<String> cartasMesa,ArrayList<String> cartasMano) {
        this.mano = cartasMano;
        this.cartasMesa = cartasMesa;
        for (int i = 0; i < mano.size(); i++) {
            txtSalida.append(mano.get(i));
        }
        for (int i = 0; i < cartasMesa.size(); i++) {
            txtSalida.append(cartasMesa.get(i));
        }
    }


    private enum fase{
        draw,
        mainfase,
        shout,
        opponent,
        salaespera
    }

    public void setFaseactual(fase faseactual) {
        this.faseactual = faseactual;
    }

    //-------------------------comportamiento---------------------------------------------------
    public VistaConsola() {
        tabbedPane1.setTitleAt(0,"juego");
        tabbedPane1.setTitleAt(0,"reglas");
        frame = new JFrame("<class name>");
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        txtSalida.append("aprete si cuando desee empezar");

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
        switch (faseactual){
            case draw : draw(input);
            break;
            case mainfase : mainfase(input);
            break;
            case shout : shout(input);
            break;
            case salaespera : salaespera(input);
            break;
        }
        mostrarMenu();

    }
    private void salaespera(String input){
        if (input == "si"){
            controlador.empezar();
        }
    }
    private void draw(String input){
        if (input == "si"){
            controlador.robar(nombre);
            faseactual = fase.mainfase;
        }
        if (input == "no"){
            faseactual = fase.mainfase;
        }
    }

    private void mainfase(String input){
        int indice = cartasMesa.size();
        if (Integer.valueOf(input) > 0){
            if (Integer.valueOf(input) >= mano.size()){
                txtSalida.append("ingrese la posicion de una carta que tenga o 0 para pasar de turno");
            }
            else {
                controlador.jugada(nombre,input,indice);
                indice--;
                txtSalida.append("que carta de la mano desea emparejar con la carta " + indice);
            }
            //"ingrese la carta con la que quiere emparejar la " + indice + "carta"
        }
        if (Integer.valueOf(input) == 0){
            if (indice == 0){
                faseactual = fase.shout;
            }
            indice = indice - 1;
            txtSalida.append("que carta de la mano desea emparejar con la carta " + indice);
        }
        if (Integer.valueOf(input) < 0){
            txtSalida.append("ingrese la posicion de una carta que tenga o 0 para pasar de turno");
        }
    }
    private void shout(String input){
        if (mano.size() == 2){
            if (input == "si"){
                faseactual = fase.opponent;
                controlador.pasoturno();
            }
            else {
                controlador.nocanto(nombre);
                faseactual = fase.opponent;
                controlador.pasoturno();
            }
        }

    }
    //-------------------visual-----------------------------------------------
    private void mostraropponent(){
        for (int i = 0; i < mano.size(); i++) {
            txtSalida.append(mano.get(i));
        }
    }
    private void mostrardraw(){
        for (int i = 0; i < mano.size(); i++) {
            txtSalida.append(mano.get(i));
        }
        txtSalida.append("desea agarrar una carta?");
    }
    private void mostrarmain(){
        for (int i = 0; i < mano.size(); i++) {
            txtSalida.append(mano.get(i));
        }
        txtSalida.append("que carta de la mano desea emparejar con la carta " + cartasMesa.size());
    }
    private void mostrarshout(){
        txtSalida.removeAll();
        for (int i = 0; i < mano.size(); i++) {
            txtSalida.append(mano.get(i));
        }
    }
    public void mostrarMenu(){
        //txtSalida.removeAll();
        switch(faseactual){
            case draw: mostrardraw();
            break;
            case mainfase : mostrarmain();
            break;
            case shout : mostrarshout();
            break;
            case opponent : mostraropponent();
            break;
        }

    }

}
