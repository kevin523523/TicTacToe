/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author kevin
 */
public class JugadorM {

    private String nombre;
    private TipoImagen tipoImagen;
    private String tablero[][];

    public JugadorM() {
        tablero = new String[3][3];
        limpiarTablero();
    }

    public JugadorM(TipoImagen tipoImagen) {
        this.tipoImagen = tipoImagen;
        tablero = new String[3][3];
        limpiarTablero();
    }

    public JugadorM(String nombre, TipoImagen tipoImagen) {
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

    public TipoImagen tresEnRaya(Maquina jugadorRival) {
        if (tablero[0][0] == "x" && tablero[0][1] == "x" && tablero[0][2] == "x") {
            return TipoImagen.LINEA1;
        }
        if (tablero[1][0] == "x" && tablero[1][1] == "x" && tablero[1][2] == "x") {
            return TipoImagen.LINEA2;
        }
        if (tablero[2][0] == "x" && tablero[2][1] == "x" && tablero[2][2] == "x") {
            return TipoImagen.LINEA3;
        }
        if (tablero[0][0] == "x" && tablero[1][0] == "x" && tablero[2][0] == "x") {
            return TipoImagen.LINEA4;
        }
        if (tablero[0][1] == "x" && tablero[1][1] == "x" && tablero[2][1] == "x") {
            return TipoImagen.LINEA5;
        }
        if (tablero[0][2] == "x" && tablero[1][2] == "x" && tablero[2][2] == "x") {
            return TipoImagen.LINEA6;
        }
        if (tablero[2][0] == "x" && tablero[1][1] == "x" && tablero[0][2] == "x") {
            return TipoImagen.LINEA7;
        }
        if (tablero[0][0] == "x" && tablero[1][1] == "x" && tablero[2][2] == "x") {
            return TipoImagen.LINEA8;
        }

        int contador = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == "x") {
                    contador++;
                }
                if (jugadorRival.getTablero()[i][j] == "o") {
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
