package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class DnsItemTest {
    DnsItem di;

    @Before
    public void creation_DnsItem() throws IOException {
        NomMachine machine = new NomMachine("www.ahmadhatoum.uvsq");
        AdresseIP adresse_ip = new AdresseIP("123.10.10.20");
        di = new DnsItem(machine, adresse_ip);
    }

    @Test
    public void testGetNom() {
        assertEquals("www.ahmadhatoum.uvsq", di.getNom());
    }

    @Test
    public void testGetAdresse() {
        assertEquals("123.10.10.20", di.getAdresse());
    }

    @Test
    public void testGetDns() {
        assertEquals("123.10.10.20 www.ahmadhatoum.uvsq", di.getDns());
    }
}