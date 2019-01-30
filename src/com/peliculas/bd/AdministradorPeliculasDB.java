package com.peliculas.bd;

import com.peliculas.clases.Peliculas;
import com.peliculas.exception.ErrorPrograma;
import org.apache.log4j.Logger;

/**
 *
 * @author mreyesi
 */
public class AdministradorPeliculasDB {
    
    private final static Logger log = Logger.getLogger(AdministradorPeliculasDB.class);
    
    public static boolean subirPelicula(Peliculas pelicula){
      
        log.info("Preparando INSERCION de PELICULA: " + pelicula.getNombre());
        boolean error = false;
        
        try {
            Consultas con = new Consultas();
            
            con.insertPelicula(pelicula.getNombre(), pelicula.getUrl_video(), pelicula.getUrl_img(), 
                    1, pelicula.getAno(), pelicula.getDirector());
            log.info("PELICULA añadida con EXITO");
        } catch (ErrorPrograma ex) {
            error = true;
            log.error("Error al INSERTAR Pelicula");
        }
                
        return error;
    }
    
}
