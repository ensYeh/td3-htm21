package fr.uvsq.cprog.collex;

public class CmdErreur implements Commande {
    private String message;

    public CmdErreur(String message) {
        this.message = message;
    }

    @Override 
    public String execute(Dns dns) {
        return "Erreur: " + message + "\n";
    } 
}