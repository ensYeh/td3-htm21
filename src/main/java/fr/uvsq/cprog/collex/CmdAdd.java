package fr.uvsq.cprog.collex;

public class CmdAdd implements Commande {
    private String ip;
    private String nqmachine;

    public CmdAdd(String ip, String nqmachine) {
        this.ip = ip;
        this.nqmachine = nqmachine;
    }

    @Override
    public String execute(Dns dns) {
        String res = dns.addItem(new AdresseIP(ip), new NomMachine(nqmachine));
        return res;
    }
}
