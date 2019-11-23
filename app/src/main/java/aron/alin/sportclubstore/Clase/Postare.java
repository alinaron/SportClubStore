package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class Postare implements Serializable {
    private Integer idPostare;
    private String continut;
    private String poza;
    private Integer idClient;

    public Postare(Integer idPostare, String continut, String poza, Integer idClient) {
        this.idPostare = idPostare;
        this.continut = continut;
        this.poza = poza;
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Postare{" +
                "idPostare=" + idPostare +
                ", continut='" + continut + '\'' +
                ", poza='" + poza + '\'' +
                ", idClient=" + idClient +
                '}';
    }

    public Integer getIdPostare() {
        return idPostare;
    }

    public void setIdPostare(Integer idPostare) {
        this.idPostare = idPostare;
    }

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
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

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

}
