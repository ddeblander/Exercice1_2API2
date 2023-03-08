package metier;

public class Infos
{
    private int nh;
    Formateur formateur;


    /**
     *
     * @param formateur
     */
    public Infos(Formateur formateur, int nh) {
        setNh(nh);
        setId_formateur(formateur);

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
     * @param nhi
     * @return
     */
    private Boolean setNh(int nhi) {

        nh=nhi;
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
     * @return
     */
    @Override
    public String toString() {
        return "Infos{" +
                "nh=" + nh +
                ", formateur=" + formateur +
                '}';
    }
}
