package com.peliculas.main;

import com.peliculas.configuracion.XMLCargarConfiguracion;
import com.peliculas.exception.ErrorPrograma;
import com.peliculas.utils.BuscarInfoPelicula;
import com.peliculas.utils.Directorio;
import java.io.File;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author mreyesi
 */
public class Gestor_Peliculas {

    private final static Logger log = Logger.getLogger(Gestor_Peliculas.class);
        
    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        
        int estadisticaContadorPeliculas = 0;
        int estadisticasContadorPeliculasCorrectas = 0;
        ArrayList<String> estadisticasControladorFallos = new ArrayList<>();
        
        try{
            log.info("Inicio de Programa");
            Directorio dir = new Directorio();
            ArrayList<String> lista;
            
            lista = dir.formatearRutaFicheros(dir.listaDirectoriosNuevo(XMLCargarConfiguracion.getPath()), XMLCargarConfiguracion.getPath());
            
            log.info("Gestionando busqueda de "+ lista.size() +" peliculas");
            
            BuscarInfoPelicula buscar = new BuscarInfoPelicula();
                        
            int cont = 0;
            
            String path = XMLCargarConfiguracion.getPath().contains("\\")?XMLCargarConfiguracion.getPath()+"\\":XMLCargarConfiguracion.getPath()+"/";
            
            for(String arch: lista){
                log.info(arch.contains(".")?"Preparandose para buscar info de pelicula: "+ arch.substring(0,arch.lastIndexOf(".")):"Preparandose para buscar info de Saga: "+arch);
                if(!arch.contains(".")){
                    File file = new File(path+arch);
                    if(file.isDirectory()){
                        for(String archAux: dir.formatearRutaFicheros(dir.listaDirectoriosNuevo(path+arch), path+arch)){
                            try{
                                estadisticaContadorPeliculas++;
                                buscar.buscarInfoPeliculas(archAux);
                                estadisticasContadorPeliculasCorrectas++;
                            } catch(ErrorPrograma ex){
                                estadisticasControladorFallos.add(ex.toString()+" -> "+archAux.substring(0,archAux.lastIndexOf(".")));
                                log.error(ex.toString());
                            }
                        }
                    }
                } else{
                    try{
                        estadisticaContadorPeliculas++;
                        buscar.buscarInfoPeliculas(arch);
                        estadisticasContadorPeliculasCorrectas++;
                    } catch(ErrorPrograma ex){
                        estadisticasControladorFallos.add(ex.toString()+" -> "+arch.substring(0,arch.lastIndexOf(".")));
                        log.error(ex.toString());
                    }
                }              
            }
            
            log.info("*****************************************************************************");
            log.info("ESTADISTICAS:");
            log.info("Estadistica peliculas listadas: "+ estadisticaContadorPeliculas);
            log.info("Estadisticas peliculas insertadas en DB correctamente: "+ estadisticasContadorPeliculasCorrectas);
            log.info("Estadisticas peliculas erroneas: ");
            estadisticasControladorFallos.forEach((String estadis) -> {
                log.info(estadis);
            });
            
        } catch(ErrorPrograma ex){
            log.error(ex.toString());
        } catch(Exception ex){
            log.error("Error Generico", ex);
        }
    }
}