package DesignPatterns;

import metier.Local;

import java.util.ArrayList;
import java.util.List;

public class Site extends Element
{
    private List<Element> ll = new ArrayList<>();
    private String nomSite;
    private Site site;

    public Site(int id,String nomSite, Site site) {
        super(id);
        this.nomSite = nomSite;
        this.site = site;
    }
    public Site(int id,String nomSite)
    {
        super(id);
        this.nomSite = nomSite;
        this.site = null;

    }

    public String getNomSite() {
        return nomSite;
    }

    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }


    public void addLocal(Local2 l)
    {
        ll.add(l);
    }

    @Override
    public int getLocal() {
        int tot=0;
        for (Element l2 : ll)
        {
            tot+=l2.getId();
        }
        return tot;
    }
    @Override
    public String toString() {
        StringBuilder aff= new StringBuilder(getId()+" "+getNomSite()+"\n");
        for(Element e:ll){
            aff.append(e+"\n");
        }
        return aff+" somme " +getNomSite() +" = "+getLocal()+"\n";
    }
}
