package org.example.entities;

import lombok.Data;

@Data
public class Joueur {
    private String nom;
    private int point;
    private int jeux;
    private int pointJeuDecisif;
    private int set;

    private String statut;

    private boolean gagnant;

    public Joueur(String nom)
    {
        point = 0;
        jeux = 0;
        set = 0;
        pointJeuDecisif = 0;
        gagnant = false;
        statut = "AU VESTIAIRE";
    }
}
