package presenter;

import metier.Cours;
import metier.Formateur;
import model.DAO;
import model.FormateurModelDB;
import view.CoursViewConsole;
import view.ViewInterface;

import java.util.Comparator;
import java.util.List;

public class CoursPresenter extends Presenter<Cours> implements SpecialCoursPresenter
{
    private DAO model;
    private Comparator<Cours> cmp;
    FormateurModelDB fmDB= new FormateurModelDB();
    private ViewInterface view;

    public CoursPresenter(DAO<Cours> model, ViewInterface<Cours> view, Comparator<Cours> cmp) {
        super(model, view, cmp);
    }


    public Cours selection() {
        return super.selection();
    }
    @Override
    public void getFormateursByCours(Cours c)
    {
        List<Formateur> lf=fmDB.getFormateurByCours(c);
        view.setListDatas(lf,cmp);
    }
}
