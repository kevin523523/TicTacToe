package org.openjfx.tictactoe;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.awt.Color;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelo.Jugador;
import modelo.TipoImagen;

public class VistaInicioController implements Initializable {

    @FXML
    private TextField txtJugador1;
    @FXML
    private TextField txtJugador2;
    @FXML
    private Button btnJugar;
    @FXML
    private HBox hBoxJugador1;
    @FXML
    private HBox hBoxJugador2;
    @FXML
    private VBox VBoxJugadores;
    @FXML
    private ImageView jugador1Img;
    @FXML
    private ImageView jugador2Img;
    @FXML
    private ImageView xImg;
    @FXML
    private ImageView oImg;
    @FXML
    private AnchorPane panelFondo;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panelFondo.setStyle("-fx-border-color: #D2B48C; -fx-border-width: 10px;-fx-background-color: #87CEEB");

        try {
            InputStream jugador1 = App.class.getResource("jugadorUno.png").openStream();
            InputStream jugador2 = App.class.getResource("jugadorDos.png").openStream();
            InputStream x = App.class.getResource("Equis.png").openStream();
            InputStream o = App.class.getResource("Circulo.png").openStream();
            Image imgJugador1 = new Image(jugador1, 50, 50, true, true);
            Image imgJugador2 = new Image(jugador2, 50, 50, true, true);
            Image imgx = new Image(x, 50, 50, true, true);
            Image imgo = new Image(o, 50, 50, true, true);
            jugador1Img = new ImageView(imgJugador1);
            jugador2Img = new ImageView(imgJugador2);
            xImg = new ImageView(imgx);
            oImg = new ImageView(imgo);

        } catch (NullPointerException | IOException ex) {
            jugador1Img = new ImageView();
            jugador2Img = new ImageView();
            xImg = new ImageView();
            oImg = new ImageView();
        }

        hBoxJugador1.getChildren().add(0, jugador1Img);
        hBoxJugador1.getChildren().add(xImg);
        hBoxJugador2.getChildren().add(0, jugador2Img);
        hBoxJugador2.getChildren().add(oImg);
    }

    @FXML
    private void txtJugador1MouseEntered(MouseEvent event) {
        // Cambia el estilo del TextField cuando el mouse entra
        txtJugador1.setStyle("-fx-border-color: #3488EB; -fx-border-width: 2px;");
    }

    @FXML
    private void txtJugador1MouseExited(MouseEvent event) {
        // Restaura el estilo original del TextField cuando el mouse sale
        txtJugador1.setStyle(null);
    }

    @FXML
    private void txtJugador2MouseEntered(MouseEvent event) {
        // Cambia el estilo del TextField cuando el mouse entra
        txtJugador2.setStyle("-fx-border-color: #3488EB; -fx-border-width: 2px;");
    }

    @FXML
    private void txtJugador2MouseExited(MouseEvent event) {
        // Restaura el estilo original del TextField cuando el mouse sale
        txtJugador2.setStyle(null);
    }

    @FXML
    private void txtJugador1KeyTyped(KeyEvent event) {
        int maxLength = 8;
        txtJugador1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                txtJugador1.setText(newValue.substring(0, maxLength));
            }
        });
    }

    @FXML
    private void txtJugador2KeyTyped(KeyEvent event) {
        int maxLength = 8;
        txtJugador2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                txtJugador2.setText(newValue.substring(0, maxLength));
            }
        });
    }

    @FXML
    private void btnJugarMouseEntered(MouseEvent event) {
        // Cambia el color del fondo del botón a verde cuando el mouse entra
        btnJugar.setStyle("-fx-background-color: green;");
    }

    @FXML
    private void btnJugarMouseExited(MouseEvent event) {
        // Cambia el color del fondo del botón a verde cuando el mouse entra
        btnJugar.setStyle(null);
    }

    @FXML
    private void switchToJugar() throws IOException {
        Jugador jugador1 = new Jugador(TipoImagen.EQUIS);
        if (txtJugador1.getText().equals("")) {
            jugador1.setNombre("JUGADOR 1");
        } else {
            jugador1.setNombre(txtJugador1.getText());
        }
        Jugador jugador2 = new Jugador(TipoImagen.CIRCULO);
        if (txtJugador2.getText().equals("")) {
            jugador2.setNombre("JUGADOR 2");
        } else {
            jugador2.setNombre(txtJugador2.getText());
        }
        VistaJugarController.EnviarJugador(jugador1,jugador2);
        App.setRoot("VistaJugar");

    }

}
