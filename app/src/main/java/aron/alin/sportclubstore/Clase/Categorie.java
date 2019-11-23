package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class Categorie implements Serializable {
    private Integer idCategorie;
    private String denumireCategorie, poza;
    private Integer idClient;

    public Categorie() {
    }

    public Categorie(Integer idCategorie, String denumireCategorie, String poza) {
        this.idCategorie = idCategorie;
        this.denumireCategorie = denumireCategorie;
        this.poza = poza;
    }

    public Categorie(Integer idCategorie, String denumireCategorie, String poza, Integer idClient) {
        this.idCategorie = idCategorie;
        this.denumireCategorie = denumireCategorie;
        this.poza = poza;
        this.idClient = idClient;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getDenumireCategorie() {
        return denumireCategorie;
    }

    public void setDenumireCategorie(String denumireCategorie) {
        this.denumireCategorie = denumireCategorie;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }

    public Integer getIdClient() {
        return idClient;
    }
}
