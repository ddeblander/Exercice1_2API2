package DesignPatterns;

import metier.SessionCours;

import java.util.ArrayList;
import java.util.List;

public class Local2 extends Element
{
    private int places;
    private String sigle, description;

    private List<SessionCours> lSessions;


    /**
     *
     * @param id
     * @param places
     * @param sigle
     * @param description
     */
    public Local2(int id, int places, String sigle, String description) {
        super(id);
        setPlaces(places);
        setSigle(sigle);
        setDescription(description);

        lSessions= new ArrayList<>();
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
                ", places=" + places +
                ", sigle='" + sigle + '\'' +
                ", description='" + description + '\'' +
                ", NB places='" + getNBPlaces() + '\'' +
                '}';
    }


    @Override
    public int getNBPlaces() {
        return getPlaces();
    }
}
