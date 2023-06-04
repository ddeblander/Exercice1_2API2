package view;

import metier.Cours;
import metier.Formateur;
import metier.SessionCours;
import model.DAO;
import model.SessionCoursModelDB;
import presenter.CoursPresenter;
import presenter.Presenter;
import presenter.SpecialCoursPresenter;
import utilitaires.Utilitaire;

import java.time.ZoneId;
import java.util.*;

import static utilitaires.Utilitaire.*;

public class CoursViewConsole extends AbstractViewConsole<Cours> implements SpecialCoursView
{

    private DAO<SessionCours> sscDB = new SessionCoursModelDB();
    private List<SessionCours> lssc;




    @Override
    public void add()
    {
        int heures;
        System.out.println("Nouveau Cours :");
        System.out.println("introduire Code du cours :");
        String codeCour=sc.nextLine();
        System.out.println("introduire Description du cours :");
        String desc=sc.nextLine();
        System.out.println("introduire heures cours :");
        try {
            heures=sc.nextInt();
        }catch (Exception e)
        {
            System.out.println("Veuillez insérez uniquement des chiffres");
            sc= new Scanner(System.in);
            return;
        }

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
        int nl;
        System.out.println("numéro de ligne : ");
        try {
            nl =  sc.nextInt()-1;
            sc.skip("\n");
        }catch (Exception e)
        {
            System.out.println("Veuillez insérez uniquement des chiffres");
            sc= new Scanner(System.in);
            return;
        }


        if (nl >= 0) {
            Cours cours = ldatas.get(nl);
            //System.out.println("introduire la nouvelle Description du cours :");
            cours.setDescription(modifyIfNotBlank("Description",cours.getDescription()));
            System.out.println("introduire nouvelle heures cours :");
            try {
                cours.setHeures(sc.nextInt());
            }catch (Exception e)
            {
                System.out.println("Veuillez insérez uniquement des chiffres");
                sc= new Scanner(System.in);
                return;
            }

            presenter.update(cours);
            ldatas=presenter.getAll();
            affListe(ldatas);
        }
    }
    @Override
    public void special()
    {
        List<String> loptions = Arrays.asList("formateurs par cours","toutes les sessions avec local","toutes les sessions avec local entre deux dates","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: getFormateurByCours();
                    break;
                case 2: affList(getSessionCoursByCoursWithLocal());
                    break;
                case 3: affList(getSessionCoursByCoursBT2Dates());
                    break;
                case 4 : return;
            }
        }while(true);


    }

    @Override
    public void getFormateurByCours()
    {
        Cours c = getCoursChoice();
        if(c==null) return;
        ((SpecialCoursPresenter)presenter).getFormateursByCours(c);

    }

    @Override
    public List<SessionCours> getSessionCoursByCoursWithLocal()
    {
        List<SessionCours> lsscAdd;
        lsscAdd=new ArrayList<>();
        Cours c = getCoursChoice();
        if(c==null) return null;
        lssc=sscDB.getAll();
        for(SessionCours sc : lssc)
        {
            if(sc.getCour().equals(c));
            {
                lsscAdd.add(sc);
            }
        }
        return lsscAdd;
    }

    @Override
    public List<SessionCours> getSessionCoursByCoursBT2Dates()
    {
        List<SessionCours> lsscAdd;
        lsscAdd=new ArrayList<>();
        System.out.println("introduire Date du début de la recherche au format (01 01 2000) :");
        Date dtd1=Date.from(lecDate().atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println("introduire Date de la fin de la recherche au format (01 01 2000) :");
        Date dtd2=Date.from(lecDate().atStartOfDay(ZoneId.systemDefault()).toInstant());

        lssc=getSessionCoursByCoursWithLocal();
        for(SessionCours sc : lssc)
        {
            if((sc.getDateDebut().after(dtd1))&&(sc.getDateDebut().before(dtd2)))
            {
                lsscAdd.add(sc);
            }
        }
        return lsscAdd;

    }
    private Cours getCoursChoice() {
        affList(ldatas);
        int nl;
        sc = new Scanner(System.in);
        System.out.println("numéro de ligne : ");
        try {
            nl = sc.nextInt() - 1;
        } catch (Exception e) {
            System.out.println("Veuillez insérez uniquement des chiffres");
            sc = new Scanner(System.in);
            return null;
        }

        sc.skip("\n");
        if (nl >= 0) {
            return ldatas.get(nl);
        }
        return null;
    }
}
