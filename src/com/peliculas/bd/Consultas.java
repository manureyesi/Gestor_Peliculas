package com.peliculas.bd;

import com.peliculas.exception.CodigoError;
import com.peliculas.exception.ErrorPrograma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author MANU
 */
public class Consultas {
    
    private final Logger log = Logger.getLogger(Consultas.class);
    Connection con = null;
    
    public Consultas() throws ErrorPrograma{
        
        Conexion conexion = new Conexion();
        this.con = conexion.getCn();
        
    }

    public void insertPelicula(String nombre, String urlVideo, String urlImg, int genero, int fechaSalida, String director) throws ErrorPrograma {
        
        try{
            String sql = "INSERT INTO PELICULAS (NOMBRE, URL_VIDEO, URL_IMG, FECHA_SALIDA, DIRECTOR) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insert = con.prepareStatement(sql);
            insert.setString(1, nombre);
            insert.setString(2, urlVideo);
            insert.setString(3, urlImg);
            insert.setInt(4, fechaSalida);
            insert.setString(5, director);
            insert.executeUpdate();
            log.info("Preparando INSERT en PELICULAS: "+insert.toString());
            log.info("PELICULA añadida con EXITO");
            
        }
        catch(SQLException ex){
            log.warn("Error al Crear PELICULA en DB.", ex);
            throw new ErrorPrograma(CodigoError.ERROR_CONSULTAS);
        }
    }
    
    public ResultSet selectGenero(String condicion) throws ErrorPrograma{
        
        ResultSet rs;
        PreparedStatement consulta;
        try {
            consulta = con.prepareStatement("SELECT ID FROM GENERO WHERE GENEROIMDB LIKE '%"+condicion+"%'");
        
            rs = consulta.executeQuery();
        } catch (SQLException ex) {
            log.warn("Error al buscar Genero de peliculas",ex);
            throw new ErrorPrograma(CodigoError.ERROR_CONSULTAS);
        } 
        return rs;
        
    }
    
    public void crearGeneros(String generoIMDB) throws ErrorPrograma{
        try{
            String sql = "INSERT INTO GENERO (GENEROIMDB) VALUES (?)";
            PreparedStatement insert = con.prepareStatement(sql);
            insert.setString(1, generoIMDB);
            insert.executeUpdate();
            log.info("Preparando INSERT en GENERO");
            log.info("GENERO añadido con EXITO");
            
        }
        catch(SQLException ex){
            log.warn("Error al Crear GENERO en DB: ", ex);
            throw new ErrorPrograma(CodigoError.ERROR_CONSULTAS);
        }
    }
    
}
