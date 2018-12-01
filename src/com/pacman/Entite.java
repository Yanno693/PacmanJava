package com.pacman;

public abstract class Entite // Une entite pouvant se deplacer dans une grille
{
    protected int x, y; // La position de lentite dans la grille
    protected int direction; // la direction vers laquelle va léntite

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

    public boolean haut(Grille g) // Deplacement vers le haut, renvoie true si le deplacement a ete effectue
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

    public boolean bas(Grille g) // Deplacement vers le bas
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

    public boolean gauche(Grille g) // Deplacement vers la gauche
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

    public boolean droit(Grille g) // Deplacement vers la droite
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

    public abstract void deplacer(Grille g); // Deplacement de l'entite dans la grille donnée en paramètre

}
