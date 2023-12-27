/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author kevin
 */
public class Jugador {

    private String nombre;
    private TipoImagen tipoImagen;

    public Jugador() {
    }

    public Jugador(TipoImagen tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public Jugador(String nombre, TipoImagen tipoImagen) {
        this.nombre = nombre;
        this.tipoImagen = tipoImagen;
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
    

}
