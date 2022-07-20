import org.example.CompteurDeScoreTennis;
import org.example.entities.Joueur;
import org.example.entities.Partie;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class CompteurCreationPartieTest {

    Joueur joueurUn = new Joueur("Kevin Monac");
    Joueur joueurDeux = new Joueur("Shahin Biramane");
    Partie partie = new Partie(joueurUn,joueurDeux);
    CompteurDeScoreTennis cpt = new CompteurDeScoreTennis(partie);

    @Test
    @DisplayName("Ajouter un joueur avec un nom null jeter une NullPointerException")
    public void testNomJoueurNull()
    {
        joueurUn.setNom(null);
        joueurDeux.setNom(null);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            Partie partie2 = new Partie(joueurUn, joueurDeux);
        });
        String expectedMessage = "Le nom d'un joueur ne peut pas être null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Ajouter un joueur avec un nom vide devrait jeter une IllegalArgumentException")
    public void testNomJoueurVide()
    {
        joueurUn.setNom("");
        joueurDeux.setNom("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Partie partie2 = new Partie(joueurUn, joueurDeux);
        });
        String expectedMessage = "Le nom d'un joueur ne peut pas être vide";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        // On test également si on met juste un espace
        joueurUn.setNom(" ");
        joueurDeux.setNom(" ");
        exception = assertThrows(IllegalArgumentException.class, () -> {
            Partie partie2 = new Partie(joueurUn, joueurDeux);
        });
        expectedMessage = "Le nom d'un joueur ne peut pas être vide";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Un utilisateur peut choisir les deux joueurs qui joueront la partie")
    public void creerDeuxJoueurs(){
        assertNotNull(joueurUn);
        assertNotNull(joueurDeux);
    }

    @Test
    @DisplayName("Un utilisateur peut créer une partie")
    public void creerUnePartie(){
        assertNotNull(partie);
    }

    @Test
    @DisplayName("En debut de partie les joueurs ont 0 point, 0 jeu gagné, et 0 set gagné, et sont encore aux vestiaires")
    public void testDebutDePartieA0Point()
    {
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
}
