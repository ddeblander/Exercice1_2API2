package presenter;

import metier.Cours;
import metier.Formateur;
import model.DAO;
import model.FormateurModelDB;
import view.CoursViewConsole;
import view.FormateurViewConsole;
import view.ViewInterface;

import java.util.Comparator;
import java.util.List;

public class CoursPresenter extends Presenter<Cours> implements SpecialCoursPresenter
{
    private FormateurModelDB fmDB= new FormateurModelDB();


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
        /*DAO<Formateur> fm= new FormateurModelDB();
        ViewInterface<Formateur> fi = new FormateurViewConsole();
        Presenter fp = new FormateurPresenter(fm,fi,(formateur1,formateur2)->formateur1.getMatricule().compareTo(formateur2.getMatricule()));
        ((FormateurPresenter)fp).getFormateurByCours(c);*/
        DAO<Formateur> fm= new FormateurModelDB();
        List<Formateur> lf=((FormateurModelDB)fm).getFormateurByCours(c);
        for(Formateur f : lf)
        {
            System.out.println(f);
        }
    }
}
