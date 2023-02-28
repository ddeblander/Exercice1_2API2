package metier;

public class Infos
{
    private int nh;
    Formateur formateur;
    SessionCours sessionCours;


    /**
     *
     * @param formateur
     * @param sessionCours
     */
    public Infos(Formateur formateur, SessionCours sessionCours) {
        setNh();
        setId_formateur(formateur);
        setId_SessionCours(sessionCours);

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
    public Formateur getFormateur() {
        return formateur;
    }

    /**
     *
     * @return
     */
    public SessionCours getSessionCours() {
        return sessionCours;
    }

    /**
     *
     * @return
     */
    private Boolean setNh() {

        nh=sessionCours.getCour().getHeures()*sessionCours.getNbreJours();
        return true;
        //Todo vérifier que le nombre total d’heures_formateur pour une session correspond bien au nombre total d’heures du cours auquel la session correspond.
    }

    /**
     *
     * @param formateur
     */
    public void setId_formateur(Formateur formateur) {
        this.formateur = formateur;
    }

    /**
     *
     * @param sessionCours
     */
    public void setId_SessionCours(SessionCours sessionCours) {
        this.sessionCours = sessionCours;
    }


}
