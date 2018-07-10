package repository;

import gui.MessageBox;
import services.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ZaposleniOdsustvoRepository {

    private static final String KREIRAJ_ODSUSTVO_ZA_ZAPOSLENOG="insert into zaposleni_odsustvo values(?,?,?,?)";

    public void kreirajOdsustvoZaZaposlenog(Integer zaposleniId, Date datumOd, Date datumDo){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(KREIRAJ_ODSUSTVO_ZA_ZAPOSLENOG);
            ps.setDate(1, datumOd);
            ps.setDate(2, datumDo);
            ps.setInt(3, zaposleniId);
            ps.setInt(4, 0);
            ps.executeUpdate();
            pool.checkIn(conn);
        }catch (SQLException e){
            MessageBox.display(e.getMessage());
        }

    }
}
