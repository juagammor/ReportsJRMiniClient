/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.juanra.aplicaciones.minijrclient.model;

/**
 *
 * @author juanra
 */
public interface Conexion {
    
    
    public String getDriver();
    public void setDriver(String driver);
    public String getUrl();
    public void setUrl(String url);
    public String getUser();
    public void setUser(String user);
    public String getPass();
    public void setPass(String pass);
    
    
}
