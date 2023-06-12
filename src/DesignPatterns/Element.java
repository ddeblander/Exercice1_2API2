package DesignPatterns;

import java.util.List;

public abstract class Element
{
    private int id;
    public Element(int id){
        this.id=id;
    }
    public int getId() {
        return id;
    }
    public abstract int getNBPlaces();
}

