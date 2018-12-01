package com.pacman;

public class Grille // Une grille de jeu
{
    private int largeur, hauteur;
    public Case etatGrille[][]; // Un tableau 2D de case representant la grille
    private Fantome fantomes[]; // Les 4 fantomes
    private Pacman pacman; // Le joueur

    public Grille()
    {
        hauteur = (14*2)+1;
        largeur = 28;

        etatGrille = new Case[largeur][hauteur];
        initialiser();
    }

    public int getHauteur()
    {
        return this.hauteur;
    }

    public int getLargeur()
    {
        return this.largeur;
    }

    public Fantome[] getFantomes() { return this.fantomes; }

    public Pacman getPacman() { return pacman; }

    private void initialiser() // Initialise une nouvelle grille en placant les pac-gomme, les fantomes et le joueur
    {
        String str_grille[] = new String[hauteur]; // Hauteur de 29
        str_grille[0 ] = "OOOOOOOOOOOOOOOOOOOOOOOOOOOO";
        str_grille[1 ] = "O*****T*****TOOT*****T*****O";
        str_grille[2 ] = "O*OOOO*OOOOO*OO*OOOOO*OOOO*O";
        str_grille[3 ] = "OYOOOO*OOOOO*OO*OOOOO*OOOOYO";
        str_grille[4 ] = "O*OOOO*OOOOO*OO*OOOOO*OOOO*O";
        str_grille[5 ] = "OT****T**T**T**T**T**T****TO";
        str_grille[6 ] = "O*OOOO*OO*OOOOOOOO*OO*OOOO*O";
        str_grille[7 ] = "O*OOOO*OO*OOOOOOOO*OO*OOOO*O";
        str_grille[8 ] = "O*****TOO****OO****OOT*****O";
        str_grille[9 ] = "OOOOOO*OOOOO*OO*OOOOO*OOOOOO";
        str_grille[10] = "OOOOOO*OOOOO*OO*OOOOO*OOOOOO";
        str_grille[11] = "OOOOOO*O****TTTT****O*OOOOOO";
        str_grille[12] = "OOOOOO*O*OOOO--OOOO*O*OOOOOO";
        str_grille[13] = "OOOOOO*O*OOOO--OOOO*O*OOOOOO";
        str_grille[14] = "------T*TOO--TT--OOT*T------";
        str_grille[15] = "OOOOOO*O*OOOOOOOOOO*O*OOOOOO";
        str_grille[16] = "OOOOOO*O*OOOOOOOOOO*O*OOOOOO";
        str_grille[17] = "OOOOOO*O****T**T****O*OOOOOO";
        str_grille[18] = "OOOOOO*OOOOO*OO*OOOOO*OOOOOO";
        str_grille[19] = "OOOOOO*OOOOO*OO*OOOOO*OOOOOO";
        str_grille[20] = "O*****TOO****OO****OOT*****O";
        str_grille[21] = "O*OOOO*OO*OOOOOOOO*OO*OOOO*O";
        str_grille[22] = "O*OOOO*OO*OOOOOOOO*OO*OOOO*O";
        str_grille[23] = "OT****T**T**T--T**T**T****TO";
        str_grille[24] = "O*OOOO*OOOOO*OO*OOOOO*OOOO*O";
        str_grille[25] = "OYOOOO*OOOOO*OO*OOOOO*OOOOYO";
        str_grille[26] = "O*OOOO*OOOOO*OO*OOOOO*OOOO*O";
        str_grille[27] = "O*****T******OO******T*****O";
        str_grille[28] = "OOOOOOOOOOOOOOOOOOOOOOOOOOOO";

        for(int i = 0; i < largeur; i++)
        {
            for(int j = 0; j < hauteur; j++)
            {
                etatGrille[i][j] = new Case(str_grille[j].charAt(i));
            }
        }

        placerEntite();
    }

    public  void placerEntite() // place les fantpmes et le joueur
    {
        pacman = new Pacman(14,23);
        fantomes = new Fantome[4];
        fantomes[0] = new Fantome(13,11);
        fantomes[1] = new Fantome(14,11);
        fantomes[2] = new Fantome(11,14);
        fantomes[3] = new Fantome(16,14);
    }

    private int collision() // detecte si il y a eu une collision entre le joueur et un fantome, renvoi l'indice du fantome dans le tableau, sinon -1
    {
        int collision = -1;

        for(int i = 0; i < fantomes.length; i++)
        {
            if(fantomes[i].getX() == pacman.getX() && fantomes[i].getY() == pacman.getY() && fantomes[i].getDirection() != pacman.getDirection())
                collision = i;
        }

        return collision;
    }

    public int deplacer() // Deplace toutes les entites de la grille et renvoie le score lie au deplacement
    {
        int pacGomme = 0;

        for(int i = 0; i < fantomes.length; i++)
        {
            //Si le joueur n'est pas en mode Super
            //les fantomes ne peuvent pas être mangés
            fantomes[i].deplacer(this);
            if(!pacman.estSuper())
                fantomes[i].setVulnerable(false);
        }

        int c;

        //On vérifie s'il y avait une collision entre les fantomes et le pac-man
        c = collision();
        if(c != -1)
        {
            // Si on mange le fantome, on gagne des points et on le replace au centre de la grille
            if (fantomes[c].estVulnerable())
            {
                pacGomme = 200;
                fantomes[c].setX(11);
                fantomes[c].setY(14);
                fantomes[c].setVulnerable(false);

            }
            // Sinon on meurt
            else
            {
                return -1;
            }
        }

        pacman.deplacer(this);

        // On refait la meme opération après le deplacement du joueur
        c = collision();
        if(c != -1)
        {
            if (fantomes[c].estVulnerable())
            {
                pacGomme = 200;
                fantomes[c].setX(11);
                fantomes[c].setY(14);
                fantomes[c].setVulnerable(false);
            }
            else
            {
                return -1;
            }
        }

        // Si on mange une pac-gomme, on vide la case et on donne 10 points au joueur
        if(etatGrille[pacman.getX()][pacman.getY()].getType() == '*')
        {
            etatGrille[pacman.getX()][pacman.getY()].setType('-');
            pacGomme = 10;
        }

        // Super pac-gomme -> 50 points + Super Pacman
        if(etatGrille[pacman.getX()][pacman.getY()].getType() == 'Y')
        {
            etatGrille[pacman.getX()][pacman.getY()].setType('-');
            pacGomme = 50;
            pacman.passerSuper();

            for(int i = 0; i < fantomes.length; i++)
            {
                fantomes[i].setVulnerable(true);
            }
        }

        return pacGomme;
    }

    @Override
    public String toString()
    {
        String s = new String();

        for(int i = 0; i < hauteur; i ++)
        {
            for(int j = 0; j < largeur; j++)
            {
                if(j == pacman.getX() && i == pacman.getY())
                {
                    s += "P";
                }
                else if(j == fantomes[0].getX() && i == fantomes[0].getY())
                {
                    s += "F";
                }
                else if(j == fantomes[1].getX() && i == fantomes[1].getY())
                {
                    s += "F";
                }
                else if(j == fantomes[2].getX() && i == fantomes[2].getY())
                {
                    s += "F";
                }
                else if(j == fantomes[3].getX() && i == fantomes[3].getY())
                {
                    s += "F";
                }
                else
                {
                    if(etatGrille[j][i].estVide())
                    {
                        s+= " ";
                    }
                    else
                    {
                        s += "O";
                    }
                }
            }
            s += '\n';
        }
        return s;
    }
}
