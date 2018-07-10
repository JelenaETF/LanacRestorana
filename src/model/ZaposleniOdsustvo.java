package model;

import java.sql.Date;

public class ZaposleniOdsustvo {

    private Integer odsustvoId;
    private Integer zaposleniId;
    private Date datumOd;
    private Date datumDo;

    public ZaposleniOdsustvo(Integer odsustvoId, Integer zaposleniId, Date datumOd, Date datumDo) {
        this.odsustvoId = odsustvoId;
        this.zaposleniId = zaposleniId;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public Integer getOdsustvoId() {
        return odsustvoId;
    }

    public void setOdsustvoId(Integer odsustvoId) {
        this.odsustvoId = odsustvoId;
    }

    public Integer getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(Integer zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }
}
