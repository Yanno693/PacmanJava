package com.pacman;

public class Fantome extends Entite {

    public Fantome(int _x, int _y)
    {
        super(_x, _y);
    }

    @Override
    public void deplacer(Grille g)
    {

        boolean deplacement = false;

        if(g.etatGrille[this.x][this.y].getType() != 'T')
        {
            switch (this.direction) // Etat "Je continue dans ma direction"
            {
                case 0 : deplacement = this.haut(g); break;
                case 1 : deplacement = this.bas(g); break;
                case 2 : deplacement = this.gauche(g); break;
                default : deplacement = this.droit(g); break;
            }
        }

        if(!deplacement)
        {
            do{
                this.direction = Tool.monRandom(0,3);
                switch (this.direction) // Etat "Je change de direction"
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
