package view;

import metier.Cours;
import presenter.CoursPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoursViewConsole implements ViewInterface
{

    private Scanner sc;
    private CoursPresenter presenter;
    private List<Cours>lc;

    public CoursViewConsole()
    {
        lc= new ArrayList<>();
        sc=new Scanner(System.in);
    }

    @Override
    public void setPresenter(Object presenter)
    {
        this.presenter=(CoursPresenter) presenter;
    }

    @Override
    public void setListDatas(List listD)
    {
        this.lc=listD;
        int i=1;
        for(Cours c : lc){
            System.out.println((i++)+"."+c);
        }
        menu();
    }

    @Override
    public void affMsg(String msg)
    {
        System.out.println("information:"+msg);
    }
    public void menu()
    {
        do{
            System.out.println("1.ajout 2.retrait 3.update 4.fin");

            int ch = sc.nextInt();
            sc.skip("\n");
            switch(ch){
                case 1: add();
                    break;
                case 2 : remove();
                    break;
                case 3 : update();
                case 4 : System.exit(0);
            }
        }while(true);
    }
    public void add()
    {
        System.out.println("Nouveau Cours :");
        System.out.println("introduire Code du cours :");
        String codeCour=sc.nextLine();
        System.out.println("introduire Description du cours :");
        String desc=sc.nextLine();
        System.out.println("introduire heures cours :");
        int heures=sc.nextInt();
        presenter.addCours(new Cours(heures,codeCour,desc));
    }
    public void update()
    {
        System.out.println("numéro de ligne : ");
        int nl =  sc.nextInt()-1;
        sc.skip("\n");
        if (nl >= 0) {
            Cours cours = lc.get(nl);
            System.out.println("introduire la nouvelle Description du cours :");
            cours.setDescription(sc.nextLine());
            System.out.println("introduire nouvelle heures cours :");
            cours.setHeures(sc.nextInt());
            presenter.updateCours(cours);
        }
    }
    public void remove()
    {
        System.out.println("numéro de ligne : ");
        int nl =  sc.nextInt()-1;
        sc.skip("\n");
        if (nl >= 0) {
            Cours cours = lc.get(nl);
            presenter.removeCours(cours);
        }
    }
}
