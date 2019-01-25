package com.peliculas.clases;

/**
 *
 * @author mreyesi
 */
public class Peliculas {

    private final String nombre;
    private String url_video;
    private String url_img;
    private final int genero;
    private final String ano;

    public Peliculas(String nombre, int genero, String ano) {
        this.nombre = nombre;
        this.genero = genero;
        this.ano = ano;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUrl_video() {
        return url_video;
    }

    public String getUrl_img() {
        return url_img;
    }

    public int getGenero() {
        return genero;
    }

    public String getAno() {
        return ano;
    }
    
}
