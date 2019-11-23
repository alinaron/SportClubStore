package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class Favorit implements Serializable {
    private Integer idClient;
    private String codFavorit;
    private Integer idFavorit;

    public Favorit(Integer idClient, String codFavorit, Integer idFavorit) {
        this.idClient = idClient;
        this.codFavorit = codFavorit;
        this.idFavorit = idFavorit;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getCodFavorit() {
        return codFavorit;
    }

    public void setCodFavorit(String codFavorit) {
        this.codFavorit = codFavorit;
    }

    public Integer getIdFavorit() {
        return idFavorit;
    }

    public void setIdFavorit(Integer idFavorit) {
        this.idFavorit = idFavorit;
    }

    @Override
    public String toString() {
        return "Favorit{" +
                "idClient=" + idClient +
                ", codFavorit='" + codFavorit + '\'' +
                ", idFavorit=" + idFavorit +
                '}';
    }
}
