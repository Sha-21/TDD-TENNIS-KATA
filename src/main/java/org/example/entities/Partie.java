package org.example.entities;

import lombok.Data;

@Data
public class Partie {

    private Joueur joueurUn;
    private Joueur joueurDeux;

    public Partie(Joueur joueurUn, Joueur joueurDeux) {
        if(joueurUn.getNom() == null || joueurDeux.getNom() == null)
        {
            throw new NullPointerException("Le nom d'un joueur ne peut pas être null");
        }
        else if(joueurUn.getNom().isBlank() || joueurDeux.getNom().isBlank())
        {
            throw new IllegalArgumentException("Le nom d'un joueur ne peut pas être vide");
        }
        else
        {
            this.joueurUn = joueurUn;
            this.joueurDeux = joueurDeux;
        }
    }
}
