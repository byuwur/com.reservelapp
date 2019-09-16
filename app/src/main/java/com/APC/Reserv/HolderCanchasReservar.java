package com.APC.Reserv;

public class HolderCanchasReservar {
    private String reservarnombre;
    private String reservarid;
    private String reservarvalor;
    private String reservardireccion;
    private String reservarciudad;
    private String reservardias;
    private String reservarhorario;
    private String reservarimg;

    public HolderCanchasReservar(String nombre, String id, String valor, String direccion, String ciudad, String dias, String horario, String imgcancha) {
        this.reservarnombre = nombre;
        this.reservarid = id;
        this.reservarvalor = valor;
        this.reservardireccion = direccion;
        this.reservarciudad = ciudad;
        this.reservardias = dias;
        this.reservarhorario = horario;
        this.reservarimg = imgcancha;
    }

    public String getNombre() {
        return reservarnombre;
    }

    public void setNombre(String nombre) {
        this.reservarnombre = nombre;
    }

    public String getId() {
        return reservarid;
    }

    public void setId(String id) {
        this.reservarid = id;
    }

    public String getValor() {
        return reservarvalor;
    }

    public void setValor(String valor) {
        this.reservarvalor = valor;
    }

    public String getDireccion() {
        return reservardireccion;
    }

    public void setDireccion(String direccion) {
        this.reservardireccion = direccion;
    }

    public String getCiudad() {
        return reservarciudad;
    }

    public void setCiudad(String ciudad) {
        this.reservarciudad = ciudad;
    }

    public String getDias() {
        return reservardias;
    }

    public void setDias(String dias) {
        this.reservardias = dias;
    }

    public String getHorario() {
        return reservarhorario;
    }

    public void setHorario(String horario) {
        this.reservarhorario = horario;
    }

    public String getImg() {
        return reservarimg;
    }

    public void setImgcancha(String imgcancha) {
        this.reservarimg = imgcancha;
    }
}
