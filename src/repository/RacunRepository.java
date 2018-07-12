package repository;

import gui.LoginSceneController;
import model.Racun;
import services.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class RacunRepository {

    private static final String KREIRAJ_RACUN_I_VRATI_ID_KREIRANOG="insert into racun values(?,?,?,?,?,?,?)";
    private static final String VRATI_SVE_RACUNE_IZ_RESTORANA="select racunId, datumIzdavanja, ukupnaCijena, placanjeKarticom, zaposleniId, taksaId, ponisten from racun join zaposleni_restoran using(zaposleniId) where restoranId=?";
    private static final String PONISTI_RACUN_NA_OSNOVU_ID="update racun set ponisten=1 where racunId=?";

    public Integer kreirajRacun(Date datumIzdavanja, BigDecimal ukupnaCijena, Boolean placanjeKarticom, Integer taksaId ){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareCall(KREIRAJ_RACUN_I_VRATI_ID_KREIRANOG);
            ps.setInt(1, 0);
            ps.setDate(2, datumIzdavanja);
            ps.setBigDecimal(3, ukupnaCijena);
            ps.setBoolean(4, placanjeKarticom);
            ps.setInt(5, LoginSceneController.getTrenutniNalog().getZaposleniId());
            ps.setInt(6, taksaId);
            ps.setBoolean(7, false);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            Integer id = null;
            if(rs.next())
                id = rs.getInt(1);
            pool.checkIn(conn);
            return id;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Racun> vratiSveRacuneKojeSuIzdaliZaposleniIzOvogRestorana(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        ArrayList<Racun> racuniIzRestorana = new ArrayList<>();
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(VRATI_SVE_RACUNE_IZ_RESTORANA);
            ps.setInt(1, LoginSceneController.getRestoranId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String placanjeKarticom = rs.getBoolean(4) ? "da" : "ne";
                String ponisten = rs.getBoolean(7) ? "da" : "ne";
                racuniIzRestorana.add(new Racun(rs.getInt(1), rs.getDate(2), rs.getBigDecimal(3), placanjeKarticom, rs.getInt(5), rs.getInt(6), ponisten));
            }
            pool.checkIn(conn);
            return racuniIzRestorana;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void ponistiRacun(Integer id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(PONISTI_RACUN_NA_OSNOVU_ID);
            ps.setInt(1,id);
            ps.executeUpdate();
            pool.checkIn(conn);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
