package repository;

import model.Nalog;
import services.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NalogRepository {

    private static final String VRATI_NALOG_ZA_KORISNICKO_IME="select * from nalog where korisnickoIme=?";

    public Nalog vratiNalogZaKorisnickoIme(String korisnickoIme){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try {
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(VRATI_NALOG_ZA_KORISNICKO_IME);
            ps.setString(1, korisnickoIme);
            ResultSet result = ps.executeQuery();
            Nalog nalog = null;
            if(result.next()) {
                nalog = new Nalog(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4));
            }
            pool.checkIn(conn);
            return nalog;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
