package repository;

import services.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class KategorijaStavkeRepository {

    private static final String VRATI_SVE_KATEGORIJE_STAVKI="select naziv from kategorija_stavke";
    private static final String VRATI_ID_KATEGORIJE_NA_OSNOVU_NAZIVA="select kategorijaId from kategorija_stavke where naziv=?";

    public ArrayList<String> vratiSveKategorije(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn;
        ArrayList<String> kategorije = new ArrayList<>();
        try{
            conn = pool.checkOut();
            Statement statement = conn.createStatement();
            statement.execute(VRATI_SVE_KATEGORIJE_STAVKI);
            ResultSet rs = statement.getResultSet();
            while(rs.next()){
                kategorije.add(rs.getString(1));
            }
            pool.checkIn(conn);
            return kategorije;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //moguce jer je naziv unique
    public Integer vratiIdKategorijeNaOsnovuNaziva(String naziv){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn;
        try{
            conn=pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(VRATI_ID_KATEGORIJE_NA_OSNOVU_NAZIVA);
            ps.setString(1, naziv);
            ResultSet rs = ps.executeQuery();
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

}
