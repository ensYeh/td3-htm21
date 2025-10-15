package fr.uvsq.cprog.collex;
import java.util.List;

public class CmdLs implements Commande {
    private String domaine;
    private boolean tri;

    public CmdLs(String domaine, boolean tri) {
        this.domaine = domaine;
        this.tri = tri;
    }

    @Override
    public String execute(Dns dns) {
        List<DnsItem> liste_items = dns.getItems(domaine);

        if (tri) {
            liste_items.sort((a, b) -> a.getAdresse().compareTo(b.getAdresse()));
        } else {
            liste_items.sort((a, b) -> a.getNom().compareTo(b.getNom()));
        }

        String resultat = "";
        for (DnsItem couple : liste_items) {
            resultat += couple.getAdresse() + " " + couple.getNom() + "\n";
        }
        return resultat;
    }
}