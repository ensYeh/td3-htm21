package fr.uvsq.cprog.collex;

public class CmdNomIp implements Commande {
    private String nom;

    public CmdNomIp(String nom) {
        this.nom = nom;
    }

    @Override
    public String execute(Dns dns) {
        DnsItem resultat = dns.getItem(new NomMachine(nom));
        if (resultat == null) {
            return "Pas d'adresse associé à " + nom +"\n";
        }
        return resultat.getAdresse() + "\n";
    }
}
