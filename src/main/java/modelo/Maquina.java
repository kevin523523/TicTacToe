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
    private int tablero[][];

    public Maquina() {
        tablero = new int[3][3];
        limpiarTablero();
    }

    public Maquina(TipoImagen tipoImagen) {
        this.tipoImagen = tipoImagen;
        tablero = new int[3][3];
        limpiarTablero();
    }

    public Maquina(String nombre, TipoImagen tipoImagen) {
        this.nombre = nombre;
        this.tipoImagen = tipoImagen;
        tablero = new int[3][3];
        limpiarTablero();
    }

    public void limpiarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = 0;
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

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public TipoImagen tresEnRaya(Jugador jugadorRival) {
        if (tablero[0][0] == 1 && tablero[0][1] == 1 && tablero[0][2] == 1) {
            return TipoImagen.LINEA1;
        }
        if (tablero[1][0] == 1 && tablero[1][1] == 1 && tablero[1][2] == 1) {
            return TipoImagen.LINEA2;
        }
        if (tablero[2][0] == 1 && tablero[2][1] == 1 && tablero[2][2] == 1) {
            return TipoImagen.LINEA3;
        }
        if (tablero[0][0] == 1 && tablero[1][0] == 1 && tablero[2][0] == 1) {
            return TipoImagen.LINEA4;
        }
        if (tablero[0][1] == 1 && tablero[1][1] == 1 && tablero[2][1] == 1) {
            return TipoImagen.LINEA5;
        }
        if (tablero[0][2] == 1 && tablero[1][2] == 1 && tablero[2][2] == 1) {
            return TipoImagen.LINEA6;
        }
        if (tablero[2][0] == 1 && tablero[1][1] == 1 && tablero[0][2] == 1) {
            return TipoImagen.LINEA7;
        }
        if (tablero[0][0] == 1 && tablero[1][1] == 1 && tablero[2][2] == 1) {
            return TipoImagen.LINEA8;
        }

        int contador = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == 1) {
                    contador++;
                }
                if (jugadorRival.getTablero()[i][j] == 1) {
                    contador++;
                }

            }
        }

        if (contador == 9) {
            return TipoImagen.EMPATE;
        }

        return null;
    }

    public int utilidad(Tablero tablero) {
        int tableroJugador[][] = tablero.getJugador1().getTablero();
        int tableroOponente[][] = tablero.getJugador2().getTablero();
        int pJugadorJ1 = Pjugador(tableroOponente);
        int pOponente = Pjugador(tableroJugador);

        return pJugadorJ1 - pOponente;

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
