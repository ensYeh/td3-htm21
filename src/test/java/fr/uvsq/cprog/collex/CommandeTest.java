package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.BeforeClass;
import org.junit.AfterClass;

public class CommandeTest {
	private Dns dns;

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
	public void testCmdNomIp_trouve() {
		Commande cmd = new CmdNomIp("www.uvsq.fr");
		String res = cmd.execute(dns);
		assertEquals("193.51.31.90\n", res);
	}

	@Test
	public void testCmdNomIp_introuvable() {
		Commande cmd = new CmdNomIp("abcd.uvsq.fr");
		String res = cmd.execute(dns);
		assertEquals("Pas d'adresse associé à abcd.uvsq.fr\n", res);
	}

	@Test
	public void testCmdIpNom_trouve() {
		Commande cmd = new CmdIpNom("193.51.31.90");
		String res = cmd.execute(dns);
		assertEquals("www.uvsq.fr\n", res);
	}

	@Test
	public void testCmdIpNom_introuvable() {
		Commande cmd = new CmdIpNom("10.10.10.10");
		String res = cmd.execute(dns);
		assertEquals("Pas de machine à l'adresse 10.10.10.10\n", res);
	}

	@Test
	public void testCmdLs_triParNom() {
        // Simulation du ls "standard"
		Commande cmd = new CmdLs("uvsq.fr", false);
		String attendu = "193.51.25.12 ecampus.uvsq.fr\n"+ "193.51.31.154 poste.uvsq.fr\n"+ "193.51.31.90 www.uvsq.fr\n";
		assertEquals(attendu, cmd.execute(dns));
	}

	@Test
	public void testCmdLs_triParIP() {
        // Simulation du ls -a
		Commande cmd = new CmdLs("uvsq.fr", true);
		String attendu = "193.51.25.12 ecampus.uvsq.fr\n"+ "193.51.31.90 www.uvsq.fr\n"+ "193.51.31.154 poste.uvsq.fr\n";
		assertEquals(attendu, cmd.execute(dns));
	}

	@Test
	public void testCmdAdd_ajoute() {
		Commande cmd = new CmdAdd("123.45.67.89", "test.uvsq.fr");
		cmd.execute(dns);
		DnsItem item = dns.getItem(new NomMachine("test.uvsq.fr"));
        // Si on a null, alors l'ajout ne s'est pas fait
		assertNotNull(item);
		assertEquals("123.45.67.89", item.getAdresse());
	}

	@Test
	public void testCmdAdd_erreurs() {
		// Adresse déjà existante
		Commande cmd1 = new CmdAdd("193.51.31.90", "bdhbv.uvsq.fr");
		assertEquals("ERREUR : L'adresse IP existe déjà !\n", cmd1.execute(dns));
		// Nom déjà existant
		Commande cmd2 = new CmdAdd("9.9.9.9", "www.uvsq.fr");
		assertEquals("ERREUR : Le nom de machine existe déjà !\n", cmd2.execute(dns));
	}
}