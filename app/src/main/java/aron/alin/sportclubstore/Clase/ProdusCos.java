package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class ProdusCos implements Serializable {
    private Integer idProdus;
    private String denumire;
    private String poza;
    private Double pret;
    private Integer idClient;
    private Double discount;

    public ProdusCos(Integer idProdus, String denumire, String poza, Double pret, Integer idClient, Double discount) {
        this.idProdus = idProdus;
        this.denumire = denumire;
        this.poza = poza;
        this.pret = pret;
        this.idClient = idClient;
        this.discount = discount;
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

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
