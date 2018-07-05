package model.modelCustom;

import java.math.BigDecimal;

public class StavkaRestoranCustom {

    private Integer stavkaId;
    private String nazivStavke;
    private String nazivKategorije;
    private BigDecimal cijena;

    public StavkaRestoranCustom(Integer stavkaId, String nazivStavke, String nazivKategorije, BigDecimal cijena) {
        this.stavkaId = stavkaId;
        this.nazivStavke = nazivStavke;
        this.nazivKategorije = nazivKategorije;
        this.cijena = cijena;
    }

    public String getNazivStavke() {
        return nazivStavke;
    }

    public void setNazivStavke(String nazivStavke) {
        this.nazivStavke = nazivStavke;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public BigDecimal getCijena() {
        return cijena;
    }

    public void setCijena(BigDecimal cijena) {
        this.cijena = cijena;
    }

    public Integer getStavkaId() {
        return stavkaId;
    }

    public void setStavkaId(Integer stavkaId) {
        this.stavkaId = stavkaId;
    }
}
