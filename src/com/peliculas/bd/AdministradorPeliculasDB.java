package com.peliculas.bd;

import com.peliculas.clases.Peliculas;
import com.peliculas.exception.CodigoError;
import com.peliculas.exception.ErrorPrograma;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author mreyesi
 */
public class AdministradorPeliculasDB {
    
    private final static Logger log = Logger.getLogger(AdministradorPeliculasDB.class);
    
    public static void subirPelicula(Peliculas pelicula) throws ErrorPrograma{
      
        log.info("Preparando INSERCION de PELICULA: " + pelicula.getNombre());
      
        int genero = crearGenero(pelicula);
        
        Consultas.insertPelicula(pelicula.getNombre(), pelicula.getUrl_video(), pelicula.getUrl_img(), 
                genero, pelicula.getAno(), pelicula.getDirector());
        log.info("PELICULA añadida con EXITO");
    }
    
    private static int crearGenero(Peliculas pelicula) throws ErrorPrograma{
    
        log.info("Preparando Busqueda de Genero de Pelicula");
        
        boolean generoEncontrado = false;
        int idGenero = 0;
        
        if(!pelicula.getGenero().isEmpty()){
            
            ResultSet rsGenero = Consultas.selectGenero(pelicula.getGenero().get(0));
            
            try{
                while(rsGenero.next()){
                    generoEncontrado = true;
                    idGenero = rsGenero.getInt("ID");
                }
            } catch(SQLException e){
                log.warn("Error al buscar Genero de peliculas",e);
                throw new ErrorPrograma(CodigoError.ERROR_CONSULTAS);
            }
            
            if(!generoEncontrado){
                Consultas.crearGeneros(pelicula.getGenero().get(0));
                log.info("GENERO "+pelicula.getGenero().get(0)+" insertado correctamente");
            }
            
            try{
                rsGenero = Consultas.selectGenero(pelicula.getGenero().get(0));
                while(rsGenero.next()){
                    idGenero = rsGenero.getInt("ID");
                }
            } catch(SQLException e){
                log.warn("Error al buscar Genero de peliculas",e);
                throw new ErrorPrograma(CodigoError.ERROR_CONSULTAS);
            }
            
        } else{
            log.warn("Error al buscar Genero, no se a pasado Genero");
            throw new ErrorPrograma(CodigoError.ERROR_CONSULTAS);
        }
        
        return idGenero;
    }
}
