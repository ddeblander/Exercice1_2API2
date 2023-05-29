import metier.Cours;
import metier.Formateur;
import metier.SessionCours;
import model.*;
import presenter.CoursPresenter;
import presenter.FormateurPresenter;
import presenter.Presenter;
import utilitaires.Utilitaire;
import view.CoursViewConsole;
import view.FormateurViewConsole;
import view.ViewInterface;

import java.text.Normalizer;
import java.util.*;

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

        DAO<Formateur> fm= new FormateurModelDB();
        ViewInterface<Formateur> fi = new FormateurViewConsole();
        Presenter fp = new FormateurPresenter(fm,fi,(formateur1,formateur2)->formateur1.getMatricule().compareTo(formateur2.getMatricule()));


        List<String> loptions = Arrays.asList("Cours","Formateurs","Infos","Locals","SessionCours","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: cp.start();
                    break;
                case 2: fp.start();
                    break;
                case 3: ;
                    break;
                case 4: ;
                    break;
                case 5: ;
                    break;

                case 6 : System.exit(0);
            }
        }while(true);


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