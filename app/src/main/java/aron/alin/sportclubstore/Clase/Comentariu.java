package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class Comentariu implements Serializable {
    private Integer idComentariu, idPostare;
    private String continut;
    private Integer idClient;

    public Comentariu() {
    }

    public Comentariu(Integer idComentariu, Integer idPostare, String continut, Integer idClient) {
        this.idComentariu = idComentariu;
        this.idPostare = idPostare;
        this.continut = continut;
        this.idClient = idClient;
    }

    public Comentariu(Integer idPostare, String continut, Integer idClient) {
        this.idPostare = idPostare;
        this.continut = continut;
        this.idClient = idClient;
    }

    public Integer getIdComentariu() {
        return idComentariu;
    }

    public void setIdComentariu(Integer idComentariu) {
        this.idComentariu = idComentariu;
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

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }
}
