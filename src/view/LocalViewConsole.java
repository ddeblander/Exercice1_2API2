package view;

import metier.Cours;
import metier.Local;
import metier.SessionCours;
import model.SessionCoursModelDB;
import utilitaires.Utilitaire;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static utilitaires.Utilitaire.*;

public class LocalViewConsole extends AbstractViewConsole<Local>
{
    private SessionCoursModelDB sscmDB = new SessionCoursModelDB();


    //question 1
    @Override
    protected void search()
    {

    }

    @Override
    protected void update() {
        System.out.println("numéro de ligne : ");
        try {
            int nl =  sc.nextInt()-1;
            sc.skip("\n");
            if (nl >= 0) {
                Local local = ldatas.get(nl);
                local.setDescription(modifyIfNotBlank("Description",local.getDescription()));
                System.out.println("introduire nbre places du local :");
                local.setPlaces(sc.nextInt());
                presenter.update(local);
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
        System.out.println("Nouveau local :");
        System.out.println("introduire Sigle du local :");
        String sigle=sc.nextLine();

        System.out.println("introduire le nb de places du local :");
        try {
            int places=sc.nextInt();
            sc.skip("\n");
            System.out.println("introduire Description du local :");
            String desc=sc.nextLine();
            try
            {
                presenter.add(new Local(places,sigle,desc));
            } catch (Exception e) {
                System.out.println("erreur : " + e);
            }
        }catch (Exception e)
        {
            System.out.println("Veuillez insérez uniquement des chiffres");
            sc= new Scanner(System.in);
            return;
        }

        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);

    }

    @Override
    protected void special()
    {
        List<String> loptions = Arrays.asList("nombre d'heures de cours par Local","Afficher tous les locaux libres à une date donnée","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: getNHTotalCoursByLocal();
                    break;
                case 2:getLocalFreeByDate();
                    break;
                case 3 : return;
            }
        }while(true);
    }

    private void getNHTotalCoursByLocal()
    {
        System.out.println("numéro de ligne : ");
        try {
            int nl =  sc.nextInt()-1;
            sc.skip("\n");
            if (nl >= 0) {
                Local local = ldatas.get(nl);
                int nhTotal=0;
                List<SessionCours> lsc = sscmDB.getSessionCourByLocal(local);
                for (SessionCours ssc:lsc)
                {
                    nhTotal+=ssc.getNbreJours()*ssc.getCour().getHeures();
                }
                System.out.println("local selectionné: "+local.toString() );
                System.out.println("nombre d'heure de cours correspondant au local : " + nhTotal);
                System.out.println("\n");


                affListe(ldatas);
            }
        }catch (Exception e)
        {
            System.out.println("Veuillez insérez uniquement des chiffres");
            sc= new Scanner(System.in);
        }
    }
    //question 23
    private void getLocalFreeByDate()
    {
        List<Local> lll = presenter.getAll();
        List<SessionCours> lsc = sscmDB.getAll();
        System.out.println("introduire Date du début de cours format (01 01 2000) :");
        try{
            Date dtd=Date.from(lecDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Calendar c = Calendar.getInstance();
            int index;

            Date dtdAfter;



            for (SessionCours ssc:lsc)
            {
                c.setTime(ssc.getDateDebut());
                c.add(Calendar.DATE,ssc.getNbreJours());
                dtdAfter=c.getTime();
              /*  System.out.println(dtd);
                System.out.println(dtdAfter);
                System.out.println(dtdAfter.after(dtd));
                System.out.println(ssc.getDateDebut().before(dtd));*/


                if((ssc.getDateDebut().before(dtd))&&(dtdAfter.after(dtd)))
                {
                    index=-1;
                    for(Local l: lll)
                    {
                        if(ssc.getLocal().getId()==l.getId())
                            index= lll.indexOf(l);

                    }
                    if(index!=-1)
                        lll.remove(index);
                }

            }
            System.out.println("Local disponible(s) : ");
            System.out.println(lll.size());
            affList(lll);

        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
