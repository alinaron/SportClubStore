package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class Produs implements Serializable {
    private Integer idProdus, idCategorie;
    private String denumireProdus, poza, descriereProdus;
    private Integer stoc;
    private Double pret;
    private Integer idMarime;

    public Produs() {
    }

    public Produs(Integer idProdus, Integer idCategorie, String denumireProdus, String poza, String descriereProdus, Integer stoc, Double pret, Integer idMarime) {
        this.idProdus = idProdus;
        this.idCategorie = idCategorie;
        this.denumireProdus = denumireProdus;
        this.poza = poza;
        this.descriereProdus = descriereProdus;
        this.stoc = stoc;
        this.pret = pret;
        this.idMarime = idMarime;
    }

    public Produs(Integer idProdus, Integer idCategorie, String denumireProdus, String descriereProdus, Integer stoc, Double pret,
                  Integer idMarime) {
        this.idProdus = idProdus;
        this.idCategorie = idCategorie;
        this.denumireProdus = denumireProdus;
        this.descriereProdus = descriereProdus;
        this.stoc = stoc;
        this.pret = pret;
        this.idMarime = idMarime;
    }

    public Integer getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(Integer idProdus) {
        this.idProdus = idProdus;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getDenumireProdus() {
        return denumireProdus;
    }

    public void setDenumireProdus(String denumireProdus) {
        this.denumireProdus = denumireProdus;
    }

    public String getDescriereProdus() {
        return descriereProdus;
    }

    public void setDescriereProdus(String descriereProdus) {
        this.descriereProdus = descriereProdus;
    }

    public Integer getStoc() {
        return stoc;
    }

    public void setStoc(Integer stoc) {
        this.stoc = stoc;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public Integer getIdMarime() {
        return idMarime;
    }

    public void setIdMarime(Integer idMarime) {
        this.idMarime = idMarime;
    }

    public String getPoza() {
        return poza;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "idProdus=" + idProdus +
                ", idCategorie=" + idCategorie +
                ", denumireProdus='" + denumireProdus + '\'' +
                ", poza='" + poza + '\'' +
                ", descriereProdus='" + descriereProdus + '\'' +
                ", stoc=" + stoc +
                ", pret=" + pret +
                ", idMarime=" + idMarime +
                '}';
    }
}

