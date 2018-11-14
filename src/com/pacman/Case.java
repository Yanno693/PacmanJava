package com.pacman;

public class Case
{

    // - = Vide
    // T = Carrefour
    // O = Mur
    // * = Pac Gomme
    // Y = Super Pac Gomme

    private char type;

    public Case(char _type)
    {
        this.type = _type;
    }

    public char getType()
    {
        return this.type;
    }
    public void setType(char _type){ this.type = _type; }

    public boolean estVide()
    {
        return (this.type != 'O');
    }

}