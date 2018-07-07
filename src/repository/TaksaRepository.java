package repository;

import services.ConnectionPool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TaksaRepository {

    public static String VRATI_ID_TRENUTNE_VRIJEDNOSTI_TAKSE="select taksaId from taksa where datumDo is null";
    public static String VRATI_TRENUTNU_VRIJEDNOST_TAKSE_NA_OSNOVU_ID_TAKSE="select vrijednost from taksa where taksaId=?";

    public Integer vratiIdTrenutneVrijednostiTakse(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            conn = pool.checkOut();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(VRATI_ID_TRENUTNE_VRIJEDNOSTI_TAKSE);
            Integer rez = null;
            if(rs.next()) {
               rez = rs.getInt(1);
            }
            pool.checkIn(conn);
            return rez;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public BigDecimal vratiTrenutnuVrijednostTakse(Integer id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(VRATI_TRENUTNU_VRIJEDNOST_TAKSE_NA_OSNOVU_ID_TAKSE);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            BigDecimal rez = null;
            if(rs.next())
                rez = rs.getBigDecimal(1);
            pool.checkIn(conn);
            return rez;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
