package com.peliculas.utils;

import com.peliculas.clases.Peliculas;
import com.peliculas.exception.CodigoError;
import com.peliculas.exception.ErrorPrograma;
import java.io.IOException;
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
    
    public String BuscarInfoPelicula(String pelicula) throws ErrorPrograma{
        
        String buscarIMDB = url+"find?ref_=nv_sr_fn&q="+pelicula.replaceAll(" ", "+")+"&s=all";
        
        log.info("Url a buscar: "+buscarIMDB);
        
        Document document = null;
	
        document = getHtmlDocument(buscarIMDB);
        
        Elements elem = document.select("tr.findResult");
        
        Peliculas pel = BuscarDatosPelicula(url+elem.get(0).select("a").get(0).attr("href"));
        
        log.info("Titulo: "+pel.getNombre());
        log.info("AÑO: "+pel.getAno());
        
        return "";
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
    
    private Peliculas BuscarDatosPelicula(String urlPelicula) throws ErrorPrograma{
    
        Peliculas peli = null;
        
        Document document = null;
	
        document = getHtmlDocument(urlPelicula);
        
        Elements elem = document.select("div.titleBar");
        elem = elem.select("div.title_wrapper");
        
        String nombre = elem.select("h1").text();
        String ano = elem.select("span#titleYear").select("a").text();
        
        elem.select("div.subtext").select("a");
        String generos = "";
        nombre = nombre.replace("("+ano+")", "");
        
        peli = new Peliculas(nombre, 0, ano);
        
        return peli;
    }
}
