package metier;
import java.util.Date;

public class SessionCours
{
    private int id,nbreJours;
    private Date dateDebut;

    /**
     *
     * @param id
     * @param nbreJours
     * @param dateDebut
     */
    public SessionCours(int id, int nbreJours, Date dateDebut) {
        setId(id);
        setNbreJours(nbreJours);
        setDateDebut(dateDebut);
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
}
