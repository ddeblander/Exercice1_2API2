package metier;
import java.util.ArrayList;
import java.util.Date;

public class SessionCours
{
    private int id,nbreJours;
    private Date dateDebut;
    private Cours cour;
    private ArrayList<Formateur> listFormateur;
    private Local Local;
    /**
     *
     * @param id
     * @param nbreJours
     * @param dateDebut
     * @param cour
     * @param local
     */
    public SessionCours(int id, int nbreJours, Date dateDebut,Cours cour,Local local) {
        setId(id);
        setNbreJours(nbreJours);
        setDateDebut(dateDebut);
        setCour(cour);
        setLocal(local);
        listFormateur= new ArrayList<>();
    }


    /**
     *
     * @return
     */
    public Local getLocal() {
        return Local;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public int getNbreJours() {
        return nbreJours;
    }

    /**
     *
     * @return
     */
    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param nbreJours
     */
    public void setNbreJours(int nbreJours) {
        this.nbreJours = nbreJours;
    }

    /**
     *
     * @param dateDebut
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     *
     * @param local
     */
    public void setLocal(Local local) {
        Local = local;
    }

    /**
     *
     * @return
     */
    public Cours getCour()
    {
        return cour;
    }

    /**
     *
     * @param c
     */
    public void setCour(Cours c)
    {
        cour=c;
    }

    /**
     *
     * @return
     */
    public ArrayList<Formateur> getListFormateur()
    {
        return listFormateur;
    }

    /**
     *
     * @param list
     */
    public void setListFormateur(ArrayList<Formateur> list)
    {
        listFormateur=list;
    }

    /**
     *
     * @param f
     */
    public void addFormateur(Formateur f)
    {
        listFormateur.add(f);
    }

    /**
     *
     * @param f
     */
    public void deleteFormateur(Formateur f)
    {
        if(!listFormateur.isEmpty())
        {
            listFormateur.remove(f);
        }

    }

    /**
     *
     * @param f
     * @return
     */
    public boolean updateFormateur(Formateur f)
    {
        int ind=listFormateur.indexOf(f);
        if(ind>-1)
        {
            listFormateur.add(ind,f);
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public String toString() {
        return "SessionCours{" +
                "id=" + id +
                ", nbreJours=" + nbreJours +
                ", dateDebut=" + dateDebut +
                ", cour=" + cour +
                ", listFormateur=" + listFormateur +
                ", Local=" + Local +
                '}';
    }
}
