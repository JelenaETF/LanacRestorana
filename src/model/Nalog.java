package model;

public class Nalog {

    private Integer nalogId;
    private Integer zaposleniId;
    private String korisnickoIme;
    private String hashLozinke;

    public Nalog(){}

    public Nalog(Integer nalogId, String korisnickoIme,String hashLozinke, Integer zaposleniId ) {
        this.nalogId = nalogId;
        this.zaposleniId = zaposleniId;
        this.korisnickoIme = korisnickoIme;
        this.hashLozinke = hashLozinke;
    }

    public Integer getNalogId() {
        return nalogId;
    }

    public void setNalogId(Integer nalogId) {
        this.nalogId = nalogId;
    }

    public Integer getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(Integer zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getHashLozinke() {
        return hashLozinke;
    }

    public void setHashLozinke(String hashLozinke) {
        this.hashLozinke = hashLozinke;
    }
}
