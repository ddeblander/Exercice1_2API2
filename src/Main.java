import metier.Cours;
import metier.Formateur;
import metier.Local;
import metier.SessionCours;
import model.*;
import presenter.*;
import utilitaires.Utilitaire;
import view.*;

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
        Presenter cp = new CoursPresenter(cm, ci, (cours1, cours2) -> cours1.getCodeCours().compareTo(cours2.getCodeCours()));

        DAO<Formateur> fm= new FormateurModelDB();
        ViewInterface<Formateur> fi = new FormateurViewConsole();
        Presenter fp = new FormateurPresenter(fm,fi,(formateur1,formateur2)->formateur1.getMatricule().compareTo(formateur2.getMatricule()));

        DAO<Local> lm= new LocalModelDB();
        ViewInterface<Local> li = new LocalViewConsole();
        Presenter lp = new LocalPresenter(lm,li,(local1, local2)->local1.getSigle().compareTo(local2.getSigle()));

        DAO<SessionCours> ssm= new SessionCoursModelDB();
        ViewInterface<SessionCours> ssi = new SessionCoursViewConsole();
        Presenter ssp = new SessionCoursPresenter(ssm,ssi,(session1, session2)->Integer.compare(session1.getId(),session2.getId()));


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
                case 4: lp.start();
                    break;
                case 5: ssp.start();
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