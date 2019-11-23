package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class Sport implements Serializable {
    private Integer idSport;
    private String denumireSport;

    public Sport() {
    }

    public Sport(Integer idSport, String denumireSport) {
        this.idSport = idSport;
        this.denumireSport = denumireSport;
    }

    public Integer getIdSport() {
        return idSport;
    }

    public void setIdSport(Integer idSport) {
        this.idSport = idSport;
    }

    public String getDenumireSport() {
        return denumireSport;
    }

    public void setDenumireSport(String denumireSport) {
        this.denumireSport = denumireSport;
    }
}
