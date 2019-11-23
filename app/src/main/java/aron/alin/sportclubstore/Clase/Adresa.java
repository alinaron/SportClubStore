package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class Adresa implements Serializable {
    private Integer idAdresa;
    private String judet, oras, strada;

    public Adresa() {
    }

    public Adresa(Integer idAdresa, String judet, String oras, String strada) {
        this.idAdresa = idAdresa;
        this.judet = judet;
        this.oras = oras;
        this.strada = strada;
    }

    public Integer getIdAdresa() {
        return idAdresa;
    }

    public void setIdAdresa(Integer idAdresa) {
        this.idAdresa = idAdresa;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }
}
