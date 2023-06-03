package presenter;

import metier.SessionCours;
import model.DAO;
import view.ViewInterface;

import java.util.Comparator;

public class SessionCoursPresenter extends Presenter<SessionCours>
{

    public SessionCoursPresenter(DAO<SessionCours> model, ViewInterface<SessionCours> view, Comparator<SessionCours> cmp) {
        super(model, view, cmp);
    }
}
