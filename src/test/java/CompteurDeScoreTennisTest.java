import org.example.CompteurDeScoreTennis;
import org.example.entities.Joueur;
import org.example.entities.Partie;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class CompteurDeScoreTennisTest {

    @Test
    @DisplayName("Un utilisateur peut choisir les deux joueurs qui joueront la partie")
    public void creerDeuxJoueurs(){
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        assertNotNull(joueurUn);
        assertNotNull(joueurDeux);
    }

    @Test
    @DisplayName("Un utilisateur peut créer une partie")
    public void creerUnePartie(){
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        assertNotNull(partie);
    }

    @Test
    @DisplayName("En debut de partie les joueurs ont 0 point, 0 jeu gagné, et 0 set gagné, et sont encore aux vestiaires")
    public void testDebutDePartieA0Point()
    {
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        assertEquals(0, partie.getJoueurUn().getPoint());
        assertEquals(0, partie.getJoueurUn().getJeux());
        assertEquals(0, partie.getJoueurUn().getPointJeuDecisif());
        assertEquals(0, partie.getJoueurUn().getSet());
        assertEquals("AU VESTIAIRE", partie.getJoueurUn().getStatut());
        assertEquals(0, partie.getJoueurDeux().getPoint());
        assertEquals(0, partie.getJoueurDeux().getJeux());
        assertEquals(0, partie.getJoueurDeux().getPointJeuDecisif());
        assertEquals(0, partie.getJoueurDeux().getSet());
        assertEquals("AU VESTIAIRE", partie.getJoueurDeux().getStatut());
    }

    @Test
    @DisplayName("Un utilisateur peut notifier qu'un joueur a gagné un point")
    public void testGagneUnPoint()
    {
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
        cpt.AjouterPoint(joueurUn);
        assertEquals(15, joueurUn.getPoint());
    }

    @Test
    @DisplayName("Si le gagnant a 0 point, il passe 0 15 points")
    public void testPoint0A15(){
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
        assertEquals(0, joueurUn.getPoint());
        cpt.AjouterPoint(joueurUn);
        assertEquals(15, joueurUn.getPoint());
    }

    @Test
    @DisplayName("Si le gagnant a 15 points, il passe a 30 points")
    public void testPoint15A30(){
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
        joueurUn.setPoint(15);
        assertEquals(15, joueurUn.getPoint());
        cpt.AjouterPoint(joueurUn);
        assertEquals(30, joueurUn.getPoint());
    }

    @Test
    @DisplayName("Si le gagnant a 30 points, il passe a 40 points")
    public void testPoint30A40(){
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
        joueurUn.setPoint(30);
        assertEquals(30, joueurUn.getPoint());
        cpt.AjouterPoint(joueurUn);
        assertEquals(40, joueurUn.getPoint());
    }

    @Test
    @DisplayName("Le perdant ne gagne aucun point")
    public void testPerdantAucunPoint()
    {
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
        joueurUn.setPoint(30);
        assertEquals(30, joueurUn.getPoint());
        cpt.AjouterPoint(joueurUn);
        assertEquals(40, joueurUn.getPoint());
        assertEquals(0, joueurDeux.getPoint());
    }

    @Test
    @DisplayName("Si les deux joueurs sont a egalité a 40 points, si aucun joueur a un avantage, le joueur qui gagne le point gagne un avantage")
    public void test40AAvantage()
    {
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
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
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
        joueurUn.setPoint(40);
        joueurDeux.setStatut("A");
        cpt.AjouterPoint(joueurUn);
        assertEquals("EGALITE", partie.getJoueurDeux().getStatut());
    }

    @Test
    @DisplayName("Si les deux joueurs sont a egalité a 40 points, si le gagnant a un avantage, alors il gagne le jeu")
    public void testAvantageJeuGagne()
    {
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
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
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
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
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
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
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
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
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
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
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
        assertEquals(0, partie.getJoueurUn().getPointJeuDecisif());
        cpt.AjouterPointDecisif(joueurUn);
        assertEquals(1, partie.getJoueurUn().getPointJeuDecisif());
    }
    @Test
    @DisplayName("Si un joueur gagne un point pendant un jeu decisif il passe de 0 à 1, puis 2, ... jusqu'à 7")
    public void testJeuDecisif0A7()
    {
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
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
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
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

    @Test
    @DisplayName("Le premier joueur a 2 sets gagnés gagne la partie")
    public void testVictoire2Sets()
    {
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
        joueurUn.setSet(1);
        cpt.AjouterSet(joueurUn);
        assertEquals("GAGNER", partie.getJoueurUn().getStatut());
        assertTrue(partie.getJoueurUn().isGagnant());
    }
    @Test
    @DisplayName("Quand un joueur a gagné, il n'est plus possible de changer les scores")
    public void testBlocageScore()
    {
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
        joueurUn.setGagnant(true);
        cpt.AjouterPoint(joueurUn);
        assertEquals(0, partie.getJoueurUn().getPoint());
        assertEquals(0, partie.getJoueurUn().getJeux());
        assertEquals(0, partie.getJoueurUn().getPointJeuDecisif());
        assertEquals(0, partie.getJoueurUn().getSet());
    }

    @Test
    @DisplayName("L'utilisateur doit être avertie que la partie est finie")
    public void testPartieFinie()
    {
        Joueur joueurUn = new Joueur("Kevin Monac");
        Joueur joueurDeux = new Joueur("Shahin Biramane");
        Partie partie = new Partie(joueurUn, joueurDeux);
        CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);
    }
}
