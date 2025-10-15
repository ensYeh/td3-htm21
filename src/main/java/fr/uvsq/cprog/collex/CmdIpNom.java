package fr.uvsq.cprog.collex;

public class CmdIpNom implements Commande {
    private String ip;

    public CmdIpNom(String ip) {
        this.ip = ip;
    }

    @Override
    public String execute(Dns dns) {
        DnsItem resultat = dns.getItem(new AdresseIP(ip));
        if (resultat == null) {
            return "Pas de machine à l'adresse " + ip;
        }
        return resultat.getNom();
    }
}
