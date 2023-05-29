package view;

import metier.Cours;
import metier.Formateur;
import presenter.CoursPresenter;
import presenter.FormateurPresenter;
import presenter.Presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.affListe;
import static utilitaires.Utilitaire.modifyIfNotBlank;

public class FormateurViewConsole extends AbstractViewConsole<Formateur>
{
    private Scanner sc;
    private FormateurPresenter presenter;
    private List<Formateur>lc;

    public FormateurViewConsole()
    {
        lc= new ArrayList<>();
        sc=new Scanner(System.in);
    }


    @Override
    protected void search()
    {

    }

    @Override
    protected void update()
    {
        System.out.println("numéro de ligne : ");
        int nl =  sc.nextInt()-1;
        sc.skip("\n");
        if (nl >= 0) {
            Formateur fo = ldatas.get(nl);
            System.out.println("introduire le matricule :");
            fo.setMatricule(modifyIfNotBlank(sc.nextLine(),fo.getMatricule()));
            System.out.println("introduire nom :");
            fo.setNom(modifyIfNotBlank(sc.nextLine(),fo.getNom()));
            System.out.println("introduire prenom :");
            fo.setNom(modifyIfNotBlank(sc.nextLine(),fo.getPrenom()));
            presenter.update(fo);
            presenter.getAll();
            affListe(ldatas);
        }

    }

    @Override
    protected void add()
    {
        System.out.println("Nouveau Formateur :");
        System.out.println("introduire Matricule :");
        String matricule=sc.nextLine();
        System.out.println("introduire Nom :");
        String nom=sc.nextLine();
        System.out.println("introduire Prenom :");
        String prenom=sc.nextLine();

            presenter.add(new Formateur(matricule,nom,prenom));

        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);
    }

    @Override
    protected void special() {

    }
}
