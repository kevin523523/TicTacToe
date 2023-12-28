/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class PruebaController implements Initializable {
    @FXML
    private AnchorPane ponerLabel;
    @FXML
    private Label pruebaLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pruebaLabel.setText("hola");
    }    
    
}
