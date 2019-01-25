/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peliculas.utils;

import java.io.File;

/**
 *
 * @author mreyesi
 */

public class ComprobarFichero {
    
    public static boolean comprobarSiExiste(String path){
        
        File af = new File(path);
        
        return af.exists();
    }
    
    public static boolean comprobarSiEsUnFichero(String path){
        
        File af = new File(path);
        
        return af.isFile();
        
    }
    
}