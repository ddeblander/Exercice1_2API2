package view;

import metier.Cours;
import metier.Formateur;
import presenter.CoursPresenter;
import presenter.Presenter;
import presenter.SpecialCoursPresenter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.affListe;
import static utilitaires.Utilitaire.modifyIfNotBlank;

public class CoursViewConsole extends AbstractViewConsole<Cours>
{





    @Override
    public void add()
    {
        System.out.println("Nouveau Cours :");
        System.out.println("introduire Code du cours :");
        String codeCour=sc.nextLine();
        System.out.println("introduire Description du cours :");
        String desc=sc.nextLine();
        System.out.println("introduire heures cours :");
        int heures=sc.nextInt();
        try
        {
            presenter.add(new Cours(heures,codeCour,desc));
        } catch (Exception e) {
            System.out.println("erreur : " + e);
        }
        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);

    }

    @Override
    protected void search()
    {


    }
    @Override
    public void update()
    {
        System.out.println("numéro de ligne : ");
        int nl =  sc.nextInt()-1;
        sc.skip("\n");
        if (nl >= 0) {
            Cours cours = ldatas.get(nl);
            //System.out.println("introduire la nouvelle Description du cours :");
            cours.setDescription(modifyIfNotBlank("Description",cours.getDescription()));
            System.out.println("introduire nouvelle heures cours :");
            cours.setHeures(sc.nextInt());
            presenter.update(cours);
            presenter.getAll();
            affListe(ldatas);
        }
    }
    @Override
    public void special()
    {
        sc = new Scanner(System.in);
        System.out.println("numéro de ligne : ");
        int nl =  sc.nextInt()-1;
        sc.skip("\n");
        if (nl >= 0) {
            Cours cours = ldatas.get(nl);
            ((SpecialCoursPresenter)presenter).getFormateursByCours(cours);
        }
    }

}
