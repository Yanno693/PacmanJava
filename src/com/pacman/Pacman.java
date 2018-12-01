package com.pacman;

public class Pacman extends Entite
{
    // 0 : Haut
    // 1 : Bas
    // 2 : Gauche
    // 3 : Droite

    private int directionInput; // La direction donnee par láction du joueur
    private int compteurSuper; // Un compteur pour savoir si on est en mode Super Pacman

    public void changerDirection(int d)
    {
        directionInput = d;
    }

    public boolean estSuper()
    {
        return (compteurSuper > 0);
    }

    public void passerSuper()
    {
        compteurSuper = 50;
    }

    public Pacman(int _x, int _y)
    {
        super(_x,_y);
        directionInput = -1;
        direction = -1;
        compteurSuper = 0;
    }

    @Override
    public void deplacer(Grille g)
    {
        boolean changement = false;

        switch (this.directionInput) // Est-ce que je peux aller dans la direction de l'input
        {
            case 0 : changement = this.haut(g); break;
            case 1 : changement = this.bas(g); break;
            case 2 : changement = this.gauche(g); break;
            case 3 : changement = this.droit(g); break;
        }

        if(changement) // Si le deplacement a eu lieu
        {
            this.direction = this.directionInput;
        }
        else // Sinon on continue dans notre direction
        {
            switch (this.direction) // Est-ce que je peux aller dans la direction de m
            {
                case 0 : this.haut(g); break;
                case 1 : this.bas(g); break;
                case 2 : this.gauche(g); break;
                case 3 : this.droit(g); break;
            }
        }

        if(estSuper())
            compteurSuper--;
    }
}
