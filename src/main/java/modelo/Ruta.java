/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author kevin
 */
public class Ruta {
    public static final String EQUIS = "Equis.png";
    public static final String CIRCULO = "Circulo.png";
    public static final String JUGADOREQUIS = "jugadorUno.png";
    public static final String JUGADORCIRCULO = "jugadorDos.png";
    public static final String JUGADORMAQUINA = "maquina.png";
    public static final String JUGADORAUXILLAR = "JugadorAuxillar.png";
    
    public static String LINEA1 = "CirculoLINEA1.png";
    public static String LINEA2 = "CirculoLINEA2.png";
    public static String LINEA3 = "CirculoLINEA3.png";
    public static String LINEA4 = "CirculoLINEA4.png";
    public static String LINEA5 = "CirculoLINEA5.png";
    public static String LINEA6 = "CirculoLINEA6.png";
    public static String LINEA7 = "CirculoLINEA7.png";
    public static String LINEA8 = "CirculoLINEA8.png";
    public static String JUGADORAUXILLARMAQUINA = "maquinaAuxiliar.png";
    
    public static void cambiarRutas(TipoImagen tipoImagen){
        if(tipoImagen==TipoImagen.CIRCULO){
           LINEA1 = "CirculoLINEA1.png";
           LINEA2 = "CirculoLINEA2.png";
           LINEA3 = "CirculoLINEA3.png";
           LINEA4 = "CirculoLINEA4.png";
           LINEA5 = "CirculoLINEA5.png";
           LINEA6 = "CirculoLINEA6.png";
           LINEA7 = "CirculoLINEA7.png";
           LINEA8 = "CirculoLINEA8.png";
        }
        else if(tipoImagen==TipoImagen.EQUIS){
            LINEA1 = "EquisLINEA1.png";
           LINEA2 = "EquisLINEA2.png";
           LINEA3 = "EquisLINEA3.png";
           LINEA4 = "EquisLINEA4.png";
           LINEA5 = "EquisLINEA5.png";
           LINEA6 = "EquisLINEA6.png";
           LINEA7 = "EquisLINEA7.png";
           LINEA8 = "EquisLINEA8.png";
        }
    }
}
    

