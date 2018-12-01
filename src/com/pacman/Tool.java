package com.pacman;

import java.util.Random;

public class Tool // classe utilitaire avec quelques fonctions generales
{
    public static int monRandom(int min, int max) // renvoie un entier au hasard entre deux bornes
    {
        Random rand = new Random();
        int value = rand.nextInt((max - min)+ 1) + min;

        return value;
    }
}
