package view;

import metier.Cours;
import metier.Formateur;
import metier.Infos;
import metier.SessionCours;
import model.CoursModelDB;
import model.DAO;
import model.FormateurModelDB;
import model.SessionCoursModelDB;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.affListe;
import static utilitaires.Utilitaire.lecDate;

public class InfosViewConsole extends AbstractViewConsole<Infos>
{

    private SessionCoursModelDB sscDB=new SessionCoursModelDB();
    private FormateurModelDB fmDB=new FormateurModelDB();

    @Override
    protected void search() {

    }

    @Override
    protected void update()
    {

        System.out.println("mise à jours des nb heures automatique  : ");
        ldatas=presenter.getAll();
        for(Infos o : ldatas)
        {
            presenter.update(o);
        }
        ldatas=presenter.getAll();
        affListe(ldatas);

    }

    @Override
    protected void add()
    {

        Formateur f = null;
        SessionCours ssc = null;
        int nl;
        System.out.println("Nouvelle Infos :");

        System.out.println("introduire le formateur de la session:");
        System.out.println("numéro de ligne : ");
        List<Formateur> lf= fmDB.getAll();
        affList(lf);
        try{
            nl =  sc.nextInt()-1;
            sc.skip("\n");
            if (nl >= 0)
            {
                f = lf.get(nl);
            }
        }catch (Exception e)
        {
            System.out.println("Veuillez insérez uniquement des chiffres");
            sc= new Scanner(System.in);
            return;
        }

        System.out.println("introduire la session du cours:");
        System.out.println("numéro de ligne : ");
        List<SessionCours> lssc= sscDB.getAll();
        affList(lssc);
        try {
            nl =  sc.nextInt()-1;
            sc.skip("\n");
            if (nl >= 0)
            {
                ssc = lssc.get(nl);
            }
        }catch (Exception e)
        {
            System.out.println("Veuillez insérez uniquement des chiffres");
            sc= new Scanner(System.in);
            return;
        }

        Infos iTest=presenter.search(new Infos(f,ssc));
        if(iTest==null) {
            presenter.add(new Infos(f, ssc));
            ldatas = presenter.getAll();
            affListe(ldatas);
        }
        else {
            affMsg("Formateur déjà assigné à la Session du cours \n veuillez recommencer et insérez un autre formateur");
        }


    }

    @Override
    protected void special()
    {

    }
}
