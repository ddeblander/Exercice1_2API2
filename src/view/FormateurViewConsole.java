package view;

import metier.Cours;
import metier.Formateur;
import presenter.CoursPresenter;
import presenter.Presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FormateurViewConsole
{
    private Scanner sc;
    private CoursPresenter presenter;
    private List<Cours>lc;

    public FormateurViewConsole()
    {
        lc= new ArrayList<>();
        sc=new Scanner(System.in);
    }




}
