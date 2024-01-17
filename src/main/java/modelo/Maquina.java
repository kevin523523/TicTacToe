/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

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

    public int tableroLleno(String[][] tableroActual) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tableroActual[i][j] == "") {
                    return 0;
                }
            }
        }
        return 1;
    }

    public TipoImagen tresEnRaya(String[][] tableroGene) {
        if (tableroGene[0][0] == "x" && tableroGene[0][1] == "x" && tableroGene[0][2] == "x") {
            return TipoImagen.LINEA1;
        }
        if (tableroGene[1][0] == "x" && tableroGene[1][1] == "x" && tableroGene[1][2] == "x") {
            return TipoImagen.LINEA2;
        }
        if (tableroGene[2][0] == "x" && tableroGene[2][1] == "x" && tableroGene[2][2] == "x") {
            return TipoImagen.LINEA3;
        }
        if (tableroGene[0][0] == "x" && tableroGene[1][0] == "x" && tableroGene[2][0] == "x") {
            return TipoImagen.LINEA4;
        }
        if (tableroGene[0][1] == "x" && tableroGene[1][1] == "x" && tableroGene[2][1] == "x") {
            return TipoImagen.LINEA5;
        }
        if (tableroGene[0][2] == "x" && tableroGene[1][2] == "x" && tableroGene[2][2] == "x") {
            return TipoImagen.LINEA6;
        }
        if (tableroGene[2][0] == "x" && tableroGene[1][1] == "x" && tableroGene[0][2] == "x") {
            return TipoImagen.LINEA7;
        }
        if (tableroGene[0][0] == "x" && tableroGene[1][1] == "x" && tableroGene[2][2] == "x") {
            return TipoImagen.LINEA8;
        }
        if (tableroGene[0][0] == "o" && tableroGene[0][1] == "o" && tableroGene[0][2] == "o") {
            return TipoImagen.LINEA1;
        }
        if (tableroGene[1][0] == "o" && tableroGene[1][1] == "o" && tableroGene[1][2] == "o") {
            return TipoImagen.LINEA2;
        }
        if (tableroGene[2][0] == "o" && tableroGene[2][1] == "o" && tableroGene[2][2] == "o") {
            return TipoImagen.LINEA3;
        }
        if (tableroGene[0][0] == "o" && tableroGene[1][0] == "o" && tableroGene[2][0] == "o") {
            return TipoImagen.LINEA4;
        }
        if (tableroGene[0][1] == "o" && tableroGene[1][1] == "o" && tableroGene[2][1] == "o") {
            return TipoImagen.LINEA5;
        }
        if (tableroGene[0][2] == "o" && tableroGene[1][2] == "o" && tableroGene[2][2] == "o") {
            return TipoImagen.LINEA6;
        }
        if (tableroGene[2][0] == "o" && tableroGene[1][1] == "o" && tableroGene[0][2] == "o") {
            return TipoImagen.LINEA7;
        }
        if (tableroGene[0][0] == "o" && tableroGene[1][1] == "o" && tableroGene[2][2] == "o") {
            return TipoImagen.LINEA8;
        }
        if (tableroLleno(tableroGene) == 1) {
            return TipoImagen.EMPATE;
        }

        return null;
    }

}
