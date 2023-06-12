package DesignPatterns;

import metier.Local;

public class DPMain
{
    public static void main(String[] args)
    {
        Site bCM = new Site(1,"site du champ de Mars");
        Site bF = new Site(2,"site du bâtiment F",bCM);
        Local2 local = new Local2(1,20,"test","cours x");
        Local2 local2 = new Local2(4,20,"test","cours x");
        bF.addLocal(local);
        bF.addLocal(local2);
        System.out.println(bF);



    }
}
