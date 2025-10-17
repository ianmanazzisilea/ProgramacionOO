package ar.edu.unlu.poo.tpfinal;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class VistaGrafica implements IVista, Serializable {
    private JTabbedPane tabbedPane1;
    private JFrame frame;
    private JPanel contentPane;
    private JTextArea textArea2;
    private JButton button1;
    private JPanel salida;
    private ArrayList<Carta> mano = new ArrayList<>();
    private Controlador controlador;
    private ArrayList<Carta> cartasMesa = new ArrayList<>();
    private fase faseactual = fase.salaespera;
    private Carta cartaMesa;
    private ArrayList<Carta> jugada = new ArrayList<>();
    private int indicebonus;
    private ImageIcon dos;
    //private ArrayList<JButton> arraycartamesa = new ArrayList<>();
    //private ArrayList<JButton> arraycartamano = new ArrayList<>();
    @Override
    public void mesaactualizada(fase fase){
        faseactual = fase;
        mano = controlador.getMano();
        cartasMesa = controlador.getMesa();
        int cantidadelementossalida = salida.getComponentCount();
        for (int i = 0; i < cantidadelementossalida; i++) {
            salida.remove(0);
        }
        JLabel label = new JLabel("Mesa:");
        label.setForeground(Color.WHITE);
        salida.add(label);
        JPanel cartas = new JPanel();
        cartas.setLayout(new FlowLayout());
        for (int i = 0; i < cartasMesa.size(); i++) {  //MESA
            //JLabel carta = new JLabel();
            JButton carta = new JButton();
            carta.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            carta.setBorderPainted(false);
            carta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (carta.isBorderPainted())
                        carta.setBorderPainted(false);
                    else{
                        for(int j = 0; j < cartasMesa.size(); j++){
                            //arraycartamesa.get(j).setBorderPainted(false);
                            JButton jcarta= (JButton) cartas.getComponents()[j];
                            jcarta.setBorderPainted(false);
                        }
                        carta.setBorderPainted(true);
                    }


                }
            });
            String nombremesa =  cartasMesa.get(i).getColor() + " " + cartasMesa.get(i).getNumero();
            carta.setIcon(new ImageIcon("cartas/" + nombremesa + ".png"));
            cartas.add(carta);
        }
        cartas.setOpaque(false);
        salida.add(cartas);
        JPanel cartasmano = new JPanel();
        cartasmano.setLayout(new FlowLayout());
        JLabel lebel = new JLabel("Mano:");
        lebel.setForeground(Color.WHITE);
        salida.add(lebel);
        for (int i = 0; i < mano.size(); i++) {
            //JLabel cartamano = new JLabel();
            JButton cartamano = new JButton();
            cartamano.setOpaque(false);
            cartamano.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            cartamano.setBorderPainted(false);
            cartamano.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cartamano.isBorderPainted())
                        cartamano.setBorderPainted(false);
                    else
                        cartamano.setBorderPainted(true);
                }
            });
            //arraycartamano.add(cartamano);
            String nombremano =  mano.get(i).getColor() + " " + mano.get(i).getNumero();
            cartamano.setIcon(new ImageIcon("cartas/" + nombremano + ".png"));
            cartasmano.add(cartamano);
        }
        cartasmano.setOpaque(false);
        salida.add(cartasmano);
        mostrarMenu();
    }
    private enum fase{
        draw,
        mainfase,
        bonus,
        opponent,
        fin, salaespera
    }
    //-------------------------comportamiento---------------------------------------------------
    public VistaGrafica(Controlador controlador){
        this.controlador = controlador;
        //hago esto por orden de ejecucion, yo lo hubiera hecho en el main
        controlador.setVista((IVista) this);
        this.frame = new JFrame("VistaConsola");
        //salida.setOpaque(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane1.setTitleAt(0,"juego");
        tabbedPane1.setTitleAt(0,"opciones");
        frame = new JFrame(controlador.getnombre());
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        button1.setLabel("empezar");
        //cargarimagenes();
        salida.setLayout(new BoxLayout(salida,BoxLayout.Y_AXIS));
        JLabel label = new JLabel("aprete empezar en opciones cuando desee empezar");
        label.setForeground(Color.WHITE);
        salida.add(label);
        recuperartop();
        frame.setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarEntrada();
            }
        });
    }

    private void recuperartop(){
        Object[] lista = controlador.getscore();//5
        int i = 0;
        if (lista!=null){
            while(i < 5 && lista[i]!= null) {
                Object[] jugador = (Object[]) lista[i];
                textArea2.append(jugador[0] + " " + jugador[1]);
                i++;
            }
        }

    }
    private void procesarEntrada(){
        //mesaactualizada();
        if (mano.size()==0){
            if(faseactual != fase.salaespera)
                controlador.hayganador();
        }
        switch (faseactual){
            case draw : draw();
                break;
            case mainfase : mainfase();
                break;
            case bonus : bonus();
                break;
            case salaespera : salaespera();
                break;
            //case fin: recuperartop();
        }
        if (Objects.requireNonNull(faseactual) == fase.bonus) {
            mostrarMenu();
        }
    }

    private void salaespera(){
            controlador.empezar();
    }
    private void draw(){
            faseactual = fase.mainfase;
            controlador.robar();
            mostrarMenu();
    }

    private void mainfase(){
        Carta mesaseleccionada = null;
        jugada = new ArrayList<>();
        JPanel jcartas = (JPanel) salida.getComponents()[1];
        for (int i = 0; i < cartasMesa.size(); i++) {
            JButton jcarta= (JButton) jcartas.getComponents()[i];
            if (jcarta.isBorderPainted()){
                mesaseleccionada=cartasMesa.get(i);
                break;
            }
        }
        jcartas = (JPanel) salida.getComponents()[3];
        for (int i = 0; i < mano.size(); i++) {
            JButton jcarta= (JButton) jcartas.getComponents()[i];
            if (jcarta.isBorderPainted()){
                jugada.add(mano.get(i));
                break;
            }
        }
        if (mesaseleccionada == null)
            faseactual = fase.opponent;
        else
            if (jugada.size()==0)
                faseactual = fase.opponent;
            else
            {
                controlador.jugada(jugada,cartaMesa);
                setearopciones();
                faseactual = fase.bonus;// lo tiene que asignar el modelo
                //mesaactualizada(); hace falta?
            }
    }
    private void bonus(){
        JPanel jcartas = (JPanel) salida.getComponents()[1];
        jugada = new ArrayList<>();
        for (int i = 0; i < mano.size(); i++) {
            JButton jcarta= (JButton) jcartas.getComponents()[i];
            if (jcarta.isBorderPainted()){
                jugada.add(mano.get(i));
                break;
            }
        }
        controlador.descartarbonus(jugada);
    }
        //-------------------visual-----------------------------------------------
    private void mostraropponent(){
        JLabel label = new JLabel("Turno del oponente");
        label.setForeground(Color.WHITE);
        salida.add(label);
    }
    private void mostrarfin(){
        JLabel label = new JLabel("Fin de la partida");
        label.setForeground(Color.WHITE);
        salida.add(label);
    }
    private void mostrardraw(){
        //JLabel label = new JLabel("desea agarrar una carta?");
        //label.setForeground(Color.WHITE);
        //salida.add(label);
    }
    private void mostrarmain(){
        JLabel label = new JLabel("que cartas de la mano desea emparejar con una carta de la mesa");
        label.setForeground(Color.WHITE);
        salida.add(label);
    }
    private void mostrarbonus(){
        mesaactualizada();
        if (controlador.booleanbonus()){
            JLabel label = new JLabel("ingrese carta que desee descartar por bonus de color");
            label.setForeground(Color.WHITE);
            salida.add(label);
        }
    }
    private void setearopciones(){
        switch (faseactual){
            case salaespera:button1.setLabel("esperando");
            break;
            case draw:button1.setLabel("robar?");
            break;
            case mainfase:button1.setLabel("combinar/terminar turno");
            break;
            case bonus:button1.setLabel("tirar bonus");
            break;
            case opponent:button1.setLabel("esperar");
            break;
            case fin:button1.setLabel("mostrar score");
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
            case fin : mostrarfin();
            break;
        }
    }
    @Override
    public void inicioturno() {
        if (controlador.getTurno()){
            faseactual = fase.draw;
            setearopciones();
            mesaactualizada();
        }
        else {
            faseactual = fase.opponent;
            setearopciones();
        }

    }

    @Override
    public void finpartida() {
        faseactual = fase.fin;
        JLabel label = new JLabel("ganó el jugador: " + controlador.ganador());
        label.setForeground(Color.WHITE);
        salida.add(label);
    }

    @Override
    public String getnombre() {
        String ganador ="";
        return ganador;
    }
}
