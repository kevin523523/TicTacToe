/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.tictactoe;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.JugadorM;
import modelo.Maquina;
import modelo.Ruta;
import modelo.TipoImagen;
import modelo.Tree;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class VistaJ1VsMaquinaController implements Initializable {

    @FXML
    private AnchorPane panelFondo;
    @FXML
    private ImageView jugador1Img;
    @FXML
    private ImageView jugadorMImag;
    @FXML
    private VBox vBoxJugador1;
    @FXML
    private VBox vBoxJugadorM;
    @FXML
    private Label lblJugador1;
    @FXML
    private Label lblJugadorM;
    @FXML
    private Label lblPuntaje1;
    @FXML
    private Label lblPuntaje2;
    @FXML
    private AnchorPane paneTablero;
    @FXML
    private AnchorPane paneCuadro;
    @FXML
    private HBox hBoxTablero;
    @FXML
    private static VBox vBoxTablero;
    @FXML
    private ImageView img;
    @FXML
    private ImageView imgFrontal;
    @FXML
    private AnchorPane paneCuadroFrontal;

    public static JugadorM jugador1;
    public static Maquina jugadorM;
    public static Tablero tablero;
    private TipoImagen jugadorActual;
    private TipoImagen turnoPartida;
    private TipoImagen tipoImagen;

    private ArrayList<Cuadro> cuadros = new ArrayList();

    public static void EnviarJugador(JugadorM jugador1, Maquina jugadorM) {
        VistaJ1VsMaquinaController.jugador1 = jugador1;
        VistaJ1VsMaquinaController.jugadorM = jugadorM;
    }

    public class Tablero {

        public Tablero() {
        }

        public void borrarImagenes() {
            paneTablero.getChildren().clear();
            paneCuadroFrontal.getChildren().clear();
        }

        public void desactivarCuadros(boolean valor) {
            for (Cuadro cuadro : cuadros) {
                cuadro.setDibujado(valor);
            }
        }

        public void reiniciarTablero(TipoImagen ganador) {
            desactivarCuadros(false);
            borrarImagenes();
            System.out.println(ganador);
            if (ganador == TipoImagen.EQUIS) {
                int puntajeNuevo = Integer.parseInt(lblPuntaje1.getText()) + 1;
                lblPuntaje1.setText(String.valueOf(puntajeNuevo));
            } else if (ganador == TipoImagen.CIRCULO) {
                int puntajeNuevo = Integer.parseInt(lblPuntaje2.getText()) + 1;
                lblPuntaje2.setText(String.valueOf(puntajeNuevo));
            }
            if (turnoPartida == TipoImagen.EQUIS) {
                jugadorActual = TipoImagen.CIRCULO;
                turnoPartida = TipoImagen.CIRCULO;
            } else if (turnoPartida == TipoImagen.CIRCULO) {
                jugadorActual = TipoImagen.EQUIS;
                turnoPartida = TipoImagen.EQUIS;
            }
            cambiarEstilos(jugadorActual.EQUIS);
            jugador1.limpiarTablero();
            jugadorM.limpiarTablero();
        }

        public void resultado(TipoImagen tipoImagenResultado, TipoImagen jugadorGanador, AnchorPane paneCuadroFrontal) {
            try {
                if (tipoImagenResultado == TipoImagen.EMPATE) {
                    Timer timer = new Timer();
                    TimerTask tarea = new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                VistaVictoriaControllerM v1 = new VistaVictoriaControllerM();
                                v1.pintarGanador(tipoImagenResultado, tablero, jugador1, jugadorM);
                            });
                        }
                    };
                    timer.schedule(tarea, 800);

                } else if (tipoImagenResultado != null) {
                    if (jugadorGanador == TipoImagen.CIRCULO) {
                        InputStream input1 = App.class.getResource("Circulo" + tipoImagenResultado.toString() + ".png").openStream();
                        Image imgF = new Image(input1, paneTablero.getWidth() - 50, paneTablero.getHeight() - 50, true, true);
                        imgFrontal = new ImageView(imgF);
                    } else if (jugadorGanador == TipoImagen.EQUIS) {
                        InputStream input1 = App.class.getResource("Equis" + tipoImagenResultado.toString() + ".png").openStream();
                        Image imgF = new Image(input1, paneTablero.getWidth() - 50, paneTablero.getHeight() - 50, true, true);
                        imgFrontal = new ImageView(imgF);
                    }
                    int indice = paneTablero.getChildren().indexOf(paneCuadroFrontal);

                    if (indice != -1) {
                        paneTablero.getChildren().remove(indice);
                    }

                    paneCuadroFrontal.getChildren().add(imgFrontal);

                    paneCuadroFrontal.setPrefWidth(paneTablero.getWidth() - 50); // Establecer el nuevo ancho
                    paneCuadroFrontal.setPrefHeight(paneTablero.getHeight() - 50);
                    //paneCuadroFrontal.setLayoutX(0);
                    //paneCuadroFrontal.setLayoutY(0);
                    paneTablero.getChildren().add(paneCuadroFrontal);

                    desactivarCuadros(true);

                    Timer timer = new Timer();
                    TimerTask tarea = new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                VistaVictoriaControllerM v1 = new VistaVictoriaControllerM();
                                v1.pintarGanador(jugadorGanador, tablero, jugador1, jugadorM);
                            });

                        }
                    };
                    timer.schedule(tarea, 800);

                }
            } catch (NullPointerException | IOException ex) {
                imgFrontal = new ImageView();
            }

        }

        public void crearTablero() {
            paneTablero.setStyle("-fx-border-color: #004080; -fx-border-width: 10px;-fx-background-color: #FFFF99");
            //paneCuadroFrontal.setStyle("-fx-background-color: #86ffff");
            paneCuadroFrontal.setPrefWidth(350); // Establecer el nuevo ancho
            paneCuadroFrontal.setPrefHeight(350);
            paneCuadroFrontal.setLayoutX(15);
            paneCuadroFrontal.setLayoutY(15);

        }

        public void crearCuadrosInternos() {
            int margen = 20;
            int anchoCI = 100;
            int alturaCI = 100;
            int x = margen;
            int y = margen;
            for (int i = 0; i < 3; i++) {
                x = margen;
                for (int j = 0; j < 3; j++) {
                    AnchorPane paneCuadro = new AnchorPane();
                    Cuadro cuadro = new Cuadro();
                    cuadros.add(cuadro);
                    paneCuadro.setStyle(" -fx-border-width: 10px;-fx-background-color: #007ACC");
                    paneCuadro.setLayoutX(x);
                    paneCuadro.setLayoutY(y);
                    cuadro.setI(i);
                    cuadro.setJ(j);
                    paneCuadro.setCursor(Cursor.HAND);
                    paneCuadro.setPrefWidth(anchoCI); // Establecer el nuevo ancho
                    paneCuadro.setPrefHeight(alturaCI); // Establecer la nueva altura
                    crearEventosCuadro(paneCuadro, cuadro);
                    paneTablero.getChildren().add(paneCuadro);
                    x += (margen + anchoCI);
                }
                y += (margen + alturaCI);
            }
        }

        public void jugarTurnoHumano(AnchorPane paneCuadro, Cuadro cuadro) {
            TipoImagen tipoImagenResultado = null;
            if (jugadorActual == TipoImagen.EQUIS) {
                jugador1.getTablero()[cuadro.getI()][cuadro.getJ()] = "x";

                tipoImagenResultado = jugador1.tresEnRaya(jugadorM);
                setTipoImagen(TipoImagen.EQUIS);
                cuadro.paintComponent(paneCuadro);
                jugadorActual = TipoImagen.CIRCULO;
                cambiarEstilos(TipoImagen.CIRCULO);
                resultado(tipoImagenResultado, TipoImagen.EQUIS, paneCuadroFrontal);
                cuadro.setDibujado(true);
            }
        }

        public void crearEventosCuadro(AnchorPane paneCuadro, Cuadro cuadro) {
            paneCuadro.setOnMousePressed((MouseEvent event) -> {
                if (cuadro.isDibujado()) {
                    return;
                }
                jugarTurnoHumano(paneCuadro, cuadro);
                jugarTurnoMaquina(paneCuadro);
            });

        }

        public void jugarTurnoMaquina(AnchorPane paneCuadro) {
            Cuadro cuadroDefinido = turnoMaquina(jugador1);
            cuadroDefinido.paintComponent(paneCuadro);
            jugadorActual = TipoImagen.EQUIS;
            cambiarEstilos(TipoImagen.EQUIS);
            TipoImagen tipoImagenResultado = jugadorM.tresEnRaya(jugador1);
            resultado(tipoImagenResultado, TipoImagen.CIRCULO, paneCuadroFrontal);
            cuadroDefinido.setDibujado(true);
        }

        public Cuadro turnoMaquina(JugadorM jugador) {
            Tree<String[][]> padre = generarEstadosActual(jugador.getTablero());
            ArrayList<Integer> posicionCuadro = utilidadMaxima(padre);
            jugadorM.getTablero()[posicionCuadro.get(0)][posicionCuadro.get(1)] = "o";
            System.out.println(posicionCuadro.get(0));
            System.out.println(posicionCuadro.get(1));
            TipoImagen tipoImagenResultado = jugadorM.tresEnRaya(jugador1);
            setTipoImagen(TipoImagen.CIRCULO);
            Cuadro cuadro = new Cuadro();
            cuadro.setI(posicionCuadro.get(0));
            cuadro.setJ(posicionCuadro.get(1));
            cuadro.paintComponent(paneCuadro);
            return cuadro;
        }

        //OJO
        public Tree<String[][]> generarEstadosActual(String[][] tableroActual) {
            Tree<String[][]> tree = new Tree<>(tableroActual);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tableroActual[i][j] == "") {
                        String tableroCopia[][] = hacerCopia(tableroActual);
                        tableroCopia[i][j] = "o";
                        Tree<String[][]> hijoArbol = new Tree<>(tableroCopia);
                        tree.getRootNode().addChild(hijoArbol);

                    }
                }
            }
            List<Tree<String[][]>> hijosPadre = tree.getRootNode().getChildren();
            for (Tree<String[][]> child : hijosPadre) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (child.getRoot()[i][j] == "") {
                            String tableroCopia[][] = hacerCopia(child.getRoot());
                            tableroCopia[i][j] = "x";
                            Tree<String[][]> hijoArbol = new Tree<>(tableroCopia);
                            child.getRootNode().addChild(hijoArbol);
                        }
                    }
                }

            }

            return tree;
        }

        public String[][] hacerCopia(String[][] tableroActual) {
            String[][] tableroCopia = new String[tableroActual.length][];
            for (int i = 0; i < tableroActual.length; i++) {
                tableroCopia[i] = tableroActual[i].clone();
            }
            return tableroCopia;
        }

        public ArrayList<Integer> utilidadMaxima(Tree<String[][]> tree) {
            List<Tree<String[][]>> hijosPadre = tree.getRootNode().getChildren();
            ArrayList<Integer> utilidadesMinimas = new ArrayList<>();
            ArrayList<Integer> posicionCuadro = new ArrayList<>();

            for (Tree<String[][]> child : hijosPadre) {
                List<Tree<String[][]>> sobrinos = child.getRootNode().getChildren();
                for (Tree<String[][]> sobrino : sobrinos) {
                    String tableroOponente[][] = child.getRoot();
                    String tableroJugador[][] = sobrino.getRoot();
                    int utilidad = PjugadorX(tableroJugador) - PjugadorO(tableroOponente);
                    utilidadesMinimas.add(utilidad);
                }
            }
            if (!utilidadesMinimas.isEmpty()) {
                int maximo = Collections.max(utilidadesMinimas);
                int indice = utilidadesMinimas.indexOf(maximo);
                Tree<String[][]> movimientoSeleccionado = hijosPadre.get(indice);
                String[][] tableroAct = movimientoSeleccionado.getRoot();
                int posicionI = 0;
                int posicionJ = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (tableroAct[i][j] == "o") {
                            posicionI = i;
                            posicionJ = j;
                        }
                    }
                }
                posicionCuadro.add(posicionI);
                posicionCuadro.add(posicionJ);
                return posicionCuadro;

            } else {
                return posicionCuadro;
            }
        }

        public int PjugadorX(String tableroOponente[][]) {
            int pjugador = 0;
            for (int i = 0; i < 3; i++) {
                if ((tableroOponente[i][0] == "" || tableroOponente[i][0] == "x") && (tableroOponente[i][1] == "" || tableroOponente[i][1] == "x") && (tableroOponente[i][2] == "" || tableroOponente[i][2] == "x")) {
                    pjugador++;
                }
                if ((tableroOponente[0][i] == "" || tableroOponente[0][i] == "x") && (tableroOponente[1][i] == "" || tableroOponente[1][i] == "x") && (tableroOponente[2][i] == "" || tableroOponente[2][i] == "x")) {
                    pjugador++;
                }
            }

            if ((tableroOponente[0][0] == "" || tableroOponente[0][0] == "x") && (tableroOponente[1][1] == "" || tableroOponente[1][1] == "x") && (tableroOponente[2][2] == "" || tableroOponente[2][2] == "x")) {
                pjugador++;
            }
            if ((tableroOponente[0][2] == "" || tableroOponente[0][2] == "x") && (tableroOponente[1][1] == "" || tableroOponente[1][1] == "x") && (tableroOponente[2][0] == "" || tableroOponente[2][0] == "x")) {
                pjugador++;
            }
            return pjugador;
        }

        public int PjugadorO(String tableroOponente[][]) {
            int pjugador = 0;
            for (int i = 0; i < 3; i++) {
                if ((tableroOponente[i][0] == "" || tableroOponente[i][0] == "o") && (tableroOponente[i][1] == "" || tableroOponente[i][1] == "o") && (tableroOponente[i][2] == "" || tableroOponente[i][2] == "o")) {
                    pjugador++;
                }
                if ((tableroOponente[0][i] == "" || tableroOponente[0][i] == "o") && (tableroOponente[1][i] == "" || tableroOponente[1][i] == "o") && (tableroOponente[2][i] == "" || tableroOponente[2][i] == "o")) {
                    pjugador++;
                }
            }

            if ((tableroOponente[0][0] == "" || tableroOponente[0][0] == "o") && (tableroOponente[1][1] == "" || tableroOponente[1][1] == "o") && (tableroOponente[2][2] == "" || tableroOponente[2][2] == "o")) {
                pjugador++;
            }
            if ((tableroOponente[0][2] == "" || tableroOponente[0][2] == "o") && (tableroOponente[1][1] == "" || tableroOponente[1][1] == "o") && (tableroOponente[2][0] == "" || tableroOponente[2][0] == "o")) {
                pjugador++;
            }
            return pjugador;
        }

        public void cambiarEstilos(TipoImagen jugadorActual) {
            try {
                InputStream jugadorAuxiliar = App.class.getResource(Ruta.JUGADORAUXILLAR).openStream();
                InputStream jugadorAuxiliarM = App.class.getResource(Ruta.JUGADORAUXILLARM).openStream();
                Image imgAuxiliar = new Image(jugadorAuxiliar, 50, 50, true, true);
                Image imgAuxiliarM = new Image(jugadorAuxiliar, 50, 50, true, true);
                InputStream jugador1 = App.class.getResource("jugadorDos.png").openStream();
                InputStream jugador2 = App.class.getResource("maquina.png").openStream();
                Image imgJugador1 = new Image(jugador1, 50, 50, true, true);
                Image imgJugador2 = new Image(jugador2, 50, 50, true, true);

                if (jugadorActual == TipoImagen.CIRCULO) {
                    jugador1Img = new ImageView(imgAuxiliar);
                    vBoxJugador1.getChildren().remove(0);
                    vBoxJugador1.getChildren().add(0, jugador1Img);
                    jugadorMImag = new ImageView(imgJugador2);
                    vBoxJugadorM.getChildren().remove(0);
                    vBoxJugadorM.getChildren().add(0, jugadorMImag);

                } else {
                    jugadorMImag = new ImageView(imgAuxiliar);
                    vBoxJugadorM.getChildren().remove(0);
                    vBoxJugadorM.getChildren().add(0, jugadorMImag);
                    jugador1Img = new ImageView(imgJugador1);
                    vBoxJugador1.getChildren().remove(0);
                    vBoxJugador1.getChildren().add(0, jugador1Img);
                }

            } catch (IOException ex) {
            }
        }

        public JugadorM getJugador1() {
            return jugador1;
        }

        public void setJugador1(JugadorM jugador1) {
            VistaJ1VsMaquinaController.jugador1 = jugador1;
        }

        public Maquina getJugador2() {
            return jugadorM;
        }

        public void setJugador2(Maquina jugador2) {
            VistaJ1VsMaquinaController.jugadorM = jugador2;
        }

    }

    public class Cuadro {

        private int ancho;
        private int altura;
        private Color color;
        private boolean dibujado;
        private int i;
        private int j;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public int getJ() {
            return j;
        }

        public void setJ(int j) {
            this.j = j;
        }

        public boolean isDibujado() {
            return dibujado;
        }

        public void setDibujado(boolean dibujado) {
            this.dibujado = dibujado;
        }

        public void paintComponent(AnchorPane paneCuadro) {
            try {
                InputStream input = null;
                if (tipoImagen == TipoImagen.CIRCULO) {
                    input = App.class.getResource(Ruta.CIRCULO).openStream();
                } else if (tipoImagen == TipoImagen.EQUIS) {
                    input = App.class.getResource(Ruta.EQUIS).openStream();
                } else if (tipoImagen == TipoImagen.LINEA1) {
                    input = App.class.getResource(Ruta.LINEA1).openStream();
                } else if (tipoImagen == TipoImagen.LINEA2) {
                    input = App.class.getResource(Ruta.LINEA2).openStream();
                } else if (tipoImagen == TipoImagen.LINEA3) {
                    input = App.class.getResource(Ruta.LINEA3).openStream();
                } else if (tipoImagen == TipoImagen.LINEA4) {
                    input = App.class.getResource(Ruta.LINEA4).openStream();
                } else if (tipoImagen == TipoImagen.LINEA5) {
                    input = App.class.getResource(Ruta.LINEA5).openStream();
                } else if (tipoImagen == TipoImagen.LINEA6) {
                    input = App.class.getResource(Ruta.LINEA6).openStream();
                } else if (tipoImagen == TipoImagen.LINEA7) {
                    input = App.class.getResource(Ruta.LINEA7).openStream();
                } else if (tipoImagen == TipoImagen.LINEA8) {
                    input = App.class.getResource(Ruta.LINEA8).openStream();
                }
                Image imagen = new Image(input, 80, 80, true, true);
                img = new ImageView(imagen);
                paneCuadro.getChildren().add(img);
            } catch (NullPointerException | IOException ex) {
                img = new ImageView();
            }

        }

        public int getAncho() {
            return ancho;
        }

        public void setAncho(int ancho) {
            this.ancho = ancho;
        }

        public int getAltura() {
            return altura;
        }

        public void setAltura(int altura) {
            this.altura = altura;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

    }

    public TipoImagen getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(TipoImagen jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public TipoImagen getTipoImagen() {
        return tipoImagen;
    }

    public void setTipoImagen(TipoImagen tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jugadorActual = TipoImagen.EQUIS;
        turnoPartida = TipoImagen.EQUIS;
        tablero = new Tablero();
        tablero.setJugador1(jugador1);
        tablero.setJugador2(jugadorM);
        panelFondo.setStyle("-fx-border-color: #D2B48C; -fx-border-width: 10px;-fx-background-color: #87CEEB");
        tablero.crearTablero();
        tablero.crearCuadrosInternos();

        try {
            InputStream jugador1 = App.class
                    .getResource("jugadorUno.png").openStream();
            InputStream jugador2 = App.class
                    .getResource("maquina.png").openStream();
            //InputStream x = App.class.getResource("Equis.png").openStream();
            //InputStream o = App.class.getResource("Circulo.png").openStream();
            Image imgJugador1 = new Image(jugador1, 50, 50, true, true);
            Image imgJugador2 = new Image(jugador2, 50, 50, true, true);
            //Image imgx = new Image(x, 50, 50, true, true);
            //Image imgo = new Image(o, 50, 50, true, true);
            jugador1Img = new ImageView(imgJugador1);
            jugadorMImag = new ImageView(imgJugador2);
            //xImg = new ImageView(imgx);
            //oImg = new ImageView(imgo);

        } catch (NullPointerException | IOException ex) {
            jugador1Img = new ImageView();
            jugadorMImag = new ImageView();
            //xImg = new ImageView();
            //oImg = new ImageView();
        }
        vBoxJugador1.getChildren().add(0, jugador1Img);
        //hBoxJugador1.getChildren().add(xImg);
        vBoxJugadorM.getChildren().add(0, jugadorMImag);

        tablero.cambiarEstilos(TipoImagen.EQUIS);
        //hBoxJugador2.getChildren().add(oImg);
        lblJugador1.setText(jugador1.getNombre());
        lblJugadorM.setText(jugadorM.getNombre());
    }

}
