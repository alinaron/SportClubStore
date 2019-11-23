package aron.alin.sportclubstore.Clase;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

public class Client implements Serializable {
    private Integer idClient;
    private String username, password, nume, prenume, telefon, dataNastere;
    private Integer idSport;
    private String sex;
    private Integer idAdresa;
    private String poza;
    private String bio;

    public Client() {
    }

    public Client(String username, String password, String nume, String prenume, String telefon, String dataNastere, Integer idSport, String sex,
                  Integer idAdresa, String poza, String bio) {
        this.username = username;
        this.password = password;
        this.nume = nume;
        this.prenume = prenume;
        this.telefon = telefon;
        this.dataNastere = dataNastere;
        this.idSport = idSport;
        this.sex = sex;
        this.idAdresa = idAdresa;
        this.poza = poza;
        this.bio = bio;
    }

    public Client(Integer idClient, String username, String password, String nume, String prenume, String telefon, String dataNastere,
                  Integer idSport, String sex, Integer idAdresa, String poza, String bio) {
        this.idClient = idClient;
        this.username = username;
        this.password = password;
        this.nume = nume;
        this.prenume = prenume;
        this.telefon = telefon;
        this.dataNastere = dataNastere;
        this.idSport = idSport;
        this.sex = sex;
        this.idAdresa = idAdresa;
        this.poza = poza;
        this.bio = bio;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(String dataNastere) {
        this.dataNastere = dataNastere;
    }

    public Integer getIdSport() {
        return idSport;
    }

    public void setIdSport(Integer idSport) {
        this.idSport = idSport;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getIdAdresa() {
        return idAdresa;
    }

    public void setIdAdresa(Integer idAdresa) {
        this.idAdresa = idAdresa;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }

    public String getBio() {
        return bio;
    }

    public Integer getIdClient() { return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
