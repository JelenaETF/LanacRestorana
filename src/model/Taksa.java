package model;

import java.math.BigDecimal;

public class Taksa {

    Integer taksaId;
    BigDecimal vrijednost;

    public Taksa(Integer taksaId, BigDecimal vrijednost) {
        this.taksaId = taksaId;
        this.vrijednost = vrijednost;
    }

    public Integer getTaksaId() {
        return taksaId;
    }

    public void setTaksaId(Integer taksaId) {
        this.taksaId = taksaId;
    }

    public BigDecimal getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(BigDecimal vrijednost) {
        this.vrijednost = vrijednost;
    }
}
