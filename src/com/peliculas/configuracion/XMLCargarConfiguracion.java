package com.peliculas.configuracion;

import com.peliculas.exception.CodigoError;
import com.peliculas.exception.ErrorPrograma;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author mreyesi
 */
public class XMLCargarConfiguracion {
   
    private final static Logger log = Logger.getLogger(XMLCargarConfiguracion.class);
    private final static String file = "datos.xml";
    
    private static final String user = cargarUser();
    private static final String pass = cargarPass();
    private static final String ip = cargarIp();
    private static final int port = cargarPort();
    private static final String db = cargarDb();
    private static final String path = cargarPath();
    
    private static Element cargarXML() throws ErrorPrograma{
        
        log.info("Cargando configuracion");
        NodeList listaConf = null;
        try{
            
            File archivo = new File(file);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            document.getDocumentElement().normalize();

            log.info("Lista de nodos preparada");
            listaConf = document.getElementsByTagName("datos");
        
        } catch(IOException | ParserConfigurationException | SAXException ex){
            log.warn("Error al leer XML: ", ex);
            throw new ErrorPrograma(CodigoError.ERROR_XML);
        }
        
        return (Element) listaConf.item(0);
        
    }
    
    private static String cargarUser() {
        String aux = "";
        try {
            aux = cargarXML().getElementsByTagName("user").item(0).getTextContent();
        } catch (ErrorPrograma ex) {
            log.error("Error al cargar Usuario de XML");
        }
        return aux;
    }
    
    private static String cargarPass() {
        String aux = "";
        try {
            aux = cargarXML().getElementsByTagName("pass").item(0).getTextContent();
        } catch (ErrorPrograma ex) {
            log.error("Error al cargar Password de XML");
        }
        return aux;
    }
    
    private static int cargarPort() {
        int aux = 0;
        try {
            aux = Integer.parseInt(cargarXML().getElementsByTagName("port").item(0).getTextContent());
        } catch (ErrorPrograma ex) {
            log.error("Error al cargar Port de XML");
        }
        return aux;
    }
    
    private static String cargarIp() {
        String aux = "";
        try {
            aux = cargarXML().getElementsByTagName("ip").item(0).getTextContent();
        } catch (ErrorPrograma ex) {
            log.error("Error al cargar IP de XML");
        }
        return aux;
    }
    
    private static String cargarDb() {
        String aux = "";
        try {
            aux = cargarXML().getElementsByTagName("db").item(0).getTextContent();
        } catch (ErrorPrograma ex) {
            log.error("Error al cargar Base de Datos de XML");
        }
        return aux;
    }
    
    private static String cargarPath() {
        String aux = "";
        try {
            aux = cargarXML().getElementsByTagName("path").item(0).getTextContent();
        } catch (ErrorPrograma ex) {
            log.error("Error al cargar Path de XML");
        }
        return aux;
    }
    
    public static String getUser() {
        return user;
    }

    public static String getPass() {
        return pass;
    }

    public static String getIp() {
        return ip;
    }

    public static int getPort() {
        return port;
    }

    public static String getDb() {
        return db;
    }
    
    public static String getPath() {
        return path;
    }

}