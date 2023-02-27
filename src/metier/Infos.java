package metier;

public class Infos
{
    private int nh,id_formateur,id_SessionCours;

    /**
     *
     * @param nh
     * @param id_formateur
     * @param id_SessionCours
     */
    public Infos(int nh, int id_formateur, int id_SessionCours) {
        setNh(nh);
        setId_formateur(id_formateur);
        setId_SessionCours(id_SessionCours);
    }

    /**
     *
     * @return
     */
    public int getNh() {
        return nh;
    }

    /**
     * /
     * @return
     */
    public int getId_formateur() {
        return id_formateur;
    }

    /**
     *
     * @return
     */
    public int getId_SessionCours() {
        return id_SessionCours;
    }

    /**
     *
     * @param nh
     */
    public void setNh(int nh) {
        this.nh = nh;
    }

    /**
     *
     * @param id_formateur
     */
    public void setId_formateur(int id_formateur) {
        this.id_formateur = id_formateur;
    }

    /**
     *
     * @param id_SessionCours
     */
    public void setId_SessionCours(int id_SessionCours) {
        this.id_SessionCours = id_SessionCours;
    }
}
