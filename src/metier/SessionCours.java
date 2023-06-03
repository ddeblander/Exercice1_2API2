package metier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static utilitaires.Utilitaire.getDateFrench;

public class SessionCours
{
    private int id,nbreJours;
    private Date dateDebut;
    private Cours cour;
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
    }
    public SessionCours(int nbreJours, Date dateDebut,Cours cour,Local local) {
        setNbreJours(nbreJours);
        setDateDebut(dateDebut);
        setCour(cour);
        setLocal(local);
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


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "SessionCours{" +
                "id=" + id +
                ", nbreJours=" + nbreJours +
                ", dateDebut=" + sdf.format(dateDebut) +
                ", cour=" + cour +
                ", listFormateur="  +
                ", Local=" + Local +
                '}';
    }
}
