package repository;

import gui.LoginSceneController;
import services.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ZaposleniRestoranRepository {

    private static final String VRATI_ULOGU_NA_OSNOVU_ZAPOSLENI_ID = "select uloga from zaposleni_restoran where zaposleniId=? and datumDo is null and restoranId=?";
    private static final String VRATI_ID_TRENUTNOG_RESTORANA_NA_OSNOVU_ZAPOSLENI_ID = "select restoranId from zaposleni_restoran where zaposleniId=? and datumDo is null";
    private static final String VRATI_IME_I_PREZIME_ZAPOLENOG_U_RESOTORANU_NA_OSNOVU_ZAPOSLENI_ID="select concat(ime,\" \",prezime) from zaposleni join zaposleni_restoran using(zaposleniId) where restoranId = ? and zaposleniId=?";
    private static final String POSTAVI_DATUM_PREKIDA_RADA_U_POSLOVNICI = "update zaposleni_restoran set DatumDo=? where datumDo is null and zaposleniId=? and RestoranId=?";

    public String vratiUloguNaOsnovuZaposleniId(Integer zaposleniId){
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(VRATI_ULOGU_NA_OSNOVU_ZAPOSLENI_ID);
            ps.setInt(1, zaposleniId);
            ps.setInt(2, LoginSceneController.getRestoranId());
            ResultSet rs = ps.executeQuery();
            String result = null;
            if(rs.next()){
                result = rs.getString(1);
            }
            pool.checkIn(conn);
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Integer vratiIdRestoranaNaOsnovuZaposleniId(Integer id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(VRATI_ID_TRENUTNOG_RESTORANA_NA_OSNOVU_ZAPOSLENI_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Integer result = null;
            if(rs.next()){
                result = rs.getInt(1);
            }
            pool.checkIn(conn);
            return result;
        }catch (Exception e){
          e.printStackTrace();
        }
        return null;
    }

    public String vratiImeIPrezimeZapolenogUResotoranuNaOsnovuZaposleniId(Integer zaposleniId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        String result = null;
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(VRATI_IME_I_PREZIME_ZAPOLENOG_U_RESOTORANU_NA_OSNOVU_ZAPOSLENI_ID);
            ps.setInt(1, LoginSceneController.getRestoranId());
            ps.setInt(2, zaposleniId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                result = rs.getString(1);
            }
            pool.checkIn(conn);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void postaviDatumPrekidaRadaUPoslovnici(Integer zaposleniId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(POSTAVI_DATUM_PREKIDA_RADA_U_POSLOVNICI);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setInt(2, zaposleniId);
            ps.setInt(3, LoginSceneController.getRestoranId());
            ps.executeUpdate();
            pool.checkIn(conn);
        }catch (Exception e){
         e.printStackTrace();
        }
    }
}
