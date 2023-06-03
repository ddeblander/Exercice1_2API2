package view;

import metier.Cours;
import metier.Local;

import static utilitaires.Utilitaire.affListe;
import static utilitaires.Utilitaire.modifyIfNotBlank;

public class LocalViewConsole extends AbstractViewConsole<Local>
{

    @Override
    protected void search() {

    }

    @Override
    protected void update() {
        System.out.println("numéro de ligne : ");
        int nl =  sc.nextInt()-1;
        sc.skip("\n");
        if (nl >= 0) {
            Local local = ldatas.get(nl);
            local.setDescription(modifyIfNotBlank("Description",local.getDescription()));
            System.out.println("introduire nbre places du local :");
            local.setPlaces(sc.nextInt());
            presenter.update(local);
            presenter.getAll();
            affListe(ldatas);
        }

    }

    @Override
    protected void add()
    {
        System.out.println("Nouveau local :");
        System.out.println("introduire Sigle du local :");
        String sigle=sc.nextLine();

        System.out.println("introduire le nb de places du local :");
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
        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);

    }

    @Override
    protected void special() {

    }
}
