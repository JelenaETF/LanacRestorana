package repository.repositoryCustom;

import gui.LoginSceneController;
import model.modelCustom.StavkaRestoranCustom;
import services.ConnectionPool;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StavkaRestoranRepositoryCustom {

    public static final String VRATI_LISTU_STAVKI_U_PONUDI_RESTORANA="call tabelaStavki(?)";
    public static final String DODAJ_NOVU_STAVKU="call dodajStavku(?,?,?,?,?)";

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

    public int dodajNovuStavku(String nazivStavke, String nazivKategorije, Integer kategorijaId, BigDecimal cijena){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        try{
            conn = pool.checkOut();
            CallableStatement cs = conn.prepareCall(DODAJ_NOVU_STAVKU);
            cs.setString(1, nazivStavke);
            cs.setString(2,nazivKategorije );
            cs.setInt(3, kategorijaId);
            cs.setBigDecimal(4, cijena);
            cs.setInt(5, LoginSceneController.getRestoranId());
            int result = cs.executeUpdate();
            pool.checkIn(conn);
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
