package aron.alin.sportclubstore.Clase;

import java.io.Serializable;
import java.util.Date;

public class Competitie implements Serializable {
    private Integer idCompetitie, idSport;
    private String denumireCompetitie, descriereCompetitie;
    private String dataCompetitie;
    private Integer idAdresa;
    private String poza;

    public Competitie() {
    }

    public Competitie(Integer idCompetitie, Integer idSport, String denumireCompetitie, String descriereCompetitie, String dataCompetitie,
                      Integer idAdresa, String poza) {
        this.idCompetitie = idCompetitie;
        this.idSport = idSport;
        this.denumireCompetitie = denumireCompetitie;
        this.descriereCompetitie = descriereCompetitie;
        this.dataCompetitie = dataCompetitie;
        this.idAdresa = idAdresa;
        this.poza = poza;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }

    public Integer getIdCompetitie() {
        return idCompetitie;
    }

    public void setIdCompetitie(Integer idCompetitie) {
        this.idCompetitie = idCompetitie;
    }

    public Integer getIdSport() {
        return idSport;
    }

    public void setIdSport(Integer idSport) {
        this.idSport = idSport;
    }

    public String getDescriereCompetitie() {
        return descriereCompetitie;
    }

    public void setDescriereCompetitie(String descriereCompetitie) {
        this.descriereCompetitie = descriereCompetitie;
    }

    public String getDenumireCompetitie() {
        return denumireCompetitie;
    }

    public void setDenumireCompetitie(String denumireCompetitie) {
        this.denumireCompetitie = denumireCompetitie;
    }

    public String getDataCompetitie() {
        return dataCompetitie;
    }

    public void setDataCompetitie(String dataCompetitie) {
        this.dataCompetitie = dataCompetitie;
    }

    public Integer getIdAdresa() {
        return idAdresa;
    }

    public void setIdAdresa(Integer idAdresa) {
        this.idAdresa = idAdresa;
    }
}
