package presenter;

import metier.Formateur;
import model.DAO;
import view.ViewInterface;

import java.util.Comparator;

public class FormateurPresenter extends Presenter<Formateur> {


    public FormateurPresenter(DAO<Formateur> model, ViewInterface<Formateur> view, Comparator<Formateur> cmp) {
        super(model, view, cmp);
    }



}
