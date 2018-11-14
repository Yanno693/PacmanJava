package com.pacman;

import java.util.Random;

public class Tool
{
    public static int monRandom(int min, int max)
    {
        Random rand = new Random();
        int value = rand.nextInt((max - min)+ 1) + min;

        return value;
    }
}
