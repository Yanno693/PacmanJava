package com.pacman;

import java.util.Observable;

public class Jeu extends Observable
{
    private boolean pause;
    private Grille grille;
    private int score;

    public Jeu()
    {
        pause = false;
        grille = new Grille();
        score = 0;
    }

    public void nouvellePartie()
    {
        grille = new Grille();
        score = 0;
        pause = false;
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
            grille.deplacer();
        }
    }
}
