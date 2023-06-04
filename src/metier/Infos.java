package metier;

import java.text.Normalizer;

public class Infos
{
    private int nh;
    private Formateur formateur;
    private SessionCours ssc;




    /**
     *
     * @param formateur
     */
    public Infos( int nh,Formateur formateur,SessionCours ssc) {
        setNh(nh);
        setFormateur(formateur);
        setSessionCours(ssc);

    }
    public Infos(Formateur formateur,SessionCours ssc) {
        setFormateur(formateur);
        setSessionCours(ssc);

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

    public void setFormateur(Formateur f)
    {

        this.formateur=f;
    }
    public SessionCours getSessionCours() {
        return ssc;
    }

    public void setSessionCours(SessionCours ssc)
    {

        this.ssc=ssc;
    }
    /**
     *
     * @param nhi
     * @return
     */
    public void setNh(int nhi) {

        nh=nhi;

    }


    @Override
    public String toString() {
        return "Infos{" +
                "nh=" + nh +
                ", formateur=" + formateur +
                ", ssc=" + ssc +
                '}';
    }
}
