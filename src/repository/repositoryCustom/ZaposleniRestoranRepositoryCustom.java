package repository.repositoryCustom;

import gui.LoginSceneController;
import model.modelCustom.ZaposleniRestoranCustom;
import services.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ZaposleniRestoranRepositoryCustom {

    private static final String VRATI_SVE_TRENUTNO_ZAPOSLENE_I_NJIHOVU_ULOGU_U_RESTORANU = "select z.zaposleniId, JMB, ime, prezime, adresa, stepenStrucneSpreme, uloga from zaposleni_restoran join zaposleni z using(zaposleniId) where restoranId=? and datumDo is null";

    public ArrayList<ZaposleniRestoranCustom> vratiSveZaposleneINjihovuUloguURestoranu(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        ArrayList<ZaposleniRestoranCustom> zaposleni = new ArrayList<>();
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(VRATI_SVE_TRENUTNO_ZAPOSLENE_I_NJIHOVU_ULOGU_U_RESTORANU);
            ps.setInt(1, LoginSceneController.getRestoranId());
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                zaposleni.add(new ZaposleniRestoranCustom(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            pool.checkIn(conn);
            return zaposleni;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
