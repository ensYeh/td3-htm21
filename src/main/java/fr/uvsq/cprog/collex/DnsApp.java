package fr.uvsq.cprog.collex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DnsApp {
    public static void run() {
        Dns dns;
        try {
            dns = new Dns();
        } catch (IOException erreur) {
            System.out.println("ERREUR: " + erreur.getMessage());
            return; // Impossible de continuer sans DNS chargé
        }
        DnsTUI interaction_utilisateur = new DnsTUI();
        // https://stackoverflow.com/questions/5287538/how-to-get-the-user-input-in-java
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String entree_utilisateur;

        while (true) {
            System.out.print("> ");
            try {
                entree_utilisateur = br.readLine();
            } catch (IOException erreur) {
                System.out.println("ERREUR: " + erreur.getMessage());
                continue;
            }

            if ("quit".equals(entree_utilisateur)) {
                break;
            }
            Commande commande = interaction_utilisateur.nextCommande(entree_utilisateur);
            String resultat = commande.execute(dns);
            interaction_utilisateur.affiche(resultat);
        }
    }

    public static void main(String[] args) throws IOException {
        clearConsole();
        run();
    }

    // Pour rendre l'affichage dans le terminal plus propre, on le "clean", équivalent au print('\033c') en Python
    public static void clearConsole() {
        System.out.print("\033\143");
    }
}