package fr.uvsq.cprog.collex;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Dns {
    private String fichier;
    private List<DnsItem> items = new ArrayList<>();

    public Dns() throws IOException {
        // Méthode sur Properties issue de ChatGPT
        Properties p = new Properties();
        p.load(Files.newBufferedReader(Path.of("src/main/resources/config.properties")));
        fichier = p.getProperty("fichier.dns");

        Path path = Path.of(fichier);
        if (!Files.exists(path)) return;

        for (String ligne : Files.readAllLines(path)) {
            if (ligne.length() == 0) {
                continue;
            }
            String[] parts = ligne.split(" ");
            if (parts.length == 2) {
                items.add(new DnsItem(new NomMachine(parts[0]), new AdresseIP(parts[1])));
            }
        }
    }

    public void addItem(AdresseIP adresse, NomMachine nom) {
        if (getItem(nom) != null) {
            throw new IllegalArgumentException("Le nom de machine existe déjà : " + nom.getNomMachine());
        }
        if (getItem(adresse) != null) {
            throw new IllegalArgumentException("L'adresse IP existe déjà : " + adresse.getAdresseIP());
        }
        DnsItem nouveau_item = new DnsItem(nom, adresse);
        items.add(nouveau_item);
    }

    // Getters
    public List<DnsItem> getItems() {
        return items;
    }

    // Méthode écrite avec l'aide de ChatGPT pour permettre de filtrer les addresses en fonction du nom de la machine/domaine
    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> res = new ArrayList<>();
        for (DnsItem element : items) {
            NomMachine nm = new NomMachine(element.getNom());
            if (nm.getDomaine().equals(domaine)) {
                res.add(element);
            }
        }
        return res;
    }

    public DnsItem getItem(NomMachine nom) {
        for (DnsItem item : items) {
            if (item.getNom().equals(nom.getNomMachine())) {
                return item;
            }
        }
        return null; 
    }

    public DnsItem getItem(AdresseIP adresse) {
        for (DnsItem item : items) {
            if (item.getAdresse().equals(adresse.getAdresseIP())) {
                return item;
            }
        }
        return null; 
    }
}