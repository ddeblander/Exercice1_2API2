package presenter;

import metier.Cours;
import metier.Formateur;
import model.DAO;
import model.FormateurModelDB;
import view.ViewInterface;

import java.util.Comparator;
import java.util.List;

public class FormateurPresenter extends Presenter<Formateur> implements SpecialFormateur
{


    public FormateurPresenter(DAO<Formateur> model, ViewInterface<Formateur> view, Comparator<Formateur> cmp) {
        super(model, view, cmp);
    }



}
