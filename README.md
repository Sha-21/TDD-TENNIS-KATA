# Tennis Kata avec Java et JUnit

## Présentation
Le but de cette exercice est de faire une application pour compter les points aux tennis en utilisant la methode TDD. Nous utiliserons le langage Java et la librairie de test JUnit.

## Définition
* __Test Driven Design [TDD]__ : Methode de développement qui permet de limiter fortement le nombre de bug et d'avoir un developpement Incremental. Le principe :
    * `_[RED]_` faire un test d'une unité de besoin puis le lancer pour voir qu'il ne valide pas encore notre code.
    * `_[GREEN]_` coder le code minimal qui permet de repondre au test tout en validant les testes précedents.
    * `_[REFECTOR]_` chercher des amelioration pour la syntaxe et la lisibilité du code. Relancer les testes pour verifier qu'il n'y a pas eu de regression avant de passé au test suivent.


## L'exercice: Un Compteur de point en Java
Une entreprise ayant besoin d'un système pour compter les points aux tennis nous contact. Elle ne sait pas encore comment ils vont l’utiliser : API REST, Angular, Vue Js, CLI, application lourde....

### Les Entités:
Une partie : Représente la partie de tennis en cours.
Les joueurs : Il possède un nom.

### Les besoins
* Un utilisateur peut créer une nouvelle partie.
* Un utilisateur peut choisir les 2 joueurs qui jouerons la partie.
* Au début de la partie les deux joueurs ont 0 points.
* Au début de la partie les deux joueurs ont 0 jeux gagnés.
* Au début de la partie les deux joueurs ont 0 sets gagnés.

* L'utilisateur peut notifier qu'un Joueur a gagné un point.
* Quand un joueur gagne un point, il passe de 0 à 15. Puis de 15 à 30. Puis de 30 à 40.
* Si les deux joueurs sont a egalité a 40 points, si aucun joueur a un avantage, le joueur qui gagne le point gagne un avantage.
* Si les deux joueurs sont a egalité a 40 points, si le perdant a un avantage, alors il le perd.
* Si les deux joueurs sont a egalité a 40 points, si le gagnant a un avantage, alors il gagne le jeu.

* Quand un jeu est gagné, alors les deux joueurs retournent à 0 point.
* Quand un joueur arrive à gagner 6 jeux et que son adversaire 4 ou moins jeux gagnés, alors le joueur gagne un set.
* Quand les deux joueurs ont 5 points alors faut avoir 2 points d'avance et 7 jeux pour gagner le set.

* Quand les deux joueurs ont 6 jeux gagné, alors on passe en `jeux decisif`.
* Quand il y a jeu décisif les points sont compté par point.
* Si un joueur gagne un point pendant un jeu decisif il passe de 0 à 1, puis 2, ... jusqu'à 7.
* Il faut avoir deux points d'avance pour gagner le jeu et donc le set.

* Le premier joueur a 2 sets gagnés gagne la partie.
* Quand un joueur a gagné, il n'est plus possible de changer les scores.
* L'utilisateur doit être avertie que la partie est finie.

### Developpement
Nous devons créer la classe `CompteurDeScoreTennis` qui contiendra une bonne patie de notre code.
Nous devons aussi créer nos entités `Partie` et `Joueur`.
