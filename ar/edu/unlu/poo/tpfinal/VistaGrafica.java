package ar.edu.unlu.poo.tpfinal;

import javax.swing.*;
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
    //private JTextArea txtSalida;
    private JComboBox entrada;
    private JTextArea textArea2;
    private JButton button1;
    private JButton cargarPartidaButton;
    private JPanel salida;
    private JList listasalida;
    private ArrayList<String> mano = new ArrayList<>();
    private Controlador controlador;
    private ArrayList<String> cartasMesa = new ArrayList<>();
    private fase faseactual = fase.salaespera;
    private int indice;
    private ArrayList<String> jugada = new ArrayList<>();
    private int indicebonus;
    private ImageIcon dos;
    @Override
    public void mesaactualizada(){
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
        for (int i = 0; i < cartasMesa.size(); i++) {
            JLabel carta = new JLabel();
            carta.setIcon(new ImageIcon("cartas/" + cartasMesa.get(i) + ".png"));
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
            JLabel cartamano = new JLabel();
            cartamano.setIcon(new ImageIcon("cartas/" + mano.get(i) + ".png"));
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
        cargarPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //txtSalida.append("cargando partida" + "\n");
                controlador.cargarpartida();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarEntrada((String) entrada.getSelectedItem());
            }
        });
    }

    /*private void cargarimagenes() {
        dos = new ImageIcon(("cartas/2.png"));
        for (int i = 0; i < 10; i++) {
            amarillo[i] = new ImageIcon(System.getProperty("cartas/amarillo "+ (i+1) + ".png"));
        }
        amarillo[10] = new ImageIcon(System.getProperty("cartas/amarillo_#.png"));
        for (int i = 0; i < 10; i++) {
            verde[i] = new ImageIcon(System.getProperty("cartas/verde_"+ (i+1) + ".png"));
        }
        verde[10] = new ImageIcon(System.getProperty("cartas/verde_#.png"));
        for (int i = 0; i < 10; i++) {
            rojo[i] = new ImageIcon(System.getProperty("cartas/rojo_"+ (i+1) + ".png"));
        }
        rojo[10] = new ImageIcon(System.getProperty("cartas/rojo_#.png"));
        for (int i = 0; i < 10; i++) {
            azul[i] = new ImageIcon(System.getProperty("cartas/azul_"+ (i+1) + ".png"));
        }
        azul[10] = new ImageIcon(System.getProperty("cartas/azul_#.png"));
    }
    private JLabel insertarimagen(JLabel carta,String scarta){
        //scarta "color numero"
        String[] arr = new String[2];
        if (scarta == "2"){
            carta.setIcon(dos);
        }else {
            arr = scarta.split(" ", 2);
        }
        if (arr[0] == "amarillo"){
            if (arr[1] == "#"){
                carta.setIcon(amarillo[10]);
            }else carta.setIcon(amarillo[Integer.valueOf(arr[1])]);
        }
        if (arr[0] == "verde"){
            if (arr[1] == "#"){
                carta.setIcon(verde[10]);
            }else carta.setIcon(verde[Integer.valueOf(arr[1])]);
        }
        if (arr[0] == "rojo"){
            if (arr[1] == "#"){
                carta.setIcon(rojo[10]);
            }else carta.setIcon(rojo[Integer.valueOf(arr[1])]);
        }
        if (arr[0] == "azul"){
            if (arr[1] == "#"){
                carta.setIcon(azul[10]);
            }else carta.setIcon(azul[Integer.valueOf(arr[1])]);
        }
        return carta;
    }*/

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
    private void procesarEntrada(String input){
        //mesaactualizada();
        if (mano.size()==0){
            if(faseactual != fase.salaespera)
                controlador.hayganador();
        }
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
                    opcionesmain();
                }
                faseactual = fase.bonus;
                indice--;
            }
            else {
                if (jugada.size() > 0){
                    controlador.jugada(jugada,indice-1);
                }
                indice--;
                mesaactualizada();
            }
            jugada = new ArrayList<>();
        }
    }
    private void bonus(String in){

        if (indicebonus == 0){
            indicebonus = controlador.bonus();
            if(indicebonus == 0){
                faseactual = fase.opponent;
                entrada.removeAllItems();
                controlador.pasoturno();
            }
        }

        if (indicebonus > 1){
            String input="-1";
            for (int i = 0; i < mano.size(); i++) {
                if (mano.get(i)==in){
                    input = String.valueOf(i);
                    //mano.set(i,"");
                    break;
                }
            }
            if (Integer.valueOf(input)>=0){
                if (Integer.valueOf(input)<= mano.size()){
                    indicebonus--;
                    controlador.descartarbonus(Integer.valueOf(input));
                    opcionesbonus();
                    mano.remove(Integer.valueOf(input));
                }
            }

        }
        if (indicebonus == 1){
            String input="-1";
            for (int i = 0; i < mano.size(); i++) {
                if (mano.get(i)==in){
                    input = String.valueOf(i);
                    //mano.set(i,"");
                    break;
                }
            }
            if (Integer.valueOf(input)>=0){
                if (Integer.valueOf(input)<= mano.size()){
                    indicebonus--;
                    controlador.descartarbonus(Integer.valueOf(input));
                    mano.remove(Integer.valueOf(input));
                    //pasa turno
                    faseactual = fase.opponent;
                    entrada.removeAllItems();
                    controlador.pasoturno();
                }
            }

        }
        /*if (!canto){
            controlador.nocanto();
        }*/
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
        JLabel label = new JLabel("desea agarrar una carta?");
        label.setForeground(Color.WHITE);
        salida.add(label);
    }
    private void mostrarmain(){
        if (indice == 0){
            indice = cartasMesa.size();
        }
        JLabel label = new JLabel("que carta de la mano desea emparejar con la carta " + indice );
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
    private void opcionesespera(){
        entrada.removeAllItems();
        button1.setLabel("esperando");
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
        else {
            JLabel label = new JLabel("Aprete terminar");
            label.setForeground(Color.WHITE);
            salida.add(label);
        }
        button1.setLabel("terminar");
    }
    private void opcionesfin(){
        entrada.removeAllItems();
    }
    private void opcionesopponent(){
        entrada.removeAllItems();
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
            case opponent:opcionesopponent();
            break;
            case fin:opcionesfin();
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
            opcionesdraw();
            mesaactualizada();
        }
        else faseactual = fase.opponent;
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
