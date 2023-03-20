package presenter;

import metier.Cours;
import metier.Formateur;
import model.DAO;
import model.FormateurModelDB;
import view.ViewInterface;

import java.util.List;

public class FormateurPresenter
{
    private FormateurModelDB model;
    private ViewInterface view;

    public FormateurPresenter(FormateurModelDB model, ViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        List<Formateur> lform = model.getAll();
        view.setListDatas(lform);
    }
    public void getFormateurByCours(Cours c) {
        List<Formateur> lform = model.getFormateurByCours(c);
        view.setListDatas(lform);
    }



}
