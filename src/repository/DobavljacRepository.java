package repository;

import gui.MessageBox;
import model.Dobavljac;
import services.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;

public class DobavljacRepository {

    private static final String VRATI_SVE_DOBAVLJACE="select * from dobavljac";
    private static final String DODAJ_DOBAVLJACA_AKO_NE_POSTOJI_SA_DATIM_JIB="insert into dobavljac values(?,?,?,?)";
    private static final String IZMIJENI_DOBAVLJACA="update dobavljac set JIB=?, kontaktTelefon=?, adresa=? where dobavljacId=?";
    private static final String OBRISI_DOBAVLJACA="delete from dobavljac where dobavljacId=?";

    public ArrayList<Dobavljac> vratiSveDobavljace(){
        ArrayList<Dobavljac> dobavljaci = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            dobavljaci = new ArrayList<>();
            conn = pool.checkOut();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(VRATI_SVE_DOBAVLJACE);
            while(rs.next()){
                dobavljaci.add(new Dobavljac(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            pool.checkIn(conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dobavljaci;
    }

    public int dodajDobavljaca(Dobavljac dobavljac){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(DODAJ_DOBAVLJACA_AKO_NE_POSTOJI_SA_DATIM_JIB, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 0);
            ps.setString(2, dobavljac.getJIB());
            ps.setString(3, dobavljac.getTelefon());
            ps.setString(4, dobavljac.getAdresa());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int rez = 0;
            if(rs.next()){
            rez = rs.getInt(1);
            }
            pool.checkIn(conn);
            return rez;
        }catch (SQLException e){
            MessageBox.display(e.getMessage());
            return 0;
        }
    }

    public boolean izmijeniDobavljaca(Integer id, String noviJIB, String noviTelefon, String novaAdresa) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try {
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(IZMIJENI_DOBAVLJACA);
            ps.setString(1, noviJIB);
            ps.setString(2, noviTelefon);
            ps.setString(3, novaAdresa);
            ps.setInt(4, id);
            int rez = ps.executeUpdate();
            pool.checkIn(conn);
            if(rez > 0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean obrisiDobavljaca(Integer id){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try {
            conn = pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(OBRISI_DOBAVLJACA);
            ps.setInt(1, id);
            int rez = ps.executeUpdate();
            pool.checkIn(conn);
            if(rez > 0)
                return true;
        } catch (Exception e) {
           return false;
        }
        return false;
    }


}
