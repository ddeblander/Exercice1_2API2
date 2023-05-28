package view;


import presenter.Presenter;

import java.util.*;

import static utilitaires.Utilitaire.*;


public abstract class AbstractViewConsole<T> implements ViewInterface<T> {
    protected Presenter<T> presenter;
    protected List<T> ldatas;
    protected  Scanner sc = new Scanner(System.in);

    @Override
    public void setPresenter(Presenter<T> presenter) {
        this.presenter = presenter;

    }

    @Override
    public void setListDatas(List<T> ldatas, Comparator<T> cmp) {
        this.ldatas = ldatas;
        this.ldatas.sort(cmp);
        affListe(ldatas);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

    @Override
    public void affList(List lelts) {
        affListe(lelts);
    }

    public void menu() {
        List options = new ArrayList<>(Arrays.asList("ajouter", "retirer", "rechercher","modifier","special","fin"));
        do {
            int ch = choixListe(options);

            switch (ch) {
                case 1:
                    add();
                    break;
                case 2:
                    remove();
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    update();
                    break;
                case 5:
                    affListe(ldatas);
                    special();
                    break;
                case 6:
                    return;
            }
        } while (true);
    }
    protected void remove() {
        int choix = choixElt(ldatas);
        T elt= ldatas.get(choix-1);
        presenter.remove(elt);
        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);
    }
    @Override
    public T selectionner(List<T> l) {
        int nl = choixListe(l);
        T elt = l.get(nl - 1);
        return elt;
    }
    protected abstract void search();

    protected  abstract void update();

    protected abstract  void add();
    protected abstract void special();
}


