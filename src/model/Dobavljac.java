package model;

public class Dobavljac {

    private Integer dobavljacId;
    private String JIB;
    private String telefon;
    private String adresa;

    public Dobavljac(Integer dobavljacId, String JIB, String telefon, String adresa) {
        this.JIB = JIB;
        this.telefon = telefon;
        this.adresa = adresa;
        this.dobavljacId = dobavljacId;
    }

    public String getJIB() {
        return JIB;
    }

    public void setJIB(String JIB) {
        this.JIB = JIB;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Integer getDobavljacId() {
        return dobavljacId;
    }

    public void setDobavljacId(Integer dobavljacId) {
        this.dobavljacId = dobavljacId;
    }
}
