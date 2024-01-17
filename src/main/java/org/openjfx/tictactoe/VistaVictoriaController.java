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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import modelo.Jugador;
import modelo.Ruta;
import modelo.TipoImagen;
import org.openjfx.tictactoe.VistaJugarController.Tablero;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class VistaVictoriaController implements Initializable {
    @FXML
    private static ImageView imgGanador;
    @FXML
    private static ImageView imgIcono;
    @FXML
    private HBox hBoxVictoria;
    @FXML
    private Label lblGanador;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button btnJugarDeNuevo;
    private static Tablero tablero;
    private static TipoImagen jugadorGanador;
    private Jugador jugadorP1 = new Jugador();
    private Jugador jugador2;

    public VistaVictoriaController() {
    }

    public void pintarGanador(TipoImagen jugadorGanador, Tablero tablero, Jugador jugador1, Jugador jugador2) {
        jugadorP1.setNombre(jugador1.getNombre());
        this.jugadorGanador = jugadorGanador;
        this.tablero = tablero;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("VistaVictoria.fxml"));
            Parent root = loader.load();
            // Crear una nueva Scene
            Scene scene = new Scene(root);
            // Configurar un nuevo Stage
            Stage nuevoStage = new Stage();
            nuevoStage.setScene(scene);
            nuevoStage.setTitle("Vista Ganador");
            // Mostrar el nuevo Stage
            nuevoStage.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    public void jugarDeNuevo(MouseEvent event) {
        // Obtén la referencia al Stage actual
        Stage stage = (Stage) btnJugarDeNuevo.getScene().getWindow();

        // Cierra la ventana actual
        stage.close();
        tablero.reiniciarTablero(jugadorGanador);
        tablero.crearTablero();
        tablero.crearCuadrosInternos();

    }

    @FXML
    public void regresar(MouseEvent event) throws IOException {
        Stage stage = (Stage) btnJugarDeNuevo.getScene().getWindow();
        stage.close();
        tablero.reiniciarTablero(jugadorGanador);
        tablero.crearTablero();
        tablero.crearCuadrosInternos();
        App.setRoot("VistaPrincipal");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            InputStream input1;
            InputStream input2;
            if (VistaVictoriaController.jugadorGanador == TipoImagen.EQUIS) {
                input1 = App.class.getResource(Ruta.EQUIS).openStream();
                input2 = App.class.getResource(Ruta.JUGADOREQUIS).openStream();
                Image imagen1 = new Image(input1, 80, 80, true, true);
                Image imagen2 = new Image(input2, 80, 80, true, true);
                imgIcono = new ImageView(imagen1);
                imgGanador = new ImageView(imagen2);
                lblGanador.setText(VistaJugarController.jugador1.getNombre());
                hBoxVictoria.getChildren().add(0, imgGanador);
                hBoxVictoria.getChildren().add(imgIcono);
            } else if (VistaVictoriaController.jugadorGanador == TipoImagen.CIRCULO) {
                input1 = App.class.getResource(Ruta.CIRCULO).openStream();
                input2 = App.class.getResource(Ruta.JUGADORCIRCULO).openStream();
                Image imagen1 = new Image(input1, 80, 80, true, true);
                Image imagen2 = new Image(input2, 80, 80, true, true);
                imgIcono = new ImageView(imagen1);
                imgGanador = new ImageView(imagen2);
                lblGanador.setText(VistaJugarController.jugador2.getNombre());
                hBoxVictoria.getChildren().add(0, imgGanador);
                hBoxVictoria.getChildren().add(imgIcono);
            } else {
                input1 = App.class.getResource(Ruta.JUGADOREQUIS).openStream();
                input2 = App.class.getResource(Ruta.JUGADORCIRCULO).openStream();
                Image imagen1 = new Image(input1, 80, 80, true, true);
                Image imagen2 = new Image(input2, 80, 80, true, true);
                imgIcono = new ImageView(imagen1);
                imgGanador = new ImageView(imagen2);
                lblGanador.setText(VistaJugarController.jugador2.getNombre());
                hBoxVictoria.getChildren().add(0, imgIcono);
                hBoxVictoria.getChildren().add(imgGanador);
                lblGanador.setText("Empate");
            }

        } catch (IOException ex) {
            imgIcono = new ImageView();
            imgGanador = new ImageView();
        }
    }

}
