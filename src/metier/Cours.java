package metier;

public class Cours
{
    private int id,heures;
    private String codeCours,description;

    /**
     *
     * @param id
     * @param heures
     * @param codeCours
     * @param description
     */
    public Cours(int id, int heures, String codeCours, String description) {
        setId(id);
        setHeures(heures);
        setCodeCours(codeCours);
        setDescription(description);
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
    public int getHeures() {
        return heures;
    }

    /**
     *
     * @return
     */
    public String getCodeCours() {
        return codeCours;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
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
     * @param heures
     */
    public void setHeures(int heures) {
        this.heures = heures;
    }

    /**
     *
     * @param codeCours
     */
    public void setCodeCours(String codeCours) {
        this.codeCours = codeCours;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
