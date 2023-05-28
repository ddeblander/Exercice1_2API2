package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cours
{
    private int id,heures;
    private String codeCours,description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return id == cours.id && heures == cours.heures && codeCours.equals(cours.codeCours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heures, codeCours);
    }

    private List<SessionCours> lSessions;

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

        lSessions= new ArrayList<>();
    }
    public Cours(int heures, String codeCours, String description) {
        setHeures(heures);
        setCodeCours(codeCours);
        setDescription(description);

        lSessions= new ArrayList<>();
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", heures=" + heures +
                ", codeCours='" + codeCours + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
