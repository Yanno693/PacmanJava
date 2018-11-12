package com.pacman;

public class Grille
{
    private int hauteur, largeur;
    private Case etatGrille[][];

    public Grille()
    {
        hauteur = (14*2)+1;
        largeur = 28;

        etatGrille = new Case[largeur][hauteur];
        initialiser();

    }

    private void initialiser()
    {
        String str_grille[] = new String[hauteur]; // Hauteur de 29
        str_grille[0 ] = "OOOOOOOOOOOOOOOOOOOOOOOOOOOO";
        str_grille[1 ] = "O------------OO------------O";
        str_grille[2 ] = "O-OOOO-OOOOO-OO-OOOOO-OOOO-O";
        str_grille[3 ] = "O-OOOO-OOOOO-OO-OOOOO-OOOO-O";
        str_grille[4 ] = "O-OOOO-OOOOO-OO-OOOOO-OOOO-O";
        str_grille[5 ] = "O--------------------------O";
        str_grille[6 ] = "O-OOOO-OO-OOOOOOOO-OO-OOOO-O";
        str_grille[7 ] = "O-OOOO-OO-OOOOOOOO-OO-OOOO-O";
        str_grille[8 ] = "O------OO----OO----OO------O";
        str_grille[9 ] = "OOOOOO-OOOOO-OO-OOOOO-OOOOOO";
        str_grille[10] = "OOOOOO-OOOOO-OO-OOOOO-OOOOOO";
        str_grille[11] = "OOOOOO-O------------O-OOOOOO";
        str_grille[12] = "OOOOOO-O-OOOOOOOOOO-O-OOOOOO";
        str_grille[13] = "OOOOOO-O-OOOOOOOOOO-O-OOOOOO";
        str_grille[14] = "---------OOOOOOOOOO---------";
        str_grille[15] = "OOOOOO-O-OOOOOOOOOO-O-OOOOOO";
        str_grille[16] = "OOOOOO-O-OOOOOOOOOO-O-OOOOOO";
        str_grille[17] = "OOOOOO-O------------O-OOOOOO";
        str_grille[18] = "OOOOOO-OOOOO-OO-OOOOO-OOOOOO";
        str_grille[19] = "OOOOOO-OOOOO-OO-OOOOO-OOOOOO";
        str_grille[20] = "O------OO----OO----OO------O";
        str_grille[21] = "O-OOOO-OO-OOOOOOOO-OO-OOOO-O";
        str_grille[22] = "O-OOOO-OO-OOOOOOOO-OO-OOOO-O";
        str_grille[23] = "O--------------------------O";
        str_grille[24] = "O-OOOO-OOOOO-OO-OOOOO-OOOO-O";
        str_grille[25] = "O-OOOO-OOOOO-OO-OOOOO-OOOO-O";
        str_grille[26] = "O-OOOO-OOOOO-OO-OOOOO-OOOO-O";
        str_grille[27] = "O------------OO------------O";
        str_grille[28] = "OOOOOOOOOOOOOOOOOOOOOOOOOOOO";

        for(int i = 0; i < largeur; i++)
        {
            for(int j = 0; j < hauteur; j++)
            {
                etatGrille[i][j] = new Case(str_grille[j].charAt(i));
            }
        }

        System.out.println(str_grille[0].length());
    }
}
