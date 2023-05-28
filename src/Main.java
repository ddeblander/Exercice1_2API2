import metier.Cours;
import metier.Formateur;
import metier.SessionCours;
import model.*;
import presenter.CoursPresenter;
import presenter.Presenter;
import view.CoursViewConsole;
import view.ViewInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
       /* // sera implémenté dans la gestion SessionCours
        SessionCoursModelDB db= new SessionCoursModelDB();
        List<SessionCours> lsc = db.getAll();
        System.out.println("List des sessions de cours avec local");
        for(SessionCours s:lsc)
        {
            System.out.println(s.toString());
        }
        System.out.println("List des sessions de cours avec local de 2016->2019");
        for(SessionCours s:lsc)
        {
            Date min = new java.sql.Date(116,0,1);
            Date max = new java.sql.Date(119,11,30);
            // obligé d'utiliser SQL.DATE sinon ils ne comprends pas les dates entre
            if((s.getDateDebut().after(min))&&(s.getDateDebut().before(max)))
            {
                System.out.println(s.toString());
            }

        }*/


        DAO<Cours> cm= new CoursModelDB();

        ViewInterface<Cours> ci = new CoursViewConsole();
        //Presenter cp = new CoursPresenter(cm,ci,(cours1,cours2)->cours1.getCodeCours().compareTo(cours2.getCodeCours()));
        Presenter cp = new CoursPresenter(cm, ci, (cours1, cours2) -> {
            if (cours1 != null && cours2 != null) {
                return cours1.getCodeCours().compareTo(cours2.getCodeCours());
            } else {
                // Gérer le cas où l'un des objets est null
                return 0; // Ou une autre valeur appropriée selon vos besoins
            }
        });
        cp.start();


       /* FormateurModelDB fd= new FormateurModelDB();
        List<Formateur> l;
        l= fd.getFormateurByCours(new Cours(1,40,"",""));
        for(Formateur f : l)
        {
            System.out.println(f.toString());
        }
*/

    }
}