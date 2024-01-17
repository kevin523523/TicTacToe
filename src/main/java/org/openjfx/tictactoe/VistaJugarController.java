package org.openjfx.tictactoe;

import java.awt.Color;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelo.Jugador;
import modelo.Ruta;
import modelo.TipoImagen;

public class VistaJugarController implements Initializable {

    @FXML
    private AnchorPane panelFondo;
    @FXML
    private ImageView jugador1Img;
    @FXML
    private ImageView jugador2Img;
    /* @FXML
    private ImageView xImg;
    @FXML
    private ImageView oImg;*/
    @FXML
    private VBox vBoxJugador1;
    @FXML
    private VBox vBoxJugador2;
    @FXML
    private Label lblJugador1;
    @FXML
    private Label lblJugador2;
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

    public static Jugador jugador1;
    public static Jugador jugador2;
    public static Tablero tablero;
    private TipoImagen jugadorActual;
    private TipoImagen turnoPartida;
    private TipoImagen tipoImagen;

    private ArrayList<Cuadro> cuadros = new ArrayList();

    public static void EnviarJugador(Jugador jugador1, Jugador jugador2) {
        VistaJugarController.jugador1 = jugador1;
        VistaJugarController.jugador2 = jugador2;
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
            jugador2.limpiarTablero();
        }

        public void resultado(TipoImagen tipoImagenResultado, TipoImagen jugadorGanador, AnchorPane paneCuadroFrontal) {
            try {
                if (tipoImagenResultado == TipoImagen.EMPATE) {
                    Timer timer = new Timer();
                    TimerTask tarea = new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                VistaVictoriaController v1 = new VistaVictoriaController();
                                v1.pintarGanador(tipoImagenResultado, tablero, jugador1, jugador2);
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
                                VistaVictoriaController v1 = new VistaVictoriaController();
                                v1.pintarGanador(jugadorGanador, tablero, jugador1, jugador2);
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
                //Cuadro cuadro = new Cuadro(5, 5, Color.BLUE);

            }
        }

        public void crearEventosCuadro(AnchorPane paneCuadro, Cuadro cuadro) {
            paneCuadro.setOnMousePressed((MouseEvent event) -> {
                if (cuadro.isDibujado()) {
                    return;
                }
                TipoImagen tipoImagenResultado = null;

                if (jugadorActual == TipoImagen.EQUIS) {
                    jugador1.getTablero()[cuadro.getI()][cuadro.getJ()] = 1;

                    tipoImagenResultado = jugador1.tresEnRaya(jugador2);
                    setTipoImagen(TipoImagen.EQUIS);
                    cuadro.paintComponent(paneCuadro);
                    jugadorActual = TipoImagen.CIRCULO;
                    cambiarEstilos(TipoImagen.CIRCULO);
                    resultado(tipoImagenResultado, TipoImagen.EQUIS, paneCuadroFrontal);
                } else if (jugadorActual == TipoImagen.CIRCULO) {
                    jugador2.getTablero()[cuadro.getI()][cuadro.getJ()] = 1;
                    tipoImagenResultado = jugador2.tresEnRaya(jugador1);
                    setTipoImagen(TipoImagen.CIRCULO);
                    cuadro.paintComponent(paneCuadro);
                    jugadorActual = TipoImagen.EQUIS;
                    cambiarEstilos(TipoImagen.EQUIS);

                    resultado(tipoImagenResultado, TipoImagen.CIRCULO, paneCuadroFrontal);
                }

                cuadro.setDibujado(true);

            });

        }

        public void cambiarEstilos(TipoImagen jugadorActual) {
            try {
                InputStream jugadorAuxiliar = App.class.getResource(Ruta.JUGADORAUXILLAR).openStream();
                Image imgAuxiliar = new Image(jugadorAuxiliar, 50, 50, true, true);
                InputStream jugador1 = App.class.getResource("jugadorUno.png").openStream();
                InputStream jugador2 = App.class.getResource("jugadorDos.png").openStream();
                //InputStream x = App.class.getResource("Equis.png").openStream();
                //InputStream o = App.class.getResource("Circulo.png").openStream();
                Image imgJugador1 = new Image(jugador1, 50, 50, true, true);
                Image imgJugador2 = new Image(jugador2, 50, 50, true, true);
                //Image imgx = new Image(x, 50, 50, true, true);
                //Image imgo = new Image(o, 50, 50, true, true);

                if (jugadorActual == TipoImagen.CIRCULO) {
                    jugador1Img = new ImageView(imgAuxiliar);
                    vBoxJugador1.getChildren().remove(0);
                    vBoxJugador1.getChildren().add(0, jugador1Img);

                    jugador2Img = new ImageView(imgJugador2);
                    vBoxJugador2.getChildren().remove(0);
                    vBoxJugador2.getChildren().add(0, jugador2Img);

                } else {
                    jugador2Img = new ImageView(imgAuxiliar);
                    vBoxJugador2.getChildren().remove(0);
                    vBoxJugador2.getChildren().add(0, jugador2Img);
                    jugador1Img = new ImageView(imgJugador1);
                    vBoxJugador1.getChildren().remove(0);
                    vBoxJugador1.getChildren().add(0, jugador1Img);
                }

            } catch (IOException ex) {
            }
        }

        public Jugador getJugador1() {
            return jugador1;
        }

        public void setJugador1(Jugador jugador1) {
            VistaJugarController.jugador1 = jugador1;
        }

        public Jugador getJugador2() {
            return jugador2;
        }

        public void setJugador2(Jugador jugador2) {
            VistaJugarController.jugador2 = jugador2;
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
                //img.setLayoutX(3);
                //img.setLayoutY(3);
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
        tablero.setJugador2(jugador2);
        panelFondo.setStyle("-fx-border-color: #D2B48C; -fx-border-width: 10px;-fx-background-color: #87CEEB");
        tablero.crearTablero();
        tablero.crearCuadrosInternos();
        try {
            InputStream jugador1 = App.class.getResource("jugadorUno.png").openStream();
            InputStream jugador2 = App.class.getResource("jugadorDos.png").openStream();
            //InputStream x = App.class.getResource("Equis.png").openStream();
            //InputStream o = App.class.getResource("Circulo.png").openStream();
            Image imgJugador1 = new Image(jugador1, 50, 50, true, true);
            Image imgJugador2 = new Image(jugador2, 50, 50, true, true);
            //Image imgx = new Image(x, 50, 50, true, true);
            //Image imgo = new Image(o, 50, 50, true, true);
            jugador1Img = new ImageView(imgJugador1);
            jugador2Img = new ImageView(imgJugador2);
            //xImg = new ImageView(imgx);
            //oImg = new ImageView(imgo);

        } catch (NullPointerException | IOException ex) {
            jugador1Img = new ImageView();
            jugador2Img = new ImageView();
            //xImg = new ImageView();
            //oImg = new ImageView();
        }
        vBoxJugador1.getChildren().add(0, jugador1Img);
        //hBoxJugador1.getChildren().add(xImg);
        vBoxJugador2.getChildren().add(0, jugador2Img);

        tablero.cambiarEstilos(TipoImagen.EQUIS);
        //hBoxJugador2.getChildren().add(oImg);
        lblJugador1.setText(jugador1.getNombre());
        lblJugador2.setText(jugador2.getNombre());

    }



}
