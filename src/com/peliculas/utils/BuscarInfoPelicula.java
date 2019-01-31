package com.peliculas.utils;

import com.peliculas.bd.AdministradorPeliculasDB;
import com.peliculas.clases.Peliculas;
import com.peliculas.exception.CodigoError;
import com.peliculas.exception.ErrorPrograma;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author mreyesi
 */
public class BuscarInfoPelicula {

    private final String url = "https://www.imdb.com/";
    
    private final static Logger log = Logger.getLogger(BuscarInfoPelicula.class);
    
    public boolean buscarInfoPeliculas(String arch) throws ErrorPrograma{
        Peliculas pelicula = null;
        
        pelicula = BuscarInfoPeliculaEnWeb(arch.substring(0,arch.lastIndexOf(".")), arch);
        log.info(arch.substring(0,arch.lastIndexOf(".")));
        
        return AdministradorPeliculasDB.subirPelicula(pelicula);
    }
    
    private Peliculas BuscarInfoPeliculaEnWeb(String pelicula, String urlPelicula) throws ErrorPrograma{
        
        String buscarIMDB = url+"find?q="+pelicula.replaceAll(" ", "+")+"&s=tt&ttype=ft&ref_=fn_ft";
        
        log.info("Url a buscar: "+buscarIMDB);
        
        Document document = null;
	
        document = getHtmlDocument(buscarIMDB);
        
        Elements elem = null;
        try{
            elem = document.select("tr.findResult");
        } catch(Exception ex){
            log.error("Error al buscar pelicula: "+ pelicula +", no se han encontrado resultados");
            throw new ErrorPrograma(CodigoError.ERROR_BUSCAR_PELICULAS);
        }
        
        Peliculas pel = BuscarDatosPelicula(url+elem.get(0).select("a").get(0).attr("href"));

        pel.setUrl_video(urlPelicula);
        
        log.info("Titulo: "+pel.getNombre());
        log.info("AÑO: "+pel.getAno());
        
        return pel;
    }
    
    private static Document getHtmlDocument(String url) throws ErrorPrograma {

        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            log.error("Error al buscar pelicula en Web: "+url);
            throw new ErrorPrograma(CodigoError.ERROR_SCRAPING);
        }
        return doc;
    }
    
    private ArrayList<String> BuscarGeneroPelicula(Elements elem){
        ArrayList<String> generos = new ArrayList<>();
        
        int cont = 0;
        boolean salir = false;
        while(!salir){
            if(elem.get(cont).attr("href").contains("title?genres=")){
                generos.add(elem.get(cont).text());
            }
            else{
                salir = true;
            }
            cont++;
        }
        
        return generos;
    }
    
    private Peliculas BuscarDatosPelicula(String urlPelicula) throws ErrorPrograma{
    
        Peliculas peli = null;
        
        ArrayList<String> generos = new ArrayList<>();
        
        Document document = null;
	
        document = getHtmlDocument(urlPelicula);
        
        Elements elem = document.select("div.titleBar");
        elem = elem.select("div.title_wrapper");
        
        String nombre = elem.select("h1").text();
        String ano = elem.select("span#titleYear").select("a").text();
        
        generos = BuscarGeneroPelicula(elem.select("div.subtext").select("a"));
        
        nombre = nombre.replace("("+ano+")", "");
        
        elem = document.select("div.slate_wrapper");
        
        String urlImg = elem.select("img").attr("src");
        
        elem = document.select("div.plot_summary");
        String director = elem.select("div.credit_summary_item").select("a").get(0).text();
      
        peli = new Peliculas(nombre, urlImg, generos, ano, director);
        
        return peli;
    }
    
}