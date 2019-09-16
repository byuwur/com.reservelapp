package com.APC.Reserv;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class HolderTest {
    private HolderCanchasReservar holderCanchasReservar;
    private HolderCanchasReservas holderCanchasReservas;
    private HolderCanchasFavoritos holderCanchasFavoritos;

    @Before
    public void setUp() throws Exception{
        holderCanchasReservar = new HolderCanchasReservar("nombre","aaaaaa", "0",
                "dir", "ciudad", "dias", "horario", "url://");
        holderCanchasReservas = new HolderCanchasReservas("nombre","aaaaaa", "dir",
                "ciudad", "3", "dia", "hora", "url://");
        holderCanchasFavoritos = new HolderCanchasFavoritos("nombre","aaaaaa", "0",
                "dir", "ciudad", "dias", "horario", "url://");
    }

    @Test
    public void testHolderReservar() throws Exception {
        assertEquals(holderCanchasReservar.getNombre(), "nombre");
        assertEquals(holderCanchasReservar.getId(), "aaaaaa");
        assertEquals(holderCanchasReservar.getValor(), "0");
        assertEquals(holderCanchasReservar.getDireccion(), "dir");
        assertEquals(holderCanchasReservar.getCiudad(), "ciudad");
        assertEquals(holderCanchasReservar.getDias(), "dias");
        assertEquals(holderCanchasReservar.getHorario(), "horario");
        assertEquals(holderCanchasReservar.getImg(), "url://");
    }

    @Test
    public void testHolderReservas() throws Exception {
        assertEquals(holderCanchasReservas.getNombre(), "nombre");
        assertEquals(holderCanchasReservas.getIdC(), "aaaaaa");
        assertEquals(holderCanchasReservas.getDireccion(), "dir");
        assertEquals(holderCanchasReservas.getCiudad(), "ciudad");
        assertEquals(holderCanchasReservas.getIdR(), "3");
        assertEquals(holderCanchasReservas.getDia(), "dia");
        assertEquals(holderCanchasReservas.getHora(), "hora");
        assertEquals(holderCanchasReservas.getImg(), "url://");
    }

    @Test
    public void testHolderFavoritos() throws Exception {
        assertEquals(holderCanchasFavoritos.getNombre(), "nombre");
        assertEquals(holderCanchasFavoritos.getId(), "aaaaaa");
        assertEquals(holderCanchasFavoritos.getValor(), "0");
        assertEquals(holderCanchasFavoritos.getDireccion(), "dir");
        assertEquals(holderCanchasFavoritos.getCiudad(), "ciudad");
        assertEquals(holderCanchasFavoritos.getDias(), "dias");
        assertEquals(holderCanchasFavoritos.getHorario(), "horario");
        assertEquals(holderCanchasFavoritos.getImg(), "url://");
    }

    @After
    public void tearDown() throws Exception {
        holderCanchasReservar = null;
        holderCanchasReservas = null;
        holderCanchasFavoritos = null;
    }
}
