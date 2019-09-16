package com.APC.Reserv;

public class HolderCanchasFavoritos {
    private String Favoritosnombre;
    private String Favoritosid;
    private String Favoritosvalor;
    private String Favoritosdireccion;
    private String Favoritosciudad;
    private String Favoritosdias;
    private String Favoritoshorario;
    private String Favoritosimg;

    public HolderCanchasFavoritos(String nombre, String id, String valor, String direccion, String ciudad, String dias, String horario, String imgcancha) {
        this.Favoritosnombre = nombre;
        this.Favoritosid = id;
        this.Favoritosvalor = valor;
        this.Favoritosdireccion = direccion;
        this.Favoritosciudad = ciudad;
        this.Favoritosdias = dias;
        this.Favoritoshorario = horario;
        this.Favoritosimg = imgcancha;
    }

    public String getNombre() {
        return Favoritosnombre;
    }

    public void setNombre(String nombre) {
        this.Favoritosnombre = nombre;
    }

    public String getId() {
        return Favoritosid;
    }

    public void setId(String id) {
        this.Favoritosid = id;
    }

    public String getValor() {
        return Favoritosvalor;
    }

    public void setValor(String valor) {
        this.Favoritosvalor = valor;
    }

    public String getDireccion() {
        return Favoritosdireccion;
    }

    public void setDireccion(String direccion) {
        this.Favoritosdireccion = direccion;
    }

    public String getCiudad() {
        return Favoritosciudad;
    }

    public void setCiudad(String ciudad) {
        this.Favoritosciudad = ciudad;
    }

    public String getDias() {
        return Favoritosdias;
    }

    public void setDias(String dias) {
        this.Favoritosdias = dias;
    }

    public String getHorario() {
        return Favoritoshorario;
    }

    public void setHorario(String horario) {
        this.Favoritoshorario = horario;
    }

    public String getImg() {
        return Favoritosimg;
    }

    public void setImgcancha(String imgcancha) {
        this.Favoritosimg = imgcancha;
    }
}
