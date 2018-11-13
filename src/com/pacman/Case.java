package com.pacman;

public class Case {

    // - = Vide
    // O = Mur

    private char type;

    public Case(char _type)
    {
        this.type = _type;
    }

    public boolean estVide()
    {
        return (this.type == '-');
    }

}
