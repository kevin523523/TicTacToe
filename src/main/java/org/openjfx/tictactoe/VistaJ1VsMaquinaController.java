/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.tictactoe;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import modelo.Jugador;
import modelo.Tree;
import org.openjfx.tictactoe.VistaJugarController.Tablero;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class VistaJ1VsMaquinaController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void generarEstados(Jugador jugador) {
        int tableroActual[][] = jugador.getTablero();
        Tree<int[][]> tree = new Tree<>(tableroActual);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tableroActual[i][j] == 0) {
                    tableroActual[i][j] = 1;
                    Tree<int[][]> hijoArbol = new Tree<>(tableroActual);
                    tree.getRootNode().addChild(hijoArbol);
                }
            }
        }
    }

    public int utilidadMaxima(Tree<int[][]> tree) {
        List<Tree<int[][]>> hijosPadre = tree.getRootNode().getChildren();
        ArrayList<Integer> utilidadesMinimas = new ArrayList<>();
        for (Tree<int[][]> child : hijosPadre) {
            List<Tree<int[][]>> sobrinos = child.getRootNode().getChildren();
            for (Tree<int[][]> sobrino : sobrinos) {
                int tableroJugador[][] = (int[][]) child.getRootNode().getContent();
                int tableroOponente[][] = (int[][]) sobrino.getRootNode().getContent();
                int utilidad = Pjugador(tableroOponente) - Pjugador(tableroJugador);
                utilidadesMinimas.add(utilidad);
            }
        }
        if (!utilidadesMinimas.isEmpty()) {
            return Collections.max(utilidadesMinimas);
        } else {
            return 0; 
        }
    }

    public int Pjugador(int tableroOponente[][]) {
        int pjugador = 0;
        for (int i = 0; i < tableroOponente.length; i++) {
            if (tableroOponente[i][0] == 0 && tableroOponente[i][1] == 0 && tableroOponente[i][2] == 0) {
                pjugador++;
            }
            if (tableroOponente[0][i] == 0 && tableroOponente[1][i] == 0 && tableroOponente[2][i] == 0) {
                pjugador++;
            }
        }
        if (tableroOponente[0][0] == 0 && tableroOponente[1][1] == 0 && tableroOponente[2][2] == 0) {
            pjugador++;
        }
        if (tableroOponente[0][2] == 0 && tableroOponente[1][1] == 0 && tableroOponente[2][0] == 0) {
            pjugador++;
        }
        return pjugador;
    }

}
