package com.peliculas.bd;

import com.peliculas.configuracion.XMLCargarConfiguracion;
import com.peliculas.exception.CodigoError;
import com.peliculas.exception.ErrorPrograma;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 *
 * @author mreyesi
 */
public class Conexion {
    
    private Logger log = Logger.getLogger(Conexion.class);
    
    private Connection cn = null;
    
    Conexion() throws ErrorPrograma{
        
        XMLCargarConfiguracion arc = null;
        
        try{
            log.info("Cargando configuracion");
            arc = new XMLCargarConfiguracion();
        }
        catch(IOException | ParserConfigurationException | SAXException e){
            log.error("Error al cargar datos de archivo: ", e);
            throw new ErrorPrograma(CodigoError.ERROR_XML);
        } catch(Exception e){
            log.error("Error al cargar datos de archivo: ", e);
            throw new ErrorPrograma(CodigoError.ERROR_XML);
        }
        
        String url = "jdbc:mysql://"+arc.getIp()+":"+arc.getPort()+"/"+arc.getDb();
        
        try {
            cn = DriverManager.getConnection(url,arc.getUser(),arc.getPass());
            log.info("Conexion creada correctamente");
        } catch (SQLException ex) {
            log.error("Error al montar conexion MySQL: ", ex);
            throw new ErrorPrograma(CodigoError.ERROR_CONEXION);
        }
    }

    public Connection getCn() {
        return cn;
    }
    
}
