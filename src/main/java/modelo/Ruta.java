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
    public static final String JUGADOREQUIS = "JugadorEquis.png";
    public static final String JUGADORCIRCULO = "JugadorCirculo.png";
    public static final String JUGADORAUXILLAR = "JugadorAuxillar.png";
    
    public static String LINEA1 = "CirculoLinea1.png";
    public static String LINEA2 = "CirculoLinea2.png";
    public static String LINEA3 = "CirculoLinea3.png";
    public static String LINEA4 = "CirculoLinea4.png";
    public static String LINEA5 = "CirculoLinea5.png";
    public static String LINEA6 = "CirculoLinea6.png";
    public static String LINEA7 = "CirculoLinea7.png";
    public static String LINEA8 = "CirculoLinea8.png";
    
    public static void cambiarRutas(TipoImagen tipoImagen){
        if(tipoImagen==TipoImagen.CIRCULO){
           LINEA1 = "CirculoLinea1.png";
           LINEA2 = "CirculoLinea2.png";
           LINEA3 = "CirculoLinea3.png";
           LINEA4 = "CirculoLinea4.png";
           LINEA5 = "CirculoLinea5.png";
           LINEA6 = "CirculoLinea6.png";
           LINEA7 = "CirculoLinea7.png";
           LINEA8 = "CirculoLinea8.png";
        }
        else if(tipoImagen==TipoImagen.EQUIS){
            LINEA1 = "EquisLinea1.png";
           LINEA2 = "EquisLinea2.png";
           LINEA3 = "EquisLinea3.png";
           LINEA4 = "EquisLinea4.png";
           LINEA5 = "EquisLinea5.png";
           LINEA6 = "EquisLinea6.png";
           LINEA7 = "EquisLinea7.png";
           LINEA8 = "EquisLinea8.png";
        }
    }
}
    

