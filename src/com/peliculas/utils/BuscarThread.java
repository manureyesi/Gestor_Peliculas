package com.peliculas.utils;

import com.peliculas.exception.ErrorPrograma;
import com.peliculas.main.Gestor_Peliculas;
import java.io.File;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author mreyesi
 */
public class BuscarThread extends Thread {
    
    private final static Logger log = Logger.getLogger(BuscarThread.class);
    
    private final String arch;
    private final String path;
    
    public BuscarThread(String archivo, String path){
        this.arch = archivo;
        this.path = path;
    }
    
    @Override
    public void run(){
        
        long s = System.currentTimeMillis();
        
        BuscarInfoPelicula buscar = new BuscarInfoPelicula();
        Directorio dir = new Directorio();
        
        
        
        log.error("Pelicula: "+arch+" Tiempo: " + (System.currentTimeMillis() - s) + " ms. ");
        
    }
}
