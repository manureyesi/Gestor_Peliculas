package com.peliculas.clases;

import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author mreyesi
 */
public class Estadisticas {
    
    private final static Logger log = Logger.getLogger(Estadisticas.class);
    
    private int estadisticaContadorPeliculas;
    private int estadisticasContadorPeliculasCorrectas;
    private ArrayList<String> estadisticasControladorFallos;
    
    public Estadisticas(){
        this.estadisticaContadorPeliculas = 0;
        this.estadisticasContadorPeliculasCorrectas = 0;
    }
    
    public void sumarContadorPeliculasCorrectas(){
        this.estadisticasContadorPeliculasCorrectas++;
    }
    
    public int devolverContadorPeliculasCorrectas(){
        return this.estadisticasContadorPeliculasCorrectas;
    }
    
    public void sumarContadorPeliculasIncorrectas(){
        this.estadisticaContadorPeliculas++;
    }
    
    public int devolverContadorPeliculasIncorrectas(){
        return this.estadisticaContadorPeliculas;
    }
    
    public void anadirErrorPelicula(String error){
        this.estadisticasControladorFallos.add(error);
    }
    
    public ArrayList<String> devolverListaErrores(){
        return this.estadisticasControladorFallos;
    }
}
