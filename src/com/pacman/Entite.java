package com.pacman;

public abstract class Entite // Une entité pouvant se deplacer dans une grille
{
    protected int x, y; // La position de l'entité dans la grille
    protected int direction; // la direction vers laquelle va l'entité

    public Entite(int _x, int _y)
    {
        this.x = _x;
        this.y = _y;
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

    public int getDirection(){ return this.direction; }

    // Deplacement vers le haut, renvoie true si le deplacement a été effectué
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

    // Deplacement vers le bas
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

    // Deplacement vers la gauche
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

    // Deplacement vers la droite
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

    // Deplacement de l'entité dans la grille donnée en paramètre
    public abstract void deplacer(Grille g);

}
