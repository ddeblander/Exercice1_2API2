package presenter;

import metier.Infos;
import model.DAO;
import view.ViewInterface;

import java.util.Comparator;

public class InfosPresenter extends Presenter<Infos>
{

    public InfosPresenter(DAO<Infos> model, ViewInterface<Infos> view, Comparator<Infos> cmp) {
        super(model, view, cmp);
    }
}
