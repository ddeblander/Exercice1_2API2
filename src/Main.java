import demoDB.GestionCours;
import metier.Cours;
import model.CoursModelDB;

public class Main {
    public static void main(String[] args)
    {

        CoursModelDB c = new CoursModelDB();
        /*System.out.println(c.getAll().toString());
        System.out.println(c.getByCodeCours("Math2023").toString());*/
        //c.add(new Cours(10,"FRCS2023","Cours de frcs"));

        System.out.println(c.getAll().toString());

    }
}