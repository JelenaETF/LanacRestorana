package model.modelCustom;

import java.math.BigDecimal;

public class NamirnicaStavkeCustom {

    private Integer stavkaId;
    private Integer namirnicaId;
    private String naziv;
    private BigDecimal kolicinaUStavki;

    public NamirnicaStavkeCustom(Integer stavkaId, Integer namirnicaId, String naziv, BigDecimal kolicinaUStavki) {
        this.stavkaId = stavkaId;
        this.namirnicaId = namirnicaId;
        this.naziv = naziv;
        this.kolicinaUStavki = kolicinaUStavki;
    }

    public Integer getStavkaId() {
        return stavkaId;
    }

    public void setStavkaId(Integer stavkaId) {
        this.stavkaId = stavkaId;
    }

    public Integer getNamirnicaId() {
        return namirnicaId;
    }

    public void setNamirnicaId(Integer namirnicaId) {
        this.namirnicaId = namirnicaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getKolicinaUStavki() {
        return kolicinaUStavki;
    }

    public void setKolicinaUStavki(BigDecimal kolicinaUStavki) {
        this.kolicinaUStavki = kolicinaUStavki;
    }
}
