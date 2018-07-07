package repository.repositoryCustom;

import model.modelCustom.NamirnicaStavkeCustom;
import services.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NamirnicaStavkeRepositoryCustom {

    public static final String VRATI_NAMIRNICE_STAVKE_NA_OSNOVU_ID="select stavkaId, n.namirnicaId, naziv, kolicinaUStavki from namirnica_stavke n join namirnica using (namirnicaId) where stavkaId=?";

    public ArrayList<NamirnicaStavkeCustom> vratiListuNamirnicaZaStavkuNaOsnovuIdStavke(Integer id){
        ArrayList<NamirnicaStavkeCustom> namirnice = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            conn= pool.checkOut();
            PreparedStatement ps = conn.prepareStatement(VRATI_NAMIRNICE_STAVKE_NA_OSNOVU_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                namirnice.add(new NamirnicaStavkeCustom(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBigDecimal(4)));
            }
            pool.checkIn(conn);
            return namirnice;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
