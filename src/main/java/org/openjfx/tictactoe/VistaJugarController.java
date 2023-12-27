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
    private AnchorPane paneTablero;
    @FXML
    private AnchorPane paneCuadro;
    @FXML
    private HBox hBoxTablero;
    @FXML
    private static VBox vBoxTablero;
    @FXML
    private ImageView img;

    private static Jugador jugador1;
    private static Jugador jugador2;
    private static Tablero tablero;
    private TipoImagen jugadorActual;
    private TipoImagen turnoPartida;
    private TipoImagen tipoImagen;

    public static void EnviarJugador(Jugador jugador1, Jugador jugador2) {
        VistaJugarController.jugador1 = jugador1;
        VistaJugarController.jugador2 = jugador2;

    }

    public class Tablero {

        public Tablero() {
        }

        public void crearTablero() {
            paneTablero.setStyle("-fx-border-color: #8B4513; -fx-border-width: 10px;-fx-background-color: #cef139");
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
                    paneCuadro.setStyle("-fx-border-color: #fe9430; -fx-border-width: 10px;-fx-background-color: #fe9430");
                    paneCuadro.setLayoutX(x);
                    paneCuadro.setLayoutY(y);
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
                TipoImagen tipoImagenResultado = null;
                if (cuadro.isDibujado()) {
                    return;
                }
                if (jugadorActual == TipoImagen.EQUIS) {
                    setTipoImagen(TipoImagen.EQUIS);
                    cuadro.paintComponent(paneCuadro);
                    jugadorActual = TipoImagen.CIRCULO;
                    cambiarEstilos(TipoImagen.CIRCULO);
                } else if (jugadorActual == TipoImagen.CIRCULO) {
                    setTipoImagen(TipoImagen.CIRCULO);
                    cuadro.paintComponent(paneCuadro);
                    jugadorActual = TipoImagen.EQUIS;
                    cambiarEstilos(TipoImagen.EQUIS);
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
        panelFondo.setStyle("-fx-border-color: #8B4513; -fx-border-width: 10px;-fx-background-color: #00b3ff");
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
