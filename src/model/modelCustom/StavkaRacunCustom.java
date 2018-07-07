package model.modelCustom;

import java.math.BigDecimal;

public class StavkaRacunCustom {

    private StavkaRestoranCustom stavkaUPonudi;
    private Integer kolicinaKupljenog;
    private BigDecimal cijenaKupljenog;
    private String naziv;

    public StavkaRacunCustom(StavkaRestoranCustom stavkaUPonudi, Integer kolicinaKupljenog){
       this.stavkaUPonudi = stavkaUPonudi;
       this.cijenaKupljenog = stavkaUPonudi.getCijena();
       this.kolicinaKupljenog = kolicinaKupljenog;
       this.naziv = stavkaUPonudi.getNazivStavke();
    }

    public StavkaRacunCustom(Integer kolicinaKupljenog, BigDecimal cijenaKupljenog, String naziv) {
        this.kolicinaKupljenog = kolicinaKupljenog;
        this.cijenaKupljenog = cijenaKupljenog;
        this.naziv = naziv;
    }

    public StavkaRestoranCustom getStavkaUPonudi() {
        return stavkaUPonudi;
    }

    public void setStavkaUPonudi(StavkaRestoranCustom stavkaUPonudi) {
        this.stavkaUPonudi = stavkaUPonudi;
    }

    public Integer getKolicinaKupljenog() {
        return kolicinaKupljenog;
    }

    public void setKolicinaKupljenog(Integer kolicinaKupljenog) {
        this.kolicinaKupljenog = kolicinaKupljenog;
    }

    public BigDecimal getCijenaKupljenog() {
        return cijenaKupljenog;
    }

    public void setCijenaKupljenog(BigDecimal cijenaKupljenog) {
        this.cijenaKupljenog = cijenaKupljenog;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
