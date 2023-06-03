package presenter;

import metier.Local;
import model.DAO;
import view.ViewInterface;

import java.util.Comparator;

public class LocalPresenter extends Presenter<Local>
{

    public LocalPresenter(DAO<Local> model, ViewInterface<Local> view, Comparator<Local> cmp) {
        super(model, view, cmp);
    }
}
