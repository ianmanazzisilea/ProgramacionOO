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
    private boolean canto = true;
    private int indice;
    private ArrayList<String> jugada = new ArrayList<>();
    private int indicebonus;
    private ArrayList<ImageIcon> amarillo = new ArrayList<>();
    private ArrayList<ImageIcon> verde = new ArrayList<>();
    private ArrayList<ImageIcon> rojo = new ArrayList<>();
    private ArrayList<ImageIcon> azul = new ArrayList<>();
    private ImageIcon dos;
    @Override
    public void mesaactualizada(){
        //txtSalida.setText("");
        mano = controlador.getMano();
        cartasMesa = controlador.getMesa();
        //txtSalida.append("mano:" + "\n");
        int cantidadelementossalida = salida.getComponentCount();
        for (int i = 0; i < cantidadelementossalida; i++) {
            salida.remove(0);
        }
        salida.add(new JLabel("Mano:"));
            for (int i = 0; i < mano.size(); i++) {
                JLabel carta = new JLabel();
                salida.add(insertarimagen(carta,mano.get(i)));
            }
        salida.add(new JLabel("Mesa:"));
            for (int i = 0; i < cartasMesa.size(); i++) {
                JLabel carta = new JLabel();
                salida.add(insertarimagen(carta,cartasMesa.get(i)));
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
        cargarimagenes();
        salida.setLayout(new BoxLayout(salida,BoxLayout.Y_AXIS));
        salida.add(new JLabel("aprete empezar en opciones cuando desee empezar"));
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
                //txtSalida.append(entrada.getSelectedItem() + "\n");
                procesarEntrada((String) entrada.getSelectedItem());
            }
        });
    }

    private void cargarimagenes() {
        dos = new ImageIcon(("cartas/2.png"));
        for (int i = 0; i < 10; i++) {
            amarillo.add(new ImageIcon(("cartas/amarillo/"+ (i+1) + ".png")));
        }
        amarillo.add(new ImageIcon(("cartas/amarillo/#.png")));
        for (int i = 0; i < 10; i++) {
            verde.add(new ImageIcon(("cartas/verde/"+ (i+1) + ".png")));
        }
        verde.add(new ImageIcon(("cartas/verde/#.png")));
        for (int i = 0; i < 10; i++) {
            rojo.add(new ImageIcon(("cartas/rojo/"+ (i+1) + ".png")));
        }
        rojo.add(new ImageIcon(("cartas/rojo/#.png")));
        for (int i = 0; i < 10; i++) {
            azul.add(new ImageIcon(("cartas/azul/"+ (i+1) + ".png")));
        }
        azul.add(new ImageIcon(("cartas/azul/#.png")));
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
                carta.setIcon(amarillo.get(10));
            }else carta.setIcon(amarillo.get(Integer.valueOf(arr[1])-1));
        }
        if (arr[0] == "verde"){
            if (arr[1] == "#"){
                carta.setIcon(verde.get(10));
            }else carta.setIcon(verde.get(Integer.valueOf(arr[1])-1));
        }
        if (arr[0] == "rojo"){
            if (arr[1] == "#"){
                carta.setIcon(rojo.get(10));
            }else carta.setIcon(rojo.get(Integer.valueOf(arr[1])-1));
        }
        if (arr[0] == "azul"){
            if (arr[1] == "#"){
                carta.setIcon(azul.get(10));
            }else carta.setIcon(azul.get(Integer.valueOf(arr[1])-1));
        }
        return carta;
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
            //entrada.remove(Integer.valueOf(input));
        }
        if (Integer.valueOf(input) == -1){
            if (indice == 1){
                if (jugada.size() > 0){
                    controlador.jugada(jugada,indice-1);
                    /*txtSalida.append("intentó emparejar: "+"\n");
                    for (int i = 0; i < jugada.size(); i++) {
                        txtSalida.append("carta " + (Integer.valueOf(jugada.get(i)) + 1) + "\n");
                    }
                    txtSalida.append(" con la carta " + indice + " de la mesa" + "\n");*/
                    opcionesmain();
                }
                faseactual = fase.bonus;
                indice--;
            }
            else {
                if (jugada.size() > 0){
                    controlador.jugada(jugada,indice-1);
                    /*txtSalida.append("intentó emparejar: " + "\n");
                    for (int i = 0; i < jugada.size(); i++) {
                        txtSalida.append("carta " + (Integer.valueOf(jugada.get(i))+1) + "\n");
                    }
                    txtSalida.append(" con la carta " + indice + " de la mesa" + "\n");*/
                }
                indice--;
                //txtSalida.append("que carta de la mano desea emparejar con la carta " + indice + "\n");
            }
            jugada = new ArrayList<>();
        }
    }
    private void bonus(String in){

        if (indicebonus == 0){
            indicebonus = controlador.bonus();
            if(indicebonus == 0){
                faseactual = fase.opponent;
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
            if (Integer.valueOf(input)>=1){
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
            if (Integer.valueOf(input)>=1){
                if (Integer.valueOf(input)<= mano.size()){
                    indicebonus--;
                    controlador.descartarbonus(Integer.valueOf(input));
                    //pasa turno
                    faseactual = fase.opponent;
                    controlador.pasoturno();
                    entrada.removeAllItems();
                }
            }

        }
        /*if (!canto){
            controlador.nocanto();
        }*/
    }
        //-------------------visual-----------------------------------------------
    private void mostraropponent(){
        //mesaactualizada();
    }
    private void mostrardraw(){
        salida.add(new JLabel("desea agarrar una carta?"));
    }
    private void mostrarmain(){
        if (indice == 0){
            indice = cartasMesa.size();
        }
        salida.add(new JLabel("que carta de la mano desea emparejar con la carta " + indice ));
    }
    private void mostrarbonus(){
        //mesaactualizada();
        if (controlador.booleanbonus()){
            salida.add(new JLabel("ingrese carta que desee descartar por bonus de color"));
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
        else salida.add(new JLabel("aprete en terminar"));
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
        salida.add(new JLabel("ganó el jugador: " + controlador.ganador()));
        faseactual = fase.opponent;
    }

    @Override
    public String getnombre() {
        String ganador ="";
        return ganador;
    }
}
