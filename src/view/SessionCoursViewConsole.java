package view;

import metier.Cours;
import metier.Local;
import metier.SessionCours;
import model.CoursModelDB;
import model.LocalModelDB;

import java.time.ZoneId;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class SessionCoursViewConsole extends AbstractViewConsole<SessionCours>
{
    private CoursModelDB cDB = new CoursModelDB();
    private LocalModelDB lDB = new LocalModelDB();
    @Override
    protected void search()
    {


    }

    @Override
    protected void update()
    {
        int nl;
        System.out.println("numéro de ligne : ");
        try {
            nl =  sc.nextInt()-1;
            sc.skip("\n");
            if (nl >= 0) {
                SessionCours ssc = ldatas.get(nl);
                System.out.println("introduire Date du début de cours format (01 01 2000) :");
                Date dtd=Date.from(lecDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                System.out.println("introduire le nbre du jours(avec W-E) :");
                int nbj = sc.nextInt();
                ssc.setDateDebut(dtd);
                ssc.setNbreJours(nbj);
                presenter.update(ssc);
                ldatas=presenter.getAll();
                affListe(ldatas);
            }
        }catch (Exception e)
        {
            System.out.println("Veuillez insérez uniquement des chiffres");
            sc= new Scanner(System.in);
        }

    }

    @Override
    protected void add()
    {
        try
        {
            Cours c=null;
            Local l=null;
            int nl;
            System.out.println("Nouvelle Session cours :");
            System.out.println("introduire Date du début de cours format (01 01 2000) :");
            Date dtd=Date.from(lecDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.println("introduire le nbre du jours(avec W-E) :");
            int nbj = sc.nextInt();

            System.out.println("introduire le cours de la session :");
            System.out.println("numéro de ligne : ");
            List<Cours> lc = cDB.getAll();
            affList(lc);
            nl =  sc.nextInt()-1;
            sc.skip("\n");
            if (nl >= 0)
            {
                c = lc.get(nl);
            }



            System.out.println("introduire le local de la session :");
            System.out.println("numéro de ligne : ");
            List<Local> ldB = lDB.getAll();
            affList(ldB);
            nl =  sc.nextInt()-1;
            sc.skip("\n");

            if (nl >= 0)
            {
                l = ldB.get(nl);
            }
            if(l!=null&c!=null)
            {
                presenter.add(new SessionCours(nbj,dtd,c,l));
            }
            else
            {
                System.out.println("erreur d'insertion, valeur(s) érronées");
            }
        }catch (InputMismatchException e)
        {
            System.out.println("Veuillez insérez uniquement des chiffres");
            sc= new Scanner(System.in);
            return;
        } catch (Exception e) {
            System.out.println("erreur : " + e);
        }
        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);


    }

    @Override
    protected void special() {

    }
}
