package fr.uvsq.cprog.collex;

public class DnsTUI {
    public Commande nextCommande(String input) {
        if (input == null) {
            return new CmdErreur("Entrée Invalide");
        }
        String ligne = input.trim();
        if (ligne.isEmpty()) {
            return new CmdErreur("Entrée Invalide");
        }
        String[] parts = ligne.split("\\s+");
        String cmd = parts[0];

        if (cmd.equals("quit")) {
            return new CmdQuit();

        } else if (cmd.equals("add")) {
            if (parts.length != 3) {
                return new CmdErreur("Commande invalide, format attendu : add <adr.es.se.ip> <nom.qualifié.machine>");
            }
            if (!parts[1].matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
                return new CmdErreur("Erreur : format attendu : add <adr.es.se.ip> <nom.qualifié.machine>");
            }
            return new CmdAdd(parts[1], parts[2]);

        } else if (cmd.equals("ls")) {
            if (parts.length == 3 && parts[1].equals("-a")) {
                return new CmdLs(parts[2], true);
            } else if (parts.length == 2) {
                return new CmdLs(parts[1], false);
            } else {
                return new CmdErreur("Commande invalide, format attendu : ls [-a] <domaine>");
            }
            
        } else if (parts.length == 1 && ligne.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            return new CmdIpNom(ligne);

        } else if (parts.length == 1 && ligne.contains(".")) {
            return new CmdNomIp(ligne);
        }

        return new CmdErreur("Commande invalide");
    }

    public void affiche(String resultat) {
        System.out.println(resultat);
    }
}