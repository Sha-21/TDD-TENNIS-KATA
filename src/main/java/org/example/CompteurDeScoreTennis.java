package org.example;

import lombok.AllArgsConstructor;
import org.example.entities.Joueur;
import org.example.entities.Partie;

@AllArgsConstructor
public class CompteurDeScoreTennis {

    private Partie partie;


    public void AjouterPoint(Joueur vainqueur)
    {
        if(!partie.getJoueurUn().isGagnant() && !partie.getJoueurDeux().isGagnant()) {
            if (vainqueur.getPoint() == 0) {
                vainqueur.setPoint(15);
            } else if (vainqueur.getPoint() == 15) {
                vainqueur.setPoint(30);
            } else if (vainqueur.getPoint() == 30) {
                vainqueur.setPoint(40);
            } else {
                Joueur perdant;
                if (vainqueur.equals(partie.getJoueurUn())) {
                    perdant = partie.getJoueurDeux();
                } else {
                    perdant = partie.getJoueurUn();
                }

                if (perdant.getPoint() == 40 && !vainqueur.getStatut().equals("A")) {
                    vainqueur.setStatut("A");
                } else if (perdant.getStatut().equals("A")) {
                    perdant.setPoint(40);
                    perdant.setStatut("EGALITE");
                    vainqueur.setPoint(40);
                    vainqueur.setStatut("EGALITE");
                } else {
                    AjouterJeu(vainqueur);
                }
            }
        }
    }
    public void AjouterJeu(Joueur vainqueur)
    {
        vainqueur.setJeux(vainqueur.getJeux() + 1);
        reinitialiserPoints();
        if(vainqueur.getJeux() >= 6 && differenceJeux() >= 2)
        {
            AjouterSet(vainqueur);
        }
        else if (partie.getJoueurUn().getJeux() == 6 && partie.getJoueurDeux().getJeux() == 6)
        {
            JeuxDecisif();
        }
    }
    public void JeuxDecisif()
    {
        partie.getJoueurUn().setJeux(0);
        partie.getJoueurDeux().setJeux(0);
        partie.getJoueurUn().setPoint(0);
        partie.getJoueurDeux().setPoint(0);
        partie.getJoueurUn().setStatut("EN JEU DECISIF");
        partie.getJoueurDeux().setStatut("EN JEU DECISIF");
    }

    public void AjouterPointDecisif(Joueur vainqueur)
    {
        vainqueur.setPointJeuDecisif(vainqueur.getPointJeuDecisif()+1);
        if(vainqueur.getPointJeuDecisif() >= 7 && differencePointsJeuDecisif()>=2)
        {
            AjouterSet(vainqueur);
            reinitialiserPoints();
        }
    }

    public void AjouterSet(Joueur vainqueur)
    {
        vainqueur.setSet(vainqueur.getSet()+1);
        if(vainqueur.getSet()==2)
        {
            vainqueur.setStatut("GAGNER");
            vainqueur.setGagnant(true);
        }
        else {
            partie.getJoueurUn().setJeux(0);
            partie.getJoueurDeux().setJeux(0);
            reinitialiserPoints();
        }
    }
    public void reinitialiserPoints()
    {
        partie.getJoueurUn().setPoint(0);
        partie.getJoueurDeux().setPoint(0);
        partie.getJoueurUn().setPointJeuDecisif(0);
        partie.getJoueurDeux().setPointJeuDecisif(0);
        partie.getJoueurUn().setStatut("PAUSE BOISSON");
        partie.getJoueurDeux().setStatut("PAUSE BOISSON");
    }

    public void displayScore()
    {
        System.out.println(partie.getJoueurUn().getPoint());
        System.out.println(partie.getJoueurUn().getJeux());
        System.out.println(partie.getJoueurUn().getPointJeuDecisif());
        System.out.println(partie.getJoueurUn().getSet());
        System.out.println(partie.getJoueurUn().getStatut());
        System.out.println(partie.getJoueurDeux().getPoint());
        System.out.println(partie.getJoueurDeux().getJeux());
        System.out.println(partie.getJoueurDeux().getPointJeuDecisif());
        System.out.println(partie.getJoueurDeux().getSet());
        System.out.println(partie.getJoueurDeux().getStatut());
    }
    public int differenceJeux() {
        return Math.abs(partie.getJoueurDeux().getJeux() - partie.getJoueurUn().getJeux());
    }

    public int differencePointsJeuDecisif()
    {
        return Math.abs(partie.getJoueurDeux().getPointJeuDecisif() - partie.getJoueurUn().getPointJeuDecisif());
    }
}
