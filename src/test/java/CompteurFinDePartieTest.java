import org.example.CompteurDeScoreTennis;
import org.example.entities.Joueur;
import org.example.entities.Partie;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CompteurFinDePartieTest {

    Joueur joueurUn = new Joueur("Kevin Monac");
    Joueur joueurDeux = new Joueur("Shahin Biramane");
    Partie partie = new Partie(joueurUn,joueurDeux);
    CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);


    @Test
    @DisplayName("Le premier joueur a 2 sets gagnés gagne la partie")
    public void testVictoire2Sets()
    {
        joueurUn.setSet(1);
        cpt.AjouterSet(joueurUn);
        assertEquals("GAGNER", partie.getJoueurUn().getStatut());
        assertTrue(partie.getJoueurUn().isGagnant());
    }
    @Test
    @DisplayName("Quand un joueur a gagné, il n'est plus possible de changer les scores")
    public void testBlocageScore()
    {
        joueurUn.setGagnant(true);
        cpt.AjouterPoint(joueurUn);
        assertEquals(0, partie.getJoueurUn().getPoint());
        assertEquals(0, partie.getJoueurUn().getJeux());
        assertEquals(0, partie.getJoueurUn().getPointJeuDecisif());
        assertEquals(0, partie.getJoueurUn().getSet());
    }

    @Test
    @DisplayName("L'utilisateur doit être avertie que la partie est finie")
    public void testPartieFinie() {
        joueurUn.setSet(1);
        cpt.AjouterSet(joueurUn);
        assertEquals( "GAGNER", joueurUn.getStatut());
    }
}
