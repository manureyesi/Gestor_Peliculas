package com.peliculas.bd;

import com.peliculas.configuracion.XMLCargarConfiguracion;
import com.peliculas.exception.CodigoError;
import com.peliculas.exception.ErrorPrograma;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author mreyesi
 */
public class Conexion {
    
    private static Logger log = Logger.getLogger(Conexion.class);
    
    private static Connection cn = null;
    
    public Conexion() throws ErrorPrograma{
        crearConexion();
    }
    
    private void crearConexion() throws ErrorPrograma{
        String url = "jdbc:mysql://"+XMLCargarConfiguracion.getIp()+":"+XMLCargarConfiguracion.getPort()+"/"+XMLCargarConfiguracion.getDb();
        
        try {
            cn = DriverManager.getConnection(url,XMLCargarConfiguracion.getUser(),XMLCargarConfiguracion.getPass());
            log.info("Conexion creada correctamente");
        } catch (SQLException ex) {
            log.warn("Error al montar conexion MySQL", ex);
            throw new ErrorPrograma(CodigoError.ERROR_CONEXION);
        }
    }
    
    public Connection getCn() {
        return cn;
    }
    
}
