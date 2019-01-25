package com.peliculas.configuracion;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author mreyesi
 */
public class XMLCargarConfiguracion {
   
    private final static Logger log = Logger.getLogger(XMLCargarConfiguracion.class);
    private final static String file = "datos.xml";
    
    private static String user;
    private static String pass;
    private static String ip;
    private static int port;
    private static String db;
    private static String path;
    
    
    public XMLCargarConfiguracion() throws ParserConfigurationException, SAXException, IOException{
        cargarConfiguracion();
    }
    
    private void cargarConfiguracion() throws ParserConfigurationException, SAXException, IOException {
                
        log.info("Cargando configuracion");

        File archivo = new File(file);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(archivo);
        document.getDocumentElement().normalize();

        log.info("Lista de nodos preparada");
        NodeList listaConf = document.getElementsByTagName("datos");

        Node nodo = listaConf.item(0);
        Element element = (Element) nodo;

        user = element.getElementsByTagName("user").item(0).getTextContent();
        pass = element.getElementsByTagName("pass").item(0).getTextContent();
        port = Integer.parseInt(element.getElementsByTagName("port").item(0).getTextContent());
        ip = element.getElementsByTagName("ip").item(0).getTextContent();
        db = element.getElementsByTagName("db").item(0).getTextContent();
        
        path = element.getElementsByTagName("path").item(0).getTextContent();
        
        
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getDb() {
        return db;
    }
    
    public String getPath() {
        return path;
    }

}