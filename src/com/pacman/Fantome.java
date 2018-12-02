package com.pacman;

public class Fantome extends Entite
{

    private boolean vulnerable; // Si le fantome peut être mangé par Pac-Man ou pas

    public Fantome(int _x, int _y)
    {
        super(_x, _y);
        vulnerable = false;
    }

    public boolean estVulnerable()
    {
        return this.vulnerable;
    }

    public void setVulnerable(boolean vulnerable)
    {
        this.vulnerable = vulnerable;
    }

    @Override
    public void deplacer(Grille g)
    {
        boolean deplacement = false;

        // Le fantome continue dans sa direction
        if(g.etatGrille[this.x][this.y].getType() != 'T')
        {
            switch (this.direction)
            {
                case 0 : deplacement = this.haut(g); break;
                case 1 : deplacement = this.bas(g); break;
                case 2 : deplacement = this.gauche(g); break;
                default : deplacement = this.droit(g); break;
            }
        }

        //S'il n'a pas pu continue dans sa direction,
        //il la change avec une direction choisie au hasard
        if(!deplacement)
        {
            do{
                this.direction = Tool.monRandom(0,3);
                switch (this.direction)
                {
                    case 0 : deplacement = this.haut(g); break;
                    case 1 : deplacement = this.bas(g); break;
                    case 2 : deplacement = this.gauche(g); break;
                    default : deplacement = this.droit(g); break;
                }
            }while(!deplacement);
        }
    }

}
