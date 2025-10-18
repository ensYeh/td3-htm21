package fr.uvsq.cprog.collex;

import java.util.List;

public class CmdLs implements Commande {
    private String domaine;
    private boolean tri;

    public CmdLs(String domaine, boolean tri) {
        this.domaine = domaine;
        this.tri = tri;
    }

    // Implémentation d'une méthode équivalente à String.compareTo(String) mais appliquable aux IP dans le TD
    public static int compareIP(String ip1, String ip2) {
        String[] tab_ip1 = ip1.split("\\.");
        String[] tab_ip2 = ip2.split("\\.");

        int taille_ip1 = tab_ip1.length;
        int taille_ip2 = tab_ip2.length;

        if (taille_ip1 > taille_ip2) {
            return 1;
        } else if (taille_ip1 < taille_ip2) {
            return -1;
        }

        for (int i = 0; i < taille_ip1; i++) {
            int val_ip1 = Integer.parseInt(tab_ip1[i]);
            int val_ip2 = Integer.parseInt(tab_ip2[i]);
            if (val_ip1 > val_ip2) {
                return 1;
            } else if (val_ip1 < val_ip2) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String execute(Dns dns) {
        List<DnsItem> liste_items = dns.getItems(domaine);

        if (tri) {
            liste_items.sort((a, b) -> compareIP(a.getAdresse(), b.getAdresse()));
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