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
import javafx.scene.Node;
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
    private ImageView maquinaImg;
    @FXML
    private ImageView jugador2Img;
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
    public static Maquina maquina;
    public static JugadorM jugador2;
    public static Tablero tablero;
    private TipoImagen jugadorActual;
    private TipoImagen turnoPartida;
    private TipoImagen tipoImagen;
    private String tableroGeneral[][] = new String[3][3];
    private ArrayList<Cuadro> cuadros = new ArrayList();

    public static void EnviarJugador(Maquina maquina, JugadorM jugador2) {
        VistaJ1VsMaquinaController.maquina = maquina;
        VistaJ1VsMaquinaController.jugador2 = jugador2;
    }

    public class Tablero {

        public Tablero() {
        }

        public void borrarImagenes() {
            paneTablero.getChildren().clear();
            paneCuadroFrontal.getChildren().clear();
            hBoxTablero.getChildren().clear();
        }

        public void desactivarCuadros(boolean valor) {
            for (Cuadro cuadro : cuadros) {
                cuadro.setDibujado(valor);
            }
        }

        public void reiniciarTablero(TipoImagen ganador) {
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                desactivarCuadros(false);
                borrarImagenes();
                if (ganador == TipoImagen.EQUIS) {
                    int puntajeNuevo = Integer.parseInt(lblPuntaje1.getText()) + 1;
                    lblPuntaje1.setText(String.valueOf(puntajeNuevo));
                } else if (ganador == TipoImagen.CIRCULO) {
                    int puntajeNuevo = Integer.parseInt(lblPuntaje2.getText()) + 1;
                    lblPuntaje2.setText(String.valueOf(puntajeNuevo));
                }
                if (turnoPartida == TipoImagen.EQUIS) {
                    //jugadorActual = TipoImagen.EQUIS;
                    //turnoPartida = TipoImagen.EQUIS;
                } else if (turnoPartida == TipoImagen.CIRCULO) {
                    jugadorActual = TipoImagen.EQUIS;
                    turnoPartida = TipoImagen.EQUIS;
                }
                cambiarEstilos(jugadorActual.EQUIS);
                maquina.limpiarTablero();
                jugador2.limpiarTablero();
                limpiarTableroGeneral();
            } else {
                desactivarCuadros(false);
                borrarImagenes();
                if (ganador == TipoImagen.EQUIS) {
                    int puntajeNuevo = Integer.parseInt(lblPuntaje1.getText()) + 1;
                    lblPuntaje1.setText(String.valueOf(puntajeNuevo));
                } else if (ganador == TipoImagen.CIRCULO) {
                    int puntajeNuevo = Integer.parseInt(lblPuntaje2.getText()) + 1;
                    lblPuntaje2.setText(String.valueOf(puntajeNuevo));
                }
                if (turnoPartida == TipoImagen.EQUIS) {
                    jugadorActual = TipoImagen.EQUIS;
                    turnoPartida = TipoImagen.EQUIS;
                } else if (turnoPartida == TipoImagen.CIRCULO) {
                    jugadorActual = TipoImagen.EQUIS;
                    turnoPartida = TipoImagen.EQUIS;
                }
                cambiarEstilos(jugadorActual.EQUIS);
                maquina.limpiarTablero();
                jugador2.limpiarTablero();
                limpiarTableroGeneral();
            }

        }

        public void resultado(TipoImagen tipoImagenResultado, TipoImagen jugadorGanador, AnchorPane paneCuadroFrontal) {
            try {
                if (tipoImagenResultado == TipoImagen.EMPATE) {
                    Timer timer = new Timer();
                    TimerTask tarea = new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                VistaVictoriaMController v1 = new VistaVictoriaMController();
                                v1.pintarGanador(tipoImagenResultado, tablero, maquina, jugador2);
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
                    paneTablero.getChildren().add(paneCuadroFrontal);

                    desactivarCuadros(true);

                    Timer timer = new Timer();
                    TimerTask tarea = new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                VistaVictoriaMController v1 = new VistaVictoriaMController();
                                v1.pintarGanador(jugadorGanador, tablero, maquina, jugador2);
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
            paneTablero = new AnchorPane();
            paneTablero.setStyle("-fx-border-color: #004080; -fx-border-width: 10px;-fx-background-color: #FFFF99");
            hBoxTablero.getChildren().add(paneTablero);

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
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                if (jugadorActual == TipoImagen.CIRCULO) {
                    jugador2.getTablero()[cuadro.getI()][cuadro.getJ()] = "o";
                    tableroGeneral[cuadro.getI()][cuadro.getJ()] = "o";
                    tipoImagenResultado = jugador2.tresEnRaya(tableroGeneral);
                    setTipoImagen(TipoImagen.CIRCULO);
                    AnchorPane paneCuadroD = cuadro.paintComponent(paneCuadro);
                    int index = 3 * cuadro.getI() + cuadro.getJ();
                    //Node pane = paneTablero.getChildren().get(index);
                    jugadorActual = TipoImagen.EQUIS;
                    paneTablero.getChildren().remove(index);
                    paneTablero.getChildren().add(index, paneCuadroD);
                    cambiarEstilos(TipoImagen.EQUIS);
                    resultado(tipoImagenResultado, TipoImagen.CIRCULO, paneCuadroFrontal);
                    cuadro.setDibujado(true);
                }
                if (jugadorActual == TipoImagen.EQUIS && tipoImagenResultado != TipoImagen.EMPATE && tipoImagenResultado != TipoImagen.LINEA1 && tipoImagenResultado != TipoImagen.LINEA2 && tipoImagenResultado != TipoImagen.LINEA3 && tipoImagenResultado != TipoImagen.LINEA4 && tipoImagenResultado != TipoImagen.LINEA5 && tipoImagenResultado != TipoImagen.LINEA6 && tipoImagenResultado != TipoImagen.LINEA7 && tipoImagenResultado != TipoImagen.LINEA8) {
                    jugarTurnoMaquina(jugador2);
                }
            } else {
                if (jugadorActual == TipoImagen.EQUIS) {
                    jugador2.getTablero()[cuadro.getI()][cuadro.getJ()] = "x";
                    tableroGeneral[cuadro.getI()][cuadro.getJ()] = "x";
                    tipoImagenResultado = jugador2.tresEnRaya(tableroGeneral);
                    setTipoImagen(TipoImagen.EQUIS);
                    AnchorPane paneCuadroD = cuadro.paintComponent(paneCuadro);
                    int index = 3 * cuadro.getI() + cuadro.getJ();
                    //Node pane = paneTablero.getChildren().get(index);
                    jugadorActual = TipoImagen.CIRCULO;
                    paneTablero.getChildren().remove(index);
                    paneTablero.getChildren().add(index, paneCuadroD);
                    cambiarEstilos(TipoImagen.CIRCULO);
                    resultado(tipoImagenResultado, TipoImagen.EQUIS, paneCuadroFrontal);
                    cuadro.setDibujado(true);
                    if (jugadorActual == TipoImagen.CIRCULO && tipoImagenResultado != TipoImagen.EMPATE && tipoImagenResultado != TipoImagen.LINEA1 && tipoImagenResultado != TipoImagen.LINEA2 && tipoImagenResultado != TipoImagen.LINEA3 && tipoImagenResultado != TipoImagen.LINEA4 && tipoImagenResultado != TipoImagen.LINEA5 && tipoImagenResultado != TipoImagen.LINEA6 && tipoImagenResultado != TipoImagen.LINEA7 && tipoImagenResultado != TipoImagen.LINEA8) {
                        if (tableroLleno(jugador2.getTablero()) != 1) {
                            jugarTurnoMaquina(jugador2);
                        }
                    }
                }
            }

        }

        public void crearEventosCuadro(AnchorPane paneCuadro, Cuadro cuadro) {
            paneCuadro.setOnMousePressed((MouseEvent event) -> {
                if (cuadro.isDibujado()) {
                    return;
                }

                jugarTurnoHumano(paneCuadro, cuadro);

            });

        }

        public void jugarTurnoMaquina(JugadorM jugador) {
            Timer timer = new Timer();
            TimerTask tarea = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        turnoMaquina(jugador.getTablero());
                    });
                }
            };
            timer.schedule(tarea, 700);

        }

        public void turnoMaquina(String[][] tableroJ) {
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                Tree<String[][]> padre = generarEstadosActual(tableroJ);
                ArrayList<Integer> posicionCuadro = utilidadMaxima(padre);
                try {
                    maquina.getTablero()[posicionCuadro.get(0)][posicionCuadro.get(1)] = "o";
                    jugador2.getTablero()[posicionCuadro.get(0)][posicionCuadro.get(1)] = "M";
                    tableroGeneral[posicionCuadro.get(0)][posicionCuadro.get(1)] = "x";
                    Cuadro cuadro = new Cuadro();
                    cuadro.setI(posicionCuadro.get(0));
                    cuadro.setJ(posicionCuadro.get(1));
                    InputStream input = App.class.getResource(Ruta.EQUIS).openStream();
                    Image imagen = new Image(input, 80, 80, true, true);
                    img = new ImageView(imagen);
                    AnchorPane nuevoPaneCuadro = new AnchorPane();
                    nuevoPaneCuadro.getChildren().add(img);
                    int index = 3 * posicionCuadro.get(0) + posicionCuadro.get(1);
                    Node pane = paneTablero.getChildren().get(index);
                    double x = pane.getLayoutX();
                    double y = pane.getLayoutY();
                    nuevoPaneCuadro.setLayoutX(x);
                    nuevoPaneCuadro.setLayoutY(y);
                    nuevoPaneCuadro.setPrefWidth(100);
                    nuevoPaneCuadro.setPrefHeight(100);
                    nuevoPaneCuadro.setStyle(" -fx-border-width: 10px;-fx-background-color: #007ACC");
                    paneTablero.getChildren().remove(pane);
                    paneTablero.getChildren().add(index, nuevoPaneCuadro);
                    cuadro.setDibujado(true);
                    TipoImagen tipoImagenResultado = maquina.tresEnRaya(tableroGeneral);
                    tablero.resultado(tipoImagenResultado, TipoImagen.EQUIS, paneCuadroFrontal);
                    setTipoImagen(TipoImagen.EQUIS);
                    jugadorActual = TipoImagen.CIRCULO;
                    cambiarEstilos(TipoImagen.CIRCULO);
                    posicionCuadro.clear();
                    padre.getRootNode().getChildren().clear();
                } catch (NullPointerException | IOException ex) {
                    img = new ImageView();
                }
            } else {
                Tree<String[][]> padre = generarEstadosActual(tableroJ);
                ArrayList<Integer> posicionCuadro = utilidadMaxima(padre);
                try {
                    maquina.getTablero()[posicionCuadro.get(0)][posicionCuadro.get(1)] = "x";
                    jugador2.getTablero()[posicionCuadro.get(0)][posicionCuadro.get(1)] = "M";
                    tableroGeneral[posicionCuadro.get(0)][posicionCuadro.get(1)] = "o";
                    Cuadro cuadro = new Cuadro();
                    cuadro.setI(posicionCuadro.get(0));
                    cuadro.setJ(posicionCuadro.get(1));
                    InputStream input = App.class.getResource(Ruta.CIRCULO).openStream();
                    Image imagen = new Image(input, 80, 80, true, true);
                    img = new ImageView(imagen);
                    AnchorPane nuevoPaneCuadro = new AnchorPane();
                    nuevoPaneCuadro.getChildren().add(img);
                    int index = 3 * posicionCuadro.get(0) + posicionCuadro.get(1);
                    Node pane = paneTablero.getChildren().get(index);
                    double x = pane.getLayoutX();
                    double y = pane.getLayoutY();
                    nuevoPaneCuadro.setLayoutX(x);
                    nuevoPaneCuadro.setLayoutY(y);
                    nuevoPaneCuadro.setPrefWidth(100);
                    nuevoPaneCuadro.setPrefHeight(100);
                    nuevoPaneCuadro.setStyle(" -fx-border-width: 10px;-fx-background-color: #007ACC");
                    //if (!paneTablero.getChildren().contains(nuevoPaneCuadro)) {
                    paneTablero.getChildren().remove(pane);
                    paneTablero.getChildren().add(index, nuevoPaneCuadro);
                    //}
                    cuadro.setDibujado(true);
                    TipoImagen tipoImagenResultado = maquina.tresEnRaya(tableroGeneral);
                    tablero.resultado(tipoImagenResultado, TipoImagen.CIRCULO, paneCuadroFrontal);
                    setTipoImagen(TipoImagen.CIRCULO);
                    jugadorActual = TipoImagen.EQUIS;
                    cambiarEstilos(TipoImagen.EQUIS);
                    posicionCuadro.clear();
                    padre.getRootNode().getChildren().clear();
                } catch (NullPointerException | IOException ex) {
                    img = new ImageView();
                }
            }
        }

        public int tableroLleno(String[][] tableroActual) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tableroActual[i][j] == "") {
                        return 0;
                    }
                }
            }
            return 1;
        }

        public Tree<String[][]> generarEstadosActual(String[][] tableroActual) {
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                Tree<String[][]> tree = new Tree<>(tableroActual);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (tableroActual[i][j] == "") {
                            String tableroCopia[][] = hacerCopia(tableroActual);
                            tableroCopia[i][j] = "x";
                            Tree<String[][]> hijoArbol = new Tree<>(tableroCopia);
                            tree.getRootNode().addChild(hijoArbol);
                            if (tableroLleno(hijoArbol.getRoot()) == 1) {
                                return tree;
                            }

                        }
                    }
                }
                List<Tree<String[][]>> hijosPadre = tree.getRootNode().getChildren();
                for (Tree<String[][]> child : hijosPadre) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (child.getRoot()[i][j] == "") {
                                String tableroCopia[][] = hacerCopia(child.getRoot());
                                tableroCopia[i][j] = "o";
                                Tree<String[][]> hijoArbol = new Tree<>(tableroCopia);
                                child.getRootNode().addChild(hijoArbol);
                            }
                        }
                    }

                }
                return tree;
            } else {
                Tree<String[][]> tree = new Tree<>(tableroActual);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (tableroActual[i][j] == "") {
                            String tableroCopia[][] = hacerCopia(tableroActual);
                            tableroCopia[i][j] = "o";
                            Tree<String[][]> hijoArbol = new Tree<>(tableroCopia);
                            tree.getRootNode().addChild(hijoArbol);
                            if (tableroLleno(hijoArbol.getRoot()) == 1) {
                                return tree;
                            }
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

        }

        public String[][] hacerCopia(String[][] tableroActual) {
            String[][] tableroCopia = new String[tableroActual.length][];
            for (int i = 0; i < tableroActual.length; i++) {
                tableroCopia[i] = tableroActual[i].clone();
            }
            return tableroCopia;
        }

        public int tresEnRayasXOM(String tablero[][]) {
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                String tableroCopia[][] = hacerCopia(tablero);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (tablero[i][j] == "M") {
                            tableroCopia[i][j] = "x";
                        }
                    }
                }
                return tresEnRayaX(tableroCopia);
            } else {
                String tableroCopia[][] = hacerCopia(tablero);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (tablero[i][j] == "M") {
                            tableroCopia[i][j] = "o";
                        }
                    }
                }
                return tresEnRayaO(tableroCopia);
            }

        }

        public String[][] imprimirXOM(String tablero[][]) {
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                String tableroCopia[][] = hacerCopia(tablero);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (tablero[i][j] == "M") {
                            tableroCopia[i][j] = "x";
                        }
                    }
                }
                return tableroCopia;
            } else {
                String tableroCopia[][] = hacerCopia(tablero);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (tablero[i][j] == "M") {
                            tableroCopia[i][j] = "o";
                        }
                    }
                }
                return tableroCopia;
            }

        }

        public int retornarIndiceTablero(Tree<String[][]> tree) {
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                int indice = -1;
                List<Tree<String[][]>> hijosPadre = tree.getRootNode().getChildren();
                for (Tree<String[][]> child : hijosPadre) {
                    indice += 1;
                    if (tresEnRayasXOM(child.getRoot()) == 1) {
                        return indice;
                    }

                }
                return -1;

            } else {
                int indice = -1;
                List<Tree<String[][]>> hijosPadre = tree.getRootNode().getChildren();
                for (Tree<String[][]> child : hijosPadre) {
                    indice += 1;
                    if (tresEnRayasXOM(child.getRoot()) == 1) {
                        return indice;
                    }

                }
                return -1;

            }

        }

        public ArrayList<Integer> utilidadMaxima(Tree<String[][]> tree) {
            List<Tree<String[][]>> hijosPadre = tree.getRootNode().getChildren();
            ArrayList<ArrayList<Integer>> arregloUtilidades = new ArrayList<>();
            ArrayList<Integer> arregloUtilidadesMiniminas = new ArrayList<>();
            ArrayList<Integer> posicionCuadro = new ArrayList<>();
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                if (tableroLleno(hijosPadre.get(0).getRoot()) != 1) {

                    for (Tree<String[][]> child : hijosPadre) {
                        List<Tree<String[][]>> sobrinos = child.getRootNode().getChildren();
                        ArrayList<Integer> arreglo = new ArrayList<>();
                        for (Tree<String[][]> sobrino : sobrinos) {
                            int utilidad = PjugadorX(sobrino.getRoot()) - PjugadorO(sobrino.getRoot());
                            if (tresEnRayaO(sobrino.getRoot()) == 1) {
                                utilidad = Integer.MIN_VALUE;
                            }
                            arreglo.add(utilidad);
                        }
                        arregloUtilidades.add(arreglo);
                    }
                    if (!arregloUtilidades.isEmpty()) {
                        for (ArrayList<Integer> arreglo : arregloUtilidades) {
                            int minimo = Collections.min(arreglo);
                            arregloUtilidadesMiniminas.add(minimo);
                        }
                        for (Tree<String[][]> child : hijosPadre) {
                            System.out.println("COMIENZA");
                            String imprimir[][] = imprimirXOM(child.getRoot());
                            Tree<String[][]> treePrint = new Tree<>(imprimir);
                            System.out.println(treePrint.getRootNode().toString());
                            System.out.println("TERMINA");
                        }
                        int indice = 0;
                        int maximo = Collections.max(arregloUtilidadesMiniminas);
                        if (retornarIndiceTablero(tree) != -1) {
                            indice = retornarIndiceTablero(tree);
                        } else {
                            indice = arregloUtilidadesMiniminas.indexOf(maximo);
                        }

                        Tree<String[][]> movimientoSeleccionado = hijosPadre.get(indice);
                        String[][] tableroAct = movimientoSeleccionado.getRoot();
                        int posicionI = 0;
                        int posicionJ = 0;
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (tableroAct[i][j] == "x" && tableroAct[i][j] != "M") {
                                    posicionI = i;
                                    posicionJ = j;
                                }
                            }
                        }
                        System.out.println("Movimiento Seleccionado");
                        System.out.println(movimientoSeleccionado.getRootNode().toString());
                        System.out.println(arregloUtilidadesMiniminas);
                        posicionCuadro.add(posicionI);
                        posicionCuadro.add(posicionJ);

                        return posicionCuadro;
                    }
                } else {
                    Tree<String[][]> movimientoSeleccionado = hijosPadre.get(0);
                    String[][] tableroAct = movimientoSeleccionado.getRoot();
                    int posicionI = 0;
                    int posicionJ = 0;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (tableroAct[i][j] == "x" && tableroAct[i][j] != "M") {
                                posicionI = i;
                                posicionJ = j;
                            }
                        }
                    }
                    posicionCuadro.add(posicionI);
                    posicionCuadro.add(posicionJ);
                }

            } else {
                if (tableroLleno(hijosPadre.get(0).getRoot()) != 1) {
                    for (Tree<String[][]> child : hijosPadre) {
                        List<Tree<String[][]>> sobrinos = child.getRootNode().getChildren();
                        ArrayList<Integer> arreglo = new ArrayList<>();
                        for (Tree<String[][]> sobrino : sobrinos) {
                            int utilidad = PjugadorO(sobrino.getRoot()) - PjugadorX(sobrino.getRoot());
                            if (tresEnRayaX(sobrino.getRoot()) == 1) {
                                utilidad = Integer.MIN_VALUE;
                            }
                            arreglo.add(utilidad);
                        }
                        arregloUtilidades.add(arreglo);
                    }
                    if (!arregloUtilidades.isEmpty()) {
                        for (ArrayList<Integer> arreglo : arregloUtilidades) {
                            int minimo = Collections.min(arreglo);
                            arregloUtilidadesMiniminas.add(minimo);
                        }

                        for (Tree<String[][]> child : hijosPadre) {
                            System.out.println("COMIENZA");
                            String imprimir[][] = imprimirXOM(child.getRoot());
                            Tree<String[][]> treePrint = new Tree<>(imprimir);
                            System.out.println(treePrint.getRootNode().toString());
                            System.out.println("TERMINA");
                        }
                        int indice = 0;
                        int maximo = Collections.max(arregloUtilidadesMiniminas);
                        if (retornarIndiceTablero(tree) != -1) {
                            indice = retornarIndiceTablero(tree);
                        } else {
                            indice = arregloUtilidadesMiniminas.indexOf(maximo);
                        }
                        

                        Tree<String[][]> movimientoSeleccionado = hijosPadre.get(indice);
                        String[][] tableroAct = movimientoSeleccionado.getRoot();
                        int posicionI = 0;
                        int posicionJ = 0;
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (tableroAct[i][j] == "o" && tableroAct[i][j] != "M") {
                                    posicionI = i;
                                    posicionJ = j;
                                }
                            }
                        }
                        System.out.println("Movimiento Seleccionado");
                        System.out.println(movimientoSeleccionado.getRootNode().toString());
                        System.out.println(arregloUtilidadesMiniminas);
                        posicionCuadro.add(posicionI);
                        posicionCuadro.add(posicionJ);
                        return posicionCuadro;
                    }
                } else {
                    Tree<String[][]> movimientoSeleccionado = hijosPadre.get(0);
                    String[][] tableroAct = movimientoSeleccionado.getRoot();
                    int posicionI = 0;
                    int posicionJ = 0;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (tableroAct[i][j] == "o" && tableroAct[i][j] != "M") {
                                posicionI = i;
                                posicionJ = j;
                            }
                        }
                    }
                    posicionCuadro.add(posicionI);
                    posicionCuadro.add(posicionJ);
                }
            }
            return posicionCuadro;
        }

        public int PjugadorX(String tablero[][]) {
            int pjugador = 0;
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                for (int i = 0; i < 3; i++) {
                    if (tablero[i][0] != "o" && tablero[i][1] != "o" && tablero[i][2] != "o") {
                        pjugador++;
                    }
                    if (tablero[0][i] != "o" && tablero[1][i] != "o" && tablero[2][i] != "o") {
                        pjugador++;
                    }
                }

                if (tablero[0][0] != "o" && tablero[1][1] != "o" && tablero[2][2] != "o") {
                    pjugador++;
                }
                if (tablero[0][2] != "o" && tablero[1][1] != "o" && tablero[2][0] != "o") {
                    pjugador++;
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    if (tablero[i][0] != "o" && tablero[i][1] != "o" && tablero[i][2] != "o" && tablero[i][0] != "M" && tablero[i][1] != "M" && tablero[i][2] != "M") {
                        pjugador++;
                    }
                    if (tablero[0][i] != "o" && tablero[1][i] != "o" && tablero[2][i] != "o" && tablero[0][i] != "M" && tablero[1][i] != "M" && tablero[2][i] != "M") {
                        pjugador++;
                    }
                }

                if (tablero[0][0] != "o" && tablero[1][1] != "o" && tablero[2][2] != "o" && tablero[0][0] != "M" && tablero[1][1] != "M" && tablero[2][2] != "M") {
                    pjugador++;
                }
                if (tablero[0][2] != "o" && tablero[1][1] != "o" && tablero[2][0] != "o" && tablero[0][2] != "M" && tablero[1][1] != "M" && tablero[2][0] != "M") {
                    pjugador++;
                }
            }
            return pjugador;
        }

        public int PjugadorO(String tablero[][]) {
            int pjugador = 0;
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                for (int i = 0; i < 3; i++) {
                    if (tablero[i][0] != "x" && tablero[i][1] != "x" && tablero[i][2] != "x" && tablero[i][0] != "M" && tablero[i][1] != "M" && tablero[i][2] != "M") {
                        pjugador++;
                    }
                    if (tablero[0][i] != "x" && tablero[1][i] != "x" && tablero[2][i] != "x" && tablero[0][i] != "M" && tablero[1][i] != "M" && tablero[2][i] != "M") {
                        pjugador++;
                    }
                }

                if (tablero[0][0] != "x" && tablero[1][1] != "x" && tablero[2][2] != "x" && tablero[0][0] != "M" && tablero[1][1] != "M" && tablero[2][2] != "M") {
                    pjugador++;
                }
                if (tablero[0][2] != "x" && tablero[1][1] != "x" && tablero[2][0] != "x" && tablero[0][2] != "M" && tablero[1][1] != "M" && tablero[2][0] != "M") {
                    pjugador++;
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    if (tablero[i][0] != "x" && tablero[i][1] != "x" && tablero[i][2] != "x") {
                        pjugador++;
                    }
                    if (tablero[0][i] != "x" && tablero[1][i] != "x" && tablero[2][i] != "x") {
                        pjugador++;
                    }
                }

                if (tablero[0][0] != "x" && tablero[1][1] != "x" && tablero[2][2] != "x") {
                    pjugador++;
                }
                if (tablero[0][2] != "x" && tablero[1][1] != "x" && tablero[2][0] != "x") {
                    pjugador++;
                }
            }
            return pjugador;
        }

        public int tresEnRayaX(String tablero[][]) {
            for (int i = 0; i < 3; i++) {
                if (tablero[i][0] == "x" && tablero[i][1] == "x" && tablero[i][2] == "x") {
                    return 1;
                }
                if (tablero[0][i] == "x" && tablero[1][i] == "x" && tablero[2][i] == "x") {
                    return 1;
                }
            }

            if (tablero[0][0] == "x" && tablero[1][1] == "x" && tablero[2][2] == "x") {
                return 1;
            }
            if (tablero[0][2] == "x" && tablero[1][1] == "x" && tablero[2][0] == "x") {
                return 1;
            }
            return 0;
        }

        public int tresEnRayaO(String tablero[][]) {
            if (tablero[0][0] == "o" && tablero[1][1] == "o" && tablero[2][2] == "o") {
                return 1;

            }
            if (tablero[0][2] == "o" && tablero[1][1] == "o" && tablero[2][0] == "o") {
                return 1;
            }
            for (int i = 0; i < 3; i++) {
                if (tablero[i][0] == "o" && tablero[i][1] == "o" && tablero[i][2] == "o") {
                    return 1;
                }
                if (tablero[0][i] == "o" && tablero[1][i] == "o" && tablero[2][i] == "o") {
                    return 1;
                }
            }
            return 0;
        }

        public void cambiarEstilos(TipoImagen jugadorActual) {
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                try {
                    InputStream maquinaInput = App.class.getResource("maquina.png").openStream();
                    InputStream jugador2Input = App.class.getResource("jugadorDos.png").openStream();
                    InputStream jugadorAuxiliarMaquinaInput = App.class.getResource(Ruta.JUGADORAUXILLARMAQUINA).openStream();
                    InputStream jugadorAuxiliarJugador2Input = App.class.getResource(Ruta.JUGADORAUXILLAR).openStream();
                    Image imgJugador1 = new Image(maquinaInput, 80, 50, true, true);
                    Image imgJugador2 = new Image(jugador2Input, 50, 50, true, true);
                    Image imgAuxiliarMaquina = new Image(jugadorAuxiliarMaquinaInput, 80, 50, true, true);
                    Image imgAuxiliarJugador2 = new Image(jugadorAuxiliarJugador2Input, 50, 50, true, true);
                    if (jugadorActual == TipoImagen.CIRCULO) {
                        maquinaImg = new ImageView(imgAuxiliarMaquina);
                        jugador2Img = new ImageView(imgJugador2);
                        vBoxJugador1.getChildren().remove(0);
                        vBoxJugador1.getChildren().add(0, maquinaImg);
                        vBoxJugadorM.getChildren().remove(0);
                        vBoxJugadorM.getChildren().add(0, jugador2Img);
                    } else {
                        maquinaImg = new ImageView(imgJugador1);
                        jugador2Img = new ImageView(imgAuxiliarJugador2);
                        vBoxJugador1.getChildren().remove(0);
                        vBoxJugador1.getChildren().add(0, maquinaImg);
                        vBoxJugadorM.getChildren().remove(0);
                        vBoxJugadorM.getChildren().add(0, jugador2Img);
                    }

                } catch (IOException ex) {
                }
            } else {
                try {
                    InputStream jugador2Input = App.class.getResource("jugadorUno.png").openStream();
                    InputStream maquinaInput = App.class.getResource("maquina.png").openStream();
                    InputStream jugadorAuxiliarMaquinaInput = App.class.getResource(Ruta.JUGADORAUXILLARMAQUINA).openStream();
                    InputStream jugadorAuxiliarJugador2Input = App.class.getResource(Ruta.JUGADORAUXILLAR).openStream();
                    Image imgJugador1 = new Image(jugador2Input, 50, 50, true, true);
                    Image imgJugador2 = new Image(maquinaInput, 80, 50, true, true);
                    Image imgAuxiliarMaquina = new Image(jugadorAuxiliarMaquinaInput, 80, 50, true, true);
                    Image imgAuxiliarJugador2 = new Image(jugadorAuxiliarJugador2Input, 50, 50, true, true);
                    if (jugadorActual == TipoImagen.CIRCULO) {
                        maquinaImg = new ImageView(imgJugador2);
                        jugador2Img = new ImageView(imgAuxiliarJugador2);
                        vBoxJugador1.getChildren().remove(0);
                        vBoxJugador1.getChildren().add(0, jugador2Img);
                        vBoxJugadorM.getChildren().remove(0);
                        vBoxJugadorM.getChildren().add(0, maquinaImg);
                    } else {
                        maquinaImg = new ImageView(imgAuxiliarMaquina);
                        jugador2Img = new ImageView(imgJugador1);
                        vBoxJugador1.getChildren().remove(0);
                        vBoxJugador1.getChildren().add(0, jugador2Img);
                        vBoxJugadorM.getChildren().remove(0);
                        vBoxJugadorM.getChildren().add(0, maquinaImg);
                    }

                } catch (IOException ex) {
                }
            }

        }

        public Maquina getJugador1() {
            return maquina;
        }

        public void setJugador1(Maquina maquina) {
            VistaJ1VsMaquinaController.maquina = maquina;
        }

        public JugadorM getJugador2() {
            return jugador2;
        }

        public void setJugador2(JugadorM jugador2) {
            VistaJ1VsMaquinaController.jugador2 = jugador2;
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

        public AnchorPane paintComponent(AnchorPane paneCuadro) {
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
            return paneCuadro;

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

    public void limpiarTableroGeneral() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroGeneral[i][j] = "";
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limpiarTableroGeneral();
        paneCuadroFrontal = new AnchorPane();
        jugadorActual = TipoImagen.EQUIS;
        turnoPartida = TipoImagen.EQUIS;
        tablero = new Tablero();
        tablero.setJugador1(maquina);
        tablero.setJugador2(jugador2);
        panelFondo.setStyle("-fx-border-color: #D2B48C; -fx-border-width: 10px;-fx-background-color: #87CEEB");
        tablero.crearTablero();
        tablero.crearCuadrosInternos();
        try {
            if (jugador2.getTipoImagen() == TipoImagen.CIRCULO) {
                tablero.jugarTurnoMaquina(jugador2);
                InputStream maquinaInput = App.class.getResource("maquina.png").openStream();
                InputStream jugador2Input = App.class.getResource("jugadorDos.png").openStream();
                Image imgJugador1 = new Image(maquinaInput, 80, 50, true, true);
                Image imgJugador2 = new Image(jugador2Input, 50, 50, true, true);
                maquinaImg = new ImageView(imgJugador1);
                jugador2Img = new ImageView(imgJugador2);
            } else {
                InputStream maquinaInput = App.class.getResource("jugadorUno.png").openStream();
                InputStream jugador2Input = App.class.getResource("maquina.png").openStream();
                Image imgJugador1 = new Image(maquinaInput, 80, 50, true, true);
                Image imgJugador2 = new Image(jugador2Input, 50, 50, true, true);
                maquinaImg = new ImageView(imgJugador1);
                jugador2Img = new ImageView(imgJugador2);
            }

        } catch (NullPointerException | IOException ex) {
            maquinaImg = new ImageView();
            jugador2Img = new ImageView();
        }

        tablero.cambiarEstilos(TipoImagen.EQUIS);
        lblJugador1.setText(maquina.getNombre());
        lblJugadorM.setText(jugador2.getNombre());
    }
}
