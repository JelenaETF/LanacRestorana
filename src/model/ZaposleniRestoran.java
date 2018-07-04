package model;

public class ZaposleniRestoran {

    private String uloga;

    public ZaposleniRestoran(){}

    public ZaposleniRestoran(String uloga) {
        this.uloga = uloga;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
}
