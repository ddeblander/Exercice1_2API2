package metier;

public class Local
{
    private int id,places;
    private String sigle, description;

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
}
