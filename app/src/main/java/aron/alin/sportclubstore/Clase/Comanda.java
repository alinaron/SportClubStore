package aron.alin.sportclubstore.Clase;

import java.io.Serializable;
import java.util.Date;

public class Comanda implements Serializable {
    private Integer nrComanda;
    private Double valoare;
    private String modalitatePlata;

    public Comanda() {
    }

    public Comanda(Integer nrComanda, Double valoare, String modalitatePlata) {
        this.nrComanda = nrComanda;
        this.valoare = valoare;
        this.modalitatePlata = modalitatePlata;
    }

    public Integer getNrComanda() {
        return nrComanda;
    }

    public void setNrComanda(Integer nrComanda) {
        this.nrComanda = nrComanda;
    }

    public Double getValoare() {
        return valoare;
    }

    public void setValoare(Double valoare) {
        this.valoare = valoare;
    }

    public String getModalitatePlata() {
        return modalitatePlata;
    }

    public void setModalitatePlata(String modalitatePlata) {
        this.modalitatePlata = modalitatePlata;
    }
}


