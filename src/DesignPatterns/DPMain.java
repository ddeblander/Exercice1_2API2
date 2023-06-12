package DesignPatterns;

import metier.Local;

public class DPMain
{
    public static void main(String[] args)
    {

        Site bF = new Site(2,"site du bâtiment F");
        Site bCM = new Site(1,"site du champ de Mars");

        Local2 local = new Local2(1,20,"Batiment F","cours F");
        Local2 local2 = new Local2(4,42,"local2","cours 2");

        bF.addLocal(local);
        bF.addLocal(local2);
        bCM.addLocal(bF);
        System.out.println(bCM);



    }
}
