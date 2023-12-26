package org.openjfx.tictactoe;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class VistaInicioController {

    @FXML
    private TextField txtJugador1;
    @FXML
    private TextField txtJugador2;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
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
    
}
