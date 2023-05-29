package metier;

public class Formateur
{
    private int id;
    private String matricule,nom,prenom;


    /**
     *
     * @param id
     * @param matricule
     * @param nom
     * @param prenom
     */
    public Formateur(int id, String matricule, String nom, String prenom) {
        setId(id);
        setMatricule(matricule);
        setNom(nom);
        setPrenom(prenom);
    }
    public Formateur(String matricule, String nom, String prenom) {
        setMatricule(matricule);
        setNom(nom);
        setPrenom(prenom);
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
    public String getMatricule() {
        return matricule;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @return
     */
    public String getPrenom() {
        return prenom;
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
     * @param matricule
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Formateur{" +
                "id=" + id +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
