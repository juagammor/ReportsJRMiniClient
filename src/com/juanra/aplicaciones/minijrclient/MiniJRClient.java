/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juanra.aplicaciones.minijrclient;


import com.juanra.aplicaciones.minijrclient.model.Conexion;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Clase que contiene la funcionalidad de llamada al MiniJRServer
 *
 * @author juanra
 */
public class MiniJRClient {

    private static final String RUTA_SERVER = "recursos/jrserver/MiniJRServer.jar";
    //constantes
    private static final String CHAR_ALMOHADILLA = "#";
    private static final String CHAR_ARROBA = "@";
    private static final String CHAR_VACIO = "";
    private static final String CHAR_PUNTO = ".";

    //
    private String rutaJRXML;
    private String rutaPDF;
    //
    private String driver;
    private String url;
    private String user;
    private String pass;
    private String claveCifrada;

    //
    private HashMap<String, Object> params;
    //
    private HashMap<String, Object> imagenes;

    public MiniJRClient() {
    }

    public String[] getCommand() {

        String[] lista = new String[12];

        lista[0] = "java";
        lista[1] = "-jar";
        lista[2] = RUTA_SERVER;

        lista[3] = "bbdd.url=" + url;
        lista[4] = "bbdd.user=" + user;
        lista[5] = "bbdd.pass=" + pass;
        lista[6] = "bbdd.claveCifrada=" + claveCifrada;
        lista[7] = "bbdd.driver=" + driver;
        lista[8] = "rutaPDF=" + rutaPDF;
        //rutaPDF = "/home/juanra/Escritorio/output.pdf";
        lista[9] = "rutaJRXML=" + rutaJRXML;
        lista[10] = "listaParametros=" + getParametrosString(params);
        lista[11] = "listaImagenes=" + getImagenesString(imagenes);

        //UtilEnvironment.runCommand(lista);
        return lista;
    }

    public void setConexion(Conexion conexion) {
        setDriver(conexion.getDriver());
        setUrl(conexion.getUrl());
        setUser(conexion.getUser());
        setPass(conexion.getPass());
    }

    private String getParametrosString(HashMap<String, Object> params) {

        String listaParametros = CHAR_VACIO;
        Set<String> claves = params.keySet();

        for (Iterator<String> iterator = claves.iterator(); iterator.hasNext();) {
            String clave = iterator.next();

            listaParametros += CHAR_ARROBA + params.get(clave).getClass().getName() + CHAR_ALMOHADILLA + clave + CHAR_ALMOHADILLA + params.get(clave);
        }

        return listaParametros.replaceFirst(CHAR_ARROBA, CHAR_VACIO);

    }

    private String getImagenesString(HashMap<String, Object> imagenes) {
        String listaImagenes = CHAR_VACIO;
        Set<String> keys = imagenes.keySet();

        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
            try {
                String key = iterator.next();
                String value = (String) imagenes.get(key);

                Path from = Paths.get(value);
                String extension = value.substring(value.lastIndexOf(CHAR_PUNTO) + 1);
                listaImagenes += CHAR_ARROBA + key + CHAR_ALMOHADILLA + extension;

                //System.out.println();
                Path to = Paths.get(new File(rutaPDF).getParent() + File.separator + key + CHAR_PUNTO + extension);
                Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return listaImagenes.replaceFirst(CHAR_ARROBA, CHAR_VACIO);
    }

    /*
     GETTERS AND SETTERS
     */
    public String getRutaJRXML() {
        return rutaJRXML;
    }

    public void setRutaJRXML(String rutaJRXML) {
        this.rutaJRXML = rutaJRXML;
    }

    public String getRutaPDF() {
        return rutaPDF;
    }

    public void setRutaPDF(String rutaPDF) {
        this.rutaPDF = rutaPDF;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getClaveCifrada() {
        return claveCifrada;
    }

    public void setClaveCifrada(String claveCifrada) {
        this.claveCifrada = claveCifrada;
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public HashMap<String, Object> getImagenes() {
        return imagenes;
    }

    public void setImagenes(HashMap<String, Object> imagenes) {
        this.imagenes = imagenes;
    }

}
