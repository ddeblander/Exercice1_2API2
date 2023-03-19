package model;

import metier.Cours;

import java.util.ArrayList;
import java.util.List;

public class CoursModel implements DAO
{
    private List<Cours> cours;

    public CoursModel() {
        cours=new ArrayList<>();
    }
    public Cours add(Object o)
    {
        Cours c =(Cours) o;
        if(cours.contains(c))
            return null;
        cours.add(c);
        return c;
    }

    @Override
    public boolean remove(Object o) {
        Cours c=(Cours) o;
        return cours.remove(c);
    }

    @Override
    public boolean update(Object o)
    {
        Cours c =(Cours) o;
        int id=cours.indexOf(c);
        if(id==-1)
        {
            return false;
        }
        cours.set(id,c);
        return true;

    }

    @Override
    public List getAll() {
        return cours;
    }

    @Override
    public Object getByID(int id) {
        return null;
    }


    public Cours getByCodeCours(int id) {
        return cours.get(id);
    }
}
