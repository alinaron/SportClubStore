package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class Locatie implements Serializable {
    private Integer idLocatie, idSport;
    private String denumireLocatie;
    private Double tarif;
    private String descriereLocatie;
    private Integer idAdresa;
    private String poza;

    public Locatie(Integer idLocatie, Integer idSport, String denumireLocatie, Double tarif, String descriereLocatie, Integer idAdresa, String poza) {
        this.idLocatie = idLocatie;
        this.idSport = idSport;
        this.denumireLocatie = denumireLocatie;
        this.tarif = tarif;
        this.descriereLocatie = descriereLocatie;
        this.idAdresa = idAdresa;
        this.poza = poza;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }

    public Integer getIdLocatie() {
        return idLocatie;
    }

    public void setIdLocatie(Integer idLocatie) {
        this.idLocatie = idLocatie;
    }

    public Integer getIdSport() {
        return idSport;
    }

    public void setIdSport(Integer idSport) {
        this.idSport = idSport;
    }

    public String getDenumireLocatie() {
        return denumireLocatie;
    }

    public void setDenumireLocatie(String denumireLocatie) {
        this.denumireLocatie = denumireLocatie;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }

    public String getDescriereLocatie() {
        return descriereLocatie;
    }

    public void setDescriereLocatie(String descriereLocatie) {
        this.descriereLocatie = descriereLocatie;
    }

    public Integer getIdAdresa() {
        return idAdresa;
    }

    public void setIdAdresa(Integer idAdresa) {
        this.idAdresa = idAdresa;
    }
}
