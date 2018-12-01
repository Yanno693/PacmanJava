package com.pacman;

import java.util.Observable;

public class Jeu extends Observable
{
    private boolean pause;
    private Grille grille;
    private int score;
    private int vie;
    private boolean gameover;
    private int nbPacGommeTotal;
    private int nbPacGomme;

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

    public void nouvellePartie()
    {
        grille = new Grille();
        //score = 0;
        pause = false;
        vie--;
    }

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

    public void pause()
    {
        if(!gameover)
            pause = !pause;
    }

    @Override
    public String toString()
    {
        return (this.score + "\n" + grille.toString());
    }

    public void boucleEvenement()
    {
        if(!pause)
        {
            int getScore = grille.deplacer();

            if(getScore != -1)
            {
                score += getScore;
                if(getScore == 10 || getScore == 50)
                    nbPacGomme--;

                if(nbPacGomme == 0)
                    nouvelleManche();
            }
            else
            {
                if(vie > 0)
                {
                    grille.placerEntite();
                    vie--;
                }
                else
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
            //System.out.println(this.toString());
        }

        setChanged();
        notifyObservers();
    }
}
