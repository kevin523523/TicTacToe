/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.tictactoe;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modelo.Jugador;
import modelo.JugadorM;
import modelo.Maquina;
import modelo.TipoImagen;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class VistaPrincipalController implements Initializable {

    @FXML
    AnchorPane panePantalla;
    @FXML
    HBox hBox1vs1;
    @FXML
    HBox hBox1vsM;
    @FXML
    ImageView jugador1Img;
    @FXML
    ImageView jugador2Img;
    @FXML
    ImageView jugadorMImg;
    @FXML
    ImageView jugador1MImg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            InputStream jugador1 = App.class.getResource("jugadorUno.png").openStream();
            InputStream jugador2 = App.class.getResource("jugadorDos.png").openStream();
            InputStream jugadorM = App.class.getResource("maquina.png").openStream();
            InputStream jugador1M = App.class.getResource("jugadorUno.png").openStream();
            Image imgJugador1 = new Image(jugador1, 50, 50, true, true);
            Image imgJugador1M = new Image(jugador1M, 50, 50, true, true);
            Image imgJugador2 = new Image(jugador2, 50, 50, true, true);
            Image imgJugadorM = new Image(jugadorM, 80, 80, true, true);
            jugador1Img = new ImageView(imgJugador1);
            jugador1MImg = new ImageView(imgJugador1M);
            jugador2Img = new ImageView(imgJugador2);
            jugadorMImg = new ImageView(imgJugadorM);
        } catch (NullPointerException | IOException ex) {
            jugador1Img = new ImageView();
            jugador2Img = new ImageView();
            jugadorMImg = new ImageView();
            jugador1MImg = new ImageView();
        }

        hBox1vs1.getChildren().add(0, jugador1Img);
        hBox1vs1.getChildren().add(jugador2Img);
        hBox1vsM.getChildren().add(0, jugador1MImg);
        hBox1vsM.getChildren().add(jugadorMImg);
    }

    @FXML
    public void exit(MouseEvent event) {
        Stage stage = (Stage) panePantalla.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void switchToInicio(MouseEvent event) throws IOException {
        App.setRoot("VistaInicio");
    }

    @FXML
    public void switchToInicioM(MouseEvent event) throws IOException {
        App.setRoot("VistaInicioM");
    }

}
