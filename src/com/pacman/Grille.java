package com.pacman;

public class Grille
{
    private int largeur, hauteur;
    public Case etatGrille[][];
    private Fantome fantomes[];
    private Pacman pacman;

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

    private void initialiser()
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

        pacman = new Pacman(14,23);
        fantomes = new Fantome[4];
        fantomes[0] = new Fantome(13,11);
        fantomes[1] = new Fantome(14,11);
        fantomes[2] = new Fantome(11,14);
        fantomes[3] = new Fantome(16,14);
    }

    public void deplacer()
    {
        for(int i = 0; i < fantomes.length; i++)
        {
            fantomes[i].deplacer(this);
        }
        pacman.deplacer(this);
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
