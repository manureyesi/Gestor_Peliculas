package com.peliculas.bd;

import com.peliculas.exception.CodigoError;
import com.peliculas.exception.ErrorPrograma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void insertPelicula(String nombre, String urlVideo, String urlImg, int genero, String fechaSalida, String director) throws ErrorPrograma {
        
        try{
            String sql = "INSERT INTO PELICULAS (NOMBRE, URL_VIDEO, URL_IMG, GENERO, FECHA_SALIDA, DIRECTOR) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insert = con.prepareStatement(sql);
            insert.setString(0, nombre);
            insert.setString(1, urlVideo);
            insert.setString(2, urlImg);
            insert.setInt(3, genero);
            insert.setString(4, fechaSalida);
            insert.setString(5, director);
            insert.executeUpdate();
            log.info("Preparando INSERT en PELICULAS: "+insert.toString());
            log.info("PELICULA añadida con EXITO");
            
        }
        catch(SQLException ex){
            log.error("Error al Crear PELICULA en DB.");
            throw new ErrorPrograma(CodigoError.ERROR_CONSULTAS);
        }
    }
    
    public ResultSet select(String condicion) throws SQLException{
            
        /* Select */
        PreparedStatement consulta = con.prepareStatement("SELECT * FROM Usuarios where "+condicion);

        ResultSet rs = consulta.executeQuery();
            
        return rs;
        
    }
    
}
