package com.example.tradepulse.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hallgatok")
public class Hallgato {

    @Id
    @Column(name = "EHA", length = 20)
    private String eha;

    @Column(name = "Vezeteknev", nullable = false)
    private String vezeteknev;

    @Column(name = "Keresztnev", nullable = false)
    private String keresztnev;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "SzuletesiDatum", nullable = false)
    private LocalDate szuletesiDatum;

    @Column(name = "SzakId", nullable = false)
    private Integer szakId;

    @Column(name = "OsszKreditek", nullable = false)
    private Integer osszKreditek;

    public Hallgato() {}

    public Hallgato(String eha, String vezeteknev, String keresztnev, String email, LocalDate szuletesiDatum, Integer szakId, Integer osszKreditek) {
        this.eha = eha;
        this.vezeteknev = vezeteknev;
        this.keresztnev = keresztnev;
        this.email = email;
        this.szuletesiDatum = szuletesiDatum;
        this.szakId = szakId;
        this.osszKreditek = osszKreditek;
    }

    public String getEha() {
        return eha;
    }

    public void setEha(String eha) {
        this.eha = eha;
    }

    public String getVezeteknev() {
        return vezeteknev;
    }

    public void setVezeteknev(String vezeteknev) {
        this.vezeteknev = vezeteknev;
    }

    public String getKeresztnev() {
        return keresztnev;
    }

    public void setKeresztnev(String keresztnev) {
        this.keresztnev = keresztnev;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getSzuletesiDatum() {
        return szuletesiDatum;
    }

    public void setSzuletesiDatum(LocalDate szuletesiDatum) {
        this.szuletesiDatum = szuletesiDatum;
    }

    public Integer getSzakId() {
        return szakId;
    }

    public void setSzakId(Integer szakId) {
        this.szakId = szakId;
    }

    public Integer getOsszKreditek() {
        return osszKreditek;
    }

    public void setOsszKreditek(Integer osszKreditek) {
        this.osszKreditek = osszKreditek;
    }
}
