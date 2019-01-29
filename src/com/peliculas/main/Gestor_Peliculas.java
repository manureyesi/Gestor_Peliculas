package com.peliculas.main;

import com.peliculas.bd.AdministradorPeliculasDB;
import com.peliculas.clases.Peliculas;
import com.peliculas.configuracion.XMLCargarConfiguracion;
import com.peliculas.exception.CodigoError;
import com.peliculas.exception.ErrorPrograma;
import com.peliculas.utils.BuscarInfoPelicula;
import com.peliculas.utils.Directorio;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.SAXException;

/**
 *
 * @author mreyesi
 */
public class Gestor_Peliculas {

    private final static Logger log = Logger.getLogger(Gestor_Peliculas.class);
    
    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        XMLCargarConfiguracion arc = null;
        
        try{

            log.info("Inicio de Programa");
            log.info("Leyendo propiedades");
            try{
                log.info("Cargando configuracion");
                arc = new XMLCargarConfiguracion();
            }
            catch(IOException | ParserConfigurationException | SAXException e){
                log.error("Error al cargar datos de archivo: ", e);
                throw new ErrorPrograma(CodigoError.ERROR_XML);
            } catch(Exception e){
                log.error("Error al cargar datos de archivo: ", e);
                throw new ErrorPrograma(CodigoError.ERROR_GENERICO);
            }
            
            Directorio dir = new Directorio();
            ArrayList<String> lista;
                lista = dir.formatearRutaFicheros(dir.listaDirectoriosNuevo(arc.getPath()), arc.getPath());
            
            
            BuscarInfoPelicula buscar = null;
            Peliculas pelicula = null;
            buscar = new BuscarInfoPelicula();
            for(String arch: lista){
                if(!arch.contains("$")){
                    pelicula = buscar.BuscarInfoPelicula(arch.substring(0,arch.lastIndexOf(".")), arch);
                    log.info(arch.substring(0,arch.lastIndexOf(".")));
                    if(!AdministradorPeliculasDB.subirPelicula(pelicula)){
                        log.error("ERROR al añadir Pelicula de: "+ pelicula.getNombre());
                    }
                }
            }
            
        } catch(ErrorPrograma ex){
            log.error(ex.toString());
        }
    }
    
}