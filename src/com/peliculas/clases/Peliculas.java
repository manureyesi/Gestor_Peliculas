package com.peliculas.clases;

import java.util.ArrayList;

/**
 *
 * @author mreyesi
 */
public class Peliculas {

    private final String nombre;
    private String url_video;
    private final String url_img;
    private final ArrayList<String> genero;
    private final String ano;
    private final String director;

    public Peliculas(String nombre, String url_img, ArrayList<String> genero, String ano, String director) {
        this.nombre = nombre;
        this.url_img = url_img;
        this.genero = genero;
        this.ano = ano;
        this.director = director;
    }

    public void setUrl_video(String url_video) {
        this.url_video = url_video;
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

    public ArrayList<String> getGenero() {
        return genero;
    }

    public String getAno() {
        return ano;
    }

    public String getDirector() {
        return director;
    }
    
}
