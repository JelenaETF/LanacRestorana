package repository;

import services.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ZaposleniRestoranRepository {

    private static final String VRATI_ULOGU_NA_OSNOVU_ZAPOSLENI_ID = "select uloga from zaposleni_restoran where zaposleniId=?";
    private static final String VRATI_ID_TRENUTNOG_RESTORANA_NA_OSNOVU_ZAPOSLENI_ID = "select restoranId from zaposleni_restoran where zaposleniId=? and datumDo is null";

    public String vratiUloguNaOsnovuZaposleniId(Integer zaposleniId){
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(VRATI_ULOGU_NA_OSNOVU_ZAPOSLENI_ID);
            ps.setInt(1, zaposleniId);
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

}
