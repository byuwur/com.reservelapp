package com.APC.Reserv;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class AdapterTest {
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
    public void testAdapterReservar() throws Exception {

    }

    @Test
    public void testAdapterReservas() throws Exception {

    }

    @Test
    public void testAdapterFavoritos() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        holderCanchasReservar = null;
        holderCanchasReservas = null;
        holderCanchasFavoritos = null;
    }
}
