package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class RandComenzi implements Serializable {
    private Integer nrComanda, idProdus;
    private Integer cantitate;

    public RandComenzi() {
    }

    public RandComenzi(Integer nrComanda, Integer idProdus, Integer cantitate) {
        this.nrComanda = nrComanda;
        this.idProdus = idProdus;
        this.cantitate = cantitate;
    }

    public Integer getNrComanda() {
        return nrComanda;
    }

    public void setNrComanda(Integer nrComanda) {
        this.nrComanda = nrComanda;
    }

    public Integer getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(Integer idProdus) {
        this.idProdus = idProdus;
    }

    public Integer getCantitate() {
        return cantitate;
    }

    public void setCantitate(Integer cantitate) {
        this.cantitate = cantitate;
    }
}
