package com.peliculas.utils;

/**
 *
 * @author mreyesi
 */

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class Directorio {
    
    private final static Logger log = Logger.getLogger(Directorio.class);
    
    public ArrayList<String> listaDirectorios(String path) throws IOException{
        
        log.info("Listando archivos en Array");
        
        ArrayList<String> lista = new ArrayList();
        
        Files.walk(Paths.get(path)).forEach(ruta-> {
            if (Files.isRegularFile(ruta)) {
                if(ComprobarFichero.comprobarSiEsUnFichero(ruta.toString())){
                    lista.add(ruta.toString());
                }
            }
        });
        
        return lista;
    }
    
    public ArrayList<String> formatearRutaFicheros(ArrayList<String> lista, String path){
    
        log.info("Formateando Array");
        
        ArrayList<String> listaAux = new ArrayList<>();
        
        for(String archivo: lista){
            
            listaAux.add(archivo.replace(path, ""));
            
        }
        
        return listaAux;        
    }
    
}