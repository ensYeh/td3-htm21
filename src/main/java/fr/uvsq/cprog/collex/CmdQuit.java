package fr.uvsq.cprog.collex;

public class CmdQuit implements Commande {
    @Override
    public String execute(Dns dns) {
        return "fin du programme";
    }
}
