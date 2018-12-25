package com.devk.vka.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Vka entity.
 */
public class VkaDTO implements Serializable {

    private Long id;

    private String vnr;

    private String versArt;

    private String prioritaet;

    private String bearbDat;

    private String bearbUhr;

    private String status;

    private String rd;

    private String ges;

    private String bza;

    private String tarif;

    private String tarifUnr1;

    private String tarifUnr2;

    private String mkt1;

    private String bewegSchl;

    private String wirkungDat;

    private String antAufnDat;

    private String antEingDat;

    private String anzahlStruk;

    private BigDecimal btg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVnr() {
        return vnr;
    }

    public void setVnr(String vnr) {
        this.vnr = vnr;
    }

    public String getVersArt() {
        return versArt;
    }

    public void setVersArt(String versArt) {
        this.versArt = versArt;
    }

    public String getPrioritaet() {
        return prioritaet;
    }

    public void setPrioritaet(String prioritaet) {
        this.prioritaet = prioritaet;
    }

    public String getBearbDat() {
        return bearbDat;
    }

    public void setBearbDat(String bearbDat) {
        this.bearbDat = bearbDat;
    }

    public String getBearbUhr() {
        return bearbUhr;
    }

    public void setBearbUhr(String bearbUhr) {
        this.bearbUhr = bearbUhr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public String getGes() {
        return ges;
    }

    public void setGes(String ges) {
        this.ges = ges;
    }

    public String getBza() {
        return bza;
    }

    public void setBza(String bza) {
        this.bza = bza;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getTarifUnr1() {
        return tarifUnr1;
    }

    public void setTarifUnr1(String tarifUnr1) {
        this.tarifUnr1 = tarifUnr1;
    }

    public String getTarifUnr2() {
        return tarifUnr2;
    }

    public void setTarifUnr2(String tarifUnr2) {
        this.tarifUnr2 = tarifUnr2;
    }

    public String getMkt1() {
        return mkt1;
    }

    public void setMkt1(String mkt1) {
        this.mkt1 = mkt1;
    }

    public String getBewegSchl() {
        return bewegSchl;
    }

    public void setBewegSchl(String bewegSchl) {
        this.bewegSchl = bewegSchl;
    }

    public String getWirkungDat() {
        return wirkungDat;
    }

    public void setWirkungDat(String wirkungDat) {
        this.wirkungDat = wirkungDat;
    }

    public String getAntAufnDat() {
        return antAufnDat;
    }

    public void setAntAufnDat(String antAufnDat) {
        this.antAufnDat = antAufnDat;
    }

    public String getAntEingDat() {
        return antEingDat;
    }

    public void setAntEingDat(String antEingDat) {
        this.antEingDat = antEingDat;
    }

    public String getAnzahlStruk() {
        return anzahlStruk;
    }

    public void setAnzahlStruk(String anzahlStruk) {
        this.anzahlStruk = anzahlStruk;
    }

    public BigDecimal getBtg() {
        return btg;
    }

    public void setBtg(BigDecimal btg) {
        this.btg = btg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VkaDTO vkaDTO = (VkaDTO) o;
        if (vkaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vkaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VkaDTO{" +
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
