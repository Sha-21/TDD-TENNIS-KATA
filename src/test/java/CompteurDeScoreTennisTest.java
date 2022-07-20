import org.example.CompteurDeScoreTennis;
import org.example.entities.Joueur;
import org.example.entities.Partie;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;

public class CompteurDeScoreTennisTest {
    Joueur joueurUn = new Joueur("Kevin Monac");
    Joueur joueurDeux = new Joueur("Shahin Biramane");
    Partie partie = new Partie(joueurUn,joueurDeux);
    CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);

    @Test
    @DisplayName("Un utilisateur peut notifier qu'un joueur a gagné un point")
    public void testGagneUnPoint()
    {
        cpt.AjouterPoint(joueurUn);
        assertEquals(15, joueurUn.getPoint());
    }

    @Test
    @DisplayName("Si le gagnant a 0 point, il passe 0 15 points")
    public void testPoint0A15(){
        assertEquals(0, joueurUn.getPoint());
        cpt.AjouterPoint(joueurUn);
        assertEquals(15, joueurUn.getPoint());
    }

    @Test
    @DisplayName("Si le gagnant a 15 points, il passe a 30 points")
    public void testPoint15A30(){
        joueurUn.setPoint(15);
        assertEquals(15, joueurUn.getPoint());
        cpt.AjouterPoint(joueurUn);
        assertEquals(30, joueurUn.getPoint());
    }

    @Test
    @DisplayName("Si le gagnant a 30 points, il passe a 40 points")
    public void testPoint30A40(){
        joueurUn.setPoint(30);
        assertEquals(30, joueurUn.getPoint());
        cpt.AjouterPoint(joueurUn);
        assertEquals(40, joueurUn.getPoint());
    }

    @Test
    @DisplayName("Le perdant ne gagne aucun point")
    public void testPerdantAucunPoint()
    {
        joueurUn.setPoint(30);
        assertEquals(30, joueurUn.getPoint());
        cpt.AjouterPoint(joueurUn);
        assertEquals(40, joueurUn.getPoint());
        assertEquals(0, joueurDeux.getPoint());
        joueurUn.setPoint(0);
        joueurDeux.setPoint(30);
        assertEquals(30, joueurDeux.getPoint());
        cpt.AjouterPoint(joueurDeux);
        assertEquals(40, joueurDeux.getPoint());
        assertEquals(0, joueurUn.getPoint());
    }

    @Test
    @DisplayName("Si les deux joueurs sont a egalité a 40 points, si aucun joueur a un avantage, le joueur qui gagne le point gagne un avantage")
    public void test40AAvantage()
    {
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
        joueurUn.setPoint(40);
        joueurDeux.setPoint(40);
        cpt.AjouterPoint(joueurUn);
        assertEquals("A", partie.getJoueurUn().getStatut());
    }

    @Test
    @DisplayName("Si les deux joueurs sont a egalité a 40 points, si le perdant a un avantage, alors il le perd")
    public void testAvantageA40()
    {
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
        joueurUn.setStatut("A");
        joueurDeux.setPoint(40);
        cpt.AjouterPoint(joueurDeux);
        assertEquals("EGALITE", partie.getJoueurDeux().getStatut());
    }

    @Test
    @DisplayName("Si un joueur a 40 points gagne le point alors que le perdant a moins de 40 point, alors, il gagne le jeu")
    public void testJeuGagne()
    {
        joueurUn.setPoint(40);
        joueurDeux.setPoint(0);
        cpt.AjouterPoint(joueurUn);
        assertEquals(1, partie.getJoueurUn().getJeux());
        joueurUn.setPoint(40);
        joueurDeux.setPoint(15);
        cpt.AjouterPoint(joueurUn);
        assertEquals(2, partie.getJoueurUn().getJeux());
        joueurUn.setPoint(40);
        joueurDeux.setPoint(30);
        cpt.AjouterPoint(joueurUn);
        assertEquals(3, partie.getJoueurUn().getJeux());
    }

    @Test
    @DisplayName("Si les deux joueurs sont a egalité a 40 points, si le gagnant a un avantage, alors il gagne le jeu")
    public void testAvantageJeuGagne()
    {
        assertEquals(0, partie.getJoueurUn().getJeux());
        joueurUn.setPoint(40);
        joueurUn.setStatut("A");
        joueurDeux.setPoint(40);
        cpt.AjouterPoint(joueurUn);
        assertEquals(1, partie.getJoueurUn().getJeux());
    }

    @Test
    @DisplayName("Quand un jeu est gagné, alors les deux joueurs retournent à 0 point")
    public void testReinitialiserPoint()
    {
        assertEquals(0, partie.getJoueurUn().getJeux());
        joueurUn.setPoint(40);
        joueurUn.setStatut("A");
        joueurDeux.setPoint(40);
        cpt.AjouterPoint(joueurUn);
        assertEquals(1, partie.getJoueurUn().getJeux());
        assertEquals(0, partie.getJoueurUn().getPoint());
        assertEquals(0, partie.getJoueurDeux().getPoint());
    }

    @Test
    @DisplayName("Quand un joueur arrive à gagner 6 jeux et que son adversaire 4 ou moins jeux gagnés, alors le joueur gagne un set")
    public void testGagneUnSet()
    {
        joueurUn.setPoint(40);
        joueurUn.setJeux(5);
        joueurDeux.setPoint(0);
        joueurDeux.setJeux(0);
        cpt.AjouterPoint(joueurUn);
        assertEquals(1, partie.getJoueurUn().getSet());
    }

    @Test
    @DisplayName("Quand les deux joueurs ont 5 points alors faut avoir 2 points d'avance et 7 jeux pour gagner le set.")
    public void testGagneUnSetDeuxPointDifference()
    {
        joueurUn.setPoint(40);
        joueurUn.setJeux(5);
        joueurDeux.setPoint(0);
        joueurDeux.setJeux(5);
        cpt.AjouterPoint(joueurUn);
        assertEquals(6, partie.getJoueurUn().getJeux());
        assertEquals(0, partie.getJoueurUn().getSet());
        cpt.AjouterPoint(joueurUn);
        cpt.AjouterPoint(joueurUn);
        cpt.AjouterPoint(joueurUn);
        cpt.AjouterPoint(joueurUn);
        assertEquals(1, partie.getJoueurUn().getSet());
    }

    @Test
    @DisplayName("Quand les deux joueurs ont 6 jeux gagné, alors on passe en `jeux decisif`")
    public void testJeuxDecisif()
    {
        joueurUn.setPoint(40);
        joueurUn.setJeux(5);
        joueurDeux.setJeux(6);
        cpt.AjouterPoint(joueurUn);
        assertEquals("EN JEU DECISIF", partie.getJoueurUn().getStatut());
        assertEquals("EN JEU DECISIF", partie.getJoueurDeux().getStatut());
    }

    @Test
    @DisplayName("Quand il y a jeu décisif les points sont compté par point")
    public void testJeuDecisifIncrement()
    {
        assertEquals(0, partie.getJoueurUn().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurUn);
        assertEquals(1, partie.getJoueurUn().getPointJeuDecisif());
    }

    @Test
    @DisplayName("Si un joueur gagne un point pendant un jeu decisif il passe de 0 à 1, puis 2, ... jusqu'à 7")
    public void testJeuDecisif0A7()
    {
        assertEquals(0, partie.getJoueurUn().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurUn);
        assertEquals(1, partie.getJoueurUn().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurUn);
        assertEquals(2, partie.getJoueurUn().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurUn);
        assertEquals(3, partie.getJoueurUn().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurUn);
        assertEquals(4, partie.getJoueurUn().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurUn);
        assertEquals(5, partie.getJoueurUn().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurUn);
        assertEquals(6, partie.getJoueurUn().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurUn);
        assertEquals(0, partie.getJoueurUn().getPointJeuDecisif());
    }

    @Test
    @DisplayName("Il faut avoir deux points d'avance pour gagner le jeu et donc le set")
    public void testJeuDecisif2PointAvance()
    {
        joueurUn.setPointJeuDecisif(5);
        joueurDeux.setPointJeuDecisif(5);
        cpt.AjouterPointDecisif(joueurUn);
        assertEquals(6, partie.getJoueurUn().getPointJeuDecisif());
        assertEquals(5, partie.getJoueurDeux().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurDeux);
        assertEquals(6, partie.getJoueurUn().getPointJeuDecisif());
        assertEquals(6, partie.getJoueurDeux().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurDeux);
        assertEquals(6, partie.getJoueurUn().getPointJeuDecisif());
        assertEquals(7, partie.getJoueurDeux().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurDeux);
        assertEquals(0, partie.getJoueurUn().getSet());
        assertEquals(1, partie.getJoueurDeux().getSet());
    }
}
