package services;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Util {

    //function needs to return SHA-512 value in String object of 128 character length
    public static String getHashValue(String value){
        String hashValue = null;
        MessageDigest mg = null;
        try{
            mg = MessageDigest.getInstance("SHA-512");
            byte[] result = mg.digest(value.getBytes());
            hashValue = String.format("%0128x", new BigInteger(1, result));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return hashValue;
    }
}
