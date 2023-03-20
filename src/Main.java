import demoDB.GestionCours;
import metier.Cours;
import model.CoursModelDB;
import model.DAO;
import presenter.CoursPresenter;
import view.CoursViewConsole;
import view.ViewInterface;

public class Main {
    public static void main(String[] args)
    {

        DAO cm= new CoursModelDB();

        ViewInterface ci = new CoursViewConsole();
        CoursPresenter cp = new CoursPresenter(cm,ci);
        cp.start();

    }
}