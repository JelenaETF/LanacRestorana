package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Racun {

    private Integer racunId;
    private Date datumIzdavanja;
    private BigDecimal ukupnaCijena;
    private String placanjeKarticom;
    private Integer zaposleniId;
    private Integer taksaId;
    private String ponisten;

    private String racunIzdao;
    private BigDecimal vrijednostTakse;

    public Racun(Integer racunId, Date datumIzdavanja, BigDecimal ukupnaCijena, String placanjeKarticom, Integer zaposleniId, Integer taksaId, String ponisten) {
        this.racunId = racunId;
        this.datumIzdavanja = datumIzdavanja;
        this.ukupnaCijena = ukupnaCijena;
        this.placanjeKarticom = placanjeKarticom;
        this.zaposleniId = zaposleniId;
        this.taksaId = taksaId;
        this.ponisten = ponisten;
    }


    public Integer getRacunId() {
        return racunId;
    }

    public void setRacunId(Integer racunId) {
        this.racunId = racunId;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public BigDecimal getUkupnaCijena() {
        return ukupnaCijena;
    }

    public void setUkupnaCijena(BigDecimal ukupnaCijena) {
        this.ukupnaCijena = ukupnaCijena;
    }

    public String getPlacanjeKarticom() {
        return placanjeKarticom;
    }

    public void setPlacanjeKarticom(String placanjeKarticom) {
        this.placanjeKarticom = placanjeKarticom;
    }

    public Integer getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(Integer zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public Integer getTaksaId() {
        return taksaId;
    }

    public void setTaksaId(Integer taksaId) {
        this.taksaId = taksaId;
    }

    public String getPonisten() {
        return ponisten;
    }

    public void setPonisten(String ponisten) {
        this.ponisten = ponisten;
    }

    public String getRacunIzdao() {
        return racunIzdao;
    }

    public void setRacunIzdao(String racunIzdao) {
        this.racunIzdao = racunIzdao;
    }

    public BigDecimal getVrijednostTakse() {
        return vrijednostTakse;
    }

    public void setVrijednostTakse(BigDecimal vrijednostTakse) {
        this.vrijednostTakse = vrijednostTakse;
    }
}
