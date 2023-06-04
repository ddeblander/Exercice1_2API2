package presenter;


import model.DAO;
import view.ViewInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Presenter<T> {
    protected DAO<T> model;
    protected ViewInterface<T> view;
    protected Comparator<T> cmp;
    public Presenter(DAO<T> model, ViewInterface<T> view, Comparator<T> cmp) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
        this.cmp=cmp;
    }

    public void start() {

        view.setListDatas(getAll(),cmp);
    }

    public List<T> getAll(){
        List<T> l = model.getAll();
        try
        {
            l.sort(cmp);
        }catch (NullPointerException e)
        {
            System.out.println("liste vide");
            return new ArrayList<>();
        }

        return l;
    }

    public void add(T elt) {
        T nelt = model.add(elt);
        if(nelt!=null) view.affMsg("création de :"+nelt);
        else view.affMsg("erreur de création");

    }


    public void remove(T elt) {
        boolean ok = model.remove(elt);
        if(ok) view.affMsg("élément effacé");
        else view.affMsg("élément non effacé");

    }
    public void update(T elt) {
        boolean nelt  =model.update(elt);
        if(nelt==false) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+nelt);

    }

    public T search(T rech) {
         T elt= model.read(rech);
        if(elt==null)
        {
            return null;
        }
        else return elt;
    }

    public T selection(){
       return  view.selectionner(model.getAll());
    }
}
