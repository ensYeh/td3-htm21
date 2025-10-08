package fr.uvsq.cprog.collex;

public class NomMachine {
    private String nomComplet;
    private String nom;
    private String domaine;

    public NomMachine(String NomMachine) {
        this.nomComplet = NomMachine;
        String[] parties = NomMachine.split("\\.", 2);
        this.nom = parties[0];
        if (parties.length > 1) {
            this.domaine = parties[1];
        } else {
            this.domaine = "";
        }
    }

    // Getters
    public String getNomMachine() {
        return nomComplet;
    }

    public String getNom() {
        return nom;
    }

    public String getDomaine() {
        return domaine;
    }

}
