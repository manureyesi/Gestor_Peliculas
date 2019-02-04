package com.peliculas.utils;

/**
 *
 * @author mreyesi
 */

import com.peliculas.exception.CodigoError;
import com.peliculas.exception.ErrorPrograma;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    public ArrayList<String> listaDirectoriosNuevo(String path) throws ErrorPrograma{
        
        log.info("Listando archivos en Array");
        
        ArrayList<String> lista = new ArrayList<>();
        
        File file = new File(path);
        
        String[] ficheros = file.list();
        
        for(int i = 0; i < ficheros.length; i++){
            if(!ficheros[i].contains("$") && !ficheros[i].contains("00 - Peliculas Actualizar")){
                lista.add(ficheros[i]);         
            }
        }
        
        if(lista.isEmpty()){
            log.error("Error al listar archivos");
            throw new ErrorPrograma(CodigoError.ERROR_ARCHIVOS);
        }
        
        return lista;
    }
    
    public ArrayList<String> formatearRutaFicheros(ArrayList<String> lista, String path){
    
        log.info("Formateando Array");
        
        ArrayList<String> listaAux = new ArrayList<>();
        
        for(String archivo: lista){
            listaAux.add(formatearFichero(archivo,path));
        }
        
        return listaAux;        
    }
    
    public String formatearFichero(String nombre, String path){
        return nombre.replace(path, "");
    }
    
}