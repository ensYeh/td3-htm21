package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NomMachineTest {
    NomMachine nm;

    @Before
    public void creation_machine_test() {
        nm = new NomMachine("www.ahmadhatoum.fr");
    }

    @Test
    public void testGetDomaine() {
        assertEquals("ahmadhatoum.fr", nm.getDomaine());
    }

    @Test
    public void testGetNomComplet() {
        assertEquals("www.ahmadhatoum.fr", nm.getNomMachine());
    }

    @Test
    public void testGetNom() {
        assertEquals("www", nm.getNom());
    }
}
