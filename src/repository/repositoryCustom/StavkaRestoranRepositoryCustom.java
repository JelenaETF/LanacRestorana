package repository.repositoryCustom;

import model.modelCustom.StavkaRestoranCustom;
import services.ConnectionPool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StavkaRestoranRepositoryCustom {

    public static final String VRATI_LISTU_STAVKI_U_PONUDI_RESTORANA="call tabelaStavki(?)";

    public ArrayList<StavkaRestoranCustom> vratiStavkeUPonudiIzRestorana(Integer restoranId){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        ArrayList<StavkaRestoranCustom> stavkeUPonudi = new ArrayList<>();
        try{
            conn = pool.checkOut();
            CallableStatement cs = conn.prepareCall(VRATI_LISTU_STAVKI_U_PONUDI_RESTORANA);
            cs.setInt(1, restoranId);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
              stavkeUPonudi.add(new StavkaRestoranCustom(rs.getString(1), rs.getString(2), rs.getBigDecimal(3)));
            }
            pool.checkIn(conn);
            return stavkeUPonudi;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
