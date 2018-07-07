package repository.repositoryCustom;

import model.modelCustom.StavkaRacunCustom;
import services.ConnectionPool;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StavkaRacunRepositoryCustom {

    private static final String DODAJ_STAVKU_NA_RACUN= "insert into stavka_racun values(?,?,?,?)";
    private static final String VRATI_SVE_STAVKE_SA_RACUNA_NA_OSNOVU_RACUN_ID = "select kolicinaKupljenog, cijenaKupljenog, naziv from stavka_racun  join stavka using(stavkaId) where racunId=?";

    public void dodajNovuStavkuNaRacun(Integer stavkaId, Integer racunId, Integer kolicina, BigDecimal cijenaKupljenog){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(DODAJ_STAVKU_NA_RACUN);
            ps.setInt(1,stavkaId);
            ps.setInt(2,racunId);
            ps.setInt(3, kolicina);
            ps.setBigDecimal(4, cijenaKupljenog);
            ps.executeUpdate();
            pool.checkIn(conn);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<StavkaRacunCustom> vratiStavkeSaRacuna(Integer racunId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        ArrayList<StavkaRacunCustom> stavkeRacuna = new ArrayList<>();
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(VRATI_SVE_STAVKE_SA_RACUNA_NA_OSNOVU_RACUN_ID);
            ps.setInt(1, racunId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                stavkeRacuna.add(new StavkaRacunCustom(rs.getInt(1), rs.getBigDecimal(2), rs.getString(3)));
            }
            pool.checkIn(conn);
            return stavkeRacuna;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
