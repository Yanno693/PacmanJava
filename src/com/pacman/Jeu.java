package com.pacman;

import java.util.Observable;

public class Jeu extends Observable
{
    private boolean pause; // Est-ce aue le jeu est en pause ou pas
    private Grille grille; // la grille de jeu
    private int score; // le score du joueur
    private int vie; // le nombre de vie du joueur
    private boolean gameover; // Si le jeu est termine ou pas
    private int nbPacGommeTotal; // Le nombre de PacGomme au debut de partie
    private int nbPacGomme; // Le nombre de PacGomme pendant la partie

    public Jeu()
    {
        pause = false;
        grille = new Grille();
        score = 0;
        vie = 3;
        gameover = false;

        nbPacGommeTotal = 0;

        for(int i = 0; i < grille.getLargeur(); i++)
        {
            for(int j = 0; j < grille.getHauteur(); j++)
            {
                if(grille.etatGrille[i][j].getType() == '*' || grille.etatGrille[i][j].getType() == 'Y')
                    nbPacGommeTotal++;
            }
        }

        nbPacGomme = nbPacGommeTotal;
    }

    // Lance une nouvelle partie en reinitialisant le score et la vie du joueur
    public void nouvellePartie()
    {
        pause = false;
        score = 0;
        gameover = false;
        vie = 2;

        nouvelleManche();
    }

    // Lance une nouvelle manche en gardant le score et le nombre de vie du joueur
    private void nouvelleManche()
    {
        grille = new Grille();
        nbPacGomme = nbPacGommeTotal;
    }

    public int getVie()
    {
        return this.vie;
    }

    public boolean estGameover()
    {
        return this.gameover;
    }

    public int getScore()
    {
        return this.score;
    }

    public Grille getGrille()
    {
        return this.grille;
    }

    public boolean getPause()
    {
        return this.pause;
    }

    public void pause() // Mettre le jeu en pause ou le sortir de pause
    {
        if(!gameover)
            pause = !pause;
    }

    @Override
    public String toString()
    {
        return (this.score + "\n" + grille.toString());
    }

    // Boucle qui suis le deroulement du jeu
    public void boucleEvenement()
    {
        // si le jeu n'est pas en pause
        if(!pause)
        {
            // On récupère le score lié au deplacement du joueur
            int getScore = grille.deplacer();

            // Si le joueur n'est pas touché par un fantome
            if(getScore != -1)
            {
                score += getScore;
                // Si on avait mangé une Pac-gomme
                if(getScore == 10 || getScore == 50)
                    nbPacGomme--;

                // si on a mangé toutes les Pac-gommes,
                // on remlis à nouveau la grille
                if(nbPacGomme == 0)
                    nouvelleManche();
            }
            else
            {
                // Si le joueur a encore des vies, on replace les entités et on enlève une vie
                if(vie > 0)
                {
                    grille.placerEntite();
                    vie--;
                }
                else // Sinon on arrete la partie
                {
                    pause();
                    gameover = true;
                }
            }

            if(score >= 10000)
            {
                score -= 10000;
                vie++;
            }
        }

        //On notifie l'observateur de ce qui se passe dans le modèle
        setChanged();
        notifyObservers();
    }
}
