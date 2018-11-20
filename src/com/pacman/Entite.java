package com.pacman;

public abstract class Entite implements Runnable
{
    protected boolean actif;
    protected int x, y;
    protected int direction;

    public Entite(int _x, int _y)
    {
        this.x = _x;
        this.y = _y;
        actif = false;
        direction = Tool.monRandom(0,3);
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
        if(g.etatGrille[this.x][Math.floorMod(this.y - 1, g.getHauteur())].estVide())
        {
            this.y = Math.floorMod(this.y - 1, g.getHauteur());
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean bas(Grille g)
    {

        if(g.etatGrille[this.x][Math.floorMod(this.y + 1, g.getHauteur())].estVide())
        {
            this.y = Math.floorMod(this.y + 1, g.getHauteur());
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean gauche(Grille g)
    {
        if(g.etatGrille[Math.floorMod(this.x - 1,g.getLargeur())][this.y].estVide())
        {
            this.x = Math.floorMod(this.x - 1, g.getLargeur());
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean droit(Grille g)
    {
        if(g.etatGrille[Math.floorMod(this.x + 1,g.getLargeur())][this.y].estVide())
        {
            this.x = Math.floorMod(this.x + 1, g.getLargeur());
            return true;
        }
        else
        {
            return false;
        }
    }

    public abstract void deplacer(Grille g);

    @Override
    public void run()
    {
        /*while(actif)
        {

        }*/
        System.out.println("ÄAAAAAAAAAA");
    }
}
