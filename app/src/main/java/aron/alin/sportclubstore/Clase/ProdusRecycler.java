package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class ProdusRecycler implements Serializable {
    private Integer idProdus;
    private String denumire;
    private String poza;
    private Double pret;
    private Integer idClient;

    public ProdusRecycler(Integer idProdus, String denumire, String poza, Double pret) {
        this.idProdus = idProdus;
        this.denumire = denumire;
        this.poza = poza;
        this.pret = pret;
    }

    public ProdusRecycler(Integer idProdus, String denumire, String poza, Double pret, Integer idClient) {
        this.idProdus = idProdus;
        this.denumire = denumire;
        this.poza = poza;
        this.pret = pret;
        this.idClient = idClient;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public Integer getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(Integer idProdus) {
        this.idProdus = idProdus;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }
}
