package aron.alin.sportclubstore.Clase;

import java.io.Serializable;

public class PersoanaRecycler implements Serializable {
    private Integer idClient;
    private String poza, username, nume, prenume, oras, judet, bio;

    public PersoanaRecycler(Integer idClient, String poza, String username, String nume, String prenume, String oras, String judet, String bio) {
        this.idClient = idClient;
        this.poza = poza;
        this.username = username;
        this.nume = nume;
        this.prenume = prenume;
        this.oras = oras;
        this.judet = judet;
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    @Override
    public String toString() {
        return "PersoanaRecycler{" +
                "idClient=" + idClient +
                ", poza='" + poza + '\'' +
                ", username='" + username + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", oras='" + oras + '\'' +
                ", judet='" + judet + '\'' +
                '}';
    }
}
