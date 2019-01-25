package com.peliculas.utils;

/**
 *
 * @author mreyesi
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Directorio {
    
    public ArrayList<String> listaDirectorios(String path) throws IOException{
        
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
    
}