package model;

public class Zaposleni {

    private Integer zaposleniId;
    private String JMB;
    private String ime;
    private String prezime;
    private String adresa;
    private String stepenStrucneSpreme;

    public Zaposleni(){}

    public Zaposleni(Integer zaposleniId, String JMB, String ime, String prezime, String adresa, String stepenStrucneSpreme) {
        this.zaposleniId = zaposleniId;
        this.JMB = JMB;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.stepenStrucneSpreme = stepenStrucneSpreme;
    }

    public Integer getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(Integer zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public String getJMB() {
        return JMB;
    }

    public void setJMB(String JMB) {
        this.JMB = JMB;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getStepenStrucneSpreme() {
        return stepenStrucneSpreme;
    }

    public void setStepenStrucneSpreme(String stepenStrucneSpreme) {
        this.stepenStrucneSpreme = stepenStrucneSpreme;
    }
}
