package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Partie {

    private Joueur joueurUn;
    private Joueur joueurDeux;

}
