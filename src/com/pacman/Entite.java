package com.pacman;

import java.util.Observable;

public abstract class Entite extends Observable implements Runnable
{
    private boolean actif;
    private int x, y;

    public Entite(int _x, int _y)
    {
        this.x = _x;
        this.y = _y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setX(int _x)
    {
        this.x = _x;
    }

    public void setY(int _y)
    {
        this.y = _y;
    }

    public boolean haut(Grille g)
    {
        if(g.etatGrille[this.x][(this.y - 1) % g.getHauteur()].estVide())
        {
            this.y--;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean bas(Grille g)
    {
        if(g.etatGrille[this.x][(this.y + 1) % g.getHauteur()].estVide())
        {
            this.y++;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean gauche(Grille g)
    {
        if(g.etatGrille[(this.x - 1) % g.getLargeur()][this.y].estVide())
        {
            this.x--;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean droit(Grille g)
    {
        if(g.etatGrille[(this.x + 1) % g.getLargeur()][this.y].estVide())
        {
            this.x++;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void run()
    {
        /*while(actif)
        {

        }*/
    }
}
