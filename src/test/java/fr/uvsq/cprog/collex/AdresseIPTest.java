package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class AdresseIPTest {
    AdresseIP adresse_ip;

    @Before
    public void creation_AdresseIP() throws IOException {
        adresse_ip = new AdresseIP("192.10.10.20");
    }

    @Test
    public void testGetAdresseIP() {
        assertEquals("192.10.10.20", adresse_ip.getAdresseIP());
    }
}