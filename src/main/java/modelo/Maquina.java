/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import org.openjfx.tictactoe.VistaJugarController;
import org.openjfx.tictactoe.VistaJugarController.Tablero;

/**
 *
 * @author kevin
 */
public class Maquina {

    private String nombre;
    private TipoImagen tipoImagen;
    private String tablero[][];

    public Maquina() {
        tablero = new String[3][3];
        limpiarTablero();
    }

    public Maquina(TipoImagen tipoImagen) {
        this.tipoImagen = tipoImagen;
        tablero = new String[3][3];
        limpiarTablero();
    }

    public Maquina(String nombre, TipoImagen tipoImagen) {
        this.nombre = nombre;
        this.tipoImagen = tipoImagen;
        tablero = new String[3][3];
        limpiarTablero();
    }

    public void limpiarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = "";
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoImagen getTipoImagen() {
        return tipoImagen;
    }

    public void setTipoImagen(TipoImagen tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public String[][] getTablero() {
        return tablero;
    }

    public void setTablero(String[][] tablero) {
        this.tablero = tablero;
    }

    public TipoImagen tresEnRaya(JugadorM jugadorRival) {
        if (tablero[0][0] == "o" && tablero[0][1] == "o" && tablero[0][2] == "o") {
            return TipoImagen.LINEA1;
        }
        if (tablero[1][0] == "o" && tablero[1][1] == "o" && tablero[1][2] == "o") {
            return TipoImagen.LINEA2;
        }
        if (tablero[2][0] == "o" && tablero[2][1] == "o" && tablero[2][2] == "o") {
            return TipoImagen.LINEA3;
        }
        if (tablero[0][0] == "o" && tablero[1][0] == "o" && tablero[2][0] == "o") {
            return TipoImagen.LINEA4;
        }
        if (tablero[0][1] == "o" && tablero[1][1] == "o" && tablero[2][1] == "o") {
            return TipoImagen.LINEA5;
        }
        if (tablero[0][2] == "o" && tablero[1][2] == "o" && tablero[2][2] == "o") {
            return TipoImagen.LINEA6;
        }
        if (tablero[2][0] == "o" && tablero[1][1] == "o" && tablero[0][2] == "o") {
            return TipoImagen.LINEA7;
        }
        if (tablero[0][0] == "o" && tablero[1][1] == "o" && tablero[2][2] == "o") {
            return TipoImagen.LINEA8;
        }

        int contador = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == "o") {
                    contador++;
                }
                if (jugadorRival.getTablero()[i][j] == "x") {
                    contador++;
                }

            }
        }

        if (contador == 9) {
            return TipoImagen.EMPATE;
        }

        return null;
    }

}
