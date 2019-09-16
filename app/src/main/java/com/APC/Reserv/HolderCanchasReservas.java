package com.APC.Reserv;

public class HolderCanchasReservas {
    private String reservasnombre;
    private String reservasidc;
    private String reservasdireccion;
    private String reservasciudad;
    private String reservasidr;
    private String reservasdia;
    private String reservashora;
    private String reservasimg;

    public HolderCanchasReservas(String nombre, String idc, String direccion, String ciudad, String idr, String dia, String hora, String imgcancha) {
        this.reservasnombre = nombre;
        this.reservasidc = idc;
        this.reservasdireccion = direccion;
        this.reservasciudad = ciudad;
        this.reservasidr = idr;
        this.reservasdia = dia;
        this.reservashora = hora;
        this.reservasimg = imgcancha;
    }

    public String getNombre() {
        return reservasnombre;
    }

    public void setNombre(String nombre) {
        this.reservasnombre = nombre;
    }

    public String getIdC() {
        return reservasidc;
    }

    public void setIdC(String idc) {
        this.reservasidc = idc;
    }

    public String getDireccion() {
        return reservasdireccion;
    }

    public void setDireccion(String direccion) {
        this.reservasdireccion = direccion;
    }

    public String getCiudad() {
        return reservasciudad;
    }

    public void setCiudad(String ciudad) {
        this.reservasciudad = ciudad;
    }

    public String getIdR() {
        return reservasidr;
    }

    public void setIdR(String idr) {
        this.reservasidr = idr;
    }

    public String getDia() {
        return reservasdia;
    }

    public void setDia(String dia) {
        this.reservasdia = dia;
    }

    public String getHora() {
        return reservashora;
    }

    public void setHora(String hora) {
        this.reservashora = hora;
    }

    public String getImg() {
        return reservasimg;
    }

    public void setImgcancha(String imgcancha) {
        this.reservasimg = imgcancha;
    }
}