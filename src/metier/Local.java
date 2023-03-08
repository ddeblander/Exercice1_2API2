package metier;

import java.util.ArrayList;
import java.util.List;

public class Local
{
    private int id,places;
    private String sigle, description;

    private List<SessionCours> lSessions;


    /**
     *
     * @param id
     * @param places
     * @param sigle
     * @param description
     */
    public Local(int id, int places, String sigle, String description) {
        setId(id);
        setPlaces(places);
        setSigle(sigle);
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
    public int getPlaces() {
        return places;
    }

    /**
     *
     * @return
     */
    public String getSigle() {
        return sigle;
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
     * @param places
     */
    public void setPlaces(int places) {
        this.places = places;
    }

    /**
     *
     * @param sigle
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", places=" + places +
                ", sigle='" + sigle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
