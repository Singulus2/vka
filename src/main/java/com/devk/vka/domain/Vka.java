package com.devk.vka.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Vka.
 */
@Entity
@Table(name = "vka")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vka implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vnr")
    private String vnr;

    @Column(name = "vers_art")
    private String versArt;

    @Column(name = "prioritaet")
    private String prioritaet;

    @Column(name = "bearb_dat")
    private String bearbDat;

    @Column(name = "bearb_uhr")
    private String bearbUhr;

    @Column(name = "status")
    private String status;

    @Column(name = "rd")
    private String rd;

    @Column(name = "ges")
    private String ges;

    @Column(name = "bza")
    private String bza;

    @Column(name = "tarif")
    private String tarif;

    @Column(name = "tarif_unr_1")
    private String tarifUnr1;

    @Column(name = "tarif_unr_2")
    private String tarifUnr2;

    @Column(name = "mkt_1")
    private String mkt1;

    @Column(name = "beweg_schl")
    private String bewegSchl;

    @Column(name = "wirkung_dat")
    private String wirkungDat;

    @Column(name = "ant_aufn_dat")
    private String antAufnDat;

    @Column(name = "ant_eing_dat")
    private String antEingDat;

    @Column(name = "anzahl_struk")
    private String anzahlStruk;

    @Column(name = "btg", precision = 10, scale = 2)
    private BigDecimal btg;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVnr() {
        return vnr;
    }

    public Vka vnr(String vnr) {
        this.vnr = vnr;
        return this;
    }

    public void setVnr(String vnr) {
        this.vnr = vnr;
    }

    public String getVersArt() {
        return versArt;
    }

    public Vka versArt(String versArt) {
        this.versArt = versArt;
        return this;
    }

    public void setVersArt(String versArt) {
        this.versArt = versArt;
    }

    public String getPrioritaet() {
        return prioritaet;
    }

    public Vka prioritaet(String prioritaet) {
        this.prioritaet = prioritaet;
        return this;
    }

    public void setPrioritaet(String prioritaet) {
        this.prioritaet = prioritaet;
    }

    public String getBearbDat() {
        return bearbDat;
    }

    public Vka bearbDat(String bearbDat) {
        this.bearbDat = bearbDat;
        return this;
    }

    public void setBearbDat(String bearbDat) {
        this.bearbDat = bearbDat;
    }

    public String getBearbUhr() {
        return bearbUhr;
    }

    public Vka bearbUhr(String bearbUhr) {
        this.bearbUhr = bearbUhr;
        return this;
    }

    public void setBearbUhr(String bearbUhr) {
        this.bearbUhr = bearbUhr;
    }

    public String getStatus() {
        return status;
    }

    public Vka status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRd() {
        return rd;
    }

    public Vka rd(String rd) {
        this.rd = rd;
        return this;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public String getGes() {
        return ges;
    }

    public Vka ges(String ges) {
        this.ges = ges;
        return this;
    }

    public void setGes(String ges) {
        this.ges = ges;
    }

    public String getBza() {
        return bza;
    }

    public Vka bza(String bza) {
        this.bza = bza;
        return this;
    }

    public void setBza(String bza) {
        this.bza = bza;
    }

    public String getTarif() {
        return tarif;
    }

    public Vka tarif(String tarif) {
        this.tarif = tarif;
        return this;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getTarifUnr1() {
        return tarifUnr1;
    }

    public Vka tarifUnr1(String tarifUnr1) {
        this.tarifUnr1 = tarifUnr1;
        return this;
    }

    public void setTarifUnr1(String tarifUnr1) {
        this.tarifUnr1 = tarifUnr1;
    }

    public String getTarifUnr2() {
        return tarifUnr2;
    }

    public Vka tarifUnr2(String tarifUnr2) {
        this.tarifUnr2 = tarifUnr2;
        return this;
    }

    public void setTarifUnr2(String tarifUnr2) {
        this.tarifUnr2 = tarifUnr2;
    }

    public String getMkt1() {
        return mkt1;
    }

    public Vka mkt1(String mkt1) {
        this.mkt1 = mkt1;
        return this;
    }

    public void setMkt1(String mkt1) {
        this.mkt1 = mkt1;
    }

    public String getBewegSchl() {
        return bewegSchl;
    }

    public Vka bewegSchl(String bewegSchl) {
        this.bewegSchl = bewegSchl;
        return this;
    }

    public void setBewegSchl(String bewegSchl) {
        this.bewegSchl = bewegSchl;
    }

    public String getWirkungDat() {
        return wirkungDat;
    }

    public Vka wirkungDat(String wirkungDat) {
        this.wirkungDat = wirkungDat;
        return this;
    }

    public void setWirkungDat(String wirkungDat) {
        this.wirkungDat = wirkungDat;
    }

    public String getAntAufnDat() {
        return antAufnDat;
    }

    public Vka antAufnDat(String antAufnDat) {
        this.antAufnDat = antAufnDat;
        return this;
    }

    public void setAntAufnDat(String antAufnDat) {
        this.antAufnDat = antAufnDat;
    }

    public String getAntEingDat() {
        return antEingDat;
    }

    public Vka antEingDat(String antEingDat) {
        this.antEingDat = antEingDat;
        return this;
    }

    public void setAntEingDat(String antEingDat) {
        this.antEingDat = antEingDat;
    }

    public String getAnzahlStruk() {
        return anzahlStruk;
    }

    public Vka anzahlStruk(String anzahlStruk) {
        this.anzahlStruk = anzahlStruk;
        return this;
    }

    public void setAnzahlStruk(String anzahlStruk) {
        this.anzahlStruk = anzahlStruk;
    }

    public BigDecimal getBtg() {
        return btg;
    }

    public Vka btg(BigDecimal btg) {
        this.btg = btg;
        return this;
    }

    public void setBtg(BigDecimal btg) {
        this.btg = btg;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vka vka = (Vka) o;
        if (vka.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vka.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vka{" +
            "id=" + getId() +
            ", vnr='" + getVnr() + "'" +
            ", versArt='" + getVersArt() + "'" +
            ", prioritaet='" + getPrioritaet() + "'" +
            ", bearbDat='" + getBearbDat() + "'" +
            ", bearbUhr='" + getBearbUhr() + "'" +
            ", status='" + getStatus() + "'" +
            ", rd='" + getRd() + "'" +
            ", ges='" + getGes() + "'" +
            ", bza='" + getBza() + "'" +
            ", tarif='" + getTarif() + "'" +
            ", tarifUnr1='" + getTarifUnr1() + "'" +
            ", tarifUnr2='" + getTarifUnr2() + "'" +
            ", mkt1='" + getMkt1() + "'" +
            ", bewegSchl='" + getBewegSchl() + "'" +
            ", wirkungDat='" + getWirkungDat() + "'" +
            ", antAufnDat='" + getAntAufnDat() + "'" +
            ", antEingDat='" + getAntEingDat() + "'" +
            ", anzahlStruk='" + getAnzahlStruk() + "'" +
            ", btg=" + getBtg() +
            "}";
    }
}
