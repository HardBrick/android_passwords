package com.android.joseg.android_claves.Modelo;

import java.io.Serializable;

public class Password implements Serializable {

    private String titulo;
    private String url;
    private String user;
    private String pass;
    private String observaciones;
    private String userDueno;

    public Password(String titulo, String url, String user, String pass, String observaciones, String userDueno) {
        this.titulo = titulo;
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.observaciones = observaciones;
        this.userDueno = userDueno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getUserDueno() {
        return userDueno;
    }

    public void setUserDueno(String userDueno) {
        this.userDueno = userDueno;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
