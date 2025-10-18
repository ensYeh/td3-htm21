package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DnsTest {
    Dns dns;

    // Générer à l'aide de ChatGPT (le but est de ne pas empiéter sur les autres tests à cause des modifications)
    @BeforeClass
    public static void reset_avant_test() throws IOException {
        String contenu = String.join("\n",
            "ecampus.uvsq.fr 193.51.25.12",
            "poste.uvsq.fr 193.51.31.154",
            "www.uvsq.fr 193.51.31.90"
        );
        Files.writeString(Path.of("src/main/resources/dns.txt"), contenu);
    }

    // Générer à l'aide de ChatGPT
    @AfterClass
    public static void reset_apres_test() throws IOException {
        String contenu = String.join("\n",
            "ecampus.uvsq.fr 193.51.25.12",
            "poste.uvsq.fr 193.51.31.154",
            "www.uvsq.fr 193.51.31.90"
        );
        Files.writeString(Path.of("src/main/resources/dns.txt"), contenu);
    }

    @Before
    public void creation_dns() throws IOException {
        dns = new Dns();
    }

    @Test
    public void testGetItemNomMachine() {
        NomMachine nom_machine = new NomMachine("www.uvsq.fr");
        DnsItem res = dns.getItem(nom_machine);
        // Si on a "null" alors on a pas trouvé d'AdresseIP correspondante au nom de machine 'www.uvsq.fr'
        assertNotNull(res);
    }

    @Test
    public void testGetItemAdresseIP() {
        AdresseIP adresse_ip = new AdresseIP("193.51.31.90");
        DnsItem res = dns.getItem(adresse_ip);
        // Si on a "null" alors on a pas trouvé de nom de machine correspondante à l'AdresseIP '193.51.31.90'
        assertNotNull(res);
    }
    
    @Test
    public void testGetItemNomDomaine() {
        List<DnsItem> res = dns.getItems("uvsq.fr");
        assertEquals(3, res.size());
    }

    @Test
    public void testAddItem() {
        AdresseIP adresse_ip = new AdresseIP("123.45.67.89");
        NomMachine nom_machine = new NomMachine("www.ahmadhatoum.uvsq");
        dns.addItem(adresse_ip, nom_machine);
        DnsItem res = dns.getItem(nom_machine);
        // Si on a "null" alors addItem() n'a pas ajouté notre 'couple' : 123.45.67.89 - www.ahmadhatoum.uvsq
        assertNotNull(res);
    }
}