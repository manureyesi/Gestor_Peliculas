package com.peliculas.main;

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
            try {
                lista = dir.formatearRutaFicheros(dir.listaDirectorios(arc.getPath()), arc.getPath());
                
            } catch (IOException ex) {
                log.error("Error al listar archivos");
                throw new ErrorPrograma(CodigoError.ERROR_ARCHIVOS);
            }
            
            BuscarInfoPelicula buscar = null;
            
            for(String arch: lista){
                log.info(arch.substring(0,arch.lastIndexOf(".")));
            }
            
            buscar = new BuscarInfoPelicula();
            log.info(buscar.BuscarInfoPelicula("Pan"));
            
        } catch(ErrorPrograma ex){
            log.error(ex.toString());
        }
    }
    
}
