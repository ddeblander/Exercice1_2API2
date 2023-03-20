package presenter;

import metier.Cours;
import metier.Formateur;
import model.DAO;
import model.FormateurModelDB;
import view.CoursViewConsole;
import view.ViewInterface;

import java.util.List;

public class CoursPresenter
{
    private DAO model;
    FormateurModelDB fmDB= new FormateurModelDB();
    private ViewInterface view;

    public CoursPresenter(DAO model, ViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        List<Cours> cours = model.getAll();
        view.setListDatas(cours);
    }

    public void addCours(Cours c) {
        Cours cours = (Cours) model.add(c);
        if(cours!=null) view.affMsg("création de :"+cours);
        else view.affMsg("erreur de création");
        List<Cours> coursList = model.getAll();
        view.setListDatas(coursList);
    }
    public void updateCours(Cours c) {
        boolean res =model.update(c);
        if(res) view.affMsg("mis à jour effectué :"+c.toString());
        else view.affMsg("erreur de création");
        List<Cours> coursList = model.getAll();
        view.setListDatas(coursList);
    }


    public void removeCours(Cours c ) {
        boolean ok = model.remove(c);
        if(ok) view.affMsg("client effacé");
        else view.affMsg("client non effacé");
        List<Cours> clients = model.getAll();
        view.setListDatas(clients);
    }
    public void getFormateursByCours(Cours c)
    {
        List<Formateur> lf=fmDB.getFormateurByCours(c);
        view.setListDatas(lf);
    }
}
