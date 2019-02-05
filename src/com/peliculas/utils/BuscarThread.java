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
        
        log.info("Preparandose para buscar info de pelicula: "+ arch);
        if(!arch.contains(".")){
            File file = new File(path+"\\"+arch);
            if(file.isDirectory()){
                try {
                    for(String archAux: dir.formatearRutaFicheros(dir.listaDirectoriosNuevo(path+"\\"+arch), path+"\\"+arch)){
                        try{
                            buscar.buscarInfoPeliculas(archAux);
                        } catch(ErrorPrograma ex){
                            log.error(ex.toString());
                        }
                    }
                } catch (ErrorPrograma ex) {
                    log.error(ex.toString());
                }
            }
        } else{
            try{
                buscar.buscarInfoPeliculas(arch);
            } catch(ErrorPrograma ex){
                log.error(ex.toString());
            }
        }
        
        log.error("Pelicula: "+arch+" Tiempo: " + (System.currentTimeMillis() - s) + " ms. ");
        
    }
}
