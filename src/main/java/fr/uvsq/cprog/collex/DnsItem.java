package fr.uvsq.cprog.collex;

public class DnsItem {
    private NomMachine nom;
    private AdresseIP adresse;

    public DnsItem(NomMachine nom, AdresseIP adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    // Getters
    public String getNom() {
        return nom.getNomMachine();
    }

    public String getAdresse() {
        return adresse.getAdresseIP();
    }

    public String getDns() {
        return getAdresse() + " " + getNom();
    }
}
